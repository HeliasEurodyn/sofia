package com.crm.sofia.services.xls_import;

import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDataLineDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.sofia.xls_import.XlsImportDTO;
import com.crm.sofia.mapper.sofia.xls_import.XlsImportMapper;
import com.crm.sofia.model.sofia.expression.ExprResponse;
import com.crm.sofia.model.sofia.xls_import.XlsImport;
import com.crm.sofia.repository.xls_import.XlsImportRepository;
import com.crm.sofia.services.component.ComponentPersistEntityFieldAssignmentService;
import com.crm.sofia.services.component.crud.ComponentSaverService;
import com.crm.sofia.services.expression.ExpressionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class XlsImportService {

    private final XlsImportRepository xlsImportRepository;
    private final XlsImportMapper xlsImportMapper;
    private final ComponentSaverService componentSaverService;
    private final ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService;
    private final ExpressionService expressionService;

    public XlsImportService(XlsImportRepository xlsImportRepository,
                            XlsImportMapper xlsImportMapper,
                            ComponentSaverService componentSaverService,
                            ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService, ExpressionService expressionService) {
        this.xlsImportRepository = xlsImportRepository;
        this.xlsImportMapper = xlsImportMapper;
        this.componentSaverService = componentSaverService;
        this.componentPersistEntityFieldAssignmentService = componentPersistEntityFieldAssignmentService;
        this.expressionService = expressionService;
    }

    public XlsImportDTO getObject(String id) {
        Optional<XlsImport> optionalchart = this.xlsImportRepository.findById(id);
        if (!optionalchart.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        XlsImportDTO dto = this.xlsImportMapper.map(optionalchart.get());

        List<ComponentPersistEntityDTO> componentPersistEntityList =
                this.componentPersistEntityFieldAssignmentService.retrieveFieldAssignments(
                        dto.getComponent().getComponentPersistEntityList(),
                        "xls_import",
                        dto.getId()
                );

        dto.getComponent().setComponentPersistEntityList(componentPersistEntityList);

        this.setComponentPersistEntityListDefaults(dto.getComponent().getComponentPersistEntityList());

        return dto;
    }

    public String importXls(MultipartFile multipartFile, String id) throws IOException {

        /* Check file extension */
        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());

        if (!extension.equals("xls")) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Wrong file type");
        }

        /* Retrieve xlsImportDTO */
        XlsImportDTO xlsImportDTO = this.getObject(id);

        /* Map Excel Data to Components And Save */
        this.mapExcelDataAndSave(multipartFile, xlsImportDTO);

        return "ok"; // this.componentSaverService.save(xlsImportDTO.getComponent());
    }

    public void mapExcelDataAndSave(MultipartFile multipartFile,
                                    XlsImportDTO xlsImportDTO) throws IOException {
        Gson gson = new Gson();
        Type componentType = new TypeToken<ComponentDTO>() {
        }.getType();

        String prevSaveOnUpdateFieldValue = null;

        Long firstLineNum = (xlsImportDTO.getFirstLine() == null ? 0 : xlsImportDTO.getFirstLine());
        Workbook wb = WorkbookFactory.create(multipartFile.getInputStream());
        Sheet sheet = wb.getSheetAt(0);

        ComponentDTO componentDTO =
                gson.fromJson(gson.toJson(xlsImportDTO.getComponent()), componentType);

        /* Retrieve saveOnUpdateField */
        Boolean saveEveryLine = false;
        if(this.retrieveSaveOnUpdateField(componentDTO.getComponentPersistEntityList()) == null){
            saveEveryLine = true;
        }

        for (int rowNum = Math.toIntExact(firstLineNum); rowNum <= sheet.getLastRowNum(); rowNum++) {

            // Generate rowMap from cells of row
            Row row = sheet.getRow(rowNum);
            Map<String, Object> rowMap = new HashMap<>();
            for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                Cell cell = row.getCell(cellNum);
                if (cell == null) continue;
                rowMap.put(
                        CellReference.convertNumToColString(cell.getColumnIndex()),
                        this.getCellValue(cell)
                );
            }

            /* Retrieve saveOnUpdateFieldValue */
            String saveOnUpdateFieldValue = this.calcSaveOnUpdateFieldValue(componentDTO.getComponentPersistEntityList(), rowMap);

            if(saveOnUpdateFieldValue == null){
                saveOnUpdateFieldValue = "";
            }

            /*  Decide if component should saved previous lines */
            if (!saveEveryLine &&
                    !saveOnUpdateFieldValue.equals(prevSaveOnUpdateFieldValue)
                    && rowNum > Math.toIntExact(firstLineNum)) {
                this.componentSaverService.save(componentDTO);
                componentDTO =
                        gson.fromJson(gson.toJson(xlsImportDTO.getComponent()), componentType);
            }

            /* Map rowMap to component */
            this.mapImportRowMapToComponentPersistEntities(componentDTO.getComponentPersistEntityList(), rowMap);

            if (saveEveryLine) {
                this.componentSaverService.save(componentDTO);
                componentDTO =
                        gson.fromJson(gson.toJson(xlsImportDTO.getComponent()), componentType);
            }

            prevSaveOnUpdateFieldValue = saveOnUpdateFieldValue;
        }

        if(!saveEveryLine){
            this.componentSaverService.save(componentDTO);
        }
    }

    private void setComponentPersistEntityListDefaults(List<ComponentPersistEntityDTO> componentPersistEntityList) {
        componentPersistEntityList
                .forEach(cpe -> {
                    cpe.setDefaultComponentPersistEntityFieldList(cpe.getComponentPersistEntityFieldList());
                    if (cpe.getComponentPersistEntityList() != null) {
                        cpe.setDefaultComponentPersistEntityList(cpe.getComponentPersistEntityList());
                        this.setComponentPersistEntityListDefaults(cpe.getComponentPersistEntityList());
                    }
                });
    }

    private String calcSaveOnUpdateFieldValue(List<ComponentPersistEntityDTO> componentPersistEntityList, Map<String, Object> rowMap) {
        ComponentPersistEntityFieldDTO cpef = this.retrieveSaveOnUpdateField(componentPersistEntityList);

        if (cpef == null) {
            return null;
        }

        ExprResponse exprResponse = expressionService.create(cpef.getAssignment().getDefaultValue(), rowMap);
        if (!exprResponse.getError()) {
            Object fieldValue = exprResponse.getExprUnit().getResult();
            return fieldValue.toString();
        }

        return null;
    }

    private void mapImportRowMapToComponentPersistEntities(List<ComponentPersistEntityDTO> componentPersistEntityList,
                                                           Map<String, Object> rowMap) {
        Gson gson = new Gson();
        Type cpeListType = new TypeToken<ArrayList<ComponentPersistEntityDTO>>() {
        }.getType();
        Type cpefListType = new TypeToken<ArrayList<ComponentPersistEntityFieldDTO>>() {
        }.getType();

        /* Decide if should Add new Lines */
        componentPersistEntityList
                .stream()
                .filter(cpe -> (cpe.getMultiDataLine() == null ? false : cpe.getMultiDataLine()))
                .forEach(cpe -> {

                    /* (NEWLINEONUPDATE) Decide if should Add new Line on value change */
                    Boolean addNewLineForValueChange[] = {false};
                    cpe.getComponentPersistEntityFieldList()
                            .stream()
                            .filter(cpef -> cpef.getAssignment().getDefaultValue() != null)
                            .filter(cpef -> !cpef.getAssignment().getDefaultValue().equals(""))
                            .filter(cpef -> cpef.getAssignment().getDefaultValue().contains("importColumn("))
                            .filter(cpef -> cpef.getAssignment().getEditor() != null)
                            .filter(cpef -> cpef.getAssignment().getEditor().equals("NEWLINEONUPDATE"))
                            .forEach(cpef -> {
                                ExprResponse exprResponse = expressionService.create(cpef.getAssignment().getDefaultValue(), rowMap);
                                if (exprResponse.getError()) {
                                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Import Error");
                                }
                                Object fieldValue = exprResponse.getExprUnit().getResult();
                                Object value = cpef.getValue() == null ? "" : cpef.getValue();
                                if (!value.equals(fieldValue)) {
                                    addNewLineForValueChange[0] = true;
                                }
                            });

                    /* (No NEWLINEONUPDATE) Decide if should Add new Line anyway */
                    Boolean addNewLineAnyway = false;
                    Long totalNewLineOnUpdateEditors = cpe.getComponentPersistEntityFieldList()
                            .stream()
                            .filter(cpef -> cpef.getAssignment().getDefaultValue() != null)
                            .filter(cpef -> !cpef.getAssignment().getDefaultValue().equals(""))
                            .filter(cpef -> cpef.getAssignment().getDefaultValue().contains("importColumn("))
                            .filter(cpef -> cpef.getAssignment().getEditor() != null)
                            .filter(cpef -> cpef.getAssignment().getEditor().equals("NEWLINEONUPDATE")).count();

                    if (totalNewLineOnUpdateEditors == 0) {
                        addNewLineAnyway = true;
                    }

                    /* Finally clone and add new lines */
                    if (addNewLineForValueChange[0] || addNewLineAnyway) {
                        List<ComponentPersistEntityDTO> clonedCpeList =
                                gson.fromJson(gson.toJson(cpe.getDefaultComponentPersistEntityList()), cpeListType);

                        List<ComponentPersistEntityFieldDTO> clonedCpefList =
                                gson.fromJson(gson.toJson(cpe.getDefaultComponentPersistEntityFieldList()), cpefListType);

                        ComponentPersistEntityDataLineDTO cpedl = new ComponentPersistEntityDataLineDTO();
                        cpedl.setComponentPersistEntityList(clonedCpeList);
                        cpedl.setComponentPersistEntityFieldList(clonedCpefList);

                        cpe.getComponentPersistEntityDataLines().add(cpedl);
                        cpe.setComponentPersistEntityList(clonedCpeList);
                        cpe.setComponentPersistEntityFieldList(clonedCpefList);
                    }
                });

        /* Insert values */
        componentPersistEntityList
                .forEach(cpe -> {
                    cpe.getComponentPersistEntityFieldList()
                            .stream()
                            .filter(cpef -> cpef.getAssignment() != null)
                            .filter(cpef -> cpef.getAssignment().getDefaultValue() != null)
                            .filter(cpef -> !cpef.getAssignment().getDefaultValue().equals(""))
                            .forEach(cpef -> {
                                ExprResponse exprResponse = expressionService.create(cpef.getAssignment().getDefaultValue(), rowMap);
                                if (!exprResponse.getError()) {
                                    Object fieldValue = exprResponse.getExprUnit().getResult();
                                    cpef.setValue(fieldValue);
                                }
                            });

                    if (cpe.getComponentPersistEntityList() != null) {
                        this.mapImportRowMapToComponentPersistEntities(cpe.getComponentPersistEntityList(), rowMap);
                    }
                });

        //componentPersistEntityList
        //        .forEach(cpe -> cpe.setDefaultComponentPersistEntityFieldList(cpe.getComponentPersistEntityFieldList()));

        // componentPersistEntityList
        //       .forEach(cpe -> cpe.setDefaultComponentPersistEntityList(cpe.getComponentPersistEntityList()));
    }

//    private String retrieveSaveOnUpdateField(List<ComponentPersistEntityDTO> componentPersistEntityList) {
//
//        for (ComponentPersistEntityDTO cpe : componentPersistEntityList) {
//            Optional<ComponentPersistEntityFieldDTO> optionalCpef =
//                    cpe.getComponentPersistEntityFieldList()
//                            .stream()
//                            .filter(cpef -> cpef.getAssignment().getDefaultValue() != null)
//                            .filter(cpef -> cpef.getAssignment().getDefaultValue().contains("importColumn("))
//                            .filter(cpef -> cpef.getAssignment().getEditor().equals("SAVEONUPDATE"))
//                            .findFirst();
//
//            if (optionalCpef.isPresent()) {
//                return optionalCpef.get().getValue()==null?"":optionalCpef.get().getValue().toString();
//            }
//        }
//
//        List<ComponentPersistEntityDTO> cpeWithChildren =
//                componentPersistEntityList
//                        .stream()
//                        .filter(cpe -> cpe.getComponentPersistEntityList() != null)
//                        .collect(Collectors.toList());
//
//        for (ComponentPersistEntityDTO cpe : cpeWithChildren) {
//            String cpefValue = this.retrieveSaveOnUpdateField(cpe.getComponentPersistEntityList());
//            if (cpefValue != null) {
//                return cpefValue;
//            }
//        }
//
//        return null;
//    }



    private ComponentPersistEntityFieldDTO retrieveSaveOnUpdateField(List<ComponentPersistEntityDTO> componentPersistEntityList) {

        for (ComponentPersistEntityDTO cpe : componentPersistEntityList) {
            Optional<ComponentPersistEntityFieldDTO> optionalCpef =
                    cpe.getComponentPersistEntityFieldList()
                            .stream()
                            .filter(cpef -> cpef.getAssignment() != null)
                            .filter(cpef -> cpef.getAssignment().getDefaultValue() != null)
                            .filter(cpef -> cpef.getAssignment().getDefaultValue().contains("importColumn("))
                            .filter(cpef -> (cpef.getAssignment().getEditor() == null?"":cpef.getAssignment().getEditor()).equals("SAVEONUPDATE"))
                            .findFirst();

            if (optionalCpef.isPresent()) {
                return optionalCpef.get();
            }
        }

        List<ComponentPersistEntityDTO> cpeWithChildren =
                componentPersistEntityList
                        .stream()
                        .filter(cpe -> cpe.getComponentPersistEntityList() != null)
                        .collect(Collectors.toList());

        for (ComponentPersistEntityDTO cpe : cpeWithChildren) {
            ComponentPersistEntityFieldDTO cpef = this.retrieveSaveOnUpdateField(cpe.getComponentPersistEntityList());
            if (cpef != null) {
                return cpef;
            }
        }

        return null;
    }

//    private List<Long> retrieveNewLineOnUpdateFieldIdList(List<ComponentPersistEntityDTO> componentPersistEntityList) {
//
//        List<Long> cpefIdList = new ArrayList<>();
//
//        for (ComponentPersistEntityDTO cpe : componentPersistEntityList) {
//            List<Long> curCpefIdList =
//                    cpe.getComponentPersistEntityFieldList()
//                            .stream()
//                            .filter(cpef -> cpef.getAssignment().getEditor().equals("NEWLINEONUPDATE"))
//                            .map(cpef -> cpef.getId())
//                            .collect(Collectors.toList());
//
//            cpefIdList.addAll(curCpefIdList);
//        }
//
//        List<ComponentPersistEntityDTO> cpeWithChildren =
//                componentPersistEntityList
//                        .stream()
//                        .filter(cpe -> cpe.getComponentPersistEntityList() != null)
//                        .collect(Collectors.toList());
//
//        for (ComponentPersistEntityDTO cpe : cpeWithChildren) {
//            List<Long> curCpefIdList = this.retrieveNewLineOnUpdateFieldIdList(cpe.getComponentPersistEntityList());
//            cpefIdList.addAll(curCpefIdList);
//        }
//
//        return cpefIdList;
//    }

    private ComponentPersistEntityDTO findComponentPersistEntity(List<ComponentPersistEntityDTO> componentPersistEntityList,
                                                                 String persistEntityCode) {

        Optional<ComponentPersistEntityDTO> componentPersistEntityOptional =
                componentPersistEntityList
                        .stream()
                        .filter(cpe -> cpe.getCode().equals(persistEntityCode)).findFirst();

        if (componentPersistEntityOptional.isPresent()) {
            return componentPersistEntityOptional.get();
        }

        return null;
    }

    public List<Map<String, Object>> excelDataToComponents(MultipartFile multipartFile, XlsImportDTO xlsImportDTO) throws IOException {
        Gson gson = new Gson();
        Type componentType = new TypeToken<ComponentDTO>() {
        }.getType();
        List<ComponentDTO> components = new ArrayList<>();

        //    List<Map<String,Object>> rowsList = new ArrayList<>();
        Long firstLineNum = (xlsImportDTO.getFirstLine() == null ? 0 : xlsImportDTO.getFirstLine());
        Workbook wb = WorkbookFactory.create(multipartFile.getInputStream());
        Sheet sheet = wb.getSheetAt(0);

        ComponentDTO componentDTO =
                gson.fromJson(gson.toJson(xlsImportDTO.getComponent()), componentType);
        for (int rowNum = Math.toIntExact(firstLineNum); rowNum < sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            Map<String, Object> rowMap = new HashMap<>();
            for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                Cell cell = row.getCell(cellNum);
                // String persistEntityCode
                rowMap.put(
                        CellReference.convertNumToColString(cell.getColumnIndex()),
                        this.getCellValue(cell)
                );
            }
            // rowsList.add(rowMap);
        }
        return null;
        //  return rowsList;
    }

    private Object getCellValue(Cell cell) {

        if (cell == null) {
            return "";
        }

        CellType cellType = cell.getCellType();
        switch (cellType) {
            case NUMERIC:
                double doubleVal = cell.getNumericCellValue();
                return doubleVal;
            case STRING:
                return cell.getStringCellValue();
            case ERROR:
                return cell.getErrorCellValue();
            case BLANK:
                return "";
            case FORMULA:
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
        }
        return "error decoding string value of the cell";
    }


}

package com.crm.sofia.controllers.list;

import com.crm.sofia.dto.list.GroupEntryDTO;
import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.dto.list.ListResultsDataDTO;
import com.crm.sofia.services.list.ListService;
import com.crm.sofia.utils.ExcelGenerator;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.http.HttpHeaders;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.core.io.InputStreamResource;

@Slf4j
@RestController
@Validated
@RequestMapping("/list")
public class ListController {

    private final ListService listService;

    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping
    List<ListDTO> getObject() {
        return this.listService.getObject();
    }

    @GetMapping(path = "/by-id")
    ListDTO getObject(@RequestParam("id") Long id) {
        return this.listService.getObject(id);
    }

    @GetMapping(path = "data/by-id")
    ListDTO getObjectData(@RequestParam("id") Long id) {
        return this.listService.getObjectData(id);
    }

//    @PostMapping(path = "/data/results")
//    ListResultsDataDTO getObjectData(@RequestBody ListDTO dto) {
//        return this.listService.getObjectData(dto);
//    }

    @GetMapping(path = "/data/results")
    ListResultsDataDTO getObjectData(@RequestParam Map<String, String> parameters, @RequestParam("id") Long id) {
        return this.listService.getObjectDataByParameters(parameters,id);
    }

//    @PostMapping(path = "/data/left-grouping/results")
//    List<GroupEntryDTO> getObjectLeftGroupingData(@RequestBody ListDTO dto) {
//        return this.listService.getObjectLeftGroupingData(dto);
//    }


    @GetMapping(path = "/data/left-grouping/results")
    List<GroupEntryDTO> getObjectLeftGroupingData(@RequestParam Map<String, String> parameters, @RequestParam("id") Long id) {
        return this.listService.getObjectLeftGroupingDataByParameters(parameters,id);
    }


    @GetMapping(path = "/by-name")
    ListDTO getObject(@RequestParam("name") String name) {
        return this.listService.getObjectByName(name);
    }

    @PostMapping
    public ListDTO postObject(@RequestBody ListDTO dto) {
        ListDTO createdDTO = this.listService.postObject(dto);
        return createdDTO;
    }

    @PutMapping
    public ListDTO putObject(@RequestBody ListDTO dto) {
        ListDTO createdDTO = this.listService.putObject(dto);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        this.listService.deleteObject(id);
    }

    @GetMapping(path = "/jasper-test-pdf")
    void doJasperTest() throws FileNotFoundException, JRException {
        this.listService.doJasperPdfTest();
    }

    @GetMapping(path = "/jasper-test-excel")
    void doJasperTestExcel() throws FileNotFoundException, JRException {
        this.listService.doJasperExcelTestExcel();
    }

    @PostMapping(path = "/data-excel")
    public ResponseEntity<InputStreamResource> getObjectExcelData(@RequestBody ListDTO dto) throws IOException, JRException {
        ListResultsDataDTO resultsDataDTO = this.listService.getObjectData(dto);
        ByteArrayInputStream in =   ExcelGenerator.listToExcel(dto, resultsDataDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=list-data.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));

    }


//    @RequestMapping("/test")
//    public String example(@RequestParam Map<String, Object> map, TimeZone timezone){
//        String apple = (String) map.get("AAA");//apple
//        String banana = (String) map.get("BBB");//banana
//        Instant date = (Instant) map.get("CCC");//banana
//
//        return apple + banana + date.toString();
//    }


//    @GetMapping(path = "/poi-test-excel")
//    public ResponseEntity<InputStreamResource> doPoiTestExcel() throws IOException, JRException {
//        ByteArrayInputStream in =  this.listService.doPoiTestExcel();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=customers.xlsx");
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .body(new InputStreamResource(in));
//
//    }



//    @PostMapping(path = "/data")
//    ListResultsDataDTO getObjectData(@RequestBody ListDTO dto) {
//        return this.listService.getObjectData(dto);
//    }

//    @RequestMapping(path = "/download_excel", method = RequestMethod.GET)
//    public ResponseEntity<Resource> download(
//            @RequestParam("filename")  String fileName,
//            HttpServletResponse response
//    ) throws IOException {

//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition", "attachment;filename=testexcel.xlsx");
//        response.setStatus(HttpServletResponse.SC_OK);

//        ByteArrayResource resource = listService.export(fileName,response);
//
//        return ResponseEntity.ok()
//                .headers(headers) // add headers if any
//                .contentLength(resource.contentLength())
//                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//                .body(resource);
//    }

//        return ResponseEntity.ok()
//              //  .headers(null) // add headers if any
//                .contentLength(resource.contentLength())
//                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//                .body(resource);

}

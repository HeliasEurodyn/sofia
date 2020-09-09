package com.crm.sofia.utils;

import com.crm.sofia.dto.list.ListComponentFieldDTO;
import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.dto.list.ListResultsDataDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelGenerator {


    public static ByteArrayInputStream listToExcel(ListDTO dto, ListResultsDataDTO resultsDataDTO) throws IOException {

        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Sofia Exports");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            int col = 0;
            for (ListComponentFieldDTO column : dto.getListComponentList().get(0).getListComponentColumnFieldList()) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(column.getDescription());
                cell.setCellStyle(headerCellStyle);
                col++;
            }

            int rowIdx = 1;
            for (Map<String, Object> dataRow : resultsDataDTO.getListContent()) {

                Row row = sheet.createRow(rowIdx++);
                col = 0;
                for (ListComponentFieldDTO column : dto.getListComponentList().get(0).getListComponentColumnFieldList()) {

                    if (column.getType().equals("date") || column.getType().equals("datetime")) {
                        Timestamp timestampValue = (Timestamp) dataRow.get(column.getCode());
                        Date dateValue = new Date(timestampValue.getTime());
                        row.createCell(col).setCellValue(dateValue);
                    } else if (column.getType().equals("int")) {
                        Integer integerValue = (Integer) (dataRow.get(column.getCode()));
                        row.createCell(col).setCellValue(integerValue);
                    } else if (column.getType().equals("bigint")) {
                        BigInteger bigIntegerValue = (BigInteger) (dataRow.get(column.getCode()));
                        row.createCell(col).setCellValue(bigIntegerValue.intValue());
                    } else if (column.getType().equals("double")) {
                        Double doubleValue = (Double) (dataRow.get(column.getCode()));
                        row.createCell(col).setCellValue(doubleValue);
                    } else
                        row.createCell(col).setCellValue((String) dataRow.get(column.getCode()));


                    col++;
                }

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }


}

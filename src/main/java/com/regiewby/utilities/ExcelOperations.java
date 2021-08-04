package com.regiewby.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.util.HashMap;

public class ExcelOperations {


    String filePath;
    Sheet sheet;

    public ExcelOperations(String sheetName) {
        try {
            filePath = System.getProperty("user.dir")+PropertiesOperation.getPropertyValueByKey("testDataLocation").trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //open file - workbook
        File testDataFile = new File(filePath);
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(testDataFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sheet = wb.getSheet(sheetName);
    }

    //get test data from test data sheet in hashmap based on row number
    @SuppressWarnings("deprecation")
    public HashMap<String, String> getTestDataInMap(int rowNum) throws Exception {
        //read data row by row and put in map
        HashMap<String, String> hm = new HashMap<String, String>();

        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            String value;
            if(sheet.getRow(rowNum).getCell(i) != null) {
                sheet.getRow(rowNum).getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                value = sheet.getRow(rowNum).getCell(i).toString();
            }
            else {
                value = "";
            }
            hm.put(sheet.getRow(0).getCell(i).toString(), value);
        }
        return hm;
    }

    //get row count
    public int getRowCount() {
        return sheet.getLastRowNum();
    }

    //ger column count
    public int getColCount() {
        return sheet.getRow(0).getLastCellNum();

    }

}

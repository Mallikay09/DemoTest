package com.Module1.Utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/*
 * Create a utility class for reading data from an Excel file using Apache POI.
 */
public class ExcelUtility {
	
	private Workbook workbook;
	//Parameterized constructor
    public ExcelUtility(String filePath) {
        try 
        {
        	FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCellData(int sheetIndex, int row, int column) {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row rowObj = sheet.getRow(row);
        Cell cell = rowObj.getCell(column);
        return cell.toString();
    }

    public int getRowCount(int sheetIndex) {
        return workbook.getSheetAt(sheetIndex).getLastRowNum();
    }

    public void closeWorkbook() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.Module1.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class CustomeData {

	XSSFWorkbook wb;
	@DataProvider(name="exceldata")
	public Object[][] excelData()
	{
		//Locate the excel file path using file class
		  File f1=new File(System.getProperty("user.dir")+"//TestData//ExcelLogin.xlsx");
		  FileInputStream fs;
		  Object arr[][]=null;
		try {
			fs = new FileInputStream(f1);
			//Workbook
			  wb=new XSSFWorkbook(fs);
			  //Sheet
//			  XSSFSheet sheet1= wb.getSheet("userData");
//			  //Row
//			  XSSFRow row1=sheet1.getRow(1);//2nd row
//			  //Cell
//			  XSSFCell cell1=row1.getCell(0);
//			  //Data
//			  String uName=cell1.getStringCellValue();
//			  System.out.println("User name: "+uName);
//			  //Output will be Admin
			  
			  //Above 4 lines we can make to single line
			  //String uName1=wb.getSheet("userData").getRow(1).getCell(0).getStringCellValue();
			  //System.out.println("User name: "+uName1);
			  /****************To Read Complete excel Data(Using Array)************/
			  
			  //Number of rows
			  //Number of columns
			  //Declare array of same size
			  //Iterate array and get data from file and stroe it in array
			  //return array to data provider
			  int rows=wb.getSheet("userData").getPhysicalNumberOfRows();
			  System.out.println("No. of Rows: "+rows);
			  
			  int cells=wb.getSheet("userData").getRow(0).getPhysicalNumberOfCells();
			  System.out.println("No. of Columns: "+cells);
			  
			  arr=new Object[rows][cells];
			  
			  for(int i=1;i<rows;i++)//to skip heading i starts with 1
			  {
				  for(int j=0;j<cells;j++)
				  {
					  arr[i][j]=wb.getSheet("userData").getRow(i).getCell(j).getStringCellValue();
					  System.out.print(arr[i][j]+"   ");
				  }
				  System.out.println();
				  
			  }    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	} 
}

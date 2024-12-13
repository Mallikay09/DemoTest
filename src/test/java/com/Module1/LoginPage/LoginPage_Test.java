package com.Module1.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Module1.Utility.ExcelUtility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class LoginPage_Test{
	WebDriver driver;
    ExcelUtility excel;
    ExtentReports extent;
    ExtentTest test;
    
	 @BeforeClass
	    public void setUp() {
	        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
	        driver = new ChromeDriver();
	        
	        //File f1=new File(System.getProperty("user.dir")+"//TestData//ExcelLogin.xlsx");
	       // excel = new ExcelUtility("\"user.dir\")"+"TestData+ExcelLogin.xlsx");
	        File f1=new File(System.getProperty("user.dir")+"//TestData//Data.xlsx");
	        excel = new ExcelUtility("D:\\StarAgile10062024\\SeleniumFolder\\CapstoneProject\\TestData\\ExcelLogin.xlsx");
	        ExtentSparkReporter sparkReporter  = new ExtentSparkReporter("extentReports/OrangeHRMReport.html");
	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter );
	    }
	//WebDriver driver=new ChromeDriver();
		
	/*
	 * @DataProvider(name = "loginData")
	 *  public Object[][] loginDataProvider()
	 *   { int
	 * rowCount = excel.getRowCount(0); Object[][] data = new Object[rowCount][2];
	 * 
	 * for (int i = 0; i < rowCount; i++) { data[i][0] = excel.getCellData(0, i + 1,
	 * 0); // Username column data[i][1] = excel.getCellData(0, i + 1, 1); //
	 * Password column } return data; 
	 * }
	 */
	 XSSFWorkbook wb;
		@DataProvider(name="loginData")
		public Object[][] excelData()
		{
			//Locate the excel file path using file class
			  File f1=new File(System.getProperty("user.dir")+"//TestData//Data.xlsx");
			  FileInputStream fs;
			  Object arr[][]=null;
			try {
				fs = new FileInputStream(f1);
				//Workbook
				  wb=new XSSFWorkbook(fs);
				  //Sheet
				  XSSFSheet sheet1= wb.getSheet("userData");
				  //Row
				  XSSFRow row1=sheet1.getRow(1);//2nd row
				  //Cell
				  XSSFCell cell1=row1.getCell(0);
				  //Data
				  String uName=cell1.getStringCellValue();
				  System.out.println("User name: "+uName);
				  //Output will be Admin
				  
				  //Above 4 lines we can make to single line
				  String uName1=wb.getSheet("userData").getRow(1).getCell(0).getStringCellValue();
				  System.out.println("User name: "+uName1);
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
	    @Test(dataProvider = "loginData")
	    public void loginTest(String username, String password) {
	        test = extent.createTest("Login Test with username: " + username);

	        driver.get("https://opensource-demo.orangehrmlive.com/");

	        // Enter username and password
	        driver.findElement(By.id("txtUsername")).sendKeys(username);
	        driver.findElement(By.id("txtPassword")).sendKeys(password);
	        driver.findElement(By.id("btnLogin")).click();

	        // Assertion and validation
	        if (username.equals("Admin") && password.equals("admin123")) {
	            Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/index.php/dashboard");
	            test.pass("Valid credentials - Login successful.");
	            logout();
	        } else {
	            WebElement errorMessage = driver.findElement(By.id("spanMessage"));
	            Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed.");
	            test.fail("Invalid credentials - Login failed as expected.");
	        }
	    }

	    public void logout() {
	        driver.findElement(By.id("welcome")).click();
	        driver.findElement(By.linkText("Logout")).click();
	    }

	    @AfterMethod
	    public void captureScreenshotOnFailure(ITestResult result) {
	        if (ITestResult.FAILURE == result.getStatus() || ITestResult.SUCCESS == result.getStatus()) {
	            takeScreenshot(result.getName());
	        }
	    }

	    public void takeScreenshot(String testName) {
	        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String filePath = "Screenshots/" + testName + "_" + timestamp + ".png";
	        try {
	            FileUtils.copyFile(screenshot, new File(filePath));
	            test.addScreenCaptureFromPath(filePath);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	        excel.closeWorkbook();
	        if (driver != null) {
	            driver.quit();
	        }
	        extent.flush();
	    }
	}
//package com.Module1.BaseTest;
//
//import java.util.Properties;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.Module1.LoginPage.LoginWith_ValidUsernameAndPassword_Test;
//import com.Module1.Utility.LoginUtility;
//import com.Module1.Utility.PropertyReaderJavaClass;
//
//public class BaseTest {
//	 public static WebDriver driver;
//	 public LoginWith_ValidUsernameAndPassword_Test lv;
//	 private PropertyReaderJavaClass prop;
//
//	
//	
//	    @BeforeClass
//	    public void setUp() { 
//	   
//	       prop = new PropertyReaderJavaClass();
//	        String browser = prop.getData("bName");
//	        //driver=new ChromeDriver();
//	        System.out.println(browser);
//
//	        if (browser.equalsIgnoreCase("chrome")) {
//	            driver = new ChromeDriver();
//	        } else if (browser.equalsIgnoreCase("firefox")) {
//	            driver = new FirefoxDriver();
//	        } else {
//	            throw new RuntimeException("Browser not supported: " + browser);
//	        }
//	        lv= new LoginWith_ValidUsernameAndPassword_Test(driver);
//	        driver.manage().window().maximize();
//	        driver.get(prop.getData("url"));
//	        
//	    }
//	    
////	    public void getUrl()
////	    {
////	    	driver.get(prop.getData("url"));
////	    }
////	    
//  
//  
//	    
//}
//

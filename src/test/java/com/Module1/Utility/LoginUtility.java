package com.Module1.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import com.Module1.BaseTest.BaseTest;
import com.Module1.LoginPage.LoginPage_Test;


public class LoginUtility{
	
//	BaseTest bt=new BaseTest();
//	WebDriver driver;
	public static WebDriver driver;
	 //public LoginWith_ValidUsernameAndPassword_Test lv;
	 private PropertyReaderJavaClass prop;

		
		 //Constructor to initialize 
//	  public LoginUtility(WebDriver driver) 
//	  {
//	  driver= new ChromeDriver(); // PageFactory.initElements(driver,this); 
//	  }
		 	
		 @BeforeClass
		    public void setUp() { 
		   
		       prop = new PropertyReaderJavaClass();
		        String browser = prop.getData("bName");
		        //driver=new ChromeDriver();
		        System.out.println(browser);

		        if (browser.equalsIgnoreCase("chrome")) {
		            driver = new ChromeDriver();
		        } else if (browser.equalsIgnoreCase("firefox")) {
		            driver = new FirefoxDriver();
		        } else {
		            throw new RuntimeException("Browser not supported: " + browser);
		        }
		        //lv= new LoginWith_ValidUsernameAndPassword_Test(driver);
		        driver.manage().window().maximize();
		        driver.get(prop.getData("url"));
		        
		    }
 
  public void validateUrl(String currentUrl) {
	 
	 String url= driver.getCurrentUrl();	
	 System.out.println(url);
	 Assert.assertEquals(url.contains(currentUrl),true, "URL Not matched");
	 System.out.println("Url Matched");
	 
  }
  
  
  
 
}


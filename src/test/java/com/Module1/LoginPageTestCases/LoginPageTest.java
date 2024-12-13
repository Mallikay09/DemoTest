package com.Module1.LoginPageTestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest {
	WebDriver driver=new ChromeDriver();
  @Test
  public void LoginWithValidUsernameAndPassword() {
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		//driver.findElement(By.name("username")).sendKeys(un);
		//driver.findElement(By.name("password")).sendKeys(pwd);
			  driver.findElement(By.xpath("//button[@type='submit']")).click();
			  //Thread.sleep(500);
			  Assert.assertEquals(driver.getCurrentUrl().contains("dashboard"),true,"Launch Failed \n");
			  //Screenshot
			  
			  //End screenshot
			  System.out.println("Launch success");
  }
}

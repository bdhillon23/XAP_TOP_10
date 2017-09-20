package com.legacyTest.test;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.legacyTeam.TestBase.TestBase;

public class TestClass extends TestBase{
	public ExtentTest logger;
	
	@Test
	public void LaunchBrowser(){
		logger=extent.createTest("LaunchBrowser");
		selectBrowser("Chrome");
		logger.info("Starting the test case");
		driver.get("http://google.com");
		
	}
	@AfterMethod
	public void quite(){
	
		if(extent!=null){
			
			
			extent.flush();
			driver.quit();
			
		}
	
		}
	@BeforeSuite
	public void init(){
		
		if(prop==null){
			TestBase tb=new TestBase();
			tb.init();
		}
	}
}
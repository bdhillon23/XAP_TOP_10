package com.legacyTest.test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.legacyTeam.TestBase.TestBase;

public class TestClass extends TestBase{
	public ExtentTest logger;
	
	@Test
	public void LaunchBrowser(){
		logger=extent.createTest("LaunchBrowser");
		selectBrowser("firefox");
		logger.info("Starting the test case");
		driver.get("http://google.com");
	
	}
	@AfterTest
	public void quite(){
		if(extent!=null){
			System.out.println("Flushing");
			
			extent.flush();
			driver.close();
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
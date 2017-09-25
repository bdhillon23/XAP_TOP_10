package com.legacyTest.test;


import junit.framework.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.gargoylesoftware.htmlunit.javascript.host.Notification;
import com.legacyTeam.TestBase.TestBase;
import com.legacyTeam.dashboard.notificationPanel;
import com.legacyTeam.loginPage.loginPage;

public class TestClass extends TestBase{
	public ExtentTest logger;
	@SuppressWarnings("deprecation")
	public static Assert asrt;
	
	@Test
	public void LaunchBrowser(){
		logger=extent.createTest("LaunchBrowser");
		
		logger.info("Starting test case");
		String URL="https://intranet.xavient.com";
	    start("Chrome", URL);
		loginPage lp=new loginPage(driver);
		lp.login("balwinder", "");
		String text=lp.validateLogin();
		if(text.contains("Success")){
		asrt.assertEquals(text, "Login Successfully");
		logger.pass("login Successfull");
		
		}
		else{
			asrt.fail();
			logger.fail("Login Failed");
		}
		notificationPanel notification=new notificationPanel(driver);
		notification.getNotification();
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
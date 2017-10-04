package com.legacyTest.test;



import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;



import org.testng.annotations.BeforeTest;

import com.legacyTeam.TestBase.TestBase;
import com.legacyTeam.dashboard.notificationPanel;
import com.legacyTeam.loginPage.loginPage;

public class TestClass extends TestBase{
	
	
	public static Assert asrt;
	
	@Test
	public void LaunchBrowser(){
		System.out.println("Hi ");
		logger=extent.createTest("LaunchBrowser");
		
		logger.info("Starting test case");
		
		loginPage lp=new loginPage(driver);
		lp.login("balwinder", "Xavient");
		String text=lp.validateLogin();
		
		if(text.contains("Success")){
		asrt.assertEquals(text, "Login Successfully");
		logger.pass("login Successfull");
		
		}
		else{
			Assert.fail();
			Assert.fail("Login Failed");
		}	
	
			
		notificationPanel notification=new notificationPanel(driver);
		notification.getNotification();
		
	
		
		boolean validateLogOut=lp.logout();
		AssertJUnit.assertEquals(validateLogOut,true);
		
		
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
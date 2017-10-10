package com.legacyTest.test;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;



import org.testng.annotations.BeforeTest;

import com.legacyTeam.MonthAttendence.getMoAttendence;
import com.legacyTeam.TestBase.TestBase;
import com.legacyTeam.dashboard.NavigateTo;
import com.legacyTeam.dashboard.notificationPanel;
import com.legacyTeam.loginPage.loginPage;

public class TestClass extends TestBase{
	
	
	public static Assert asrt;
	
	@Test
	public void LaunchBrowser(){
		
		logger=extent.createTest("LaunchBrowser");
		
		logger.info("Starting test case");
		
		loginPage lp=new loginPage(driver);
		lp.login("balwinder", "Xavient@2");
		String text=lp.validateLogin();
		
		if(text.contains("Success")){
		asrt.assertEquals(text, "Login Successfully");
		logger.pass("login Successfull");
		
		}
		else{
			Assert.fail();
			Assert.fail("Login Failed");
		}	
		
		
		
		
		fluentWait("DashboardWaiting_xpath");
		
		
		
		
		
		waitinSec(1);
		NavigateTo nt=new NavigateTo();
		nt.Navigateto("My Zone", "Attendance", "Self");
		
		
		getMoAttendence atnd=new getMoAttendence();
		atnd.selectMonth("August");
		atnd.selectYear("2017");
		fluentWait("SelfAttendanceWaiting_xpath");
		atnd.monthAttendance();
		
	
		
		
		
		
		boolean validateLogOut=lp.logout();
		if(validateLogOut==true){
		logger.pass("Logout successfull");	
		}
		asrt.assertEquals(validateLogOut, true);
		
		
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
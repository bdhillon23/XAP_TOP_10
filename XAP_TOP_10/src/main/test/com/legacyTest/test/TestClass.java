package com.legacyTest.test;
import java.util.Hashtable;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.legacyTeam.MonthAttendence.getMoAttendence;
import com.legacyTeam.TestBase.TestBase;
import com.legacyTeam.dashboard.NavigateTo;
import com.legacyTeam.loginPage.loginPage;


public class TestClass extends TestBase{
	
	
	public static Assert asrt;
	
	@Test (dataProvider="getData")
	public void LaunchBrowser(Hashtable <String,String> data){
		
		logger=extent.createTest("LaunchBrowser"+data);
		
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
		waitinSec(2);
		
		NavigateTo nt=new NavigateTo();
		nt.Navigateto("My Zone", "Attendance", "Self");
		
		
		getMoAttendence atnd=new getMoAttendence();
		
		fluentWait("SelfAttendanceWaiting_xpath");
		Map<Integer,Map<String,String>> map=atnd.monthAttendance(data.get("YearNumber"),data.get("MonthName"),data.get("MonthNumber"));
		atnd.writeInXls(map);
		
		
		boolean validateLogOut=lp.logout();
		if(validateLogOut==true){
		logger.pass("Logout successfull");	
		}
		asrt.assertEquals(validateLogOut, true);
		
		
	}
	
	
	@DataProvider
	public Object[][] getData() {
	Object[][] data = com.leagacyTeam.readExcel.DataUtil.getData(reader,
			"LaunchBrowser");
	return data;
	}
	
	
	@AfterSuite
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
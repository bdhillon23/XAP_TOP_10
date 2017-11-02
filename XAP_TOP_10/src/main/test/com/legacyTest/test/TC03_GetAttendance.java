package com.legacyTest.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.Hashtable;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.legacyTeam.MonthAttendence.getMoAttendence;
import com.legacyTeam.TestBase.TestBase;
import com.legacyTeam.dashboard.NavigateTo;
import com.legacyTeam.loginPage.loginPage;

public class TC03_GetAttendance extends TestBase{

	public  Assert asrt;
	
	@Test (dataProvider="getData")
	public void GetAttendance(Hashtable <String,String> data){
		
		logger=extent.createTest(" TC03_GetAttendance "+data);
		
		logger.info("********** Starting Test Case **********");
		start(prop.getProperty("browser"),  prop.getProperty("URL"));
		loginPage lp=new loginPage(driver);
		lp.login(prop.getProperty("username"), prop.getProperty("password"));
		String text=lp.validateLogin();
		
		if(text.contains("Success")){
		asrt.assertEquals(text, "Login Successfully");
		logger.pass("login Successfull");
		
		}
		else{
			asrt.fail();
			asrt.fail("Login Failed");
		}	
		
		
		fluentWait("DashboardWaiting_xpath");
		waitinSec(2);
		
		NavigateTo nt=new NavigateTo();
		nt.Navigateto("My Zone", "Attendance", "Self");
		
		
		getMoAttendence atnd=new getMoAttendence();
		
		fluentWait("SelfAttendanceWaiting_xpath");
		
		Map<Integer,Map<String,String>> map=atnd.monthAttendance(data.get("YearNumber"),data.get("MonthName"),data.get("MonthNumber"));
		atnd.writeInXls(map ,data.get("MonthName"));
		
		
		boolean validateLogOut=lp.logout();
		if(validateLogOut==true){
		logger.pass("Logout successfull");	
		}
		asrt.assertEquals(validateLogOut, true);
		logger.info("********** Ending Test Case **********");
		
	}
	
	
	@DataProvider
	public Object[][] getData() {
	Object[][] data = com.leagacyTeam.readExcel.DataUtil.getData(reader,
			"GetAttendance");
	return data;
	}
	
	
	@AfterMethod
	public void quite(){
	
		if(extent!=null){
			logger.info(" Closing Driver ");			
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
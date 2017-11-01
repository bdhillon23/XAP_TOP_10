package com.legacyTest.test;

import java.util.Hashtable;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.legacyTeam.MonthAttendence.getMoAttendence;
import com.legacyTeam.TestBase.TestBase;
import com.legacyTeam.dashboard.NavigateTo;
import com.legacyTeam.dashboard.ToDoListFunctions;
import com.legacyTeam.loginPage.loginPage;

public class TC09_Add_ToDoList extends TestBase{
	public static Assert asrt;
	
	@Test (dataProvider="getData")
	public void Add_ToDoList(Hashtable <String,String> data){
		
		logger=extent.createTest(" TC09_Add_ToDoList "+data);		
		logger.info("********** Starting Test Case **********");
		
		loginPage lp=new loginPage(driver);
		lp.login(prop.getProperty("username"), prop.getProperty("password"));
		String text=lp.validateLogin();
		
		if(text.contains("Success")){
		Assert.assertEquals(text, "Login Successfully");
		logger.pass("login Successfull");
		
		}
		else{
			Assert.fail();
			Assert.fail("Login Failed");
		}	
		
		
		fluentWait("DashboardWaiting_xpath");
		waitinSec(2);
		
		ToDoListFunctions td=new ToDoListFunctions(driver);
		td.scrollToDoList();
		
		/*NavigateTo nt=new NavigateTo();
		nt.Navigateto("My Zone", "Attendance", "Self");
		
		
		getMoAttendence atnd=new getMoAttendence();
		
		fluentWait("SelfAttendanceWaiting_xpath");
		
		Map<Integer,Map<String,String>> map=atnd.monthAttendance(data.get("YearNumber"),data.get("MonthName"),data.get("MonthNumber"));
		atnd.writeInXls(map ,data.get("MonthName"));
		*/
		
		boolean validateLogOut=lp.logout();
		if(validateLogOut==true){
		logger.pass("Logout successfull");	
		}
		Assert.assertEquals(validateLogOut, true);
		logger.info("********** Ending Test Case **********");
		
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

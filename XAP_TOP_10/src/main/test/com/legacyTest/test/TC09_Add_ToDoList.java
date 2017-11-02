package com.legacyTest.test;

import org.testng.annotations.Test;
import org.testng.Assert;



import java.util.Hashtable;



import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.legacyTeam.TestBase.TestBase;
import com.legacyTeam.dashboard.ToDoListFunctions;
import com.legacyTeam.loginPage.loginPage;


public class TC09_Add_ToDoList extends TestBase{
	
	public Assert  asrt;
	
	@Test (dataProvider="getData")
	public void Add_ToDoList(Hashtable <String,String> data){
	
		logger=extent.createTest(" TC09_Add_ToDoList "+data);	
		
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
		
		ToDoListFunctions td=new ToDoListFunctions(driver);
		int yearNumber=Integer.parseInt(data.get("YearNumber"));
		int date=Integer.parseInt(data.get("Date"));
		td.addlist(data.get("MonthName"), date, yearNumber , data.get("TextArea"));
		
		td.selectTab("Upcoming");
		boolean b=td.VerifyToDoList(data.get("VerifyText"));
		asrt.assertEquals(true, b);
		if(b==true){
			logger.pass("Test Case is Passed,Able to verify added String in the list : " +data.get("VerifyText"));
		}else{
			logger.fail("Test Case is Failed,Not Able to verify added String in the list : "+data.get("VerifyText"));
		}
			
		
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
			"Add_ToDoList");
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

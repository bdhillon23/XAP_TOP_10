package com.legacyTest.test;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.legacyTeam.TestBase.TestBase;
import com.legacyTeam.dashboard.NavigateTo;
import com.legacyTeam.loginPage.loginPage;

public class TC01_LeaveHistory extends TestBase{
	
public static Assert asrt;
	
	@Test (dataProvider="getData")
	public void LeaveHistory(Hashtable <String,String> data){
		
		logger=extent.createTest(" TC01_LeaveHistory "+data);
		
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
		waitinSec(1);
		//WebElement e=driver.findElement(By.xpath("id('sidebar')/li[2]/a[1]"));
		scroll();	
		
		NavigateTo nt=new NavigateTo();
		nt.Navigateto("My Zone", "Leave", "Leave Account");
		
		
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
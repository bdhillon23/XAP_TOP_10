package com.legacyTeam.MonthAttendence;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;




import com.legacyTeam.TestBase.TestBase;

public class getMoAttendence extends TestBase{

	String yearsXpath="//*[@id='optyear']/option";
	
	Map<String,String> atndc=new HashMap<String,String>();
	public void selectYear(String yearValue){
			fluentWait("SelfAttendanceWaiting_xpath");
			List<WebElement> years=new ArrayList<WebElement>();
			years=driver.findElements(By.xpath(yearsXpath));
			for(int i=0;i<=years.size();i++){
				if(years.get(i).getText().equalsIgnoreCase(yearValue)){
					years.get(i).click();
					System.out.println("This is the Year Value : "+years.get(i).getText());
					return;
				}
			}
		
	}
	
	String monthXpath="//*[@id='optmonth']/option";
	
	public void selectMonth(String monthValue){
		fluentWait("SelfAttendanceWaiting_xpath");
		List<WebElement> months=new ArrayList<WebElement>();
		months=driver.findElements(By.xpath(monthXpath));
		for(int i=0;i<=months.size();i++){
			if(months.get(i).getText().equalsIgnoreCase(monthValue)){
				months.get(i).click();
				System.out.println("This is the Month Value : "+months.get(i).getText());
				return;
			}
		}
		
	}
	
	public void monthAttendance(){
		List<WebElement> header=driver.findElements(By.xpath(prop.getProperty("AttendanceTableheader_xpath")));
		List<WebElement> tValues=driver.findElements(By.xpath(prop.getProperty("AttendanceTableValues_xpath")));
	
		List<String>  hValues=new ArrayList<String>();
		
		
		
		for(int i=1;i<=header.size();i++){
			String xpathpart1="id('tblAttendance')/thead[1]/tr[1]/th["+i+"]";
			String val=driver.findElement(By.xpath(xpathpart1)).getText();
			hValues.add(val); 
			
		}
		
		
		
		
		//Getting the values of the trows
		for(int i=1;i<=tValues.size();i++){
			String xpathpart1="id('tblAttendance')/tbody[1]/tr["+i+"]";
			for(int j=1;j<=header.size();j++){
				String xpathpart2="/td["+j+"]";
				String tvalu=driver.findElement(By.xpath(xpathpart1+xpathpart2)).getText();
				
				atndc.put(hValues.get(j-1), tvalu);
				
			}
			
		}
		
		
		
		
	}
	
	public void printValues(){
		for(int i=0;i<atndc.size();i++){
			
		}
	}
}

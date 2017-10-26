package com.legacyTeam.MonthAttendence;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.legacyTeam.TestBase.TestBase;

public class getMoAttendence extends TestBase{

	String yearsXpath="//*[@id='optyear']/option";
	
	Map<String,String> atndc=new HashMap<String,String>();
	Map<Integer,Map> fvalues=new HashMap();
	
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
	
	public Map monthAttendance(String yearValue,String monthValue,String monthNumber){
		
		selectMonth(monthValue);
		selectYear(yearValue);
		monthValidator(monthNumber, monthValue);
		
		
		List<WebElement> header=driver.findElements(By.xpath(prop.getProperty("AttendanceTableheader_xpath")));
		List<WebElement> tValues=driver.findElements(By.xpath(prop.getProperty("AttendanceTableValues_xpath")));
	
		List<String>  hValues=new ArrayList<String>();
		
		
		
		for(int i=1;i<=header.size();i++){
			String xpathpart1="id('tblAttendance')/thead[1]/tr[1]/th["+i+"]";
			String val=driver.findElement(By.xpath(xpathpart1)).getText();
			
			hValues.add(i-1, val); 
			
			
		}
		
		
		//Getting the values of the rows
		for(int i=1;i<=tValues.size();i++){
			String xpathpart1="id('tblAttendance')/tbody[1]/tr["+i+"]";
			atndc=new LinkedHashMap<String,String>();
			for(int j=1;j<=header.size();j++){ 
				
				String xpathpart2="/td["+j+"]";
				String tvalu=driver.findElement(By.xpath(xpathpart1+xpathpart2)).getText();
				
				
				atndc.put(header.get(j-1).getText(), tvalu);
				
			}
			
			fvalues.put(i-1, atndc);
		}
		
		//Printing all the values fvalues
		
		
		return fvalues;
		}
	
	
	public boolean monthValidator(String month,String monthName){
		String firstdate=getElement("FirstElementVarifier_xpath").getText();
		String s=firstdate.substring(0, 2);
		String firstdate1=getElement("SecondElementVarifier_xpath").getText();
		String s1=firstdate1.substring(0, 2);
		
		try{
		if(month.contains(s) || monthName.contains(s)){
		return true;
			
		}
		
		if(month.contains(s1) || monthName.contains(s1)){
		return true;	
		}
		else
		{
			waitinSec(1);
			monthValidator( month, monthName);
		}
	
		}catch(Exception e){
			monthValidator( month, monthName);
		}
		return false;
		}
	
	public  void  writeInXls(Map mapName,String mName){
		String XlsxPath=null;
		File xclfile=new File(System.getProperty("user.dir")+prop.getProperty("generated_xcl"));
		XlsxPath=System.getProperty("user.dir")+prop.getProperty("generated_xcl");
		Workbook workbook = null;
		FileInputStream fim= null;
		try {	
			fim=new FileInputStream(xclfile);		
			workbook = new HSSFWorkbook(fim);
		}  catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		String s=now.toString();
		s=s.replace(":","_");
		Sheet sheet1;
		
		//int sheetCount=workbook.getNumberOfSheets();
		
			sheet1=workbook.createSheet(mName+s);
			
	
		
		List<String> Keys=null;
		List<String> Values=null;
		
		Row r1=sheet1.createRow(0);
		Row r=null;		
		CellStyle style= workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		Cell c1;
		for(int i=0;i<mapName.size();i++)
		
		{
			
			
			r=sheet1.createRow(i+1);
			
			Map.Entry me2=null;
			Keys=new ArrayList<String>();
			Values=new ArrayList<String>();
			HashMap<String,String>  hm= (HashMap<String,String>) mapName.get(i);
			Iterator<Map.Entry<String, String>> iterator = hm.entrySet().iterator();
				 
				 
			 while(iterator.hasNext()){
			 me2 = (Map.Entry) iterator.next();
			 Keys.add(me2.getKey().toString());
			 Values.add(me2.getValue().toString());
		 }
			
			for(int f=0;f<Values.size();f++){
				r.createCell(f).setCellValue(Values.get(f));
				sheet1.autoSizeColumn(f);
				
			}
			if(i==0){
				for(int k=0;k<Keys.size();k++){
				
					sheet1.autoSizeColumn(k);
					c1=r1.createCell(k);
					c1.setCellStyle(style);
					c1.setCellValue(Keys.get(k));
				}
			}
			
		}
		
		
		try
		
		{
			
			FileOutputStream fsm=new FileOutputStream(xclfile);
			workbook.write(fsm);
			fim.close();
			workbook.close();
			fsm.close();
		
			logger.log(Status.PASS, "Here is the link of attendance file : <a href= '"+ XlsxPath +"'>"+"Link for Attendance Excel File"+"</a>");
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}

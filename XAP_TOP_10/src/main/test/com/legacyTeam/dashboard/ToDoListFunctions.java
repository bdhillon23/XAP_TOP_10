package com.legacyTeam.dashboard;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.legacyTeam.TestBase.TestBase;

public class ToDoListFunctions extends TestBase {

	final String DashboardWaiting_xpath="//*[@id='divDaily']//*[local-name()='svg'][1]/*[local-name()='g'][1]/*[name()='path'][1]";
	
	@FindBy(xpath =DashboardWaiting_xpath)
	WebElement dashboardWaitingElement;
	
	@FindBy(id="tourStep4")
	WebElement todolistobject;
	
	@FindBy(id="btnAddTodolist")
	WebElement addbtn;
	
	
	@FindBy(id="txtNewToDo")
	WebElement textArea;
	
	@FindBy(id="txtToDoTaskDate")
	WebElement displayCalender;
	
	@FindBy(xpath="//div[@class='datepicker-days']//*/th[1]/i")
	WebElement prevMonth;
	
	@FindBy(xpath="//div[@class='datepicker-days']//*/th[3]/i")
	WebElement nextMonth;
	
	@FindBy(xpath="//*[@class='datepicker-days' and 'datepicker-switch']//tr[1]/th[2]")
	WebElement monthyearText;
	
	@FindBy(xpath="//div[@class='datepicker-days' ]//tbody/tr")
	WebElement datetrs;
	
	@FindBy(id="btnADDNewToDo")
	WebElement saveChangesbtn;
	
	@FindBy(xpath="id('todotab'/li")
	WebElement selectedtab;
	
	@FindBy(xpath="id('divToDo')//li")
	WebElement listItemsInTab;
	
	
	public ToDoListFunctions(WebDriver driver){
		PageFactory.initElements(driver,this);
		
	}
	
	
	public boolean scrollToDoList(){
		boolean onDashboard =dashboardWaitingElement.isDisplayed();
		if(onDashboard == true){
				do{
			      scroll(320);
				}while(!todolistobject.isDisplayed());
			
		return true;
		}
			
		else {
			scroll(0);
			NavigateTo nv=new NavigateTo();
			nv.Navigateto("Dashboard", "Employee", "");
			scrollToDoList();
		}
		return false;
	}
	
	
	
	
	public void addlist(String month,int date,Integer year,String TextDetails){
		if(scrollToDoList()==true){
			addbtn.click();
			textArea.clear();
			textArea.sendKeys(TextDetails);
			displayCalender.click();
				
			selectMonth(month,year);
			waitinSec(1);
			selectDate(date);
			saveChangesbtn.click();
			
		}else
			scrollToDoList();
	}
	
	
	
	
	public void selectMonth(String Month,Integer expectedYear){
		String monthText=monthyearText.getText();
		int lengthoftext=monthText.length();
		
		String getmonth=monthText.substring(0, lengthoftext-5);
		String getYear=monthText.substring(lengthoftext-4);
		getYear=getYear.replace(" ", "");
		Integer ActualYear=Integer.parseInt(getYear);
		
		if(monthText.equalsIgnoreCase(Month) && expectedYear == ActualYear){
			return;
		}
		else
		{	
			//Validating The Year Entered and Expected
			
			while(!expectedYear.equals(ActualYear)){
				if(expectedYear>ActualYear){
					while(!ActualYear.equals(expectedYear)){
						nextMonth.click();
						monthText=monthyearText.getText();
						lengthoftext=monthText.length();
						getYear=monthText.substring(lengthoftext-4);
						ActualYear=Integer.parseInt(getYear);
					}
				}
				
			}
			
			while(!getmonth.equals(Month)){
				nextMonth.click();
				monthText=monthyearText.getText();
				lengthoftext=monthText.length();
				getmonth=monthText.substring(0, lengthoftext-5);
				
			}
			
		}
			
		
	}
	
	public void selectDate(int date){
		
		 int calendar_tr=driver.findElements(By.xpath("//div[@class='datepicker-days' ]//tbody/tr")).size();
		
		 for(int j=1;j<=calendar_tr;j++){
			 String xpath1="//div[@class='datepicker-days' ]//tr["+j+"]/td[@class='day']";
			 int calendar_trtd=driver.findElements(By.xpath(xpath1)).size();
			for(int f=1;f<=calendar_trtd;f++){
				WebElement e=driver.findElement(By.xpath(xpath1+"["+f+"]"));
				String CurrentDate=	e.getText();
				int d=Integer.parseInt(CurrentDate);
				if(d==date){
					e.click();
					return;
				}
			}
			 
			 
		 }
		 
	}
	
	public void selectTab(String tabtobeSelected){
		List<WebElement> todotabs=driver.findElements(By.xpath("id('todotab'/li"));
		for(int i=1;i<=todotabs.size();i++){
			String tab=todotabs.get(i).getText();
			if(tabtobeSelected.equalsIgnoreCase(tab)){
				todotabs.get(i).click();
				return;
			}
		}
	
	}
	
	
	public boolean VerifyToDoList(String todolisttext,String tab){
		List<WebElement> itemsOnTab=driver.findElements(By.xpath("id('divToDo')//li"));
		for(int i=1;i<=itemsOnTab.size();i++){
			String xpath1="id('divToDo')//li["+i+"]//div[@class='task-title']/span";
			String actualtext=driver.findElement(By.xpath(xpath1)).getText();
			if(actualtext.contains(todolisttext)){
				return true;
			}
		}
		return false;	
					
	}
	
}

package com.legacyTeam.dashboard;

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
	
	public ToDoListFunctions(WebDriver driver){
		PageFactory.initElements(driver,this);
		this.driver=driver;
	}
	
	
	public boolean scrollToDoList(){
		boolean onDashboard =dashboardWaitingElement.isDisplayed();
		if(onDashboard == true){
				do{
			      scroll(150);
				}while(!todolistobject.isDisplayed());
			addbtn.click();	
		
		}
			
			
			
		
		else {
			scroll(0);
			NavigateTo nv=new NavigateTo();
			nv.Navigateto("Dashboard", "Employee", "");
			scrollToDoList();
		}
		return true;
	}
	
}

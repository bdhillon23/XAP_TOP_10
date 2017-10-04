package com.legacyTeam.dashboard;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.legacyTeam.TestBase.TestBase;

public class notificationPanel extends TestBase{
	
	@FindBy(xpath="id('divNotification')/ul[1]/li[1]/div[1]/div[1]/div[2]/div[1]")
	WebElement notificationBox;
			
	String part1="id('divNotification')/ul[1]/li[";
	String part2="]";
	
	WebElement notificationbox=driver.findElement(By.xpath("id('divNotification')/ul[1]"));
	List <WebElement> notifications= notificationbox.findElements(By.tagName("li"));
	
	
	
	public notificationPanel(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	
	
	public void getNotification(){
		for(int i=1;i<=notifications.size();i++){
		try{
			String text=driver.findElement(By.xpath(part1+i+part2)).getText();
			logger.log(Status.INFO, "You have "+ notifications.size() +" Notifications.This is the Notification "+i +" :: "+text);
			
		}catch(Exception e){
			e.printStackTrace();
			break;
		}
		
		}
		
	
	
	}
	
	
	
	
	
}

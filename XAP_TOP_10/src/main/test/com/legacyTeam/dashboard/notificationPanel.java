package com.legacyTeam.dashboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class notificationPanel {
	
	@FindBy(xpath="id('divNotification')/ul[1]/li[1]/div[1]/div[1]/div[2]/div[1]")
	
	WebElement notificationBox;
	
	
	public notificationPanel(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void getNotification(){
		String text=notificationBox.getText();
		System.out.println(text);
	}
}

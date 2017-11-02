package com.legacyTeam.loginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.legacyTeam.TestBase.TestBase;

public class loginPage extends TestBase{
	public static final Logger log=Logger.getLogger(loginPage.class);
	//public ExtentTest logger;
	WebDriver driver;
	
	@FindBy(id="txtLoginName")
	WebElement loginField;
	
	@FindBy(id="txtPassword")
	WebElement passwordField;
	
	@FindBy(id="btnLogin")
	WebElement loginBtn;
	
	@FindBy(id="divAttendaceDetails")
	WebElement validatelogin;
	
	@FindBy(css="li.dropdown.dropdown-user")
	WebElement profiledropDown;
	
	@FindBy(css="li.dropdown.dropdown-user>ul>li:nth-child(3)")
	WebElement logOutBtn;
	
	
	
	
	public void login(String username,String password){
	
		loginField.sendKeys(username);
		passwordField.sendKeys(password);
		loginBtn.click();
		log.info("Clicked on the login button");
	
		
	}
	public String validateLogin(){
		if(validatelogin.isDisplayed()){
			return "Login Successfully";
		}else
			return "login Failed";
	}
	
	public loginPage(WebDriver driver){
		PageFactory.initElements(driver,this);
		this.driver=driver;
	}
	
	public boolean logout(){
		Actions act=new Actions(driver);
		fluentWait(("profiledropDownKey_css"));
		waitinSec(1);
		
		act.moveToElement(profiledropDown).build().perform();
		waitinSec(1);
		TakeScreenshot();
		act.moveToElement(logOutBtn).click().build().perform();
		
		fluentWait("loginfield_id");
		logger.log(Status.INFO, "Logout Successfull");
		
		TakeScreenshot();
		return loginField.isDisplayed();
	}
	
	
}

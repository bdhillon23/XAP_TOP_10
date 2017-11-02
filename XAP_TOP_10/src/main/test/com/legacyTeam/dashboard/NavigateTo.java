package com.legacyTeam.dashboard;




import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.Status;
import com.legacyTeam.TestBase.TestBase;

public class NavigateTo extends TestBase{

	@FindBy(xpath="id('sidebar')/li")
	WebElement sidebar;
	 
	public void Navigateto(String titleName,String firstLevelValue,String secondLevelValue){
		
		ArrayList<Integer> NavigateValues=new ArrayList();
		NavigateValues.add(1);
		NavigateValues.add(2);
		//NavigateValues.add(3);
		NavigateValues.add(7);
		
		String xpath1="id('sidebar')/li[";
		String xpath2="]";
		
		for(int i=0;i<=2;i++){
			int valueFromList=NavigateValues.get(i);
			String xpathForSidebar=xpath1+valueFromList+xpath2;
			WebElement e=driver.findElement(By.xpath(xpathForSidebar));
			String Title=e.getAttribute("title");
			String forReport="For the xpath"+ xpathForSidebar+" The title is "+e.getAttribute("title")  ;
			logger.log(Status.INFO, forReport);
			
			
			if(titleName.equalsIgnoreCase(Title)){
				logger.info("Title Matchs");
				e.click();
				waitinSec(1);
				//Actions act=new Actions(driver);
				String xpathf=xpathForSidebar+"/ul/li";
				List<WebElement> wb=driver.findElements(By.xpath(xpathf));
				for(int j=0;j<wb.size();j++){
					
						String e1=wb.get(j).getText();
						
						if(firstLevelValue.equalsIgnoreCase(e1)){
							waitinSec(1);
							wb.get(j).click();
							
							if(secondLevelValue!=(""))
							{
							logger.info("The Value of id for :" +firstLevelValue +"its id is : "+wb.get(j).getAttribute("id"));
							waitinSec(1);
							String FirstLevelValue_id=wb.get(j).getAttribute("id");
							String NewXpath="id('"+FirstLevelValue_id+"')/ul/li";
							
								List<WebElement> wb2=driver.findElements(By.xpath(NewXpath));
									for(int h=0;h<wb2.size();h++){
							
								String value=wb2.get(h).getText();
									if(value.equalsIgnoreCase(secondLevelValue)){
										wb2.get(h).click();
									//fluentWait(elementLocator);
									TakeScreenshot();
									return;
								}}
							}
							else
							{
								TakeScreenshot();
								return;
							}
							
							}
												
						}
						
						
				
				
				
				
			}
					
		}
		
	}
	
}

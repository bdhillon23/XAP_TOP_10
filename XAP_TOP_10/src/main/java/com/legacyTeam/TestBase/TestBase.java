package com.legacyTeam.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.asserts.SoftAssert;

import ExtentManager.ExtentManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.google.common.base.Function;
import com.leagacyTeam.readExcel.Xls_Reader;



public class TestBase {
	public final Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	public DesiredCapabilities cap;
	public SoftAssert asrt = new SoftAssert();
	public Xls_Reader reader;
	public  static Properties prop;
	public ExtentReports extent=ExtentManager.getInstance();
	public static ExtentTest logger;
	

	public  void init() {
		try {
			prop = new Properties();

			File file = new File(System.getProperty("user.dir")
					+ "//OR.properties");
			FileInputStream fsm = new FileInputStream(file);
			prop.load(fsm);
			fsm.close();
			String log4jconfig = "log4j.properties";
			reader = new Xls_Reader(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\legacyTeam\\testDataFile\\TestData.xlsx");
			PropertyConfigurator.configure(log4jconfig);
			start(prop.getProperty("browser"),  prop.getProperty("URL"));
			
			
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	public void selectBrowser(String Browser) {
		
		
		if (Browser.equalsIgnoreCase("Chrome")) {
			
	
			System.setProperty("webdriver.chrome.driver",
			System.getProperty("user.dir")	+"\\Drviers\\chromedriver.exe");
			log.info("Creating obejct of browser " + Browser);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		}
		else if(Browser.equalsIgnoreCase("firefox")){
			
			
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")	+"\\Drviers\\geckodriver.exe");
			
			driver = new FirefoxDriver() ;
		}
	}

		public void getURL(String URL) {
		driver.get(URL);
		log.info("Navigating to the URL : " + URL);
		
	}
		public void start(String Browser ,String URL){
		selectBrowser(Browser);
		getURL(URL);
	}
		
		//******************************Action**********************************************//		
		public void action(WebElement e){
		Actions act=new Actions(driver);
		act.moveToElement(e).build().perform();
		
	}	
		
		//******************************TakeScreenShot**********************************************//		
		public void  TakeScreenshot(){
			JavascriptExecutor js=(JavascriptExecutor)driver;
			String State=(String) js.executeScript("return document.readyState");
			
			
			if(State.equalsIgnoreCase("Complete")){
			Date d=new Date();
			DateFormat dformat=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			String name=dformat.format(d);
			
			File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			String path=System.getProperty("user.dir")+"\\src\\main\\java\\com\\leagacyTeam\\screenshots\\" +name+".png";
			
			
			try{
				
				FileUtils.copyFile(src, new File(path));
				logger.log(Status.INFO, "<a href='" +path+"'>"+ "Image Link" +"</a>");
			}
			catch(IOException e){
				System.out.println(e.getMessage()+" Exception while taking screenshot");
			}
			}
			else
			{
				TakeScreenshot();
				
			}
			
		}
		
//******************************FluentWait**********************************************//	
	public static void fluentWait(String elementLocator){
		final String elementLocatorKey=elementLocator;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(30, TimeUnit.SECONDS)
			       .pollingEvery(2, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);
			 
			   WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
			       return getElement(elementLocatorKey);
			     }
			   });
	}
//******************************GetElement**********************************************//	
	public static WebElement getElement(String elementLocator){
		WebElement element = null;
		
		if(elementLocator.contains("_xpath")){
			logger.log(Status.INFO, "Searching the Element with "+elementLocator);
			element=driver.findElement(By.xpath(prop.getProperty(elementLocator)));
			return element;
		}
		else if(elementLocator.contains("_id")){
			logger.log(Status.INFO, "Searching the Element with "+elementLocator);
			element=driver.findElement(By.id(prop.getProperty(elementLocator)));
			return element;
		}
		else if(elementLocator.contains("_css")){
			logger.log(Status.INFO, "Searching the Element with "+elementLocator);
			element=driver.findElement(By.cssSelector(prop.getProperty(elementLocator)));
			return element;
		}
		else if(elementLocator.contains("_linktext")){
			logger.log(Status.INFO, "Searching the Element with "+elementLocator);
			element=driver.findElement(By.linkText(prop.getProperty(elementLocator)));
			return element;
		}
		else if(elementLocator.contains("_className")){
			logger.log(Status.INFO, "Searching the Element with "+elementLocator);
			element=driver.findElement(By.className(prop.getProperty(elementLocator)));
			return element;
		}
		else if(elementLocator.contains("_tagName")){
			logger.log(Status.INFO, "Searching the Element with "+elementLocator);
			element=driver.findElement(By.tagName(prop.getProperty(elementLocator)));
			return element;
		}
		else if(elementLocator.contains("_name")){
			logger.log(Status.INFO, "Searching the Element with "+elementLocator);
			element=driver.findElement(By.name(prop.getProperty(elementLocator)));
			return element;
		}
		
	return element;
	}
	
//***********************************waitInSec******************************************************//
	
	public static void waitinSec(int timeinSec){
		try {
			logger.log(Status.INFO, "Driver is waiting for : "+timeinSec +" Seconds");
			Thread.sleep(timeinSec*1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	
//******************************************WaitforLoad************************************************//
	public  boolean pageReady(){
		JavascriptExecutor js=(JavascriptExecutor) driver;
		String state=(String) js.executeScript("return document.readyState" );
		if(state.equalsIgnoreCase("Complete")){
			return true;
		}else
		{
			pageReady();
		}
		return false;
	}
	
	
	
	
}

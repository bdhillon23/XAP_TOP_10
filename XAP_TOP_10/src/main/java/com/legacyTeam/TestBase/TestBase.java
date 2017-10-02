package com.legacyTeam.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.asserts.SoftAssert;

import ExtentManager.ExtentManager;

import com.aventstack.extentreports.ExtentReports;
import com.leagacyTeam.readExcel.Xls_Reader;



public class TestBase {
	public final Logger log = Logger.getLogger(TestBase.class.getName());
	public WebDriver driver;
	public DesiredCapabilities cap;
	public SoftAssert asrt = new SoftAssert();
	public Xls_Reader reader;
	public Properties prop;
	public ExtentReports extent=ExtentManager.getInstance();;
	

	public void init() {
		try {
			prop = new Properties();

			File file = new File(System.getProperty("user.dir")
					+ "//ObjectRepo.properties");
			FileInputStream fsm = new FileInputStream(file);
			prop.load(fsm);
			fsm.close();
			String log4jconfig = "log4j.properties";
			reader = new Xls_Reader(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\legacyTeam\\testDataFile\\TestData.xlsx");
			PropertyConfigurator.configure(log4jconfig);
			
		} catch (Exception e) {
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
			//cap= DesiredCapabilities.firefox();
			
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
	public void action(WebElement e){
		Actions act=new Actions(driver);
		act.moveToElement(e).build().perform();
		
	}
}

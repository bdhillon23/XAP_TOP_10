package ExtentManager;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentHtmlReporter HtmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	
	@BeforeSuite
	public static ExtentReports getInstance(){
		if(extent==null){
		
		HtmlReporter=new ExtentHtmlReporter("D://Workspace//LearningAutomation//Reports//MyOwnReport.html");
		
		
		HtmlReporter.config().setChartVisibilityOnOpen(true);
		HtmlReporter.config().setReportName("Balwinder Report");
		HtmlReporter.config().setDocumentTitle("DocumentTitle");
		HtmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		HtmlReporter.config().setTheme(Theme.DARK);
	//	HtmlReporter.loadConfig("D://Workspace//LearningAutomation//Extent-Config.xml");
	//	HtmlReporter.setAppendExisting(true);
		extent=new ExtentReports();
		extent.attachReporter(HtmlReporter);
		return extent;
		}
		System.out.println("Hello");
		return extent;
	
	}
	@AfterMethod
	public void Status(ITestResult result){
		if(ITestResult.SUCCESS==result.getStatus()){
			logger.log(com.aventstack.extentreports.Status.PASS , MarkupHelper.createLabel(result.getName()+"Test Case is passed", ExtentColor.GREEN));
			
		}
		if(result.getStatus()==ITestResult.FAILURE){
			System.out.println("Hi");
			logger.log(com.aventstack.extentreports.Status.FAIL , MarkupHelper.createLabel(result.getName() +"Test Case is Failed", ExtentColor.RED));
			logger.fail(result.getThrowable());
		}
		if(ITestResult.SKIP==result.getStatus()){
			logger.log(com.aventstack.extentreports.Status.SKIP, MarkupHelper.createLabel(result.getName() +"This test case is Skipped", ExtentColor.YELLOW));
			logger.skip(result.getThrowable());
		}
		
	}
	
/*@AfterSuite
	public void teardown(){
		if(extent!=null){
		
		extent.flush();}
				
	}*/
	
}

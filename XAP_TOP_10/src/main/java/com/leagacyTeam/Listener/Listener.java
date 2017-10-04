package com.leagacyTeam.Listener;



import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.legacyTeam.TestBase.TestBase;

public class Listener extends TestBase implements ITestListener{

	public void onFinish(ITestContext arg0) {
	
		
	}

	public void onStart(ITestContext arg0) {
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	
		
	}

	public void onTestFailure(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE){

			logger.log(Status.FAIL, "This test cases is failed");
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult result) {
		
		logger.log(Status.INFO, "Starting the test " +result.getName());
		
	}

	public void onTestSuccess(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS){
			TakeScreenshot();
			logger.log(Status.PASS, "This test cases is Passed");
		}
		
	}
	

}

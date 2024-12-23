package com.StoreAutomation.Utilities;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class Reporting extends TestListenerAdapter
{
	 public ExtentSparkReporter sparkReporter;
	 public ExtentReports extent;
	 public ExtentTest test;
	 
	  public void onStart(ITestContext context) 
	  {
	    String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    String repName="Test-Report"+timeStamp+".html";
	    sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repName);
	    sparkReporter.config().setDocumentTitle("StoreAutomationAPI");
	    sparkReporter.config().setReportName("Functional API Test Report");
	    sparkReporter.config().setTheme(Theme.DARK);
	    extent=new ExtentReports();
	    extent.attachReporter(sparkReporter);
	    extent.setSystemInfo("Host Name","local host");
	    extent.setSystemInfo("Operating System",System.getProperty("os.name"));
	    extent.setSystemInfo("Application", "Store Automation API");
	    extent.setSystemInfo("User Name", System.getProperty("user.name"));
	    extent.setSystemInfo("Environment", "QA");
	    extent.setSystemInfo("User", "srikanth");    		
	  }
	  public void onTestSuccess(ITestResult tr) 
	  {
		  test=extent.createTest(tr.getName());
		  test.assignCategory(tr.getMethod().getGroups());
		  test.createNode(tr.getName());
		  test.log(Status.PASS, "Test Passed");
	  }
	  public void onTestFailure(ITestResult tr) 
	  {

		  test=extent.createTest(tr.getName());
		  test.createNode(tr.getName());
		  test.assignCategory(tr.getMethod().getGroups());
		  test.log(Status.FAIL, "Test Failed");
		  test.log(Status.FAIL, tr.getThrowable().getMessage());
	  }
	  public void onTestSkipped(ITestResult tr)
	  {

		  test=extent.createTest(tr.getName());
		  test.createNode(tr.getName());
		  test.assignCategory(tr.getMethod().getGroups());
		  test.log(Status.SKIP, "Test Skipped");
		  test.log(Status.SKIP, tr.getThrowable().getMessage());
	  }
	  public void onFinish(ITestContext context) 
	  {
		  extent.flush();
	  }
	 
	  
}

package mukeshOtwani;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportDemo {

	//Without Screenshot
	@Test(enabled=false)
	public void f() throws IOException{
		System.out.println("Login to amazon");
		
		ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Reports/TestReport.html");
		
		ExtentReports extent=new ExtentReports();
		
		extent.attachReporter(reporter);
		
		ExtentTest logger=extent.createTest("LoginTest");
		
		logger.log(Status.INFO, "Login to amazon");
		logger.log(Status.PASS, "Title verified");
		extent.flush();
		
		
       ExtentTest logger2=extent.createTest("LogOff Test");
		
       logger2.log(Status.INFO, "Logout to amazon");
       logger2.log(Status.FAIL, "Title verified");
       
		extent.flush();
	}
	
	//With Screenshot
	@Test(enabled=true)
	public void f1() throws Exception{
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/");
		
		Thread.sleep(3000);
		
		System.out.println("Login to amazon");
		
		ExtentHtmlReporter extentHtmlReporter=new ExtentHtmlReporter("./Reports/TestReport1.html");
		
		ExtentReports extentReports=new ExtentReports();
		
		extentReports.attachReporter(extentHtmlReporter);
		
		ExtentTest extentTest=extentReports.createTest("LoginTest");
		
		extentTest.log(Status.INFO, "Login to amazon");
		extentTest.log(Status.PASS, "Title verified");
		
 
		String screen=getScreenhot(driver, "log");
		//logger.addScreenCaptureFromPath(screen);
		extentTest.fail("Test Case Failed Snapshot is below " + extentTest.addScreenCaptureFromPath(screen));
		extentTest.pass("Pass", MediaEntityBuilder.createScreenCaptureFromPath(screen).build());
       
		extentReports.flush();
	}
	
	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception 
	{
		try{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/screenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
		}
		catch(Exception e){
			return e.getMessage();
		}
	}
	
	
	
}

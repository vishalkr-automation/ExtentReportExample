package sdet;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

@Listeners(sdet.Listners.class)
public class TestClass extends BaseClass{
	public ExtentTest logger;
	
	@Test
	public void f() throws IOException{
		logger=Listners.extent.createTest("TestCase1");
		logger.log(Status.PASS, "Test Case Pass1");
		logger.log(Status.PASS, "Test Case Pass2");
		logger.log(Status.PASS, "Test Case Pass3");
		System.out.println("Test pass");
		
		String screenshotPath = getScreenShot(driver, "Screen1");
		//To add Screenshot it in the extent report 
		logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
		
	}

}

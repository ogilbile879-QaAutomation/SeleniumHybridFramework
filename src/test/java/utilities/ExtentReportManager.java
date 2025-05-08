package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {
		/*
		 * SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt=new
		 * Date(); String Currentdatetimestamp=df.format(dt);
		 */

		String timestamp = new SimpleDateFormat("yyyy.MM.HH.mm.ss").format(new Date());
		repName = "Test-Report" + timestamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location

		sparkReporter.config().setDocumentTitle("Opencart Automation Report"); // Title
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser:", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {
		// Display groups on report
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + "Go SuccessFully Executed.");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, result.getName() + "Got Failed.");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + "get Skipped.");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		// Report open directly on browser after test complete
		extent.flush();
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 
//	 try{ URL url=new
//	 URL("file:////"+System.getProperty("user.dir")+"\\reports\\"+repName);
//	 
//	 //Create the email meassage. 
//	 ImageHtmlEmail email = new ImageHtmlEmail();
//	 email.setDataSourceResolver(new DataSourceUrlResolver(url));
//	 email.setHostName("smtp.googleemail.com"); email.setSmtpPort(465);
//	 email.setAuthenticator(new
//	 DefaultAuthenticator("ogilbile879@gmail.com","password"));
//	 email.setSSLOnConnect(true);
//	 email.setFrom("ogilbile879@gmail.com","password"); //sender
//	 email.setSubject("Test Results.");
//	 email.setMsg("Please find attached reports.");
//	 email.addTo("projectteamdl@gmail.com");
//	 email.attach(url,"extent report","please check reports..");
//	 email.send();}catch(Exception e){ e.printStackTrace(); }
	
}

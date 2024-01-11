package testUtils;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterBase {
	static ExtentReports extent;
	
	@BeforeTest
	public static ExtentReports getReporterObject() {
		
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		
		//configure report doc
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		//monitors test to get info to put in report
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Daniel");
		
		return extent;
	}
}

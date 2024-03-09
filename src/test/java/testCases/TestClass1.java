package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testUtils.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestClass1 {

	
	@Test
	public void test() {
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.google.com");
		
		driver.switchTo();
	}
	
	
	@Test
	public void softassert() {
		SoftAssert softAssert = new SoftAssert();
		

	}
	
}



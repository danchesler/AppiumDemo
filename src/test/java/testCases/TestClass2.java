package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testUtils.BaseTest;

public class TestClass2 {

	String test;

	public TestClass2 (String test) {
		this.test = test;
	}
	
	@Test
	public void openpage() {
		System.out.println("test2");

	}
	
	@Test
	public void openpage1() {
		System.out.println("test22");

	}
	@BeforeTest
	public void test123() {
		System.out.println("beforetest2");
	}
	
	@BeforeMethod
	public void beforetest() {
		System.out.println("BEFORE2");
	}
	
	@BeforeClass
	public void beforeclass() {
		System.out.println("beforeclass2");
	}
	
	@AfterSuite
	public void aftersuite() {
		System.out.println("aftersuite2");
	}
}

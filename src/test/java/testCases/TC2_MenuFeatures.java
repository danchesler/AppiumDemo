package testCases;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.WebviewPage;
import testUtils.BaseTest;

public class TC2_MenuFeatures extends BaseTest {

	@Test
	public void WebViewTest() throws InterruptedException {
		cataloguePage.openMenu();
		WebviewPage webview = cataloguePage.selectWebview();
		
		System.out.println(webview.getHeaderText());
	
		Assert.assertEquals(webview.getHeaderText(), "Webview");
		
		webview.enterUrl("http://www.google.com");
		webview.goToSite();
		
		Assert.assertEquals(webview.getErrorText(), "Please provide a correct https url.");
		
		webview.enterUrl("https://www.google.com");
		webview.goToSite();
		
		
		/*
		 * NATIVE_APP
		 * WEBVIEW_com.saucelabs.mydemoapp.rn
		 */
		
		driver.context("WEBVIEW_com.saucelabs.mydemoapp.rn");
		driver.findElement(By.name("q")).sendKeys("reddit");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[@role='link'])[1]")).click();
		Thread.sleep(2000);
		
		driver.context("NATIVE_APP");
		driver.navigate().back();
		
		webview.openMenu();
		cataloguePage = webview.selectCatalogue();
		
	
	}
}

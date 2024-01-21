package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AboutPage;
import pageObjects.MyCartPage;
import pageObjects.ProductPage;
import pageObjects.WebviewPage;
import testUtils.BaseTest;

public class TC2_MenuFeatures extends BaseTest {

	@Test
	public void WebviewTest() throws InterruptedException {
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
	
	@Test
	public void AboutPageVerification() {
		cataloguePage.openMenu();
		AboutPage about = cataloguePage.selectAbout();
		
		Assert.assertEquals(about.getHeaderText(), "About");
		Assert.assertEquals(about.getVersionText(), "V.1.3.0-build 244 by ");
		
		about.goToWebSite();
		
		/*
		 * NATIVE_APP
		 * WEBVIEW_chrome
		 */
		
		driver.context("WEBVIEW_chrome");
		WebElement sauceLabs = driver.findElement(By.xpath("//img[@alt='Saucelabs']"));
		Assert.assertEquals(sauceLabs.getAttribute("alt"), "Saucelabs");
		driver.context("NATIVE_APP");
		driver.navigate().back();
		about.openMenu();
		cataloguePage = about.selectCatalogue();
	}
	
	@Test
	public void ResetAppVerification() throws InterruptedException {
		ProductPage productPage = cataloguePage.selectProduct(0);
		productPage.addToCart();
		Assert.assertEquals(productPage.getCartQuantity(), 1);
		
		productPage.openMenu();
		cataloguePage = productPage.selectCatalogue();
		
		cataloguePage.openMenu();
		cataloguePage.selectReset();
		
		WebElement cancel = driver.findElement(By.id("android:id/button2"));
		cancel.click();
		
		cataloguePage.selectReset();
		WebElement resetApp = driver.findElement(By.id("android:id/button1"));
		resetApp.click();
		Thread.sleep(2000);
		WebElement confirmReset = driver.findElement(By.id("android:id/button1"));
		confirmReset.click();
		cataloguePage.selectCatalogue();
		
		Assert.assertFalse(cataloguePage.isCartQuantityDisplayed());
		
		MyCartPage cart = cataloguePage.goToCart();
		
		Assert.assertEquals(cart.getNoItemsText(), "No Items");
		
		cart.goShopping();
	}
	
	@DataProvider
	public Object[][] getUserPurchaseInfo() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\userPurchaseInfo.json");
		
		return new Object[][] { {data.get(0)} };
	}
	
}

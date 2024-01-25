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
import pageObjects.BrowserView;
import pageObjects.LoginPage;
import pageObjects.MyCartPage;
import pageObjects.ProductPage;
import pageObjects.WebviewPage;
import testUtils.BaseTest;

public class TC2_MenuFeatures extends BaseTest {

	@Test (groups = {"hybrid"})
	public void WebviewTest() throws InterruptedException {
		cataloguePage.openMenu();
		WebviewPage webview = cataloguePage.selectWebview();
	
		Assert.assertEquals(webview.getHeaderText(), "Webview");
		
		webview.enterUrl("http://www.google.com");
		webview.goToSite();
		
		Assert.assertEquals(webview.getErrorText(), "Please provide a correct https url.");
		
		webview.enterUrl("https://www.google.com");
		BrowserView browser = webview.goToSite();
		
		/*
		 * NATIVE_APP
		 * WEBVIEW_com.saucelabs.mydemoapp.rn
		 */
		
		driver.context("WEBVIEW_com.saucelabs.mydemoapp.rn");
		browser.googleSearchAction();
		
		Thread.sleep(3000);
		
		driver.context("NATIVE_APP");
		driver.navigate().back();
		
		webview.openMenu();
		cataloguePage = webview.selectCatalogue(); 
	}
	
	@Test (groups = {"hybrid"})
	public void AboutPageVerification() {
		cataloguePage.openMenu();
		AboutPage about = cataloguePage.selectAbout();
		
		Assert.assertEquals(about.getHeaderText(), "About");
		Assert.assertEquals(about.getVersionText(), "V.1.3.0-build 244 by ");
		
		BrowserView browser = about.goToWebSite();
		
		/*
		 * NATIVE_APP
		 * WEBVIEW_chrome
		 */
		
		driver.context("WEBVIEW_chrome");
		
		Assert.assertEquals(browser.getSauceLabsTitle(), "Saucelabs");
		
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
	
	/* enter incorrect username/pw
	 * verify error msg
	 * enter correct username/pw
	 * check login button goes to cart
	 */
	
	@Test (dataProvider="getUserPurchaseInfo")
	public void LoginVerification(HashMap<String, String> data) {
		cataloguePage.openMenu();
		LoginPage login = cataloguePage.selectLogin();
		
		login.enterUsername(data.get("username") + "a");
		login.enterPassword(data.get("password"));
		login.loginFromMenu();
		
		Assert.assertEquals(login.getErrorMessage(), "Provided credentials do not match any user in this service.");
		
		login.clearUsernameText();
		login.enterUsername(data.get("username"));
		cataloguePage = login.loginFromMenu();
		cataloguePage.openMenu();
		
		MyCartPage cart = cataloguePage.selectLoginWhileLoggedIn();
		
		Assert.assertEquals(cart.getNoItemsText(), "No Items");
	
		cart.openMenu();
		cart.selectCatalogue();
	}
	
	@DataProvider
	public Object[][] getUserPurchaseInfo() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\userPurchaseInfo.json");
		
		return new Object[][] { {data.get(0)} };
	}
	
}

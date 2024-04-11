package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.AboutPage;
import pageObjects.BrowserView;
import pageObjects.GeoLocationPage;
import pageObjects.LoginPage;
import pageObjects.MyCartPage;
import pageObjects.ProductPage;
import pageObjects.WebviewPage;
import testUtils.BaseTest;

public class TC4_LoginTestsPositive extends BaseTest {
	LoginPage login;
	
	@Test (dataProvider = "getUserPurchaseInfo")
	public void LoginVerification(HashMap<String, String> data) {
		cataloguePage.openMenu();
		login = cataloguePage.selectLogin();
		
		login.enterUsername(data.get("username"));
		login.enterPassword(data.get("password"));
		cataloguePage = login.loginFromMenu();
		cataloguePage.openMenu();
		
		MyCartPage cart = cataloguePage.selectLoginWhileLoggedIn();
		
		Assert.assertEquals(cart.getNoItemsText(), "No Items");
	
		cart.openMenu();
		cataloguePage = cart.selectCatalogue();
	}
	
	@Test (dependsOnMethods = {"LoginVerification"})
	public void LogoutVerification() {
		cataloguePage.openMenu();
		cataloguePage.selectLogout();
	
		Assert.assertFalse(cataloguePage.isLogoutClickable());
		
		cataloguePage.cancelLogout();
		cataloguePage.selectLogout();
		login = cataloguePage.confirmLogout();
		
		Assert.assertEquals(login.getLogoutSuccessText(), "You are successfully logged out.");
		
		login.closeLogoutAlert();
		
		login.openMenu();
		login.selectCatalogue();
	}
	
	@DataProvider
	public Object[][] getUserPurchaseInfo() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\userPurchaseInfo.json");
		
		return new Object[][] { {data.get(0)} };
	}
}

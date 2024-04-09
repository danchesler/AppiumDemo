package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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

public class TC4_LoginTests extends BaseTest {
	
	@Test (dataProvider = "getUserPurchaseInfo")
	public void LoginVerification(HashMap<String, String> data) {
		cataloguePage.openMenu();
		LoginPage login = cataloguePage.selectLogin();
		
		login.enterUsername(data.get("username"));
		login.enterPassword(data.get("password"));
		cataloguePage = login.loginFromMenu();
		cataloguePage.openMenu();
		
		MyCartPage cart = cataloguePage.selectLoginWhileLoggedIn();
		
		Assert.assertEquals(cart.getNoItemsText(), "No Items");
	
		cart.openMenu();
		cart.selectCatalogue();
	}
	
	@Test (dependsOnMethods = {"LoginVerification"})
	public void LogoutVerification() {
		cataloguePage.openMenu();
		cataloguePage.selectLogout();
	
		Assert.assertFalse(cataloguePage.isLogoutClickable());
		
		cataloguePage.cancelLogout();
		cataloguePage.selectLogout();
		LoginPage login = cataloguePage.confirmLogout();
		
		Assert.assertEquals(login.getLogoutSuccessText(), "You are successfully logged out.");
		
		login.closeLogoutAlert();
		
		login.openMenu();
		login.selectCatalogue();
	}
	
	@Test(dataProvider="getLockedUserInfo")
	public void LoginLockedAccountVerification(HashMap<String, String> data) {
		cataloguePage.openMenu();
		LoginPage login = cataloguePage.selectLogin();
		
		login.enterUsername(data.get("username"));
		login.enterPassword(data.get("password"));
		login.loginFromMenu();
		
		Assert.assertEquals(login.getErrorMessage(), "Sorry, this user has been locked out.");
		
		login.clearUsernameField();
		login.clearPasswordField();
		login.openMenu();
		login.selectCatalogue();
	}
	
	@Test
	public void LoginWithUsernameNoPasswordVerification() {
		
	}
	
	@Test 
	public void LoginWithPasswordNoUserNameVerification() {
		
	}
	
	@Test
	public void LoginInvalidUsernameAndPasswordVerification() {
		
	}
	
	@Test
	public void LoginCorrectUserNameInvalidPassword() {
		
	}
	
	@Test
	public void LoginCorrectPasswordInvalidUsername() {
		
	}
	
	
	@DataProvider
	public Object[][] getUserPurchaseInfo() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\userPurchaseInfo.json");
		
		return new Object[][] { {data.get(0)} };
	}
	
	@DataProvider
	public Object[][] getLockedUserInfo() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\userPurchaseInfo.json");
		
		return new Object[][] { {data.get(1)} };
	}
	
}

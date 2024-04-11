package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testUtils.BaseTest;

public class TC5_LoginTestsNegative extends BaseTest {
	LoginPage login;
	
	@AfterMethod
	public void clearFieldsThenReturnToCatalogue() {
		login.clearUsernameField();
		login.clearPasswordField();
		login.openMenu();
		login.selectCatalogue();
	}
	
	@Test(dataProvider="getLockedUserInfo")
	public void LoginLockedAccountVerification(HashMap<String, String> data) {
		cataloguePage.openMenu();
		login = cataloguePage.selectLogin();
		
		login.enterUsername(data.get("username"));
		login.enterPassword(data.get("password"));
		login.loginFromMenu();
		
		Assert.assertEquals(login.getErrorMessage(), "Sorry, this user has been locked out.");
	}
	
	@Parameters ( {"username"} )
	@Test 
	public void LoginWithUsernameNoPasswordVerification(@Optional("optionalUsername") String username) {
		cataloguePage.openMenu();
		login = cataloguePage.selectLogin();
		
		login.enterUsername(username);
		login.loginFromMenu();
		
		Assert.assertEquals(login.getNoPasswordErrorMessage(), "Password is required");
	}
	
	@Parameters ( {"password"} )
	@Test 
	public void LoginWithPasswordNoUserNameVerification(@Optional("OptionalPassword") String password) {
		cataloguePage.openMenu();
		login = cataloguePage.selectLogin();
		
		login.enterPassword(password);
		login.loginFromMenu();
		
		Assert.assertEquals(login.getNoUsernameErrorMessage(), "Username is required");
	}
	
	@Test (dataProvider = "getMultipleIncorrectLoginInfo")
	public void LoginInvalidUsernameAndPasswordVerification(HashMap<String, String> data) {
		cataloguePage.openMenu();
		login = cataloguePage.selectLogin();
		
		login.enterUsername(data.get("username"));
		login.enterPassword(data.get("password"));
		login.loginFromMenu();
		
		Assert.assertEquals(login.getErrorMessage(), "Provided credentials do not match any user in this service.");
	}
	
	@DataProvider
	public Object[][] getLockedUserInfo() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\userPurchaseInfo.json");
		
		return new Object[][] { {data.get(1)} };
	}
	
	@DataProvider
	public Object[][] getMultipleIncorrectLoginInfo() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\incorrectLogins.json");
		
		return new Object[][] { {data.get(0)}, {data.get(1)}, {data.get(2)} };
	}
	
}

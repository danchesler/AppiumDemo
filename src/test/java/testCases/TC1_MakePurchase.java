package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.ProductPage;
import testUtils.BaseTest;

public class TC1_MakePurchase extends BaseTest {

	
	
	@Test (dataProvider = "getUserPurchaseInfo")
	public void SanityPurchase(HashMap<String, String> data) {

		//login
		cataloguePage.openMenu();
		LoginPage loginPage = cataloguePage.selectLogin();
		loginPage.enterUsername(data.get("username"));
		loginPage.enterPassword(data.get("password"));
		cataloguePage = loginPage.loginFromMenu();
		
		//add items to cart
		ProductPage productPage = cataloguePage.selectProduct(0);
		productPage.addToCart();
		driver.navigate().back();
		
		productPage = cataloguePage.selectProduct(1);
		productPage.addToCart();
		
		
	}
	
	
	@DataProvider
	public Object[][] getUserPurchaseInfo() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\userPurchaseInfo.json");
		
		return new Object[][] { {data.get(0)} };
	}
	
	
}

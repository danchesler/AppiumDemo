package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CheckoutCompletePage;
import pageObjects.CheckoutPaymentPage;
import pageObjects.CheckoutReviewOrderPage;
import pageObjects.CheckoutShippingPage;
import pageObjects.LoginPage;
import pageObjects.MyCartPage;
import pageObjects.ProductPage;
import testUtils.BaseTest;

public class TC1_MakePurchase extends BaseTest {

	
	@Test (dataProvider = "getUserPurchaseInfo")
	public void SanityPurchase(HashMap<String, String> data) throws InterruptedException {
		//login
		cataloguePage.openMenu();
		
		//verify open catalogue
		Assert.assertEquals(cataloguePage.getProductsText(), "Products");
		
		LoginPage loginPage = cataloguePage.selectLogin();
		loginPage.enterUsername(data.get("username"));
		loginPage.enterPassword(data.get("password"));
		cataloguePage = loginPage.loginFromMenu();
		
		//verify return to catalogue
		Assert.assertEquals(cataloguePage.getProductsText(), "Products");
		
		//add items to cart
		ProductPage productPage = cataloguePage.selectProduct(0);
		productPage.addToCart();
		
		//verify cart updates
		waitForElementToAppear(productPage.getCartQuantityElement(), driver);
		Assert.assertEquals(productPage.getCartQuantity(), 1);
		
		driver.navigate().back();
		
		productPage = cataloguePage.selectProduct(1);
		productPage.addToCart();
		
		//verify cart updates
		waitForElementToAppear(productPage.getCartQuantityElement(), driver);
		Assert.assertEquals(productPage.getCartQuantity(), 2);
		
		MyCartPage cartPage = productPage.goToCart();
		
		//verify cart price is correct
		Assert.assertEquals(cartPage.getFormattedTotalPrice(), cartPage.getSummedPrice());
		
		CheckoutShippingPage shipping = cartPage.proceedToCheckout();
		
		//Very shipping page reached
		Assert.assertEquals(shipping.getEnterShippingText(), "Enter a shipping address");
		
		shipping.enterShippingDetails(data);
		CheckoutPaymentPage payment = shipping.goToPayment();
		
		payment.isbillingAddressVisible();
		
		//verify payment page reached and billing address checked by default
		Assert.assertEquals(payment.getEnterPaymentText(), "Enter a payment method");
		Assert.assertTrue(payment.isbillingAddressVisible());
		
		payment.enterPaymentInfo(data);
		CheckoutReviewOrderPage review = payment.reviewOrder();
		
		waitForElementToAppear(review.getReviewOrderElement(), driver);
		
		//very review order page reached
		Assert.assertEquals(review.getReviewOrderText(), "Review your order");
		
		CheckoutCompletePage complete = review.placeOrder();
		
		//verify order complete page reached
		Assert.assertEquals(complete.getCheckoutCompleteText(), "Checkout Complete");
		
		cataloguePage = complete.continueShopping();
		
		waitForElementToAppear(cataloguePage.getMenuElement(), driver);
		
		//verify returned to product catalogue
		Assert.assertEquals(cataloguePage.getProductsText(), "Products");
		
		cataloguePage.openMenu();
		loginPage = cataloguePage.logout();
		
		//verify returned to login page
		Assert.assertEquals(loginPage.getLoginHeader(), "Login");
		
	}
	
	
	@DataProvider
	public Object[][] getUserPurchaseInfo() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\userPurchaseInfo.json");
		
		return new Object[][] { {data.get(0)} };
	}
	
	
}

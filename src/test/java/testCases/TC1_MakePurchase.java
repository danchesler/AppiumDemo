package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
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
		cataloguePage.openMenu();
		
		Assert.assertEquals(cataloguePage.getProductsText(), "Products");
		
		//Login
		LoginPage loginPage = cataloguePage.selectLogin();
		loginPage.enterUsername(data.get("username"));
		loginPage.enterPassword(data.get("password"));
		cataloguePage = loginPage.loginFromMenu();
		
		Assert.assertEquals(cataloguePage.getProductsText(), "Products");
		
		//Add products to cart and verify they're added with correct price
		ProductPage productPage = cataloguePage.selectProduct(0);
		productPage.addToCart();
		
		waitForElementToAppear(productPage.getCartQuantityElement(), driver);
		Assert.assertEquals(productPage.getCartQuantity(), 1);
		
		driver.navigate().back();
		
		productPage = cataloguePage.selectProduct(1);
		productPage.addToCart();
		
		waitForElementToAppear(productPage.getCartQuantityElement(), driver);
		Assert.assertEquals(productPage.getCartQuantity(), 2);
		
		MyCartPage cartPage = productPage.goToCart();
		
		Assert.assertEquals(cartPage.getFormattedTotalPrice(), cartPage.getSummedPrice());
		
		//Checkout process: shipping, payment, review
		CheckoutShippingPage shipping = cartPage.proceedToCheckout();
		
		Assert.assertEquals(shipping.getEnterShippingText(), "Enter a shipping address");
		
		shipping.enterShippingDetails(data);
		CheckoutPaymentPage payment = shipping.goToPayment();
		
		Assert.assertEquals(payment.getEnterPaymentText(), "Enter a payment method");
		Assert.assertTrue(payment.isbillingAddressVisible());
		
		payment.enterPaymentInfo(data);
		CheckoutReviewOrderPage review = payment.reviewOrder();
		
		waitForElementToAppear(review.getReviewOrderElement(), driver);
		Assert.assertEquals(review.getReviewOrderText(), "Review your order");
		
		//Place order and complete purchase
		CheckoutCompletePage complete = review.placeOrder();
		
		Assert.assertEquals(complete.getCheckoutCompleteText(), "Checkout Complete");
		
		cataloguePage = complete.continueShopping();
		
		waitForElementToAppear(cataloguePage.getMenuElement(), driver);
		
		Assert.assertEquals(cataloguePage.getProductsText(), "Products");
		
		cataloguePage.openMenu();
		loginPage = cataloguePage.logout();
		
		Assert.assertEquals(loginPage.getLoginHeader(), "Login");
		
		
	}
	
	@Test (dataProvider="getUserPurchaseInfo")
	public void SanityPurchaseNotLoggedIn(HashMap<String, String> data) throws InterruptedException {
		String text = "© 2024 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy.";
		cataloguePage.scrollToTextAction(text);
		
		//Add products to cart, change properties before adding
		ProductPage productPage = cataloguePage.selectProduct(4);
		Assert.assertEquals(productPage.getProductQuantity(), "1");
		productPage.increaseQuantity();
		Assert.assertEquals(productPage.getProductQuantity(), "2");
		
		productPage.selectColor("gray");
		productPage.addToCart();
		
		waitForElementToAppear(productPage.getCartQuantityElement(), driver);
		Assert.assertEquals(productPage.getCartQuantity(), 2);
		
		driver.navigate().back();
		
		productPage = cataloguePage.selectProduct(5);
		Assert.assertEquals(productPage.getProductQuantity(), "1");
		productPage.increaseQuantity();
		Assert.assertEquals(productPage.getProductQuantity(), "2");
		
		productPage.selectColor("black");
		productPage.addToCart();
		
		waitForElementToAppear(productPage.getCartQuantityElement(), driver);
		Assert.assertEquals(productPage.getCartQuantity(), 4);
		
		//Verify cart page then login to checkout
		MyCartPage cartPage = productPage.goToCart();
		
		Assert.assertEquals(cartPage.getFormattedTotalPrice(), cartPage.getSummedPrice());
		
		LoginPage loginPage = cartPage.proceedToLoginBeforeCheckout();
		loginPage.enterUsername(data.get("username"));
		loginPage.enterPassword(data.get("password"));
		
		//Checkout process: shipping, payment, review
		CheckoutShippingPage shipping = loginPage.checkoutAfterLogin();
		
		Assert.assertEquals(shipping.getEnterShippingText(), "Enter a shipping address");
		
		shipping.enterShippingDetails(data);
		CheckoutPaymentPage payment = shipping.goToPayment();
		
		Assert.assertEquals(payment.getEnterPaymentText(), "Enter a payment method");
		Assert.assertTrue(payment.isbillingAddressVisible());
		
		payment.enterPaymentInfo(data);
		CheckoutReviewOrderPage review = payment.reviewOrder();
		
		waitForElementToAppear(review.getReviewOrderElement(), driver);
		Assert.assertEquals(review.getReviewOrderText(), "Review your order");
		
		//Place order and complete purchase
		CheckoutCompletePage complete = review.placeOrder();
		
		Assert.assertEquals(complete.getCheckoutCompleteText(), "Checkout Complete");
		
		cataloguePage = complete.continueShopping();
		
		waitForElementToAppear(cataloguePage.getMenuElement(), driver);
		
		Assert.assertEquals(cataloguePage.getProductsText(), "Products");
		
		cataloguePage.openMenu();
		loginPage = cataloguePage.logout();
		
		Assert.assertEquals(loginPage.getLoginHeader(), "Login");
	}
	
	@DataProvider
	public Object[][] getUserPurchaseInfo() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\userPurchaseInfo.json");
		
		return new Object[][] { {data.get(0)} };
	}
	
	
}

package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
	
	@BeforeMethod
	public void GoToHomePage() throws InterruptedException {
		cataloguePage.openMenu();
		cataloguePage.selectCatalogue();
	}
	
	@Test (dataProvider = "getUserPurchaseInfo", priority = 1)
	public void SanityPurchase(HashMap<String, String> data) throws InterruptedException {
		Assert.assertEquals(cataloguePage.getProductsText(), "Products");
		
		cataloguePage.openMenu();
		
		//Login
		LoginPage loginPage = cataloguePage.selectLogin();
		loginPage.enterUsername(data.get("username"));
		loginPage.enterPassword(data.get("password"));
		cataloguePage = loginPage.loginFromMenu();
		
		Assert.assertEquals(cataloguePage.getProductsText(), "Products");
		
		//Add products to cart and verify they're added with correct price
		ProductPage productPage = cataloguePage.selectProduct(0);
		productPage.addToCart();
		
		Assert.assertEquals(productPage.getCartQuantity(), 1);
		
		driver.navigate().back();
		
		productPage = cataloguePage.selectProduct(1);
		productPage.addToCart();
		
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
		
		Assert.assertEquals(review.getReviewOrderText(), "Review your order");
		
		//Place order and complete purchase
		CheckoutCompletePage complete = review.placeOrder();
		
		Assert.assertEquals(complete.getCheckoutCompleteText(), "Checkout Complete");
		
		cataloguePage = complete.continueShopping();
		
		Assert.assertEquals(cataloguePage.getProductsText(), "Products");
		
		cataloguePage.openMenu();
		loginPage = cataloguePage.logout();
		
		Assert.assertEquals(loginPage.getLoginHeader(), "Login");
		
		
	}
	
	@Test (dataProvider="getUserPurchaseInfo", priority = 2)
	public void SanityPurchaseNotLoggedIn(HashMap<String, String> data) throws InterruptedException {
		Assert.assertEquals(cataloguePage.getProductsText(), "Products");
		
		String text = "© 2024 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy.";
		cataloguePage.scrollToTextAction(text);
		
		//Add products to cart, change properties before adding
		ProductPage productPage = cataloguePage.selectProduct(4);
		Assert.assertEquals(productPage.getProductQuantity(), "1");
		productPage.increaseQuantity();
		Assert.assertEquals(productPage.getProductQuantity(), "2");
		
		productPage.selectColor("gray");
		productPage.addToCart();
		
		Assert.assertEquals(productPage.getCartQuantity(), 2);
		
		driver.navigate().back();
		
		productPage = cataloguePage.selectProduct(5);
		Assert.assertEquals(productPage.getProductQuantity(), "1");
		productPage.increaseQuantity();
		Assert.assertEquals(productPage.getProductQuantity(), "2");
		
		productPage.selectColor("black");
		productPage.addToCart();
		
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
		
		Assert.assertEquals(review.getReviewOrderText(), "Review your order");
		
		//Place order and complete purchase
		CheckoutCompletePage complete = review.placeOrder();
		
		Assert.assertEquals(complete.getCheckoutCompleteText(), "Checkout Complete");
		
		cataloguePage = complete.continueShopping();	
		
		Assert.assertEquals(cataloguePage.getProductsText(), "Products");
		
		cataloguePage.openMenu();
		loginPage = cataloguePage.logout();
		
		Assert.assertEquals(loginPage.getLoginHeader(), "Login");
	}
	
	@Test (dataProvider="getUserPurchaseInfo", priority = 3)
	public void ReviewOrderVerification(HashMap<String, String> data) throws InterruptedException {
		Assert.assertEquals(cataloguePage.getProductsText(), "Products");
		
		cataloguePage.openMenu();
		
		//Login
		LoginPage loginPage = cataloguePage.selectLogin();
		loginPage.enterUsername(data.get("username"));
		loginPage.enterPassword(data.get("password"));
		cataloguePage = loginPage.loginFromMenu();
		
		Assert.assertEquals(cataloguePage.getProductsText(), "Products");
		
		//Select product, change quantity and color
		ProductPage productPage = cataloguePage.selectProduct(0);
		productPage.increaseQuantity();
		productPage.increaseQuantity();
		productPage.selectColor(data.get("productcolor"));
		
		productPage.addToCart();
		
		Assert.assertEquals(productPage.getCartQuantity(), 3);
		
		//Verify item's correctly displayed in cart
		//e.g. color, price
		MyCartPage cartPage = productPage.goToCart();
		double cartTotalPrice = cartPage.getFormattedTotalPrice(); //need for review page later
		
		Assert.assertEquals(cartTotalPrice, cartPage.getSummedPrice());
		Assert.assertTrue(cartPage.isColorDisplayed(data.get("productcolor")));
		Assert.assertEquals(cartPage.getFirstItemQuantity(), 3);
		
		//Enter shipping details
		CheckoutShippingPage shipping = cartPage.proceedToCheckout();
		
		Assert.assertEquals(shipping.getEnterShippingText(), "Enter a shipping address");
		
		shipping.enterShippingDetails(data);
	
		//Enter payment method
		CheckoutPaymentPage payment = shipping.goToPayment();
		
		Assert.assertEquals(payment.getEnterPaymentText(), "Enter a payment method");
		Assert.assertTrue(payment.isbillingAddressVisible());
		
		payment.enterPaymentInfo(data);
		
		// Review Order. verify all data is maintained
		CheckoutReviewOrderPage review = payment.reviewOrder();
		
		Assert.assertEquals(review.getReviewOrderText(), "Review your order");
		Assert.assertTrue(review.isColorDisplayed(data.get("productcolor")));
		Assert.assertEquals(review.getTotalItems(), 3);
		
		review.scrollToTextAction("DHL Standard Delivery");
		
		Assert.assertEquals(review.getTotalOrderPrice(), cartTotalPrice + review.getDeliveryFee());
		Assert.assertEquals(review.getDeliveryCustomerName(), data.get("fullname"));
		Assert.assertEquals(review.getDeliveryAddress1(), data.get("address1"));
		Assert.assertEquals(review.getDeliveryAddress2(), data.get("address2"));
		Assert.assertEquals(review.getDeliveryCity(), data.get("city"));
		Assert.assertEquals(review.getDeliveryState(), data.get("state"));
		Assert.assertEquals(review.getDeliveryCountry(), data.get("country"));
		Assert.assertEquals(review.getDeliveryZipcode(), data.get("zipcode"));
		Assert.assertEquals(review.getPaymentCustomerName(), data.get("fullname"));
		Assert.assertEquals(review.getPaymentCardNumber(), data.get("cardnumber"));
		Assert.assertEquals(review.getPaymentCardExpiration(), data.get("expdate"));
		
		//Place order and complete purchase
		CheckoutCompletePage complete = review.placeOrder();
		
		Assert.assertEquals(complete.getCheckoutCompleteText(), "Checkout Complete");
		
		cataloguePage = complete.continueShopping();	
		
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

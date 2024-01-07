package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MyCartPage extends PageCommon {
	private AndroidDriver driver;
	
	public MyCartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility="Proceed To Checkout button")
	private WebElement checkout;
	
	public CheckoutShippingPage proceedToCheckout() {
		checkout.click();
		return new CheckoutShippingPage(driver);
	}
	
}

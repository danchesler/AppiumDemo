package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutReviewOrderPage extends PageCommon {
	private AndroidDriver driver;
	
	public CheckoutReviewOrderPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility="Place Order button")
	private WebElement placeOrderBtn;
	
	public CheckoutCompletePage placeOrder() {
		placeOrderBtn.click();
		return new CheckoutCompletePage(driver);
	}
	
}

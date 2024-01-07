package pageObjects;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutPaymentPage extends PageCommon {
	private AndroidDriver driver;
	
	public CheckoutPaymentPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility="Full Name* input field")
	private WebElement nameField;
	
	@AndroidFindBy(accessibility="Card Number* input field")
	private WebElement cardNumberField;
	
	@AndroidFindBy(accessibility="Expiration Date* input field")
	private WebElement expDateField;
	
	@AndroidFindBy(accessibility="Security Code* input field")
	private WebElement secCodeField;
	
	@AndroidFindBy(accessibility="Review Order button")
	private WebElement reviewOrderBtn;
	
	public void enterPaymentInfo(HashMap<String, String> data) throws InterruptedException {
		Thread.sleep(1000);
		nameField.sendKeys(data.get("fullname"));
		cardNumberField.sendKeys(data.get("cardnumber"));
		expDateField.sendKeys(data.get("expdate"));
		secCodeField.sendKeys(data.get("securitycode"));
	}
	
	public CheckoutReviewOrderPage reviewOrder() {
		reviewOrderBtn.click();
		return new CheckoutReviewOrderPage(driver);
	}
}

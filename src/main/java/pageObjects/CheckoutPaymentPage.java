package pageObjects;

import java.util.HashMap;
import java.util.List;

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
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'payment')]")
	private WebElement enterPaymentHeader;
	
	@AndroidFindBy(accessibility="Full Name* input field")
	private WebElement nameField;
	
	@AndroidFindBy(accessibility="Card Number* input field")
	private WebElement cardNumberField;
	
	@AndroidFindBy(accessibility="Expiration Date* input field")
	private WebElement expDateField;
	
	@AndroidFindBy(accessibility="Security Code* input field")
	private WebElement secCodeField;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='checkbox for My billing address is the same as my shipping address.']/android.view.ViewGroup")
	private WebElement checkbox;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Full Name*']")
	private List<WebElement> fullname;
	
	@AndroidFindBy(accessibility="Review Order button")
	private WebElement reviewOrderBtn;
	
	//Element getters
	public String getEnterPaymentText() {
		return enterPaymentHeader.getText();
	}
	
	public boolean isbillingAddressVisible() {
		return fullname.size() == 1;
	}
	
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

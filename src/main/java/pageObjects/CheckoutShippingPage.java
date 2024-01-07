package pageObjects;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutShippingPage extends PageCommon {
	private AndroidDriver driver;
	
	public CheckoutShippingPage (AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'shipping')]")
	private WebElement enterShippingHeader;
	
	@AndroidFindBy(accessibility="Full Name* input field")
	private WebElement nameField;
	
	@AndroidFindBy(accessibility="Address Line 1* input field")
	private WebElement addr1Field;
	
	@AndroidFindBy(accessibility="Address Line 2 input field")
	private WebElement addr2Field;
	
	@AndroidFindBy(accessibility="City* input field")
	private WebElement cityField;
	
	@AndroidFindBy(accessibility="State/Region input field")
	private WebElement stateField;
	
	@AndroidFindBy(accessibility="Zip Code* input field")
	private WebElement zipField;
	
	@AndroidFindBy(accessibility="Country* input field")
	private WebElement countryField;
	
	@AndroidFindBy(accessibility="To Payment button")
	private WebElement toPaymentBtn;
	
	//Element getters
	public String getEnterShippingText() {
		return enterShippingHeader.getText();
	}
	
	public void enterShippingDetails(HashMap<String, String> data) {
		nameField.sendKeys(data.get("fullname"));
		driver.hideKeyboard();
		addr1Field.sendKeys(data.get("address1"));
		addr2Field.sendKeys(data.get("address2"));
		cityField.sendKeys(data.get("city"));
		stateField.sendKeys(data.get("state"));
		zipField.sendKeys(data.get("zipcode"));
		countryField.sendKeys(data.get("country"));
	}
	
	public CheckoutPaymentPage goToPayment() {
		toPaymentBtn.click();
		return new CheckoutPaymentPage(driver);
	}
}

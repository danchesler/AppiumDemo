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
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Review')]")
	private WebElement reviewOrderHeader;
	
	@AndroidFindBy(accessibility="Place Order button")
	private WebElement placeOrderBtn;
	
	//Element getters
	
	public WebElement getReviewOrderElement() {
		return reviewOrderHeader;
	}
	
	public String getReviewOrderText() {
		return reviewOrderHeader.getText();
	}
	
	//Actions methods
	
	public CheckoutCompletePage placeOrder() {
		placeOrderBtn.click();
		return new CheckoutCompletePage(driver);
	}
	
}

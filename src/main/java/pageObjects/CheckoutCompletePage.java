package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutCompletePage extends PageCommon {
	private AndroidDriver driver;
	
	public CheckoutCompletePage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Complete')]")
	private WebElement checkoutCompleteText;
	
	@AndroidFindBy(accessibility="Continue Shopping button")
	private WebElement continueShoppingBtn;
	
	//Element getters
	public String getCheckoutCompleteText() {
		return checkoutCompleteText.getText();
	}
	
	public CataloguePage continueShopping() throws InterruptedException {
		continueShoppingBtn.click();
		return new CataloguePage(driver);
	}
	
}

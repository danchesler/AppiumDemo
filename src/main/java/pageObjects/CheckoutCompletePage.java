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
	
	@AndroidFindBy(accessibility="Continue Shopping button")
	private WebElement continueShoppingBtn;
	
	public CataloguePage continueShopping() throws InterruptedException {
		continueShoppingBtn.click();
		Thread.sleep(1000);
		return new CataloguePage(driver);
	}
	
}

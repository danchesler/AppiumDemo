package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		new WebDriverWait(driver, Duration.ofSeconds(10))
			.ignoring(StaleElementReferenceException.class)
			.until((WebDriver d) -> {
				WebElement rev = d.findElement(By.xpath("//android.widget.TextView[contains(@text,'Review')]"));
				String s = rev.getText();
				return s;
			});
		return reviewOrderHeader.getText();
	}
	
	//Actions methods
	
	public CheckoutCompletePage placeOrder() {
		placeOrderBtn.click();
		return new CheckoutCompletePage(driver);
	}
	
}

package pageObjects;

import java.util.List;

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
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='product price']")
	private List<WebElement> prices;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='counter amount']/android.widget.TextView")
	private List<WebElement> quantities;
	
	@AndroidFindBy(accessibility="total price")
	private WebElement totalPrice;
	
	@AndroidFindBy(accessibility="Proceed To Checkout button")
	private WebElement checkout;
	
	public CheckoutShippingPage proceedToCheckout() {
		checkout.click();
		return new CheckoutShippingPage(driver);
	}
	
	public LoginPage proceedToLoginBeforeCheckout() {
		checkout.click();
		return new LoginPage(driver);
	}
	
	//Element getters
	public double getFormattedTotalPrice() {
		String priceStr = totalPrice.getText();
		return formatPrice(priceStr);
	}
	
	public double getSummedPrice() {
		double sum = 0;
		for (int i = 0; i < prices.size(); i++) {
			sum += formatPrice(prices.get(i).getText()) * Integer.parseInt(quantities.get(i).getText());
		}
		return sum;
	}
	
}

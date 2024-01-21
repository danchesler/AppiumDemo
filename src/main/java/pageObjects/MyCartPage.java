package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
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
	
	@AndroidFindBy(accessibility="black circle")
	private WebElement blackColor;
	
	@AndroidFindBy(accessibility="gray circle")
	private WebElement grayColor;
	
	@AndroidFindBy(accessibility="red circle")
	private WebElement redColor;
	
	@AndroidFindBy(accessibility="blue circle")
	private WebElement blueColor;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='counter amount']/android.widget.TextView")
	private List<WebElement> quantities;
	
	@AndroidFindBy(accessibility="total price")
	private WebElement totalPrice;
	
	@AndroidFindBy(accessibility="Proceed To Checkout button")
	private WebElement checkout;
	
	//Cart Empty Locators
	@AndroidFindBy(accessibility="container header")
	private WebElement noItemsText;
	
	@AndroidFindBy(accessibility="Go Shopping button")
	private WebElement goShopping;
	
	//Element getters
	public double getFormattedTotalPrice() {
		String priceStr = totalPrice.getText();
		return formatPrice(priceStr);
	}
	
	public int getFirstItemQuantity() {
		return Integer.parseInt(quantities.get(0).getText());
	}
	
	public boolean isColorDisplayed(String color) {
		boolean isDisplayed = true;
		switch (color) {
			case "black": 
				isDisplayed = blackColor.isDisplayed();
				break;
			case "gray": 
				isDisplayed = grayColor.isDisplayed();
				break;
			case "red": 
				isDisplayed = redColor.isDisplayed();
				break;
			case "blue": 
				isDisplayed = blueColor.isDisplayed();
				break;
		}
		return isDisplayed;
	}
	
	public double getSummedPrice() {
		double sum = 0;
		for (int i = 0; i < prices.size(); i++) {
			sum += formatPrice(prices.get(i).getText()) * Integer.parseInt(quantities.get(i).getText());
		}
		return sum;
	}
	
	public String getNoItemsText() {
		WebElement text = noItemsText.findElement(AppiumBy.className("android.widget.TextView"));
		return text.getText();
	}
		
	//Actions methods
	public CheckoutShippingPage proceedToCheckout() {
		checkout.click();
		return new CheckoutShippingPage(driver);
	}
	
	public LoginPage proceedToLoginBeforeCheckout() {
		checkout.click();
		return new LoginPage(driver);
	}
	
	public CataloguePage goShopping() {
		goShopping.click();
		return new CataloguePage(driver);
	}
	
	
}

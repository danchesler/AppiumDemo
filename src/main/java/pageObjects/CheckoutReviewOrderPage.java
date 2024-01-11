package pageObjects;

import java.time.Duration;
import java.util.List;

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
	
	@AndroidFindBy(accessibility="black circle")
	private WebElement blackColor;
	
	@AndroidFindBy(accessibility="gray circle")
	private WebElement grayColor;
	
	@AndroidFindBy(accessibility="red circle")
	private WebElement redColor;
	
	@AndroidFindBy(accessibility="blue circle")
	private WebElement blueColor;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='checkout delivery address']/android.widget.TextView")
	private List<WebElement> deliveryAddressDetails;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='checkout payment info']/android.widget.TextView")
	private List<WebElement> paymentMethodDetails;
	
	@AndroidFindBy(accessibility="checkout delivery details")
	private WebElement deliveryDetails;
	
	@AndroidFindBy(accessibility="total number")
	private WebElement itemCount;
	
	@AndroidFindBy(accessibility="total price")
	private WebElement totalOrderPrice;
	
	@AndroidFindBy(accessibility="Place Order button")
	private WebElement placeOrderBtn;
	
	//Getters
	public double getDeliveryFee() {
		List<WebElement> deliveryElements = deliveryDetails.findElements(By.xpath("//android.widget.TextView"));
		WebElement deliveryPrice = deliveryElements.get(1);
		return formatPrice(deliveryPrice.getText());
	}
	
	public double getTotalOrderPrice() {
		return formatPrice(totalOrderPrice.getText());
	}
	
	public int getTotalItems() {
		return Integer.parseInt(itemCount.getText().split(" ")[0]);
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
	
	//Delivery address getters
	public String getDeliveryCustomerName() {
		return deliveryAddressDetails.get(1).getText();
	}
	
	public String getDeliveryAddress1() {
		String fullAddress = deliveryAddressDetails.get(2).getText();
		String address1 = fullAddress.split(", ")[0];
		return address1;
	}
	
	public String getDeliveryAddress2() {
		String fullAddress = deliveryAddressDetails.get(2).getText();
		String address2 = fullAddress.split(", ")[1];
		return address2;
	}
	
	public String getDeliveryCity() {
		String cityState = deliveryAddressDetails.get(3).getText();
		String city = cityState.split(", ")[0];
		return city;
	}
	
	public String getDeliveryState() {
		String cityState = deliveryAddressDetails.get(3).getText();
		String state = cityState.split(", ")[1];
		return state;
	}
	
	public String getDeliveryCountry() {
		String countryZip = deliveryAddressDetails.get(4).getText();
		String country = countryZip.split(", ")[0];
		return country;
	}
	
	public String getDeliveryZipcode() {
		String countryZip = deliveryAddressDetails.get(4).getText();
		String zip = countryZip.split(", ")[1];
		return zip;
	}
	
	//Payment method getters
	public String getPaymentCustomerName() {
		return paymentMethodDetails.get(1).getText();
	}
	
	public String getPaymentCardNumber() {
		return paymentMethodDetails.get(2).getText();
	}
	
	public String getPaymentCardExpiration() {
		String expDate = paymentMethodDetails.get(3).getText().split(" ")[1];
		return expDate;
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

package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage extends PageCommon {
	private AndroidDriver driver;
	
	public ProductPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility="black circle")
	private WebElement blackColor;
	
	@AndroidFindBy(accessibility="gray circle")
	private WebElement grayColor;
	
	@AndroidFindBy(accessibility="red circle")
	private WebElement redColor;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='counter amount']/android.widget.TextView")
	private WebElement quantity;
	
	@AndroidFindBy(accessibility="counter plus button")
	private WebElement plusBtn;
	
	@AndroidFindBy(accessibility="counter minus button")
	private WebElement minusBtn;
	
	@AndroidFindBy(accessibility="Add To Cart button")
	private WebElement addToCart;
	
	//Element getters
	public String getProductQuantity() {
		return quantity.getText();
	}
	public void addToCart() {
		addToCart.click();
	}
	
	public void selectColor(String color) {
		switch (color) {
			case "black": 
				blackColor.click();
				break;
			case "gray": 
				grayColor.click();
				break;
			case "red": 
				redColor.click();
				break;
		}
	}
	
	public void increaseQuantity() {
		plusBtn.click();
	}
	
	public void decreaseQuantity() {
		minusBtn.click();
	}
	
	
}

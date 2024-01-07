package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utilities.AndroidActions;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//header references here

public class PageCommon extends AndroidActions {
	private AndroidDriver driver;
	
	public PageCommon(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//header locators
	@AndroidFindBy(accessibility="cart badge")
	private WebElement cart;

	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='cart badge']/android.widget.TextView")
	private WebElement cartCount;
	
	//hamburger menu locators
	@AndroidFindBy(accessibility="open menu")
	private WebElement hamburgerMenu;
	
	@AndroidFindBy(accessibility="menu item log in")
	private WebElement login;
	
	@AndroidFindBy(accessibility="menu item log out")
	private WebElement logout;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement confirmLogoutBtn;
	
	@AndroidFindBy(className="android.widget.Button")
	private WebElement successLogoutBtn;
	
	//Element getters
	public WebElement getMenuElement() {
		return hamburgerMenu;
	}
	
	public WebElement getCartQuantityElement() {
		return cartCount;
	}
	
	public int getCartQuantity() {
		int quantity = Integer.parseInt(cartCount.getText());
		return quantity;
	}
	
	//Header action methods
	public MyCartPage goToCart() {
		cart.click();
		return new MyCartPage(driver);
	}
	
	//Hamburger menu action methods
	public void openMenu() {
		hamburgerMenu.click();
	}
	
	
	public LoginPage selectLogin() {
		login.click();
		return new LoginPage(driver);
	}
	
	public LoginPage logout() {
		logout.click();
		confirmLogoutBtn.click();
		successLogoutBtn.click();
		return new LoginPage(driver);
	}
	
	
}

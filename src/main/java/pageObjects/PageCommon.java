package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilities.AndroidActions;

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
	protected WebElement hamburgerMenu;
	
	@AndroidFindBy(accessibility="menu item catalog")
	private WebElement catalogue;
	
	@AndroidFindBy(accessibility="menu item webview")
	private WebElement webview;
	
	@AndroidFindBy(accessibility="menu item geo location")
	private WebElement geolocation;
	
	@AndroidFindBy(accessibility="menu item about")
	private WebElement about;
	
	@AndroidFindBy(accessibility="menu item reset app")
	private WebElement reset;
	
	@AndroidFindBy(accessibility="menu item log in")
	private WebElement login;
	
	@AndroidFindBy(accessibility="menu item log out")
	private WebElement logout;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement confirmLogoutBtn;
	
	@AndroidFindBy(id="android:id/button2")
	private WebElement cancelLogoutBtn;
	
	@AndroidFindBy(className="android.widget.Button")
	private WebElement successLogoutBtn;
	
	//Android system locators
	@AndroidFindBy(id="com.android.permissioncontroller:id/permission_deny_button")
	private WebElement denyLocation;
	
	@AndroidFindBy(id="com.android.permissioncontroller:id/permission_allow_foreground_only_button")
	private WebElement alwaysAllowLocation;
	
	//Getters
	public int getCartQuantity() {
		waitForElementToAppear(cartCount, driver);
		int quantity = Integer.parseInt(cartCount.getText());
		return quantity;
	}
	
	public boolean isCartQuantityDisplayed() {
		try {
			driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='cart badge']/android.widget.TextView"));
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}
	
	public boolean isLogoutClickable() {
		boolean isClickable = true;
		
		try {
			isClickable = logout.isEnabled();
		} catch (WebDriverException e) { //originally just stale but also encountered nosuchelement
			isClickable = false;
		}
		
		return isClickable;
	}
	
	//Header action methods
	public MyCartPage goToCart() {
		cart.click();
		return new MyCartPage(driver);
	}
	
	//Hamburger menu action methods
	public void openMenu() {
		waitForElementToBeClickable(hamburgerMenu, driver);
		hamburgerMenu.click();
	}
	
	public CataloguePage selectCatalogue() {
		catalogue.click();
		return new CataloguePage(driver);
	}
	
	public WebviewPage selectWebview() {
		webview.click();
		return new WebviewPage(driver);
	}
	
	public GeoLocationPage selectGeoLocation() {
		geolocation.click();
		return new GeoLocationPage(driver);
	}
	
	public AboutPage selectAbout() {
		about.click();
		return new AboutPage(driver);
	}
	
	public void selectReset() {
		reset.click();;
	}
	
	public LoginPage selectLogin() {
		login.click();
		return new LoginPage(driver);
	}
	
	public void selectLogout() {
		logout.click();
	}
	
	public void cancelLogout() {
		cancelLogoutBtn.click();
	}
	
	public LoginPage confirmLogout() {
		confirmLogoutBtn.click();
		return new LoginPage(driver);
	}
	
	public MyCartPage selectLoginWhileLoggedIn() {
		login.click();
		return new MyCartPage(driver);
	}
	
	
	public LoginPage logout() {
		logout.click();
		confirmLogoutBtn.click();
		successLogoutBtn.click();
		return new LoginPage(driver);
	}
	
	//Android system actions
	public void dontAllowLocation() {
		denyLocation.click();
	}
	
	public void alwaysAllowLocation() {
		alwaysAllowLocation.click();
	}
	
}

package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

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
	
	//hamburger menu locators
	@AndroidFindBy(accessibility="open menu")
	private WebElement hamburgerMenu;
	
	@AndroidFindBy(accessibility="menu item log in")
	private WebElement login;
	
	//Action methods
	public void openMenu() {
		hamburgerMenu.click();
	}
	
	public LoginPage selectLogin() {
		login.click();
		return new LoginPage(driver);
	}
}

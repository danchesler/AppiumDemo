package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends PageCommon {
	private AndroidDriver driver;
	
	public LoginPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility="Username input field")
	private WebElement usernameField;
	
	@AndroidFindBy(accessibility="Password input field")
	private WebElement passwordField;
	
	@AndroidFindBy(accessibility="Login button")
	private WebElement loginBtn;
	
	public void enterUsername(String username) {
		usernameField.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public CataloguePage loginFromMenu() {
		loginBtn.click();
		return new CataloguePage(driver);
	}
}
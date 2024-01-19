package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WebviewPage extends PageCommon {
	private AndroidDriver driver;
	
	public WebviewPage (AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility="container header")
	private WebElement header;
	
	@AndroidFindBy(accessibility="URL input field")
	private WebElement inputField;
	
	@AndroidFindBy(accessibility="URL-error-message")
	private WebElement errorMsg;
	
	@AndroidFindBy(accessibility="Go To Site button")
	private WebElement goToSite;
	
	//Getters
	public String getHeaderText() {
		WebElement text = header.findElement(By.xpath("//android.widget.TextView"));
		return text.getText();
	}
	
	public String getErrorText() {
		WebElement text = errorMsg.findElement(By.xpath("//android.widget.TextView"));
		return text.getText();
	}
	//Action methods
	public void enterUrl(String url) {
		inputField.sendKeys(url);
	}
	
	public void goToSite() throws InterruptedException {
		goToSite.click();
		Thread.sleep(3000);
	}
	
	
	
}

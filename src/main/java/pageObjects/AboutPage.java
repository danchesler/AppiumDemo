package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AboutPage extends PageCommon {
	private AndroidDriver driver;
	
	public AboutPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility="container header")
	private WebElement header;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'build')]")
	private WebElement version;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'website')]")
	private WebElement websiteLink;
	
	//Getters
	public String getHeaderText() {
		WebElement text = header.findElement(AppiumBy.className("android.widget.TextView"));
		return text.getText();
	}
	
	public String getVersionText() {
		return version.getText();
	}
	
	//Action Methods
	public void goToWebSite() {
		websiteLink.click();
	}
}

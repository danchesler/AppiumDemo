package pageObjects;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class BrowserView {
	AndroidDriver driver;
	Set<String> contexts;
	
	public BrowserView (AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.contexts = driver.getContextHandles();
	}
	
	@FindBy(xpath="//img[@alt='Saucelabs']") 
	private WebElement sauceLabs;
	
	@FindBy(name="q")
	private WebElement googleSearchBox;
	
	@FindBy(xpath="(//div[@role='link'])[1]")
	private WebElement firstResult;
	
	public void printContextHandles() {
		for (String context : contexts) {
			System.out.println(context);
		}
	}
	
	//saucelabs methods
	public String getSauceLabsTitle() {
		return sauceLabs.getAttribute("alt");
	}
	
	//google methods
	public void googleSearchAction() {
		googleSearchBox.sendKeys("reddit");
		googleSearchBox.sendKeys(Keys.ENTER);
	}
	
	public void selectFirstResult() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(firstResult));
		firstResult.click();
	}
	
}

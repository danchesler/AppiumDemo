package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class GeoLocationPage extends PageCommon {
	AndroidDriver driver;
	
	public GeoLocationPage (AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility="latitude data")
	private WebElement latitude;
	
	@AndroidFindBy(accessibility="longitude data")
	private WebElement longitude;
	
	@AndroidFindBy(accessibility="Start Observing button") 
	private WebElement startObserving;
	
	@AndroidFindBy(accessibility="Stop Observing button")
	private WebElement stopObserving;
	
	public String getLatitude() {
		return latitude.getAttribute("text");
	}
	
	public String getLongitude() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains(longitude, "text", "."));
		
		return longitude.getAttribute("text");
	}
	
	public String getLogitudeAfterEnablingLocation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains(longitude, "text", "."));
		
		return longitude.getAttribute("text");
	}
	
	public String getLatitudeAfterEnablingLocation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains(latitude, "text", "."));
		
		return latitude.getAttribute("text");
	}
	
	public void startObservingLocation() {
		startObserving.click();
	}
	
	
	
}

package utilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils {
	private AndroidDriver driver;

	public AndroidActions (AndroidDriver driver) {
		this.driver = driver;
	}
	
	// Javascript executor techniques not working for this app
	public void scrollToTextAction(String text) {
		//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))")); //scroll down to the element and click
	}

}

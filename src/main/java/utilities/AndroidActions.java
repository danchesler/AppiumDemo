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
		//scroll down to text
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))"));
	}
	
	public void scrollToBottom() {
		String text = "© 2024 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy.";
		scrollToTextAction(text);
	}
	
	public void scrollToTop() {
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollToBeginning(1)"));
	}

}

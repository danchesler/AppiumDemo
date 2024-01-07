package utilities;

import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils {
	private AndroidDriver driver;

	public AndroidActions (AndroidDriver driver) {
		this.driver = driver;
	}
}

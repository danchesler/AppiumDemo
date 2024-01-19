package testUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import pageObjects.CataloguePage;
import utilities.AppiumUtils;



public class BaseTest extends AppiumUtils {
	public AndroidDriver driver;
	public AppiumDriverLocalService appiumServer;
	public CataloguePage cataloguePage;
	
	@BeforeTest
	public void ConfigureAppium() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		String deviceName = prop.getProperty("Android Device");
		
		appiumServer = startAppiumServer(ipAddress, Integer.parseInt(port)); //appiumutils
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(deviceName);
		options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\testResources\\Android-MyDemoAppRN.1.3.0.build-244.apk");
		options.setChromedriverExecutable("C:\\Users\\super\\Desktop\\Study\\Appium\\chromedriver120\\chromedriver.exe");
		
		driver = new AndroidDriver(appiumServer.getUrl(), options);
		
		cataloguePage = new CataloguePage(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		
		driver.quit();
		appiumServer.stop();
	}
	
}

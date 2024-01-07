package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {

	public AppiumDriverLocalService appiumServer;
	
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		File appiumFiles = new File("C:\\Users\\super\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");
		
		appiumServer = new AppiumServiceBuilder().withAppiumJS(appiumFiles).withIPAddress(ipAddress).usingPort(port).build();
		appiumServer.start();
		
		return appiumServer;
	}
	
	//Gets data from json for @DataProvider
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
		
	}
	
	//Remove $ sign from text and return its number
	public double formatPrice(String price) {
		Double formatted = Double.parseDouble(price.substring(1));
		return formatted;
	}
	
	//Explicit wait 10 seconds
	public void waitForElementToAppear(WebElement e, AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(e));
	}
}

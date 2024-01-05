package utilities;

import java.io.File;

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
}

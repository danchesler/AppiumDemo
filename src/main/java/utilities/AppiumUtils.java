package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
		
	}
}

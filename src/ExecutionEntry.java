import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import prerequistes.AppiumExecuter;
import prerequistes.BuilderExecution;
import prerequistes.JsonConfig;

public class ExecutionEntry {
	static String jsonFilePath = "C:\\JsonFiles\\Java\\LegalPg.json";
	static RemoteWebDriver wd = null;

	public static RemoteWebDriver getDriverInstance() {
		return wd;
	}

	public static void main(String[] a) throws Exception {
		BuilderExecution.createExcel();
		try {
			wd = ExecutionEntry.generateBrowsersInstance("", "", "");
			if (wd != null) {
				BuildeRecordedScript.sumitascript(jsonFilePath);
			} else {
				System.out.println("Driver instance is null");
			}

			// add your script name which you want to execute
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BuilderExecution.closeExcel();

	}

	public static RemoteWebDriver generateBrowsersInstance(String browserName,
			String deviceName, String deviceSerialNumber) throws Exception {

		RemoteWebDriver driver = null;
		if (browserName.toLowerCase().contains("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.toLowerCase().contains("chrome")) {
			String chromedriverPath = System.getProperty("user.dir")
					+ "//chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromedriverPath);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			driver = new ChromeDriver(capabilities);
		} else if (browserName.toLowerCase().contains("android")) {
			if (AppiumExecuter.startAppiumServer()) {
				driver = AppiumExecuter.appiumCapabilities(deviceName,
						deviceSerialNumber);
				BuildeRecordedScript.sumitascript(jsonFilePath);
			} else {
				System.out.println("Appium server not started");
			}
		} else {
			System.out.println("Browser name is not correct");

		}
		return driver;

	}

}

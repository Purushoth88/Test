package prerequistes;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AppiumExecuter {

	static AndroidDriver driver = null;

	public static boolean startAppiumServer() throws IOException, InterruptedException {
		boolean status = false;
		Runtime.getRuntime().exec("TASKKILL /F /IM node.exe");
		Thread.sleep(2000);
		Process proc1 = Runtime
				.getRuntime()
				.exec(
						"\"C:\\Appium\\node.exe\" \"C:\\Appium\\node_modules\\appium\\bin\\Appium.js\" --address 127.0.0.1 --chromedriver-port 9516 --bootstrap-port 4725 --selendroid-port 8082 --no-reset --local-timezone");
		Thread.sleep(10000);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(
				proc1.getInputStream()));
		// read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
			if (s.contains("Appium REST http interface listener started on")) {
				status = true;
				break;

			}
		}
		return status;

	}

	public static void stopAppiumServer() throws IOException {
		CommandLine command = new CommandLine("cmd");
		command.addArgument("/c");
		command.addArgument("Taskkill /F /IM node.exe");

		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		executor.execute(command, resultHandler);

	}

	public static AndroidDriver appiumCapabilities(String deviceName,
			String deviceSerialNumber) throws Exception {

		System.out.println("Appium Capabilities...................");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("automationName", "Android");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("udid", deviceSerialNumber);
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("newCommandTimeout", "120");
		driver = new AndroidDriver(new URL(

		"http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out
				.println("Appium SetUp for Android is successful and Appium Driver is launched successfully");
		return driver;
	}

	public static void main(String as[]) throws Exception {

		AppiumExecuter aps = new AppiumExecuter();

		System.out.println("Starting Server...");

		// aps.startAppiumServer();
		System.out.println("Server Started...." + aps.startAppiumServer());

		AppiumExecuter.appiumCapabilities("Nexus 6", "34235234643");
		// try{Thread.sleep(5000);}catch(Exception e){}
		//        
		// System.out.println("Stopping Server");
		// aps.stopAppiumServer();
		// System.out.println("Server Stopped");
	}

}
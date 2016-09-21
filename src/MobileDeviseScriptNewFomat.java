import io.appium.java_client.android.AndroidDriver;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import prerequistes.BuilderExecution;
import prerequistes.JsonConfig;

import org.openqa.selenium.support.ui.WebDriverWait;

public class MobileDeviseScriptNewFomat{
	public static boolean isAlertPresent(FirefoxDriver wd) {
		try {
			wd.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public static void sumitascript(String jsonFilePath) throws Exception {
	/**	DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("automationName", "Appium");
		caps.setCapability("platformName", "Android");
		caps.setCapability("deviceName", "Nexus 9");
		caps.setCapability("newCommandTimeout", "300");
		caps.setCapability("browserName", "Chrome");
		caps.setCapability("udid", "HT4A2JT03378");

		AndroidDriver wd = new AndroidDriver(new URL(
				"http://127.0.0.1:4723/wd/hub"), caps); **/

		 DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		 WebDriver wd = new ChromeDriver(capabilities);
		WebDriverWait wait = new WebDriverWait(wd, 40);
		wd.get("https://l8sita09:9744/web/earth/login");
		try {
			Thread.sleep(22222l);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// wd.findElement(By.xpath("//input[@id='cookieDialogContinueBtn']")).click();

		try {
			if ((wd.findElements(
					By.xpath("//input[@id='cookieDialogContinueBtn']")).size() == 0)) {
				System.err.println("verifyElementPresent failed");
			}
			wait.until(
					ExpectedConditions.elementToBeClickable(By
							.xpath("//input[@id='cookieDialogContinueBtn']")))
					.click();
		} catch (Exception e) {
			System.out.println("notice");
			BuilderExecution.handleExceptionInResult(e.getMessage());
			e.printStackTrace();
		}

		try {
			if ((wd.findElements(By.linkText("Legal Information1")).size() == 0)) {
				System.err.println("verifyElementPresent failed");
			}
			wait.until(
					ExpectedConditions.elementToBeClickable(By
							.linkText("Legal Information1"))).click();
			try {
				Thread.sleep(22222l);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			wait.until(
					ExpectedConditions.elementToBeClickable(By
							.linkText("Log On"))).click();
			try {
				Thread.sleep(22222l);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			System.out.println("Legal Infi");
			BuilderExecution.handleExceptionInResult(e.getMessage());
			e.printStackTrace();
		}

	/**	for (String winHandle : wd.getWindowHandles()) {
			if (winHandle.contains("WEBVIEW")) {
				wd.switchTo().window(winHandle);
			}
			System.out.println(winHandle);
		}**/
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId"))
				.clear();
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId"))
				.sendKeys("789789789");

		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password"))
				.clear();
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password"))
				.sendKeys("12345678");

		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue"))
				.clear();
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue"))
				.sendKeys("Z$JB");

		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue"))
				.clear();
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue"))
				.sendKeys("Z$JB");
		wait.until(
				ExpectedConditions.elementToBeClickable(By
						.id("_ParticipantLogon20_WAR_ahcommonauthportlet_logOn")))
				.click();
		try {
			Thread.sleep(22222l);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			Thread.sleep(22222l);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		wait.until(
				ExpectedConditions.elementToBeClickable(By
						.id("ahDialogCloseBtn"))).click();
		try {
			Thread.sleep(22222l);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			if ((wd.findElements(By.linkText("Health & Insurance")).size() == 0)) {
				System.err.println("verifyElementPresent failed");
			}
			wait.until(
					ExpectedConditions.elementToBeClickable(By
							.linkText("Health & Insurance"))).click();
			try {
				Thread.sleep(22222l);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			try {
				if ((wd.findElements(By.linkText("Learn About")).size() == 0)) {
					System.err.println("verifyElementPresent failed");
				}
				wait.until(
						ExpectedConditions.elementToBeClickable(By
								.linkText("Learn About"))).click();
				try {
					if ((wd.findElements(By.linkText("Reference Information"))
							.size() == 0)) {
						System.err.println("verifyElementPresent failed");
					}
					wait.until(
							ExpectedConditions.elementToBeClickable(By
									.linkText("Reference Information")))
							.click();
					try {
						Thread.sleep(22222l);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				} catch (Exception e) {
					System.out.println("learn about");
					BuilderExecution.handleExceptionInResult(e.getMessage());
					e.printStackTrace();
				}
			} catch (Exception e) {
				System.out.println("Reference Information");
				BuilderExecution.handleExceptionInResult(e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Health & Insurance");
			BuilderExecution.handleExceptionInResult(e.getMessage());
			e.printStackTrace();
		}
		wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//li[@class='ah-aux-nav-persistent']//a[.='Log Off']")))
				.click();
		wd.quit();
	}
}

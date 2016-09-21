import io.appium.java_client.android.AndroidDriver;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import prerequistes.AppiumExecuter;
import prerequistes.BuilderExecution;
import prerequistes.JsonConfig;

import org.openqa.selenium.support.ui.WebDriverWait;

public class BuildeRecordedScript {
	public static boolean isAlertPresent(FirefoxDriver wd) {
		try {
			wd.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public static void sumitascript(String jsonFilePath) throws Exception {
			
		RemoteWebDriver wd = ExecutionEntry.getDriverInstance();
// 

		WebDriverWait wait = new WebDriverWait(wd, 40);
		wd.get("https://l4dridap1273:8446/web/earth/login");
		try {
			Thread.sleep(22222l);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	/**	for (String winHandle : wd.getWindowHandles()) {
			if (winHandle.contains("WEBVIEW")) {
				wd.switchTo().window(winHandle);
			}

		}**/
		// String parentWindow = wd.getWindowHandle();
		// wd.findElement(By.xpath("//input[@id='cookieDialogContinueBtn']")).click();

		try {
			if ((wd.findElements(
					By.xpath("//input[@id='cookieDialogContinueBtn']")).size() == 0)) {
				System.err.println("cookieDialogContinueBtn verifyElementPresent failed");
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
       
        if (!wd.findElement(By.tagName("html")).getText().contains("LogOn")) {
            System.out.println("verifyTextPresent failed");
        }
        String LoginURL = wd.getCurrentUrl();
BuilderExecution.readAndCompareJson(jsonFilePath, wd);
        try{ 
        if ((wd.findElements(By.linkText("Legal Information")).size() == 0)) {
            System.err.println("verifyElementPresent failed");
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Legal Information"))).click();
        try { Thread.sleep(22222l); } catch (Exception e) { throw new RuntimeException(e); }
        String LegalURL = wd.getCurrentUrl();
BuilderExecution.readAndCompareJson(jsonFilePath, wd);
        if (!wd.findElement(By.tagName("html")).getText().contains("Legal Information")) {
            System.out.println("verifyTextPresent failed");
        }
       }catch(Exception e){System.out.println("Legal Info link verification");
BuilderExecution.handleExceptionInResult(e.getMessage());
e.printStackTrace();}
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log On"))).click();
        wd.quit();

	}
	
}

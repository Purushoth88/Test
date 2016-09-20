import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jsonCofig.BuilderExecution;

public class SelBuilderexpostedScripts {

	public static void testcase1(String jsonFilePath) throws Exception {
		FirefoxDriver wd;
		wd = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(wd, 40);
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.get("https://l4dridap1273:8446/web/earth/login");
		String homepage = wd.getCurrentUrl();
		BuilderExecution.readAndCompareJson(jsonFilePath, wd);
		try {
			if ((wd.findElements(By.linkText("Log On")).size() == 0)) {
				System.err.println("verifyElementPresent failed");
			}
			if (!wd.findElement(By.tagName("html")).getText()
					.contains("Log On")) {
				System.out.println("verifyTextPresent failed");
			}
			wait
					.until(
							ExpectedConditions
									.elementToBeClickable(By
											.id("_ParticipantLogon20_WAR_ahcommonauthportlet_logOn")))
					.click();
		} catch (Exception e) {
			System.out.println("Exception Handing");
			e.printStackTrace();
		}
		wd.quit();
	}

}

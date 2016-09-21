import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import prerequistes.BuilderExecution;

public class buildrExe {
	static String jsonFilePath = "C:\\JsonFiles\\Java\\SecondaryWindowScript.json";
	public static void sumita() throws Exception {
		FirefoxDriver wd;
		wd = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(wd, 40);
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.get("https://l8sita09:9744/web/earth/login");
		if (!wd.findElement(By.tagName("html")).getText().contains("Log On")) {
			System.out.println("verifyTextPresent failed");
		}
		String login = wd.getCurrentUrl();
		BuilderExecution.readAndCompareJson(jsonFilePath, wd);
		if (!(wd.findElements(By.xpath("//div/h3[contains(.,'Log On')]"))
				.size() != 0)) {
			wd.close();
			throw new RuntimeException("assertElementPresent failed");
		}
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId"))
				.click();
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId"))
				.clear();
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId"))
				.sendKeys("789789789");
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password"))
				.click();
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password"))
				.clear();
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password"))
				.sendKeys("12345678");
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue"))
				.click();
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue"))
				.clear();
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue"))
				.sendKeys("Z$JB");
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue"))
				.click();
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue"))
				.clear();
		wd.findElement(
				By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue"))
				.sendKeys("Z$JB");
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("_ParticipantLogon20_WAR_ahcommonauthportlet_logOn")));
		try {
			Thread.sleep(20000l);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			if ((wd.findElements(By.id("ahDialogCloseBtn")).size() == 0)) {
				System.err.println("verifyElementPresent failed");
			}
			wait.until(ExpectedConditions.elementToBeClickable(By
					.id("ahDialogCloseBtn")));
		} catch (Exception e) {
			System.out.println("GMCFLYout not appears");
			BuilderExecution.handleExceptionInResult(e.getMessage());
			e.printStackTrace();
		}
		try {
			if ((wd.findElements(By.linkText("Health & Insurance")).size() == 0)) {
				System.err.println("verifyElementPresent failed");
			}
			wait.until(ExpectedConditions.elementToBeClickable(By
					.linkText("Health & Insurance")));
			try {
				if ((wd.findElements(By.linkText("Take Action")).size() == 0)) {
					System.err.println("verifyElementPresent failed");
				}
				wait.until(ExpectedConditions.elementToBeClickable(By
						.linkText("Take Action")));
				try {
					if ((wd.findElements(By.linkText("Manage Beneficiaries"))
							.size() == 0)) {
						System.err.println("verifyElementPresent failed");
					}
					wait.until(ExpectedConditions.elementToBeClickable(By
							.linkText("Manage Beneficiaries")));
				} catch (Exception e) {
					System.out.println("navigation manage");
					BuilderExecution.handleExceptionInResult(e.getMessage());
					e.printStackTrace();
				}
				wait.until(ExpectedConditions.elementToBeClickable(By
						.linkText("Take Action")));
				try {
					if ((wd.findElements(
							By.linkText("Becoming Eligible for Benefits"))
							.size() == 0)) {
						System.err.println("verifyElementPresent failed");
					}
					wait.until(ExpectedConditions.elementToBeClickable(By
							.linkText("Becoming Eligible for Benefits")));
					wd = (FirefoxDriver) wd.switchTo().frame(0);
					if (!wd.findElement(By.tagName("html")).getText()
							.contains("/CsErr200DfltCrtcOpen")) {
						System.out.println("verifyTextPresent failed");
					}
		//			wd = (FirefoxDriver) wd.switchTo().switchToDefaultContent();
				} catch (Exception e) {
					System.out.println("navigation eligible");
					BuilderExecution.handleExceptionInResult(e.getMessage());
					e.printStackTrace();
				}
			} catch (Exception e) {
				System.out.println("Take Action Close");
				BuilderExecution.handleExceptionInResult(e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Health and Insurance Close");
			BuilderExecution.handleExceptionInResult(e.getMessage());
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(By
				.linkText("Legal Information")));
	//	wd = (FirefoxDriver) wd.switchTo().window();
		wd = (FirefoxDriver) wd.switchTo().window("New Tab");
		wd.quit();
		
	}

	public static boolean isAlertPresent(FirefoxDriver wd) {
		try {
			wd.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
}

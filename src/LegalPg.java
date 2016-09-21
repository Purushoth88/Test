import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import prerequistes.BuilderExecution;
import prerequistes.JsonConfig;

import com.jayway.jsonpath.JsonPath;

public class LegalPg {
	static String jsonFilePath = "C:\\JsonFiles\\Java\\LegalPg.json";

	public static void main(String[] aa) throws InvalidFormatException,
			InterruptedException, IOException {

		JsonConfig.createExcel();
		try {
			sumitascript();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonConfig.closeExcel();
	}

	public static void sumitascript() throws Exception {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		WebDriver wd = new ChromeDriver(capabilities);

		wd.get("https://l4dridap1273:8446/web/earth/login");
		try {

			Thread.sleep(10000l);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		wd.findElement(By.xpath("//input[@id='cookieDialogContinueBtn']"))
				.click();

		if (!wd.findElement(By.tagName("html")).getText().contains("LogOn")) {
			System.out.println("verifyTextPresent failed");
		}
		try {
			String LoginURL = wd.getCurrentUrl();
			JsonConfig.readAndCompareJson(jsonFilePath, wd);
			try {
				if ((wd.findElements(By.linkText("Legal Information")).size() == 0)) {
					System.err.println("verifyElementPresent failed");
				}
			} catch (Exception e) {
				BuilderExecution.handleExceptionInResult(e.getMessage());
				e.printStackTrace();
			}
			wd.findElement(By.linkText("Legal Information")).click();
			try {
				Thread.sleep(22222l);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			String LegalURL = wd.getCurrentUrl();
			JsonConfig.readAndCompareJson(jsonFilePath, wd);
			if (!wd.findElement(By.tagName("html")).getText()
					.contains("Legal Information")) {
				System.out.println("verifyTextPresent failed");
			}
		} catch (Exception e) {
			System.out.println("Legal Info link verification");
			e.printStackTrace();
		}
		wd.findElement(By.linkText("Log On")).click();
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

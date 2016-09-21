import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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

import prerequistes.JsonConfig;

import com.jayway.jsonpath.JsonPath;

import io.appium.java_client.android.AndroidDriver;

public class TwoPagesSecondScript {
	static String fileName = "file";
	static String fileParentPath = "parentpath";
	static Workbook wb = new XSSFWorkbook();
	static Sheet ws = wb.createSheet("Result");
	static int flag = 0;
	static String jsonFilePath = "C:\\JsonFiles\\Java\\TwoPagesSecondScript.json";

	

	
	public static void sumitaScript() throws InterruptedException, IOException {

		/**DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		WebDriver wd = new ChromeDriver(capabilities);**/
		
	DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("automationName","Appium");
            caps.setCapability("platformName", "Android");
            caps.setCapability("deviceName", "Nexus 9");
            caps.setCapability("newCommandTimeout", "120");
            caps.setCapability("browserName", "Chrome");
            caps.setCapability("udid", "HT4A2JT03378");

       AndroidDriver wd = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
       System.out.println("session open" + wd);
		
		//AndroidDriver wd=NewFile.androidMobileChromeLauncher("DROID MAXX", "TA9610EMOX");
            wd.get("https://l4dridap1273:8446/web/earth/login");
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		//wd.get("https://l4dridap1273:8446/web/earth/login");
		wd.findElement(By.xpath("//input[@id='cookieDialogContinueBtn']")).click();
        String Login = wd.getCurrentUrl();
        JsonConfig.readAndCompareJson(jsonFilePath, wd);
        
      wd.findElement(By.linkText("Legal Information")).click();
      wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        String LegalInfo = wd.getCurrentUrl();
        JsonConfig.readAndCompareJson(jsonFilePath, wd);
        
        if (!wd.findElement(By.tagName("html")).getText().contains("Terms of Use")) {
            System.out.println("verifyTextPresent failed");
        }
        
		wd.quit();
	}
	
	
	public static void main(String[] aa) throws InvalidFormatException,
			InterruptedException, IOException {
	
		JsonConfig.createExcel();
		try {
			sumitaScript();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonConfig.closeExcel();
		
	}

}

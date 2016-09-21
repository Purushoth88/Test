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

import prerequistes.JsonConfig;

import com.jayway.jsonpath.JsonPath;

public class ResultTryCatch {static String jsonFilePath = "C:\\JsonFiles\\Java\\ResultTryCatch.json";
  

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
        try { Thread.sleep(10000l); } catch (Exception e) { throw new RuntimeException(e); }
        String loginPageURL = wd.getCurrentUrl();
        wd.findElement(By.xpath("//input[@id='cookieDialogContinueBtn']")).click();
        String login = wd.getCurrentUrl();
        JsonConfig.readAndCompareJson(jsonFilePath, wd);
        if (!wd.findElement(By.tagName("html")).getText().contains("Log On")) {
            System.out.println("verifyTextPresent failed");
        }
        if (!(wd.findElements(By.xpath("//div/h3[contains(.,'Log On')]")).size() != 0)) {
            wd.close();
            throw new RuntimeException("assertElementPresent failed");
        }
        try{ 
        if ((wd.findElements(By.linkText("Legal Information")).size() == 0)) {
            System.err.println("verifyElementPresent failed");
        }
        wd.findElement(By.linkText("Legal Information")).click();
        try { Thread.sleep(22222l); } catch (Exception e) { throw new RuntimeException(e); }
        String LegalPageURL = wd.getCurrentUrl();
        JsonConfig.readAndCompareJson(jsonFilePath, wd);
        if (!wd.findElement(By.tagName("html")).getText().contains("Legal Information")) {
            System.out.println("verifyTextPresent failed");
        }
       }catch(Exception e){System.out.println("Legal Info Page Link not appears");
e.printStackTrace();}
        wd.findElement(By.linkText("Log On")).click();
        try { Thread.sleep(10000l); } catch (Exception e) { throw new RuntimeException(e); }
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).click();
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).clear();
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).sendKeys("789789789");
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password")).click();
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password")).clear();
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password")).sendKeys("12345678");
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue")).click();
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue")).clear();
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue")).sendKeys("Z$JB");
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue")).click();
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue")).clear();
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue")).sendKeys("Z$JB");
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_logOn")).click();
        try { Thread.sleep(22222l); } catch (Exception e) { throw new RuntimeException(e); }
        String HomePageURL = wd.getCurrentUrl();
        JsonConfig.readAndCompareJson(jsonFilePath, wd);
        try{ 
        if ((wd.findElements(By.id("ahDialogCloseBtn")).size() == 0)) {
            System.err.println("verifyElementPresent failed");
        }
        wd.findElement(By.id("ahDialogCloseBtn")).click();
       }catch(Exception e){System.out.println("GMCFlyout not appears");
e.printStackTrace();}
        try{ 
        if ((wd.findElements(By.linkText("Health & Insurance")).size() == 0)) {
            System.err.println("verifyElementPresent failed");
        }
        wd.findElement(By.linkText("Health & Insurance")).click();
        try { Thread.sleep(22222l); } catch (Exception e) { throw new RuntimeException(e); }
        try{ 
        if ((wd.findElements(By.linkText("Coverage Details")).size() == 0)) {
            System.err.println("verifyElementPresent failed");
        }
        wd.findElement(By.linkText("Coverage Details")).click();
        try { Thread.sleep(22222l); } catch (Exception e) { throw new RuntimeException(e); }
        try{ 
        if ((wd.findElements(By.linkText("Current Coverage")).size() == 0)) {
            System.err.println("verifyElementPresent failed");
        }
        wd.findElement(By.linkText("Current Coverage")).click();
        try { Thread.sleep(22222l); } catch (Exception e) { throw new RuntimeException(e); }
        wd = (FirefoxDriver) wd.switchTo().frame(0);
        if (!wd.findElement(By.tagName("html")).getText().contains("/HmCvInq010CvForYouAndYourDpndOpen")) {
            System.out.println("verifyTextPresent failed");
        }
       // wd = (FirefoxDriver) wd.switchTo().switchToDefaultContent();
        for (String winHandle : wd.getWindowHandles()) {
            wd.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }
       }catch(Exception e){System.out.println("current coverage not appears");
e.printStackTrace();}
       }catch(Exception e){System.out.println("current details not appears");
e.printStackTrace();}
       }catch(Exception e){System.out.println("Health Insurance Nav not appears");
e.printStackTrace();}
        wd.findElement(By.xpath("//div[@id='ah-footer']//a[.='Log Off']")).click();
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

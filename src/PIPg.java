import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import prerequistes.JsonConfig;

public class PIPg {static String jsonFilePath = "C:\\JsonFiles\\Java\\PIPg.json";

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
            wd.findElement(By.xpath("//input[@id='cookieDialogContinueBtn']")).click();
             

        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).clear();
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).sendKeys("789789789");

        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password")).clear();
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password")).sendKeys("12345678");
        
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue")).clear();
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue")).sendKeys("Z$JB");
        
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue")).clear();
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue")).sendKeys("Z$JB");
        wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_logOn")).click();
        try { Thread.sleep(22222l); } catch (Exception e) { throw new RuntimeException(e); }
        wd.findElement(By.id("ahDialogCloseBtn")).click();
        
        
        String PI = wd.getCurrentUrl();
        JsonConfig.readAndCompareJson(jsonFilePath, wd);
        
        wd.findElement(By.linkText("Your Profile")).click();
        wd.findElement(By.linkText("Personal Information")).click();
        try { Thread.sleep(2222l); } catch (Exception e) { throw new RuntimeException(e); }
        if (!wd.findElement(By.tagName("html")).getText().contains("Personal Information")) {
            System.out.println("verifyTextPresent failed");
        }
        wd.findElement(By.linkText("Log Off")).click();
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

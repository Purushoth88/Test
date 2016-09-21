


import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;

import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

import static org.openqa.selenium.OutputType.*;

public class LoginJava {
   
	
	  /**static void readAndCompareJson(String pathFirstJson,WebDriver wd){
		  
		File jsonFile=new File(pathFirstJson);
	
		String [] resultPathObjArray=null;
		
		String [] resultXpathListArray=null;
		
		List<String> resultXpathObjList=null;
		
		List<String> resultXpathList=null;
		
		List <String> resultConfLocList=null;
		
		String [] resultConfLocArray=null;
    	
		
    		try {
    			
	    			String url = wd.getCurrentUrl();
					
					
					 url=url.substring(url.lastIndexOf('/')+1, url.length());
					
					if(url.contains("?"))
					{
						url=url.substring(0, url.indexOf("?"));
					}
					System.out.println("url name  "+url);
    			   
					String pathResultJson=JsonPath.parse(jsonFile).read("$.inputs[0].ResultPath");	
    	            
    	          //  resultXpathList= JsonPath.parse(new File(pathResultJson)).read("$.Data[*].locator.value");
    	            
    	            resultXpathList= JsonPath.parse(new File(pathResultJson)).read("$.Data[?(@.PageName=="+url+")].locator.value");
    	            
    	            resultXpathObjList=JsonPath.parse(new File(pathResultJson)).read("$.Data[?(@.PageName=="+url+")].objList");
    	                     
    	            
    	            resultConfLocList=JsonPath.parse(new File(pathResultJson)).read("$.Data[?(@.PageName=="+url+")].ConfLoc");
    	            
    	            System.out.println("List of confloc for the page "+url+" are "+ resultConfLocList);
    	            
    	            resultConfLocArray=resultConfLocList.toArray(new String[0]);	
    	            
    				resultXpathListArray = resultXpathList.toArray(new String[0]);	
    				
    				int j=0;
    				for(String resultXpath:resultXpathListArray){	
    					
    				System.out.println("Xpath number "+j);	
    							
    			    resultXpathObjList= JsonPath.parse(new File(pathResultJson)).read("$.Data[?(@.ConfLoc=="+resultConfLocArray[j]+")].objList.Expvalue");
    			    
    			    resultPathObjArray = resultXpathObjList.toArray(new String[0]);	
    			    
    			    System.out.println("ConfLoc Related to xpath "+resultConfLocArray[j]);
    			    
    			    System.out.println("Object List related to xpath "+resultXpathObjList);

    	            List <WebElement> elements=wd.findElements(By.xpath(resultXpath));
    	        
    	            int i=0;
    	            boolean counter=false;
    				
    	            for(WebElement ele : elements){
    	            	System.out.println("Sid "+(i+1));
    	            	if(!counter && i>0){
    	            	  System.out.println("Fail  web element - "+ele.getAttribute("innerText").toString() +"  ,JSON result not present");
    	            	}
    	            	counter=false;	
    	            	if(null!=ele.getAttribute("innerText").toString() && i<resultPathObjArray.length && null!=resultPathObjArray[i] && !resultPathObjArray[i].equals("")){
    	            	
    	            	String webElement=	ele.getAttribute("innerText").toString();
    	            	
    	            	if(StringUtils.containsIgnoreCase(resultPathObjArray[i],webElement)){
    	            		System.out.println("Pass  "+"web element -" +webElement +"  ,JSON result -"+resultPathObjArray[i]);
    	            		
    	            	}else{
    	            		
    	            		System.out.println("Fail  "+" web element -" +webElement +"  ,JSON result -"+resultPathObjArray[i]);
    	            		
    	            	}
    	            	
    	            	 counter=true;
    	            	 i++;          
    	              }
    	            	
    	            	if(!counter){i++;}
    				}       
    	            j++;
    				}
    				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	  }**/
	  
       
    	public static void main(String[] args) throws MalformedURLException, InterruptedException {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("automationName","Appium");
            caps.setCapability("platformName", "Android");
            caps.setCapability("deviceName", "Nexus 9");
            caps.setCapability("newCommandTimeout", "120");
            caps.setCapability("browserName", "Chrome");
            caps.setCapability("udid", "HT4A2JT03378");

            
            AndroidDriver wd = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            System.out.println("session open" + wd);
            wd.get("https://l4dridap1273:8446/web/earth/login");
            try { Thread.sleep(10000l); } catch (Exception e) { throw new RuntimeException(e); }
           /** wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).click();
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
            wd.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_logOn")).click();**/
            try { Thread.sleep(2222l); } catch (Exception e) { throw new RuntimeException(e); }

            /**driver.manage().window().maximize();
            //wd.findElement(By.id("pnlLoginPanel_txtUsername")).sendKeys("TuserAuto");
            //wd.findElement(By.id("pnlLoginPanel_txtPassword")).sendKeys("Hewitt#123");
            //Thread.sleep(5000);
            //wd.findElement(By.id("pnlLoginPanel_btnSignIn")).click();
    		
	    		/*DesiredCapabilities capabilities=DesiredCapabilities.chrome();
	
	    	    WebDriver wd = new ChromeDriver(capabilities);
	            wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	            
	            int j=0;
	            
	         String jsonFilePath="C:\\JsonFiles\\Java\\TwoPages.json";// this is script path which i need to run.
               
	         wd.get("https://l4dridap1273:8446/web/earth/login");
	 		wd.findElement(By.xpath("//input[@id='cookieDialogContinueBtn']")).click();
	         String Login = wd.getCurrentUrl();
	         readAndCompareJson(jsonFilePath, wd);
	         
	       wd.findElement(By.linkText("Legal Information")).click();
	       try { Thread.sleep(50000l); } catch (Exception e) { throw new RuntimeException(e); }
	         String LegalInfo = wd.getCurrentUrl();
	         readAndCompareJson(jsonFilePath, wd);
	        // if (!wd.findElement(By.tagName("html")).getText().contains("Terms of Use")) {
	          //   System.out.println("verifyTextPresent failed");
	        // }
	         
	 		wd.quit();
	 	}  
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }**/
    }
}

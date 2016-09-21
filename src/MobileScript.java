import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import prerequistes.JsonConfig;

import com.jayway.jsonpath.JsonPath;

public class MobileScript {
	static String fileName = "file";
	static String fileParentPath = "parentpath";
	static Workbook wb = new XSSFWorkbook();
	static Sheet ws = wb.createSheet("Result");
	static int flag = 0;
	static String jsonFilePath = "C:\\JsonFiles\\Java\\TwoPagesSecondScript.json";

	static void readAndCompareJson(String pathFirstJson, WebDriver wd) {

		File jsonFile = new File(pathFirstJson);
		fileName = jsonFile.getName().replaceAll(".json", "");
		fileParentPath = jsonFile.getAbsolutePath();
		String[] resultPathObjArray = null;

		String[] resultXpathListArray = null;

		List<String> resultXpathObjList = null;

		List<String> resultXpathList = null;

		List<String> resultConfLocList = null;

		String[] resultConfLocArray = null;

		List<String> resultObjList = null;
		List<String> pageNameList = null;

		String[] resultObjectArray = null;
		String[] pageNameArray = null;

		try {

			String url = wd.getCurrentUrl();

			url = url.substring(url.lastIndexOf('/') + 1, url.length());
			//System.out.println(url);
			if (url.contains("?")) {
				url = url.substring(0, url.indexOf("?"));
			}
			//System.out.println(url);
			
			
			String pathResultJson = JsonPath.parse(jsonFile).read(
					"$.inputs[0].ResultPath");
		
			resultXpathList = JsonPath.parse(new File(pathResultJson)).read(
					"$.Data[?(@.PageName==" + url + ")].locator.value");

			resultConfLocList = JsonPath.parse(new File(pathResultJson)).read(
					"$.Data[?(@.PageName==" + url + ")].ConfLoc");
			resultConfLocArray = resultConfLocList.toArray(new String[0]);

			resultXpathListArray = resultXpathList.toArray(new String[0]);
			//System.out.println(resultXpathList.size());
			int j = 0;
			for (String resultXpath : resultXpathListArray) {

				resultXpathObjList = JsonPath.parse(new File(pathResultJson))
						.read(
								"$.Data[?(@.ConfLoc==" + resultConfLocArray[j]
										+ ")].objList.Expvalue");

				resultObjList = JsonPath.parse(new File(pathResultJson)).read(
						"$.Data[?(@.ConfLoc==" + resultConfLocArray[j]
								+ ")].objName");

				pageNameList = JsonPath.parse(new File(pathResultJson)).read(
						"$.Data[?(@.ConfLoc==" + resultConfLocArray[j]
								+ ")].PageName");

				resultObjectArray = resultObjList.toArray(new String[0]);
				pageNameArray = pageNameList.toArray(new String[0]);

				resultPathObjArray = resultXpathObjList.toArray(new String[0]);

				List<WebElement> elements = wd.findElements(By
						.xpath(resultXpath));
				flag = flag + 1;
				Row row1 = ws.createRow(flag);
				ws.addMergedRegion(new CellRangeAddress(flag, flag, 0, 4));
				
				row1.createCell(0).setCellValue("Page - " + pageNameArray[0]);
				CellStyle style1 = wb.createCellStyle();
				style1.setFillForegroundColor(IndexedColors.BRIGHT_GREEN
						.getIndex());
				style1.setAlignment(CellStyle.ALIGN_CENTER);
				style1.setFillPattern(CellStyle.SOLID_FOREGROUND);

				row1.getCell(0).setCellStyle(style1);
				int i = 0;
				int serialNumber = 0;
				boolean counter = false;
				String jsonResult = null;
				String jsonAttribute = null;
				String resultStatus = "Fail";
				for (WebElement ele : elements) {
					serialNumber = i + 1;
					jsonAttribute = ele.getAttribute("innerText").toString();
					if (!counter && i > 0) {
						jsonResult = "JSON result not present";

						resultStatus = "Fail";

					}
					counter = false;

					if (null != jsonAttribute && i < resultPathObjArray.length
							&& null != resultPathObjArray[i]
							&& !resultPathObjArray[i].equals("")) {

						if (!jsonAttribute.equals("")
								&& StringUtils.containsIgnoreCase(
										resultPathObjArray[i], jsonAttribute)) {
							jsonResult = resultPathObjArray[i];
							resultStatus = "PASS";
						} else {
							jsonResult = resultPathObjArray[i];
							resultStatus = "Fail";

						}
						flag = flag + 1;
				
						Row row = ws.createRow(flag);
						row.createCell(0).setCellValue(serialNumber);
						row.createCell(1).setCellValue(resultObjectArray[0]);
						jsonResult=jsonResult.split("\\|\\|")[0].trim();
						row.createCell(2).setCellValue(jsonResult);
						row.createCell(3).setCellValue(jsonAttribute.trim());
						CellStyle style = wb.createCellStyle();
						if (resultStatus.contains("PASS")) {
							style.setFillForegroundColor(IndexedColors.GREEN
									.getIndex());
						} else {
							style.setFillForegroundColor(IndexedColors.RED
									.getIndex());
						}

						style.setFillPattern(CellStyle.SOLID_FOREGROUND);
						row.createCell(4).setCellValue(resultStatus);
						row.getCell(4).setCellStyle(style);

					
						counter = true;
						i++;
					}

					if (!counter) {
						i++;
					}

				}
				j++;

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	
	public static void sumitaScript() throws InterruptedException, IOException {

		/**DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		WebDriver wd = new ChromeDriver(capabilities);**/
		///////AndroidDriver wd=NewFile.androidMobileChromeLauncher("", "");//this is for creating wd instance on mobile
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
		
		//wd.get("https://l4dridap1273:8446/web/earth/login");
//		wd.findElement(By.xpath("//input[@id='cookieDialogContinueBtn']")).click();
//        String Login = wd.getCurrentUrl();
//        readAndCompareJson(jsonFilePath, wd);
//        
//      wd.findElement(By.linkText("Legal Information")).click();
//      wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//        String LegalInfo = wd.getCurrentUrl();
//        readAndCompareJson(jsonFilePath, wd);
//        
//        if (!wd.findElement(By.tagName("html")).getText().contains("Terms of Use")) {
//            System.out.println("verifyTextPresent failed");
//        }
//        
//		wd.quit();
	}
	
	
	public static void main(String[] aa) throws InvalidFormatException,
			InterruptedException, IOException {
		
		createExcel();
		try {
			sumitaScript();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeExcel();
		
	}

	public static void closeExcel() {
		try {
			String file=System
			.getProperty("user.home")
			+ "\\Result_"
			+ fileName
			+ "_"
			+ new Random().nextInt(50046846) + ".xlsx";
			System.out.println("Result File: "+file);
			FileOutputStream out = new FileOutputStream(file);

			wb.write(out);
			out.close();
		} catch (Exception e) {
			System.out.println("unable to write to excel");
		}
	}

	public static void createExcel() {
		Row row = ws.createRow(ws.getPhysicalNumberOfRows());
		CellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		String[] stringArray = new String[] { "Serial Number", "Object Name",
				"Expected result", "Actual Result", "Status" };

		for (int j = 0; j < stringArray.length; j++) {
			Cell cell1 = row.createCell(j);
			row.getCell(j).setCellStyle(style);
			cell1.setCellValue(stringArray[j]);
		}
	}

	

}

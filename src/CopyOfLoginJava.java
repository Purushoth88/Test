import java.io.BufferedReader;
import java.io.File;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.jayway.jsonpath.JsonPath;

public class CopyOfLoginJava {
	static String fileName = "file";
	static String fileParentPath = "parentpath";
	static Workbook wb = new XSSFWorkbook();
	static Sheet ws = wb.createSheet("Result");
	static int flag = 0;
	static String jsonFilePath = "C:\\JsonFiles\\LoginPage77.json";

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

			if (url.contains("?")) {
				url = url.substring(0, url.indexOf("?"));
			}
			String pathResultJson = JsonPath.parse(jsonFile).read(
					"$.inputs[0].ResultPath");

			resultXpathList = JsonPath.parse(new File(pathResultJson)).read(
					"$.Data[?(@.PageName==" + url + ")].locator.value");

			resultConfLocList = JsonPath.parse(new File(pathResultJson)).read(
					"$.Data[?(@.PageName==" + url + ")].ConfLoc");
			resultConfLocArray = resultConfLocList.toArray(new String[0]);

			resultXpathListArray = resultXpathList.toArray(new String[0]);

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
				System.out.println(resultConfLocList);
				System.out.println(resultConfLocList.get(0));
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
						System.out.println("SerialNumber:   " + serialNumber);
						Row row = ws.createRow(flag);
						row.createCell(0).setCellValue(serialNumber);
						row.createCell(1).setCellValue(resultObjectArray[0]);
						row.createCell(2).setCellValue(jsonResult.trim());
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

						System.out.println(serialNumber + " " + " "
								+ resultObjectArray[0] + " "
								+ jsonResult.trim() + " "
								+ jsonAttribute.trim() + " " + resultStatus);
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

	public static void main(String[] aa) throws InvalidFormatException,
			InterruptedException, IOException {
		
		createExcel();
		demo();
		closeExcel();
	}

	public static void closeExcel() {
		try {
			FileOutputStream out = new FileOutputStream(System
					.getProperty("user.home")
					+ "\\Result_"
					+ fileName
					+ "_"
					+ new Random().nextInt(50046846) + ".xlsx");

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



	

	public static void demo() {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		WebDriver wd = new ChromeDriver(capabilities);
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("https://l4dridap1273:8446/web/earth/login");
        String login = wd.getCurrentUrl();
        readAndCompareJson(jsonFilePath, wd);
        wd.findElement(By.xpath("//input[@id='cookieDialogContinueBtn']")).click();
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
        try { Thread.sleep(2222l); } catch (Exception e) { throw new RuntimeException(e); }
        wd.findElement(By.id("ahDialogCloseBtn")).click();
        String home = wd.getCurrentUrl();
        readAndCompareJson(jsonFilePath, wd);
        if (!wd.findElement(By.tagName("html")).getText().contains("HomeHome")) {
            System.out.println("verifyTextPresent failed");
        }
        wd.quit();
		
	}

}

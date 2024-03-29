package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\Shahin Alam\\Page-Object-Model.git"
			+ "\\FreeCRMTestAutomation\\src\\main\\java\\com\\crm\\qa\\testdata\\CRMTestData.xlsx";
	static Workbook book;
	static Sheet sheet;
	
	
	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=20;
	
	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");	
	}

	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		//FileUtils.copyFile(scrFile, new File("C:\\Users\\Shahin Alam\\eclipse-workspace\\FreeCRMTestAutomation\\Screenshort.jpn"));
	}


//public static void takesScreenshot() throws IOException {
//	//1.Take Screenshot and store as a file formate
//	File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//	//2. now copy the Screenshot to desired location using copyFile Method:
//	FileUtils.copyFile(file, new File("C:\\Users\\Shahin Alam\\eclipse-workspace\\FreeCRMTestAutomation\\Screenshort.pnj"));
//        }

	
}





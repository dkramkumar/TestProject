package com.demoQA.BasePage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.apache.commons.io.FileUtils;
import com.demoQA.utility.ExtentManager;
import com.google.common.io.Files;
import org.apache.commons.codec.binary.Base64;


public class BasePage {

	public static WebDriver driver;
	//public static String browser = "edge";
	static String path = System.getProperty("user.dir") + "/Drivers";
	public static String downloadpath;
	public static String excelPath;
	
	@BeforeSuite
	public void beforeSuite() throws IOException {
		ExtentManager.createInstance();
	}
	
	@AfterSuite
	public void afterSuite() throws IOException {
		ExtentManager.endReport();
	}
  
	@SuppressWarnings("deprecation")
	public void InitialConfig(String browser) throws IOException {

		if(browser.equals("edge")){
			System.setProperty("webdriver.edge.driver", path + "/msedgedriver.exe");
			driver=new EdgeDriver();
		}
		
		else if(browser.equals("chrome")){	
			System.setProperty("webdriver.chrome.driver", path + "/chromedriver.exe");
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadpath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, options);

			driver = new ChromeDriver(cap);
		}
	
		driver.manage().window().maximize();
	}

	public static void openUrl() throws IOException {                           
		driver.get(EnvironmentUtils.getConfig().getProperty("url"));
		waitForPageLoad(10);
	}

	public static By findBy(String sLocator) {
		By byElement = null;
		if (sLocator.contains("/")) {
			byElement = By.xpath(sLocator);
		} else {
			byElement = By.id(sLocator);
		}
		return byElement;
	}

	public static WebElement findObject(By ele, String selectorName) {
		WebElement rClientElement = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			rClientElement = wait.until(ExpectedConditions.presenceOfElementLocated(ele));
		} catch (Exception e) {
			System.out.println("ERROR: Element " + selectorName + "  not found");
		}
		return rClientElement;
	}

	public static WebElement findObject1(By ele, String selectorName) {
		WebElement rClientElement = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			rClientElement = wait.until(ExpectedConditions.presenceOfElementLocated(ele));
		} catch (Exception e) {
			//System.out.println("ERROR: Element " + selectorName + "  not found");
		}
		return rClientElement;
	}

	public static List<WebElement> findObjects(By ele, String selectorName) {
		List<WebElement> rClientElement = null;
		try {
			rClientElement = driver.findElements(ele);
		} catch (Exception e) {
			System.out.println("ERROR: Element " + selectorName + "  not found");
		}
		return rClientElement;
	}

	public static List<WebElement> findElements(By ele, String selector) {
		List<WebElement> lsWebElement;
		lsWebElement = driver.findElements(ele);
		if (lsWebElement.size() == 0)
			System.out.println("ERROR: Expected " + selector + " found No element ");
		return lsWebElement;
	}

	public static void navigateBack() {
		driver.navigate().back();
	}

	public static void setText(By ele, String selector, String sText) {
		WebElement wEle = findObject(ele, selector);
		Actions act = new Actions(driver);
		act.sendKeys(wEle, sText).build().perform();
		System.out.println("INFO: Entering " + sText + " in " + selector);
	}

	public static String getText(By ele, String selector) {
		WebElement wEle = findObject(ele, selector);
		String text = wEle.getText();
		System.out.println("INFO: Geting text from " + selector + " is " + text);
		return text;
	}

	public static void clearText(By ele, String selector) {
		WebElement wEle = findObject(ele, selector);
		wEle.clear();
	}

	public static void selectByVisibleText(By ele, String selector, String text) {
		WebElement  wEle = findObject(ele, selector);
		try {
			Select sel = new Select(wEle);
			sel.selectByVisibleText(text);
			System.out.println("INFO: " +selector+ " as "+text+" is selected");
		} catch (Exception e) {
			System.out.println("ERROR: " +selector+ " as "+text+"  is not selected");
		}
	}

	public static boolean isElementPresent(By ele, String selector) {
		boolean status = false;
		WebElement wEle = findObject(ele, selector);
		try {
			status = wEle.isDisplayed();
			System.out.println("INFO: Element " + selector + " is present on screen ");
			return status;
		} catch (Exception e) 
		{
			System.out.println("ERROR: Element " + selector + " is not present on screen ");
		}
		return status;
	}

	public static boolean isElementPresent1(By ele, String selector) {
		boolean status = false;
		WebElement wEle = findObject(ele, selector);
		try {
			status = wEle.isDisplayed();
			System.out.println("INFO: Element " + selector + " is present on screen ");
			return status;
		} catch (Exception e) 
		{
			//System.out.println("ERROR: Element " + selector + " is not present on screen ");
		}
		return status;
	}


	public static boolean isElementListPresent(By ele, String selector, int index) {
		boolean status = false;
		List<WebElement> wEle = findElements(ele, selector);
		try {
			status = wEle.get(index).isDisplayed();
			return status;
		} 
		catch (Exception e) {
			System.out.println("ERROR: Element " + selector + " is not present on screen ");
		}
		return status;
	}

	public static boolean isElementEnabled(By ele, String selector) {
		boolean status = false;
		WebElement wEle = findObject(ele, selector);
		try {
			status = wEle.isEnabled();
			return status;
		} catch (Exception e) {
			System.out.println("ERROR: Element " + selector + " is not Enabled on screen ");
		}
		return status;
	}

	public static boolean isElementDisabled(By ele, String selector) {
		boolean status ;
		WebElement wEle = findObject(ele, selector);
		status = wEle.isEnabled();
		if(status==false){
			System.out.println("INFO: Element " + selector + " is disabled on screen ");
		}  
		else{
			System.out.println("ERROR: Element " + selector + " is not Enabled on screen ");
		}
		return status;
	}

	public static boolean isElementSelected(By ele, String selector) {
		boolean status = false;
		WebElement wEle = findObject(ele, selector);
		try {
			status = wEle.isSelected();
			return status;
		} catch (Exception e) {
			System.out.println("ERROR: Element " + selector + " is not Selected on screen ");
		}
		return status;
	}

	public static void scrollPage(By ele, String selector) {
		WebElement wEle = findObject(ele, selector);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wEle);
	}

	public static void clickElement(By ele, String selector) {
		WebElement wEle = findObject(ele, selector);
		try {
			Actions act = new Actions(driver);
			act.click(wEle).build().perform();

			System.out.println("INFO: Clicking on " + selector);
		} catch (Exception e) {
			System.out.println("ERROR: Element " + selector + " is unclickable ");
		}

	}

	public static void doubleClick(By ele, String selector) {
		WebElement wEle = findObject(ele, selector);
		try {
			Actions act = new Actions(driver);
			act.doubleClick(wEle).build().perform();
			System.out.println("INFO: Double clicked on " +selector);
		} catch (Exception e) {
			System.out.println("ERROR: Double click on " +selector+ " is failed");
		}
	}

	public static void rightClick(By ele, String selector) {
		WebElement wEle = findObject(ele, selector);
		try {
			Actions act = new Actions(driver);
			act.contextClick(wEle).build().perform();
			System.out.println("INFO: Right click on " +selector);
		} catch (Exception e) {
			System.out.println("ERROR: Right click on " +selector+ " is failed");
		}
	}
	public static void scrollDown(By ele, String selector) {
		WebElement wEle = findObject(ele, selector);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true)", wEle);
	}
	public static void clickElement1(By ele, String selector) {
		WebElement wEle = findObject(ele, selector);
		try {
			Actions act = new Actions(driver);
			act.click(wEle).build().perform();

			//System.out.println("INFO: Clicking on " + selector);
		} catch (Exception e) {
			System.out.println("ERROR: Element " + selector + " is unclickable ");
		}

	}

	public static synchronized String takeScreenshot(String methodName) {
        DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_HH_mm_ss_SSS");
        Date date = new Date();
        String dateName = dateFormat.format(date);
        String filePathExtent = "ScreenShot" + "Web"+ methodName + "_" + dateName + ".png";
        String filePath = ExtentManager.getReportBaseDirectory() + filePathExtent;
        String encodedBase64=null;
        try {
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileInputStream fileInputStreamReader ;
            fileInputStreamReader = new FileInputStream(screenshotFile);
            byte[] bytes = new byte[(int) screenshotFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = Base64.encodeBase64String(bytes);
            FileUtils.copyFile(screenshotFile, new File(filePath));
        }catch (IOException e){
            e.getStackTrace();
            Reporter.log("Failed To Take screenshot " + e, true);
        }
        return encodedBase64;
    }

	public static void clickElementFromList(By ele, String selector, int index) {
		List<WebElement> wEle = findElements(ele, selector);
		try {
			Actions act = new Actions(driver);
			act.click(wEle.get(index)).build().perform();
			System.out.println("INFO: Clicking on " + selector);
		} catch (Exception e) {
			System.out.println("ERROR: Element " + selector + " is unclickable ");
		}
	}

	public static void FileUpload(By ele, String selector, String Path) {
		try {
			Robot robot = new Robot();
			clickElement(ele, selector);
			robot.setAutoDelay(2000);

			StringSelection stringSelection = new StringSelection(Path);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

			robot.setAutoDelay(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);

			robot.setAutoDelay(1000);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			System.out.println("File Uploaded sucessfully");

		} catch (Exception e) {

			System.out.println("File failed to upload"  + selector);
		}
	}

	public void closeSession() {
		driver.quit();
	}

	public static void waitForPageLoad(int iTimeUnit) {
		driver.manage().timeouts().implicitlyWait(iTimeUnit, TimeUnit.SECONDS);
	}

	public static WebElement waitToLoadPage(By ele, String selectorName) {
		WebElement rClientElement = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			rClientElement = wait.until(ExpectedConditions.presenceOfElementLocated(ele));
		} catch (Exception e) {
			System.out.println("ERROR: Element " + selectorName + "  not found");
		}
		return rClientElement;
	}

	public static String getCurrentPageTitle() {
		return driver.getTitle();
	}

	public static String getCurrentPageURL() {
		return driver.getCurrentUrl();
	}

	public static void sleepApplication(int iTimeUnit) {
		try {
			System.out.println("INFO: Wait for " + iTimeUnit + " millis to load application completly");
			Thread.sleep(iTimeUnit);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String captureScreenshot(String name) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./ScreenShot/" + name + ".png");
		Files.copy(source, dest);
		return name;
	}

	public static void uploadFile(String filePath) {
		driver.findElement(By.id("js-file-upload-design")).sendKeys(filePath);
	}

	public static void HoverandClikcActionsElement(By ele, String selector) {
		WebElement wEle = findObject(ele, selector);
		try {
			Actions action = new Actions(driver);
			action.moveToElement(wEle).build().perform();
		} catch (Exception e) {
			System.out.println("ERROR: Element " + selector + " not Hover ");
		}
	}

	public static void DragAndDropFiles(By ele1,String sourceSelect,By ele2,String Destination){
		WebElement source= findObject(ele1,sourceSelect);
		WebElement target= findObject(ele2,Destination);
		try{
			Actions builder = new Actions(driver);
			Action dragAndDrop = builder.clickAndHold(source)
					.moveToElement(target)
					.release(target)
					.build();
			sleepApplication(2000);
			dragAndDrop.perform();
			System.out.println("Source :"+source.getText()+"Target"+target.getText());

		}catch(Exception e){
			System.out.println("drag and drop action not perform");
		}
	}

	public static void getDefaultValueDropdown(By ele, String selectorName) {
		WebElement wEle = findObject(ele, selectorName);
		Select sel = new Select(wEle);
		String dw = sel.getFirstSelectedOption().getText();
		System.out.println("INFO: Set Text " + dw);
	}

	public static void uploadFileRobotClass(String filePath) throws AWTException {
		StringSelection ss = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		// imitate mouse events like ENTER, CTRL+C, CTRL+V
		Robot robot = new Robot();
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(250);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(250);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public static String getRandomString(int length) {
		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public static String getRandomStringsmallchr(int length) {
		final String AB = "abcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public static String getRandomIntiger(int length) {
		final String AB = "0123456789";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public static String getPhantomPath() {
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec("which phantomjs");
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}

	public static String getOS() {
		return System.getProperty("os.name");
	}

	public static String todayDate() {
		String date;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		date = dtf.format(now);
		return date;
	}
}

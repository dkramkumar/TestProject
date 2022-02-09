package com.demoQA.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import com.demoQA.BasePage.BasePage;

public class ElementsPage extends BasePage{
	
	public static final By byHomeExpand = findBy("(//button[@title='Toggle'])[1]");
	public static final By byDesktopExpand = findBy("(//button[@title='Toggle'])[2]");
	public static final By byDocumentsExpand = findBy("(//button[@title='Toggle'])[3]");
	public static final By byDownloadsExpand = findBy("(//button[@title='Toggle'])[4]");
	public static final By byCommandsCheckBox = findBy("(//span[@class='rct-checkbox'])[4]");
	public static final By byGeneralCheckBox = findBy("(//span[@class='rct-checkbox'])[11]");
	public static final By byselectedText = findBy("//span[@class='text-success']");
	public static final By byExpandOffice = findBy("(//span[@class='rct-text'])[7]/button");
	public static final By byExcelFile = findBy("(//span[@class='rct-checkbox'])[14]");
	public static final By byAddButton = findBy("//button[@id='addNewRecordButton']"); 
	public static final By byLinksTab = findBy("(//li[@id='item-5'])[1]");
	public static final By byLinkStatusCode = findBy("//p[@id='linkResponse']/b[1]");
	public static final By byLinkStatusText = findBy("//p[@id='linkResponse']/b[2]");
	public static final By byValidLink = findBy("//div//p[contains(text(), 'Valid Link')]/following::a[1]");
	public static final By byBrokenLink = findBy("//div//p[contains(text(), 'Broken Link')]/following::a[1]");
	public static final By byBrokenLinkStatusCode = findBy("//div[@class='example']/p");
	public static final By byChooseFileButton = findBy("//label[@for='uploadFile']/following::input");
	public static final By byUploadedFilePath = findBy("//p[@id='uploadedFilePath']");
	public static final By byVisibleAfterFiveSecondsButton = findBy("//button[contains(text(), 'Visible After 5 Seconds')]");
	public static final By byDownloadButton = findBy("//div//a[@id='downloadButton']");
	public static String currentUrl;
	public static String ValidLinkUrl;
	
	public static void clickOnElementsMain(String a) throws InterruptedException {
		driver.findElement(By.xpath("//div[@class = 'card mt-4 top-card']["+a+"]")).click();
		System.out.println("INFO: User clicks on " +a+ " st category card" );
	}
	
	public static void clickOnElementsMainMenu(String name) throws InterruptedException {
		switch (name) {
		
		case "ELEMENTS":
			driver.findElement(By.xpath("//div[@class = 'card mt-4 top-card'][1]")).click();
			System.out.println("INFO: User clicks on ELEMENTS card" );
			Thread.sleep(2000);
			break;
			
		case "FORMS":
			driver.findElement(By.xpath("//div[@class = 'card mt-4 top-card'][2]")).click();
			System.out.println("INFO: User clicks on FORMS card" );
			Thread.sleep(2000);
			break;
			
		case "ALERTSFRAMEWINDOWS":
			driver.findElement(By.xpath("//div[@class = 'card mt-4 top-card'][3]")).click();
			System.out.println("INFO: User clicks on ALERTS FRAME and WINDOWS card" );
			Thread.sleep(2000);
			break;
			
		case "WIGETS":
			driver.findElement(By.xpath("//div[@class = 'card mt-4 top-card'][4]")).click();
			System.out.println("INFO: User clicks on WIGETS card" );
			Thread.sleep(2000);
			
		case "INTERACTIONS":
			driver.findElement(By.xpath("//div[@class = 'card mt-4 top-card'][5]")).click();
			System.out.println("INFO: User clicks on INTERACTIONS card" );
			Thread.sleep(2000);
			break;
		}
	}
	
	public static String getHeaderText() {
		String text = driver.findElement(By.cssSelector("div[class=main-header]")).getText();
		System.out.println("INFO: Header Text is : " +text);
		return text;
	}
	
	public static void navigateBack() {
		driver.navigate().back();
	}
	
	public static void clickOnTextBoxTab(String a) {
		driver.findElement(By.xpath("//span[contains(text(), '"+a+"')]")).click();
		System.out.println("INFO: User clicks on " +a+ " tab" );
	}
	
	public static void setFullNameAndEmail(String data, String text) {
		driver.findElement(By.xpath("//input[@id='"+data+"']")).sendKeys(text);
		System.out.println("INFO: User enters " +data+ " as " +text);
	}
	
	public static void setCurrentAndPermanentAddress(String data, String text) {
		driver.findElement(By.xpath("//textarea[@id='"+data+"']")).sendKeys(text);
		System.out.println("INFO: User enters " +data+ " as " +text);
	}
	
	public static void clickOnSubmitButton() {
		driver.findElement(By.xpath("//button[@id='submit']")).click();
		System.out.println("INFO: User clicks on submit button" );
	}
	
	public static String getOutPutMsg() {
		String text = driver.findElement(By.xpath("//div[@id='output']")).getText();
		System.out.println("INFO: " +text);
		return text;
	}
	
	//********************* check Box ****************************************//
	
	public static void clickOnHomeToExpand() {
		clickElement(byHomeExpand, "Home Expand");
	}
	
	public static void clickOnDesktopToExpand() {
		clickElement(byDesktopExpand, "Desktop Expand");
	}
	
	public static void clickOnCommandsCheckBox() {
		clickElement(byCommandsCheckBox, "Commands CheckBox");
		
	}
	
	public static void clickOnDocumentsToExpand() {
		clickElement(byDocumentsExpand, "Documents Expand");
	}
	
	public static void clickOnOfficeToExpand() {
		clickElement(byExpandOffice, "Office Expand");
	}
	
	public static void clickOnGeneralCheckBox() {
		clickElement(byGeneralCheckBox, "General Check Box");
	}
	
	public static String getSelectedText() {
		return getText(byselectedText, "selected Text");
	}
	
	public static void clickOnDownloadExpand() {
		clickElement(byDownloadsExpand, "Downloads Expand");
	}
	
	public static void clickOnExcelFile() {
		clickElement(byExcelFile, "Excel file");
	}
	
	//********************* Radio Button ************************//
	
	public static void clickRadioButton(String data) {
		By byRadioButton = findBy("//input[@id='"+data+"']");
		clickElement(byRadioButton, data);
	}
	
	public static boolean isNoRadioButtonEnabled() {
		boolean val = driver.findElement(By.xpath("//input[@id='noRadio']")).isEnabled();
		return val;
	}
	
	//********************** Web Tables **************************//
	
	public static void clickOnAddButton() {
		clickElement(byAddButton, "Add Button");
	}
	
	public static void getAddedUserName(String name) {
		
		List<WebElement> li = driver.findElements(By.xpath("//div[@class='rt-td']"));
		Iterator<WebElement> itr = li.iterator();
		while(itr.hasNext()) {
			String uName = itr.next().getText();
			if(uName.equals(name)) {
				System.out.println("INFO: User "+name+ " is successfully added in the web table");
			}
		}
	}
	
	//********************** Buttons **************************//
	
	public static void clickOnDoubleClickMeButton() {
		By byDoubleClickMeButton = findBy("//button[@id='doubleClickBtn']");
		doubleClick(byDoubleClickMeButton, "Double Click Me Button");
	}
	
	public static void clickOnRightClickMeButton() {
		By byRightClickMeButton = findBy("//button[@id='rightClickBtn']");
		rightClick(byRightClickMeButton, "Right Click Me Button");
	}
	
	public static void clickOnClickMeButton() {
		By byClickMeButton = findBy("(//div[@class='mt-4']/button)[2]");
		clickElement(byClickMeButton, "Click Me Button");
	}
	
	public static void getButtonsClickedMessage(String data) {
		By byButtonsClickMessage = findBy("//p[@id='"+data+"']");
		getText(byButtonsClickMessage, "Button Clicked Message");
	}
	
	//********************** Links ********************************//
	
	public static void clickOnLinksTab() {
		clickElement(byLinksTab, "Links Tab");
	}
	
	public static void clickOnLinks(String data) {
		By byLinks = findBy("//p//a[@id='"+data+"']");
		clickElement(byLinks, data);
	}
	
	public static void isNewHomeLinkWindowOpened() {
		String cWindow = driver.getWindowHandle();
		Set<String> nWindow = driver.getWindowHandles();
		Iterator<String> itr = nWindow.iterator();
		while(itr.hasNext()) {
			String newWindow = itr.next();
			if(!newWindow.equalsIgnoreCase(cWindow)) {
				String title = driver.switchTo().window(newWindow).getTitle();
				System.out.println("INFO: Title of the page is : " +title);
				driver.switchTo().window(cWindow);
			}
		}
	}
	
	public static String getStatusCode() {
		return getText(byLinkStatusCode, "Link has responded with staus "); 
	}
	
	public static String getStatusText() {
		return getText(byLinkStatusText, "and status text ");
	}
	
	public static void scrollToLinkTab() {
		scrollDown(byLinksTab, "Links Tab");
	}
	
	//********************** Broken Links ********************************//
	
	public static void isImageBroken() throws ClientProtocolException, IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement imageFile = driver.findElement(By.xpath("//div//p[contains(text(), 'Broken image')]/following::img[1]"));
		if (imageFile.getAttribute("naturalWidth").equals("0"))
        {
            System.out.println("INFO: " +imageFile.getAttribute("outerHTML") + " is broken.");
        }else {
        	System.out.println("INFO: " +imageFile.getAttribute("outerHTML") + " is not broken.");
        }
	}
	
	public static void isValidImagePresent() {
		WebElement imageFile = driver.findElement(By.xpath("//div//p[contains(text(), 'Valid image')]/following::img[1]"));
		if (imageFile.getAttribute("naturalWidth").equals("0"))
        {
            System.out.println("INFO: " +imageFile.getAttribute("outerHTML") + " is broken.");
        }else {
        	System.out.println("INFO: " +imageFile.getAttribute("outerHTML") + " is not broken.");
        }
	}
	
	public static void clickOnValidLink() {
		currentUrl = driver.getCurrentUrl();
		System.out.println("INFO: " +currentUrl);
		clickElement(byValidLink, "Valid Link");		
	}
	
	public static String isValidLinkOpened() {
		return ValidLinkUrl = driver.getCurrentUrl();
	}
	
	public static void clickOnBrokenLink() {
		clickElement(byBrokenLink, "Broken Link");
	}
	
	public static void getBrokenLinkStausCode() {
		getText(byBrokenLinkStatusCode, "Broken Link Status Code");
	}
	
	//********************** Upload and Download ********************************//
	
	public static void clickOnChooseFile() {
		clickElement(byChooseFileButton, "Choose File Button");
	}
	
	public static void setPathForImageToUpload() throws AWTException {
		StringSelection ss = new StringSelection("D:\\WS Auto Testing\\DemoQAApp\\Files\\Selenium1.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("INFO: Image path is set to upload");
	}
	
	public static void setPathForImageToDownload() throws AWTException {
		StringSelection ss = new StringSelection("D:\\WS Auto Testing\\DemoQAApp\\Files\\image" +getRandomString(4)+ ".jpeg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("INFO: Image path is set to download");
	}
	
	public static String getUploadedFilePath() {
		return getText(byUploadedFilePath, "Uploaded File Path");
	}
	
	public static void clickOnDownloadButton() {
		clickElement(byDownloadButton, "Download Button");
	}
	
	//********************** Dynamic Properties ********************************//
	
	public static void isWillEnableFiveSecondsButton() throws InterruptedException {
		WebElement wEle = driver.findElement(By.xpath("//button[contains(text(), 'Will enable 5 seconds')]"));
		Thread.sleep(8000);
		System.out.println("INFO: Will Enable Five Seconds Button is enabled : " +wEle.isEnabled());
	}
	
	public static void isColorChanged() throws InterruptedException {
		WebElement wEle = driver.findElement(By.xpath("//button[contains(text(), 'Color Change')]"));
		String wC = wEle.getCssValue("color");
		String wHex = Color.fromString(wC).asHex();
		System.out.println("INFO: Hexa code for white color is :" +wHex);
		
		Thread.sleep(6000);
		WebElement wEle1 = driver.findElement(By.xpath("//button[contains(text(), 'Color Change')]"));
		String wC1 = wEle1.getCssValue("color");
		String wHex1 = Color.fromString(wC1).asHex();
		System.out.println("INFO: Hexa code for color bright red is :" +wHex1);
	}
	
	public static void isVisibleAfterFiveSecButtonIsPresent() {
		isElementPresent(byVisibleAfterFiveSecondsButton, "Visible After Five Seconds Button");
	}
}


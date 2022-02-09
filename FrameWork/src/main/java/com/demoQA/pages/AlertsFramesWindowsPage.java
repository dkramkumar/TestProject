package com.demoQA.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.demoQA.BasePage.BasePage;

public class AlertsFramesWindowsPage extends BasePage{
	
	public static final By byAlertsButton = findBy("//button[@id='alertButton']");
	public static final By byConfirmResult = findBy("//span[@id='confirmResult']");
	public static final By byPromtResult = findBy("//span[@id='promptResult']");
	public static final By byFramesTab = findBy("//li[@id='item-2']/span[contains(text(), 'Frames')]");
	public static final By byParentFrame = findBy("//iframe[@id='frame1']");
	public static final By byChildFrame = findBy("//iframe[@id='frame2']");
	public static final By byFrameText = findBy("sampleHeading");
	public static final By byParentFrameText = findBy("//body[contains(text(), 'Parent frame')]");
	public static final By byChildFrameText = findBy("//p[contains(text(), 'Child Iframe')]");
	public static final By bySmallModalHeader = findBy("//div[@id='example-modal-sizes-title-sm']");
	public static final By byLargeModalHeader = findBy("//div[@id='example-modal-sizes-title-lg']");
	public static final By bySmallModalCloseButton = findBy("//button[@id='closeSmallModal']");
	public static final By byLargeModalCloseButton = findBy("//button[@id='closeLargeModal']");
	

	public static void clickOnNewButtons(String data) {
		By byNewButtons = findBy("//button[@id='"+data+"']");
		clickElement(byNewButtons, data);
	}

	public static void getTitleOfNewTab() throws InterruptedException {
		Set<String> handles = driver.getWindowHandles();
		List<String> li = new ArrayList<>(handles);
		String pWindow = li.get(0);
		String cWindow = li.get(1);
		driver.switchTo().window(cWindow);
		Thread.sleep(1000);
		String msg = driver.findElement(By.xpath("//h1[@id='sampleHeading']")).getText();
		System.out.println("INFO: Opened New Tab and title is: " +msg);
		driver.close();
		driver.switchTo().window(pWindow);
	}

	public static void getNewWidow() throws InterruptedException {
		String firstWindow = driver.getWindowHandle();
		Set<String> secondWindow = driver.getWindowHandles();
		Iterator<String> cWindow = secondWindow.iterator();
		while(cWindow.hasNext()) {
			String child = cWindow.next();
			if(!firstWindow.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				String text = driver.findElement(By.xpath("//h1[@id='sampleHeading']")).getText();
				System.out.println("INFO: New Window opened and text is : " +text);
				driver.close();
			}
		}
		driver.switchTo().window(firstWindow);
		Thread.sleep(1000);
	}

	public static void getNewWidowMessage() throws InterruptedException {
		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();
		Iterator<String> cWindow = allWindowHandles.iterator();
		while(cWindow.hasNext()) {
			String child = cWindow.next();
			if(!mainWindowHandle.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				String text = driver.findElement(By.xpath("//html//body")).getCssValue("value");
				System.out.println("INFO: New window message is : " +text);
				driver.close();
			}
		}
		driver.switchTo().window(mainWindowHandle);
		Thread.sleep(1000);
	}

	public static void clickOnAlertsButton(String data) {
		By byAlertsButton = findBy("//button[@id='"+data+"']");
		clickElement(byAlertsButton, data);
	}

	public static void clickOnTimerAlertsButton() {
		WebElement wEle = driver.findElement(By.xpath("//button[@id='timerAlertButton']"));
		Actions act = new Actions(driver);
		act.click(wEle).pause(8000).build().perform();
		System.out.println("INFO: Timer alert is clicked and waited for few seconds");
	}

	public static void isAlertWindowPresent() throws InterruptedException {
		String alert = driver.switchTo().alert().getText();
		System.out.println("INFO: Alert window text is : " +alert);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
	}

	public static void istimerAlertPresent() throws InterruptedException {
		String tAlert = driver.switchTo().alert().getText();
		System.out.println("INFO: Timer Alert window text is : " +tAlert);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
	}

	public static void isConfirmAlertPresent() throws InterruptedException {
		String cAlert = driver.switchTo().alert().getText();
		System.out.println("INFO: Confirm Alert window text is : " +cAlert);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
	}

	public static String getConfirmResult() {
		return getText(byConfirmResult, "Confirm Result");
	}

	public static void isPromtAlertPresent() throws InterruptedException {
		driver.switchTo().alert().sendKeys("RamKumar");
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
	}

	public static String getPromtResult() {
		return getText(byPromtResult, "Promt Result");
	}

	//************************ Frames ********************************//

	public static void clickONFramesTab() {
		clickElement(byFramesTab, "Frames Tab");
	}

	public static void switchToParentFrame() {
		driver.switchTo().frame("frame1");
		getText(byFrameText, "Parent Header");
		driver.switchTo().defaultContent();
	}

	public static void switchToChildFrame() {
		driver.switchTo().frame("frame2");
		getText(byFrameText, "Child Header");
		driver.switchTo().defaultContent();
	}

	//************************ Nested Frames ********************************//

	public static void switchParentToChildFrame() throws InterruptedException {
		driver.switchTo().frame("frame1");
		getText(byParentFrameText, "Parent Text");
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		driver.switchTo().frame("frame1").switchTo().frame(0);
		getText(byChildFrameText, "Child Text");
		driver.switchTo().defaultContent();
	}

	//************************ Modal dialogs ********************************//

	public static void clickOnModalButtons(String data) throws InterruptedException {
		By byModalButtons = findBy("//button[@id='"+data+"']");
		clickElement(byModalButtons, data);
		Thread.sleep(1000);
	}

	public static void isSmallModalHeaderIsPresent() {
		getText(bySmallModalHeader, "Small Modal Header");
	}

	public static void isLargeModalHeaderIsPresent() {
		getText(byLargeModalHeader, "Large Modal Header");
	}
	
	public static void clickOnCloseSmallModalButton() throws InterruptedException {
		clickElement(bySmallModalCloseButton, "Close Small Modal Button");
		Thread.sleep(1000);
	}
	
	public static void clickOnCloseLargeModalButton() {
		clickElement(byLargeModalCloseButton, "Large Small Modal Button");
	}
}

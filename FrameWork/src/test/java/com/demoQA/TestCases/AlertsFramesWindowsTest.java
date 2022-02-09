package com.demoQA.TestCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.demoQA.pages.AlertsFramesWindowsPage;
import com.demoQA.pages.ElementsPage;
import com.demoQA.utility.ExtentTestManager;
import com.demoQA.BasePage.BasePage;

public class AlertsFramesWindowsTest extends BasePage{
	
	@BeforeMethod (groups = "Smoke")
	@Parameters ("browser")
	public void url(String browser) throws IOException, InterruptedException {
		InitialConfig(browser);
		openUrl();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("INFO: The title of the page is : " +driver.getTitle());	
		driver.findElement(By.xpath("//a[@id='close-fixedban']")).click();
		Thread.sleep(1000);
	}
	
	@AfterMethod (groups = "Smoke")
	public void quit() {
		driver.quit();
	}
	
	@Test (groups = "Smoke")
	public void browserWindows() throws InterruptedException {
		ElementsPage.clickOnElementsMainMenu("ALERTSFRAMEWINDOWS");
		ExtentTestManager.reporterLog("User clicks on alerts frame & Windows");
		Thread.sleep(2000);
		ElementsPage.clickOnTextBoxTab("Browser Windows");
		ExtentTestManager.reporterLog("User clicks on Browser Windows");
		Thread.sleep(1000);
		AlertsFramesWindowsPage.clickOnNewButtons("tabButton");
		ExtentTestManager.reporterLog("User clicks on New Button");
		Thread.sleep(2000);
		AlertsFramesWindowsPage.getTitleOfNewTab();
		ExtentTestManager.reporterLog("Title of new tab");
		AlertsFramesWindowsPage.clickOnNewButtons("windowButton");
		ExtentTestManager.reporterLog("User clicks on window button");
		Thread.sleep(2000);
		AlertsFramesWindowsPage.getNewWidow();
		ExtentTestManager.reporterLog("New window is opened");
		
	}
	
	@Test (groups = "Smoke")
	public void alerts() throws InterruptedException {
		ElementsPage.clickOnElementsMainMenu("ALERTSFRAMEWINDOWS");
		ExtentTestManager.reporterLog("User clicks on alerts frame & Windows");
		Thread.sleep(2000);
		ElementsPage.clickOnTextBoxTab("Alerts");
		ExtentTestManager.reporterLog("User clicks on Alerts");
		Thread.sleep(1000);
		AlertsFramesWindowsPage.clickOnAlertsButton("alertButton");
		ExtentTestManager.reporterLog("User clicks on Alert button");
		Thread.sleep(1000);
		AlertsFramesWindowsPage.isAlertWindowPresent();
		ExtentTestManager.reporterLog("Alert window is displayed");
		Thread.sleep(1000);
		AlertsFramesWindowsPage.clickOnTimerAlertsButton();
		ExtentTestManager.reporterLog("User clicks on Timer alerts button");
		AlertsFramesWindowsPage.istimerAlertPresent();
		ExtentTestManager.reporterLog("Timer alert is dispalyed");
		AlertsFramesWindowsPage.clickOnAlertsButton("confirmButton");
		ExtentTestManager.reporterLog("User clicks on confirm button");
		Thread.sleep(1000);
		AlertsFramesWindowsPage.isConfirmAlertPresent();
		ExtentTestManager.reporterLog("Confirm alert is present");
		Thread.sleep(1000);
		AlertsFramesWindowsPage.getConfirmResult();
		ExtentTestManager.reporterLog("Confirm Result is dispalyed");
		AlertsFramesWindowsPage.clickOnAlertsButton("promtButton");
		ExtentTestManager.reporterLog("User clicks on promt button");
		Thread.sleep(1000);
		AlertsFramesWindowsPage.isPromtAlertPresent();
		ExtentTestManager.reporterLog("Promt alert is displayed");
		AlertsFramesWindowsPage.getPromtResult();
		ExtentTestManager.reporterLog("Promt result is : " +AlertsFramesWindowsPage.getPromtResult());
	}
}

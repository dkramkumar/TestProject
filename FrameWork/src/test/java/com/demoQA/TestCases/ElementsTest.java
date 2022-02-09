package com.demoQA.TestCases;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.demoQA.BasePage.BasePage;
import com.demoQA.pages.ElementsPage;
import com.demoQA.utility.ExtentTestManager;



public class ElementsTest extends BasePage{
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
	
	@Test(priority = 1, description = "Elements TextBox", groups = "Sanity")
	public void elementsTextBox() throws InterruptedException {
		
		ElementsPage.clickOnElementsMainMenu("ELEMENTS");
		ExtentTestManager.reporterLog("User clicks on Elements");
		Thread.sleep(2000);
		ElementsPage.clickOnTextBoxTab("Text Box");
		ExtentTestManager.reporterLog("User clicks on Text Box");
		Thread.sleep(1000);
		ElementsPage.getHeaderText();
		ExtentTestManager.reporterLog("Header message is dispalyed");
		Thread.sleep(1000);
		ElementsPage.setFullNameAndEmail("userName", "Ramkumar");
		ExtentTestManager.reporterLog("User enters Full Name");
		Thread.sleep(1000);
		ElementsPage.setFullNameAndEmail("userEmail", "ramkumardkr@gmail.com");
		ExtentTestManager.reporterLog("User enters Email Id");
		Thread.sleep(1000);
		ElementsPage.setCurrentAndPermanentAddress("currentAddress", "DNO: 7-17-345/1");
		ExtentTestManager.reporterLog("User enters current address");
		Thread.sleep(1000);
		ElementsPage.setCurrentAndPermanentAddress("permanentAddress", "Mallikarjuna peta 4/1 Lane");
		ExtentTestManager.reporterLog("User enters permanent address");
		Thread.sleep(1000);
		ElementsPage.clickOnSubmitButton();
		ExtentTestManager.reporterLog("User clicks on submit button");
		Thread.sleep(2000);
		ElementsPage.getOutPutMsg();
		ExtentTestManager.reporterLog("OutPut Message is dispalyed");
		Thread.sleep(1000);
	}
	
	@Test(priority = 2, description = "Elements CheckBox", groups = "Smoke")
	public void elementsCheckBox() throws InterruptedException {
		ElementsPage.clickOnElementsMainMenu("ELEMENTS");
		ExtentTestManager.reporterLog("User clicks on Elements");
		Thread.sleep(2000);
		ElementsPage.clickOnTextBoxTab("Check Box");
		ExtentTestManager.reporterLog("User clicks on Check Box");
		Thread.sleep(1000);
		ElementsPage.getHeaderText();
		ExtentTestManager.reporterLog("Header text is : " +ElementsPage.getHeaderText());
		Thread.sleep(1000);
		ElementsPage.clickOnHomeToExpand();
		ExtentTestManager.reporterLog("User clicks on Home to expand");
		Thread.sleep(1000);
		ElementsPage.clickOnDesktopToExpand();
		ExtentTestManager.reporterLog("User clicks on Desktop to expand");
		Thread.sleep(1000);
		ElementsPage.clickOnCommandsCheckBox();
		ExtentTestManager.reporterLog("User clicks on Commands Check Box");
		Thread.sleep(1000);
		ElementsPage.clickOnDocumentsToExpand();
		ExtentTestManager.reporterLog("User clicks on Documents to expand");
		Thread.sleep(1000);
		ElementsPage.clickOnOfficeToExpand();
		ExtentTestManager.reporterLog("User clicks on Office to expand");
		Thread.sleep(1000);
		ElementsPage.clickOnGeneralCheckBox();
		ExtentTestManager.reporterLog("User clicks on General Check Box");
		Thread.sleep(1000);
		ElementsPage.clickOnDownloadExpand();
		Thread.sleep(1000);
		ElementsPage.clickOnExcelFile();
		ExtentTestManager.reporterLog("User clicks on Excel File");
		Thread.sleep(1000);
		String text = ElementsPage.getSelectedText();
		ExtentTestManager.reporterLog("Selected text is : " +text);
		assertEquals(text, "commands");
	}
}

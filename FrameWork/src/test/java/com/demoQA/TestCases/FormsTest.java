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
import com.demoQA.pages.FormsPage;
import com.demoQA.utility.ExtentTestManager;
import com.demoQA.pages.ElementsPage;

public class FormsTest extends BasePage{
	
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
	public void setFormDetails() throws InterruptedException, Exception {
		FormsPage.clickOnElementsMainMenu("FORMS");
		ExtentTestManager.reporterLog("User clicks on Forms");
		FormsPage.clickOnTextBoxTab("Practice Form");
		ExtentTestManager.reporterLog("User clicks on practice form");
		Thread.sleep(1000);
		ElementsPage.setFullNameAndEmail("firstName", "Ram");
		ExtentTestManager.reporterLog("User enters first name");
		Thread.sleep(1000);
		ElementsPage.setFullNameAndEmail("lastName", "kumar");
		ExtentTestManager.reporterLog("User enters last name");
		Thread.sleep(1000);
		ElementsPage.setFullNameAndEmail("userEmail", "ramkumardkr@gmail.com");
		ExtentTestManager.reporterLog("User enters email id");
		Thread.sleep(1000);
		ElementsPage.clickRadioButton("gender-radio-1");
		ExtentTestManager.reporterLog("User clicks on male gender");
		Thread.sleep(1000);
		ElementsPage.setFullNameAndEmail("userNumber", "7981586060");
		ExtentTestManager.reporterLog("User enters mobile number");
		Thread.sleep(1000);
		ElementsPage.clickRadioButton("dateOfBirthInput");
		ExtentTestManager.reporterLog("User selects date of birth");
		Thread.sleep(1000);
		FormsPage.selectMonthAndYear("June", "2010");
		FormsPage.clickOnDate();
		Thread.sleep(1000);
		FormsPage.setSubjects("Maths", "English");
		ExtentTestManager.reporterLog("User enters subjects maths and english");
		Thread.sleep(1000);
		FormsPage.clickONHobbies();
		ExtentTestManager.reporterLog("User clicks on hobbies");
		Thread.sleep(1000);
		FormsPage.scrollToSubmitButton();
		Thread.sleep(1000);
		FormsPage.selectPicture();
		ExtentTestManager.reporterLog("User selects the picture");
		ElementsPage.setCurrentAndPermanentAddress("currentAddress", "DNO: 7-17-345/1");
		ExtentTestManager.reporterLog("User enters the current address");
		Thread.sleep(1000);
		FormsPage.selectState("Uttar Pradesh");
		ExtentTestManager.reporterLog("User selects state as utter pradesh");
		FormsPage.selectCity("Lucknow");
		ExtentTestManager.reporterLog("User selects city as lucknow");
		ElementsPage.clickOnSubmitButton();
		ExtentTestManager.reporterLog("User clicks on submit button");
		Thread.sleep(2000);
		assertEquals( FormsPage.getRegSuccessMsg(), "Thanks for submitting the form");
		ExtentTestManager.reporterLog("Thanks for submitting the form");
	}
}

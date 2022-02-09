package com.demoQA.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import com.demoQA.BasePage.BasePage;

public class FormsPage extends BasePage{
	
	public static final By byMonthDropDown = findBy("//select[@class='react-datepicker__month-select']");
	public static final By byYearDropDown = findBy("//select[@class='react-datepicker__year-select']");
	public static final By byDate = findBy("//div[@class='react-datepicker__month']/div[3]/div[3]");
	public static final By bySubject = findBy("//div[@id='subjectsContainer']");
	public static final By byHobbies = findBy("//input[@id='hobbies-checkbox-1']");
	public static final By byState = findBy("//div[@id='state']");
	public static final By byStateName = findBy("//div[@id='state']/div/div/div[contains(text(), 'Uttar Pradesh')]");
	public static final By byCity = findBy("//div[@id='city']");
	public static final By byCityName = findBy("//div[@id='city']/div/div/div[contains(text(), 'Lucknow')]");
	public static final By byChooseFile = findBy("//label[@for='uploadPicture']/following::input[1]");
	public static final By byRegistrationSuccessMsg = findBy("//div[@id='example-modal-sizes-title-lg']");
	
	public static void selectMonthAndYear(String string, String string2) throws InterruptedException {
		selectByVisibleText(byMonthDropDown, "Month", string);
		Thread.sleep(1000);
		selectByVisibleText(byYearDropDown, "Year", string2);
		Thread.sleep(1000);
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
	
	public static void clickOnTextBoxTab(String a) {
		driver.findElement(By.xpath("//span[contains(text(), '"+a+"')]")).click();
		System.out.println("INFO: User clicks on " +a+ " tab" );
	}
	
	public static void clickOnDate() throws InterruptedException {
		clickElement(byDate, "Date");
		Thread.sleep(1000);
		String date = driver.findElement(By.xpath("//input[@id='dateOfBirthInput']")).getAttribute("value");
		System.out.println("INFO: Selected date is : " +date);
	}
	
	public static void setSubjects(String string, String string2) throws AWTException, InterruptedException {
		clickElement(bySubject, "Subject");
		setText(bySubject, "Subject", string);
		Robot robot = new Robot();
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(1000);
		
		setText(bySubject, "Subject", string2);
		Robot robot1 = new Robot();
		robot1.delay(250);
		robot1.keyPress(KeyEvent.VK_TAB);
		robot1.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(1000);
	}
	
	public static void clickONHobbies() {
		clickElement(byHobbies, "Sports");
		isElementSelected(byHobbies, "sports");
	}
	
	public static void selectPicture() throws AWTException, InterruptedException {
		clickElement(byChooseFile, "Choose File");
		Thread.sleep(1000);
		StringSelection ss = new StringSelection("D:\\WS Auto Testing\\DemoQAApp\\Files\\Selenium1.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public static void selectState(String sName) throws InterruptedException {
		clickElement(byState, "State Dropdown");
		Thread.sleep(1000);
		By byStateName = findBy("//div[@id='state']/div/div/div[contains(text(), '"+sName+"')]");
		clickElement(byStateName, sName);
		Thread.sleep(1000);
		String val = driver.findElement(By.xpath("(//div[@class=' css-1uccc91-singleValue'])[1]")).getText();
		System.out.println("INFO: Selected state is : " +val);
		
	}
	
	public static void selectCity(String cName) throws InterruptedException {
		clickElement(byCity, "City Dropdown");
		Thread.sleep(1000);
		By byCityName = findBy("//div[@id='city']/div/div/div[contains(text(), '"+cName+"')]");
		clickElement(byCityName, cName);
		Thread.sleep(1000);
		String val = driver.findElement(By.xpath("(//div[@class=' css-1uccc91-singleValue'])[2]")).getText();
		System.out.println("INFO: Selected city is : " +val);
	}
	
	public static void scrollToSubmitButton() {
		By bySubmit = findBy("//button[@id='submit']");
		scrollDown(bySubmit, "Submit");
	}
	
	public static String getRegSuccessMsg() {
		return getText(byRegistrationSuccessMsg, "Registration Success Msg");
	}
}

package com.kone.app.pages.vbmobile;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.kone.app.pages.PhoneBasePage;
import com.kone.framework.context.TestContext;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Step;
import com.kone.framework.utility.Log;

public class LoginPage extends PhoneBasePage {
	
public static AndroidDriver<MobileElement> driver;
	
	public LoginPage() {
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	private By usernameInput = By.name("username");
	private By passwordInput = By.name("password");
	private By frontLineInput = By.xpath("(//*[starts-with(@id,'input_')])[last()]");
	private By searchFrontLineInput = By.xpath("//*[@aria-label='search']");
	private By signInButton = By.xpath("//md-icon[text()='done']");
	private By closeButton = By.xpath("//md-icon[text()='close']");
	private By releaseNote = By.xpath("//*[contains(text(), 'Release notes')]");
	private By downloading = By.xpath("//*[contains(text(), 'Downloading')]");
//	private By popUp_ReleaseNotesClose = By.xpath("//*[text()='close']");
/*  private By popUp_AppUpdateDownload = By.xpath("//*[text()='Download']");
	private By popUp_AppUpdateCancel = By.xpath("//*[text()='Cancel']");
	private By allPopUps = By.xpath("//*[contains(@aria-label,'Menu') or contains(text(), 'Release notes') or contains(text(), 'Downloading') or text()='Download']");*/
	
	@Step("Login vb-mobile with username {0} and fronline {2}")
	public SurveyManagerPage signIn(String email, String password, String frontline) {

		waitForElementPresent(usernameInput, 30);
		if(gettingMobileElement(usernameInput).getAttribute("class").contains("ng-not-empty")) {
			clearValue(usernameInput);
		}
		driver.findElement(usernameInput).sendKeys(email);
		waitForElementClickable(passwordInput, 30);
		if(gettingMobileElement(passwordInput).getAttribute("class").contains("ng-not-empty")) {
			clearValue(passwordInput);
		}
		driver.findElement(passwordInput).sendKeys(password);
		driver.findElement(frontLineInput).click();
		driver.findElement(searchFrontLineInput).sendKeys(frontline);
		By selectedFrontLine = By.xpath("//p[contains(text(), '" + frontline + "')]");
		waitForElementPresent(selectedFrontLine, 5);
		driver.findElement(selectedFrontLine).click();
		driver.findElement(signInButton).click();
		
		waitForElementPresent(downloading, 20);
		waitForElementNotPresent(downloading, 30);		
		waitForElementPresent(releaseNote, 5);
		driver.findElement(closeButton).click();
		Log.info("Click on element: " + closeButton);
		
/*		waitForElementPresent(popUp_AppUpdateDownload, 300);
		waitForElementPresent(popUp_AppUpdateDownload, 50);
		Log.info("Wait for element present: " + popUp_AppUpdateDownload);
		driver.findElement(popUp_AppUpdateCancel).click();
		Log.info("Click on element: " + popUp_AppUpdateCancel);*/
		
		SurveyManagerPage surveyManagerScreen = new SurveyManagerPage();
		Assert.assertTrue(surveyManagerScreen.
				isDisplayed(DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT), 
				"Failed to login");
		return surveyManagerScreen;
	}
	
	public boolean isDisplayed(long timeout) {
		return waitForElementPresent(usernameInput, timeout) != null;
	}

}

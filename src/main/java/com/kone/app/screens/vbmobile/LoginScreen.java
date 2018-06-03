package com.kone.app.screens.vbmobile;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.kone.app.screens.PhoneBaseScreen;
import com.kone.framework.context.TestContext;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class LoginScreen extends PhoneBaseScreen {
	
public static AndroidDriver<MobileElement> driver;
	
	public LoginScreen() {
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	private By usernameInput = By.name("username");
	private By passwordInput = By.name("password");
	private By frontLineInput = By.id("input_2");
	private By searchFrontLineInput = By.xpath("//*[@aria-label='search']");
	private By signInButton = By.xpath("//md-icon[text()='done']");
	private By closeButton = By.xpath("//md-icon[text()='close']");
	private By releaseNote = By.xpath("//*[contains(text(), 'Release notes')]");
	private By downloading = By.xpath("//*[contains(text(), 'Downloading')]");
	
	@Step("Login")
	public MainScreen signIn(String email, String password, String frontline) {

		waitForElementPresent(usernameInput, 30);
		driver.findElement(usernameInput).sendKeys(email);
		driver.findElement(passwordInput).sendKeys(password);
		driver.findElement(frontLineInput).click();
		driver.findElement(searchFrontLineInput).sendKeys(frontline);
		By selectedFrontLine = By.xpath("//p[contains(text(), '" + frontline + "')]");
		waitForElementPresent(selectedFrontLine, 5);
		driver.findElement(selectedFrontLine).click();
		driver.findElement(signInButton).click();
		
		waitForElementPresent(downloading, 10);
		waitForElementNotPresent(downloading, 30);		
		waitForElementPresent(releaseNote, 5);
		driver.findElement(closeButton).click();
		
		MainScreen mainScreen = new MainScreen();
		Assert.assertTrue(mainScreen.isDisplayed(), "Failed to login");
		return mainScreen;
	}
	
	public boolean isDisplayed() {
		return waitForElementPresent(usernameInput, 30) != null;
	}

}

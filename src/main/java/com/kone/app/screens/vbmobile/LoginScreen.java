package com.kone.app.screens.vbmobile;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.kone.app.screens.BaseScreen;
import com.kone.framework.context.TestContext;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import ru.yandex.qatools.allure.annotations.Step;

public class LoginScreen extends BaseScreen {
	
public static AndroidDriver<MobileElement> driver;
	
	public LoginScreen() {
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	private By usernameInput = By.xpath("//*[@content-desc='User']");
	private By passwordInput = By.xpath("//*[@content-desc='Password']");
	private By frontLineInput = By.xpath("//*[@content-desc='FrontlinecreateP create']");
	private By selectedFrontLine = By.xpath("//*[@content-desc='Oman (*)']");
	private By searchFrontLineInput = By.xpath("//*[@class='android.widget.EditText' and @content-desc='search']");
	private By signInButton = By.xpath("//*[@content-desc='done']");
	private By closeButton = By.xpath("//*[@class='android.widget.Button' and @content-desc='label']");
	private By releaseNote = By.xpath("//*[contains(@content-desc, 'Release notes')]");

	
	@Step("Enter email then password and sign in")
	public MainScreen signIn(String email, String password) {
		
		waitForElementPresent(usernameInput, 30);
		driver.findElement(usernameInput).click();
		typeByPressKeyCode(email);
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		
		waitForElementPresent(passwordInput, 5);
		driver.findElement(passwordInput).click();
		typeByPressKeyCode(password);
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		
		waitForElementPresent(frontLineInput, 10);
		driver.findElement(frontLineInput).click();
		
		waitForElementPresent(searchFrontLineInput, 10);
		driver.findElement(searchFrontLineInput).click();
		typeByPressKeyCode("Oman");
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		
		waitForElementPresent(selectedFrontLine, 10);
		findElement(selectedFrontLine).click();
		waitForElementPresent(signInButton, 10);
		findElement(signInButton).click();

		// Wait for page downloading completed
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		waitForElementPresent(releaseNote, 30);
		findElement(closeButton).click();
		
		MainScreen mainScreen = new MainScreen();
		Assert.assertTrue(mainScreen.isDisplayed(), "Failed to login");
		return mainScreen;
	}

	
	public boolean isDisplayed() {
		return waitForElementPresent(usernameInput, 5) != null;
	}

}

package com.kone.app.screens.salesforce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.kone.app.screens.WebBaseScreen;
import com.kone.framework.context.WebContext;
import ru.yandex.qatools.allure.annotations.Step;

public class LoginScreen extends WebBaseScreen {
	
public static WebDriver wdriver;
	
	public LoginScreen() {
		wdriver = WebContext.wdriver;
	}
	
	private By usernameInput = By.id("username");
	private By passwordInput = By.id("password");
	private By signInButton = By.id("Login");

	@Step("Login to SalesForce")
	public MainScreen signIn (String email, String password) {
		email = email + ".qa";
		
		waitForElementPresent(usernameInput, 30);
		wdriver.findElement(usernameInput).sendKeys(email);		
		wdriver.findElement(passwordInput).sendKeys(password);
		wdriver.findElement(signInButton).click();
		
		MainScreen mainScreen = new MainScreen();
		Assert.assertTrue(mainScreen.isDisplayed(), "Failed to login");
		return mainScreen;
	}

	@Step("Check if the User Name field is displayed")
	public boolean isDisplayed() {
		return waitForElementPresent(usernameInput, 5) != null;
	}

}

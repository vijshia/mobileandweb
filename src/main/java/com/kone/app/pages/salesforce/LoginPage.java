package com.kone.app.pages.salesforce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.kone.app.pages.WebBasePage;
import com.kone.framework.context.WebContext;
import ru.yandex.qatools.allure.annotations.Step;

public class LoginPage extends WebBasePage {
	
public static WebDriver wdriver;
	
	public LoginPage() {
		wdriver = WebContext.wdriver;
	}
	
	private By usernameInput = By.id("username");
	private By passwordInput = By.id("password");
	private By signInButton = By.id("Login");

	@Step("Login to SalesForce")
	public MainPage signIn (String email, String password) {
		email = email + ".qa";
		
		waitForElementPresent(usernameInput, 30);
		wdriver.findElement(usernameInput).sendKeys(email);	
		enteringPassword(passwordInput, password);
//		wdriver.findElement(passwordInput).sendKeys(password);
		wdriver.findElement(signInButton).click();
		
		MainPage mainScreen = new MainPage();
		Assert.assertTrue(mainScreen.isDisplayed(), "Failed to login");
		return mainScreen;
	}

	@Step("Check if the User Name field is displayed")
	public boolean isDisplayed() {
		return waitForElementPresent(usernameInput, 5) != null;
	}

}
package com.kone.app.screens.salesforce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.kone.app.screens.WebBaseScreen;
import com.kone.framework.context.WebContext;

import ru.yandex.qatools.allure.annotations.Step;

public class MainScreen extends WebBaseScreen {
	
    public static WebDriver wdriver;
    
    private By searchButton = By.id("phSearchButton");
	
	public MainScreen() {
		wdriver = WebContext.wdriver;
	}
	
	@Step("Check if the main page is displayed")
	public boolean isDisplayed() {
		return waitForElementPresent(searchButton, 15) != null;
	}

}

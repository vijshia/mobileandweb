package com.kone.app.pages.outlook;

import com.kone.app.pages.WebBasePage;
import com.kone.app.pages.outlook.OutlookLoginPage;

import ru.yandex.qatools.allure.annotations.Step;

import static com.kone.app.pages.WebBasePage.wdriver;

import org.openqa.selenium.By;
import org.testng.Assert;

public class OutlookURLLaunch extends WebBasePage {

    private By searchButton = By.id("phSearchButton");
    
	@Step("Launch OutLook URL")
	public OutlookLoginPage launchOutLookURL() {
		wdriver.navigate().to("https://outlook.office365.com");
	
		OutlookLoginPage outlookLoginScreen = new OutlookLoginPage();
		Assert.assertTrue(outlookLoginScreen.isDisplayed());
		return outlookLoginScreen;
	}
	
	@Step("Check if OutLook URL has been Launched")
	public boolean isDisplayed() {
		return waitForElementPresent(searchButton, 15) != null;
	}
}

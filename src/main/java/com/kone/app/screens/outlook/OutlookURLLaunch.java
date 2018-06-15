package com.kone.app.screens.outlook;

import com.kone.app.screens.WebBaseScreen;
import ru.yandex.qatools.allure.annotations.Step;
import com.kone.app.screens.outlook.OutlookLoginScreen;
import static com.kone.app.screens.WebBaseScreen.wdriver;

import org.testng.Assert;

public class OutlookURLLaunch extends WebBaseScreen {

	@Step("Launch OutLook URL")
	public OutlookLoginScreen launchOutLookURL() {
	wdriver.navigate().to("https://outlook.office365.com");
	
	OutlookLoginScreen outlookLoginScreen = new OutlookLoginScreen();
	Assert.assertTrue(outlookLoginScreen.isDisplayed());
	return outlookLoginScreen;
	
	}
}

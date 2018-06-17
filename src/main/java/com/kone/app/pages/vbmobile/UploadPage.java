package com.kone.app.pages.vbmobile;

import org.openqa.selenium.By;

import com.kone.app.pages.PhoneBasePage;
import com.kone.framework.context.TestContext;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class UploadPage extends PhoneBasePage{
	
public static AppiumDriver<MobileElement> driver;

	protected static final long TASK_DOWNLOAD_TIMEOUT = 60;
	
    private By backupText = By.xpath("//*[text()='Create backup after upload of selected surveys?']");
	
	public UploadPage() {
		
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	public boolean isDisplayed(long timeout) {
		return waitForElementPresent(backupText, timeout) != null;
	}

}

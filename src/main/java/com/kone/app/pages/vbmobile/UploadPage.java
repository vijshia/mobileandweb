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
	
    private By successApproveText = By.xpath("//*[text()='Successfully approved task']");
    private By uploadAndReleaseBtn = By.xpath("//button[@aria-label='upload']");
	
	public UploadPage() {
		
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	public void uploadSurvey() {
		
		waitForElementPresent(uploadAndReleaseBtn, 30);
		driver.findElement(uploadAndReleaseBtn).click();
		waitForElementPresent(successApproveText, 180);
	}
	
	
	public boolean isDisplayed(long timeout) {
		return waitForElementPresent(uploadAndReleaseBtn, timeout) != null;
	}

}

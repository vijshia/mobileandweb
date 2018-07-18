package com.kone.app.pages.vbmobile;

import java.util.List;
import org.openqa.selenium.By;
import com.kone.app.pages.PhoneBasePage;
import com.kone.framework.context.TestContext;
import com.kone.framework.utility.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class UploadPage extends PhoneBasePage{
	
public static AppiumDriver<MobileElement> driver;

	public static String taskIDtoLink;
	
	protected static final long TASK_DOWNLOAD_TIMEOUT = 60;
	
    private By successApproveText = By.xpath("//*[starts-with(text(),'Successfully approved task ')]"); //*[text()='Successfully approved task']
    private By uploadAndReleaseBtn = By.xpath("//button[@aria-label='upload']");
    private By spinner = By.xpath("//div[@class='md-half-circle']");
    private By approvingText = By.xpath("//*[starts-with(text(),'Currently Approving')]");
	
	public UploadPage() {
		
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	public void uploadSurvey() {
		
		waitForElementNotPresent(spinner, 100);
		waitForElementPresent(uploadAndReleaseBtn, 50);
		driver.findElement(uploadAndReleaseBtn).click();
		Log.info("Click on element: " + uploadAndReleaseBtn);
		
		waitForElementPresent(approvingText, 40);
		

		By getSurveyData = By.xpath("//*[@class='spinner']/..//div/p/span"); /*By.xpath("//android.view.View[contains(@text, '/-.')]"); */
		Boolean isdisplayed=driver.findElement(getSurveyData).isDisplayed();
		Log.info(isdisplayed.toString());
		List<MobileElement> retrivesurveydatas = driver.findElements(getSurveyData);
	
		for(MobileElement retrivesurveydata: retrivesurveydatas) {
			retrivesurveydata.getText();
			Log.info(retrivesurveydata.getText());
		}	
		waitForElementPresent(successApproveText, 180);
	}
	
	
	public boolean isDisplayed(long timeout) {
		return waitForElementPresent(uploadAndReleaseBtn, timeout) != null;
	}

}

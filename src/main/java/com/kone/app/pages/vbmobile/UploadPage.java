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
    private By getSurveyData = By.xpath("//*[@class='spinner']/..//div/p/span"); /*By.xpath("//android.view.View[contains(@text, '/-.')]"); */
	
	public UploadPage() {
		
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	public void uploadSurvey() {
		
		waitForElementNotPresent(spinner, 100);
		waitForElementPresent(uploadAndReleaseBtn, TASK_DOWNLOAD_TIMEOUT);
		driver.findElement(uploadAndReleaseBtn).click();
		Log.info("Click on element: " + uploadAndReleaseBtn);
		waitForElementPresent(approvingText, TASK_DOWNLOAD_TIMEOUT);

		List<MobileElement> retrivetaskiddatas = driver.findElements(getSurveyData);
		for(MobileElement retrivetaskid: retrivetaskiddatas) {
			if(retrivetaskid.getText().contains("Task:")) {
				String taskid_tosplit[] = retrivetaskid.getText().split(":");
				taskIDtoLink = taskid_tosplit[1].trim();
				Log.info("TaskID retrived from Mobile Application: "+taskIDtoLink);
			}
		}	
		waitForElementPresent(successApproveText, 180); //*[@id='toast-success']
	}
	
	
	public boolean isDisplayed(long timeout) {
		return waitForElementPresent(uploadAndReleaseBtn, timeout) != null;
	}

}

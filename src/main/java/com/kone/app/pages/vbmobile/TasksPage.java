package com.kone.app.pages.vbmobile;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.kone.app.pages.PhoneBasePage;
import com.kone.framework.context.TestContext;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class TasksPage extends PhoneBasePage{
	
public static AppiumDriver<MobileElement> driver;

	protected static final long TASK_DOWNLOAD_TIMEOUT = 60;
	
    private By openAndAcceptedTab = By.xpath("//*[text()='Open & Accepted']");
//    private By completedTab = By.xpath("//*[text()='Completed']");
    private By taskSearchInput = By.id("opentasksearchInput");
    private By taskCheckBox = By.xpath("//*[@aria-label='checkbox']");
    private By downloadButton = By.xpath("//span[text()='Download']");
    private By plannedSurveyType = By.xpath("//span[text()='Planned Survey Type']");
	
	public TasksPage() {
		
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	@Step("Download task which has id {0}")
	public SurveyManagerPage downloadTaksById(String taskId) {
		
		waitForElementPresent(plannedSurveyType, 30); /* Wait for tasks loaded*/
		driver.findElement(searchButton).click();
		driver.findElement(taskSearchInput).sendKeys(taskId);
		driver.hideKeyboard();
		By taskIdNumber = By.xpath("//span[text()='" + taskId + "']");
		waitForElementPresent(taskIdNumber, DEFAULT_WAIT_ELEMENT_TIMEOUT);
		driver.findElement(taskCheckBox).click();
		driver.findElement(downloadButton).click();
		SurveyManagerPage surveyManagerScreen = new SurveyManagerPage();
		Assert.assertTrue(surveyManagerScreen.
				isDisplayed(TASK_DOWNLOAD_TIMEOUT),
				"Failed download task " + taskId);
		return surveyManagerScreen;
	}
	
	public boolean isDisplayed(long timeout) {
		return waitForElementPresent(openAndAcceptedTab, timeout) != null;
	}

}

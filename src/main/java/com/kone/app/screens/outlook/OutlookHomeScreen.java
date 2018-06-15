package com.kone.app.screens.outlook;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.kone.app.screens.WebBaseScreen;
import com.kone.app.screens.salesforce.MainScreen;
import ru.yandex.qatools.allure.annotations.Step;
import static com.kone.app.screens.konesitesurvey.SiteHomeScreen.MSS_Street;

public class OutlookHomeScreen extends WebBaseScreen {

	private By header_Office365 = By.xpath("//*[text()='Office 365']");
	private By lnk_MailTimeStamp = By.xpath("//*[starts-with(text(),'New Survey Task Created')]/../../..//span[contains(text(),'PM') or contains(text(),'AM')]");
	private By mailBody = By.xpath("//*[contains(@id,'MessageUniqueBody')]/div/div/div");
  /*private By mailBodyText = By.xpath("//*[@class='allowTextSelection']/..//div/span[2]"); // *[text()='Select an item to read']
	private By mailBodyHeader = By.xpath("(//*[starts-with(@aria-label,'From no-reply@kone.com')])[last()]"); */

	public static String taskID;

	@Step("Get Task ID from mail")
	public OutlookHomeScreen getTaskID() {
		
		List<WebElement> MailTimeStamps = gettingWebElementsfromList(lnk_MailTimeStamp);
		for (WebElement MailTimeStamp : MailTimeStamps) { 		// System.out.println("TimeStamp=> "+MailTimeStamp.getText());
			MailTimeStamp.click(); 								// System.out.println("mailBodyText="+gettingWebElement(mailBodyText).getText());
			waitForpresenceOfElementLocated(mailBody, 250);		// System.out.println("mail=> "+gettingText(mailBody));
			if (gettingText(mailBody).contains(MSS_Street)) {	// String a[] = gettingText(mailBody).split("Surveyor");
				String a = gettingText(mailBody).substring(gettingText(mailBody).indexOf("Task Id:"));
				String b[] = a.split("Survey");
				String c[] = b[0].split("Id:");
				taskID = c[1].trim(); 								System.out.println(taskID);
				break;
			}
		}

		OutlookHomeScreen outlookHomeScreen = new OutlookHomeScreen();
		return outlookHomeScreen;
	}

	@Step("Check if the header displayed as Office 365")
	public boolean isDisplayed() {
		return waitForElementPresent(header_Office365, 15) != null;
	}
}

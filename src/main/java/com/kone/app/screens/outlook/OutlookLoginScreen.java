package com.kone.app.screens.outlook;

import java.util.List;
import org.openqa.selenium.By;
import org.testng.Assert;
import com.kone.app.screens.WebBaseScreen;
import com.kone.app.screens.salesforce.MainScreen;
import com.kone.framework.context.WebContext;
import ru.yandex.qatools.allure.annotations.Step;

public class OutlookLoginScreen extends WebBaseScreen {
	
	private By txt_EmailSignin = By.xpath("//*[@type='email' or @placeholder='someone@example.com ' or contains(@aria-label,'email')]");
	private By btn_Next = By.xpath("//*[contains(@type, 'submit') or contains(@value,'Next')]");
	private By progressBar = By.xpath("//*[contains(@class,'progress-container')]");
	private By txt_Emailpassword = By.id("passwordInput");
	private By btn_Emailsignin_id = By.id("submitButton");
	private By btn_Staysignedin_No = By.xpath("//*[contains(@id,'idBtn_Back') or starts-with(@value,'No')]");
	private By Checkbox_DontShow = By.xpath("//*[contains(@id,'KmsiCheckboxField')]");
	
	public static By btn_Sign=By.xpath("//*[text()='Replace']");

	@Step("Login to OutLook")
	public OutlookHomeScreen emailLogin(String username, String password) {
		
		waitForElementPresent(txt_EmailSignin, 20);
		String UserAccount_Check_New = gettingText(txt_EmailSignin);
		if (UserAccount_Check_New.isEmpty()) {
			enteringValueinTextField(txt_EmailSignin, username);
			clickonButton(btn_Next);
			waitForElementPresent(progressBar, 100);
		}
		By btn_Sign = By.xpath("//*[starts-with(@id,'formsAuthenticationArea')]");//*[starts-with(@id,'KmsiDescription') or starts-with(@id,'formsAuthenticationArea')]
//		waitForElementNotPresent(progressBar, 100);
		waitForElementtobeClickable(stringtoXpathEquals("Sign in"), 100);
		for(int i=0; i<50;) {
		List<String> Values = gettingTextfromList(btn_Sign);
			if (Values.isEmpty()) { //.contains("Sign in")
				i++;				//break;
			} else {
				break;				//i++;
			}
		}
		enteringValueinTextField(txt_Emailpassword, password);
		clickonButton(btn_Emailsignin_id);
		waitForElementtobeClickable(btn_Staysignedin_No, 20);
		clickonButton(Checkbox_DontShow);
		clickonButton(btn_Staysignedin_No);
		
		OutlookHomeScreen outlookHomeScreen=new OutlookHomeScreen();
		Assert.assertTrue(outlookHomeScreen.isDisplayed());
		return outlookHomeScreen;
	}
	
	@Step("Check if Sign in header is displayed with the text someone@example.com")
	public boolean isDisplayed() {
		return waitForElementPresent(txt_EmailSignin, 150) != null;
	}
}

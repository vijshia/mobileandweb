package com.kone.app.screens.outlook;

import java.util.List;
import org.openqa.selenium.By;
import com.kone.app.screens.WebBaseScreen;


public class OutlookLoginScreen extends WebBaseScreen {

	private By txt_EmailSignin = By.xpath("//*[@type='email' or @placeholder='someone@example.com' or contains(@aria-label,'email')]");
	private By btn_Next = By.xpath("//*[contains(@type, 'submit') or contains(@value,'Next')]");
	private By txt_Emailpassword = By.id("passwordInput");
	private By btn_Emailsignin_id = By.id("submitButton");
	private By btn_Staysignedin_No = By.xpath("//*[contains(@id,'idBtn_Back') or starts-with(@value,'No')]");
	private By Checkbox_DontShow = By.xpath("//*[contains(@id,'KmsiCheckboxField')]");
	
	private By lnk_Inbox_ITSM = By.xpath("//*[starts-with(text(),'ITSM Test')]");
	private By Lnk_Inbox_Expand_icon = By.id("_ariaId_46");
	
	private String Behalf_UserValue;

	public void emailLogin(String username, String password) {
		
		
		wdriver.navigate().to("http://outlook.office365.com/");
		
		String UserAccount_Check_New = gettingText(txt_EmailSignin);
		if (UserAccount_Check_New.isEmpty()) {
			enteringValueinTextField(txt_EmailSignin, username);
			clickonButton(btn_Next);
		}
		By btn_Sign = By.xpath("//*[starts-with(@id,'formsAuthenticationArea')]");//*[starts-with(@id,'KmsiDescription') or starts-with(@id,'formsAuthenticationArea')]
//		List<String> Values = gettingTextfromList(btn_Sign);
//		if (Values.contains("Sign in")) {
//			enteringValueinTextField(txt_Emailpassword, password);
//			clickonButton(btn_Emailsignin_id);
			/*waitForElementtobeClickable(btn_Staysignedin_No, 20);
			clickonButton(Checkbox_DontShow);
			clickonButton(btn_Staysignedin_No);*/
//		}
	}
}

package com.kone.app.tests.testcases.vbmobile;

import java.util.HashMap;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.kone.app.pages.vbmobile.LoginPage;
import com.kone.app.tests.testcases.VbmobileBaseTest;


public class VbmobileAuthenticationTests extends VbmobileBaseTest{
    
	private LoginPage loginScreen;
	
	protected String Excel_ValuetoStore;
	protected String loginUser;
	protected String loginPassword;
	protected String frontline;
	
	public VbmobileAuthenticationTests(HashMap<String, String> exceldatacombined) {
		
		for (Entry<String, String> entry : exceldatacombined.entrySet()) {
			
			String key = entry.getKey();
			Excel_ValuetoStore = entry.getValue();

				if (key.equals("Site_UserName")) {
					
					loginUser = Excel_ValuetoStore;
					
				} else if (key.equals("Site_Password")) {
					
					loginPassword = Excel_ValuetoStore;		
					
				} else if (key.equals("MSS_Frontline_Country")) {
					
					frontline = Excel_ValuetoStore;				
				}
		}
		
		loginScreen = new LoginPage();
	}
	
    @Test(groups={ "vbmobie_login" })
    void loginTest() {
    	loginScreen.signIn(this.loginUser, this.loginPassword, this.frontline);
    }
}
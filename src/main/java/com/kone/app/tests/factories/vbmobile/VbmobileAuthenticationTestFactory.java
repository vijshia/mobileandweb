package com.kone.app.tests.factories.vbmobile;

import org.testng.annotations.Factory;

import com.kone.app.tests.basetests.vbmobile.VbmobileAuthenticationTests;
import com.kone.app.tests.factories.VbmobileBaseFactory;


public class VbmobileAuthenticationTestFactory extends VbmobileBaseFactory {
	
	@Factory(dataProvider = "accountData")
    public Object[] testFactory(String email, String password) {
        return new Object[]{new VbmobileAuthenticationTests(email, password)};
    }

}

package com.kone.app.tests.factories.vbmobile;

import org.testng.annotations.Factory;

import com.kone.app.tests.factories.BaseFactory;
import com.kone.app.tests.basetests.vbmobile.VbmobileAuthenticationTests;

public class VbmobileAuthenticationTestFactory extends BaseFactory {
	
	@Factory(dataProvider = "accountData")
    public Object[] testFactory(String email, String password, String frontline) {
        return new Object[]{new VbmobileAuthenticationTests(email, password, frontline)};
    }

}

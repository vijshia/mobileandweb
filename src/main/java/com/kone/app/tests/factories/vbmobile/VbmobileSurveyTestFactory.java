package com.kone.app.tests.factories.vbmobile;

import org.testng.annotations.Factory;

import com.kone.app.tests.factories.BaseFactory;
import com.kone.app.tests.basetests.vbmobile.VbmobileSurveyTests;

public class VbmobileSurveyTestFactory extends BaseFactory {
	
	@Factory(dataProvider = "siteSurveyAccountData")
    public Object[] testFactory(String email, String password, String frontline) {
        return new Object[]{new VbmobileSurveyTests(email, password, frontline)};
    }

}

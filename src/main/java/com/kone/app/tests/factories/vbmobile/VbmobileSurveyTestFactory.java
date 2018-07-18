package com.kone.app.tests.factories.vbmobile;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Factory;

import com.kone.app.tests.factories.BaseFactory;
import com.kone.app.tests.testcases.vbmobile.VbmobileSurveyTests;

public class VbmobileSurveyTestFactory extends BaseFactory {
	
	@Factory(dataProvider = "surveyDataProvider")
    public Object[] testFactory(ArrayList<String[]> surveyData) {
        return new Object[]{new VbmobileSurveyTests(surveyData)};
    }

}

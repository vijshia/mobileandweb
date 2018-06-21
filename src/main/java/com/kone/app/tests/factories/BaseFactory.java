package com.kone.app.tests.factories;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import com.kone.framework.utility.ExcelReader;

public class BaseFactory {
	
	protected static final String TEST_DATA_EXCEL_PATH = "input/testdata/KONEMobileSiteSurvey_TestData.xlsx";
	protected static final String WEB_LOGIN_DATA_SHEET = "Web_Login_Data";
	protected static final String LOGIN_DATA_SHEET = "Login_Data";
	protected static final String SURVEY_DATA_SHEET = "Mobile_Data";

	@DataProvider(name = "excelDataProvider")
    public Iterator<Object[]> dataFromExcel(ITestContext context) throws IOException {
		
    	ExcelReader excelReader=new ExcelReader(TEST_DATA_EXCEL_PATH);
    	Map<String, String> excelDataCombined = new HashMap<String, String>();
    	List<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
    	Collection<Object[]> data = new ArrayList<Object[]>();
    	
    	try {
    		excelDataCombined.putAll(excelReader.GetData(WEB_LOGIN_DATA_SHEET));
    		excelDataCombined.putAll(excelReader.GetData(LOGIN_DATA_SHEET));
    		arrayList.add(excelDataCombined);
    		
    		for(Map<String,String> map:arrayList) {
    			data.add(new Object[] {map});
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data.iterator();
	}

	@DataProvider(name = "surveyDataProvider")
	public Object[][] surveyDataProvider(ITestContext context) throws IOException {

		ExcelReader exlReader = new ExcelReader(TEST_DATA_EXCEL_PATH);

		Object[][] data = new Object[1][1];

		try {
			data[0][0] = exlReader.GetSurveyData("Mobile_Data");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}
}

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

	private Properties testData = new Properties();

	@DataProvider(name = "siteSurveyAccountData")
	public Object[][] accountDataProvider(ITestContext context) throws IOException {

		String dataSource = context.getCurrentXmlTest().getParameter("dataSource");
		testData.load(new FileInputStream(new File(dataSource)));

		String emails = testData.getProperty("mobileSiteSurvey.login.user");
		String passwords = testData.getProperty("mobileSiteSurvey.login.password");
		String frontlines = testData.getProperty("mobileSiteSurvey.login.frontline");

		String[] email = emails.split(",");
		String[] password = passwords.split(",");
		String[] frontline = frontlines.split(",");

		Object[][] data = new Object[email.length][3];

		for (int i = 0; i < email.length; i++) {
			data[i][0] = email[i].trim();
			data[i][1] = password[i].trim();
			data[i][2] = frontline[i].trim();
		}

		return data;
	}

	@DataProvider(name = "salesforceAccountData")
	public Object[][] salesforceAccountDataProvider(ITestContext context) throws IOException {

		String dataSource = context.getCurrentXmlTest().getParameter("dataSource");
		testData.load(new FileInputStream(new File(dataSource)));

		String salesforceEmails = testData.getProperty("salesforce.login.user2");
		String salesforcepasswords = testData.getProperty("salesforce.login.password2");
		String siteUsernames = testData.getProperty("mobileSiteSurvey.login.user");
		String sitePasswords = testData.getProperty("mobileSiteSurvey.login.password");

		String[] salesforceEmail = salesforceEmails.split(",");
		String[] salesforcepassword = salesforcepasswords.split(",");
		String[] siteUsername = siteUsernames.split(",");
		String[] sitePassword = sitePasswords.split(",");

		Object[][] data = new Object[salesforceEmail.length][4];

		for (int i = 0; i < salesforceEmail.length; i++) {
			data[i][0] = salesforceEmail[i].trim();
			data[i][1] = salesforcepassword[i].trim();
			data[i][2] = siteUsername[i].trim();
			data[i][3] = sitePassword[i].trim();
		}

		return data;
	}

	@DataProvider(name = "excelDataProvider")
    public Iterator<Object[]> dataFromExcel(ITestContext context) throws IOException {
		
    	ExcelReader excelReader=new ExcelReader("C:\\Users\\con_svijay02\\Desktop\\KONEMobileSiteSurvey_TestData.xlsx");
    	Map<String, String> excelDataCombined = new HashMap<String, String>();
    	List<Map<String,String>> arrayList = new ArrayList<Map<String,String>>();
    	Collection<Object[]> data = new ArrayList<Object[]>();
    	try {
    		excelDataCombined.putAll(excelReader.GetData("Web_Login_Data"));
    		excelDataCombined.putAll(excelReader.GetData("Login_Data"));
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

		ExcelReader exlReader = new ExcelReader("properties/testdata/KONEMobileSiteSurvey_TestData.xlsx");

		Object[][] data = new Object[1][1];

		try {
			data[0][0] = exlReader.GetSurveyData("Mobile_Data");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}
}

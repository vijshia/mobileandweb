package com.kone.app.pages.vbmobile;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.kone.app.pages.PhoneBasePage;
import com.kone.framework.context.TestContext;
import com.kone.framework.utility.Log;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class SurveyPage extends PhoneBasePage {
	
public static AndroidDriver<MobileElement> driver;
	
	public SurveyPage() {
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	private By filterButton = By.xpath("//*[text()='filter_list']");
	private By progressBar = By.xpath("//md-progress-linear");
	private By sectionListButton = By.xpath("//md-icon[text()='list' " + 
	                                        "and @class='md-default-theme material-icons']");
	private By bottomSheetButton = By.xpath("//button[@aria-label='open bottom sheet']");
	private By completeButton = By.xpath("//md-icon[text()='done_all']");
	private By completeSurveyButton = By.xpath("//button/span[text()='Complete']");
	
	/**
	 * Test step method to open a section 
	 * in the survey.
	 * 
	 * @param  section: name of the section 
	 *                  For example, Elevator Data              
	 */
	@Step("Open survey section {0}")
	public void openSection(String section) {
		
		waitForElementPresent(sectionListButton, DEFAULT_WAIT_ELEMENT_TIMEOUT);
		
		/* Check if section is already opened */
		By currentSection = By.xpath("//button/span[contains(text(), '" + section + "')]");
		
		if (!isElementPresent(currentSection, 2)) {
			
			driver.findElement(sectionListButton).click();
			
			By sectionTitle = By.xpath("//span[contains(text(),'" + section + "') and " +
			                           "contains(@do-translate, 'c4lm_title')]");
			
			scrollToElement(sectionTitle);
			waitForElementPresent(sectionTitle, DEFAULT_WAIT_ELEMENT_TIMEOUT);
			
			driver.findElement(sectionTitle).click();
			waitForElementPresent(filterButton, DEFAULT_WAIT_ELEMENT_TIMEOUT);
			
		} else {
			
			Log.info("Section " + section + " is already opened");
		}
	}

	/**
	 * Test step method to select answer from
	 * drop down list.
	 * 
	 * @param  question: partial or full test of the question
	 * @param  answer: value to be selected from drop down list
	 */
	@Step("Answer [{1}] to drop down question containing text [{0}]")
	public void answerFromDropdown(String question, String answer) {
		
		// Find and open the drop down
		String dropDownXpath = "//md-select[@aria-label='dropdown' and " +
							   "contains(@placeholder, '" + question + "')]";
		
		By dropDownElement = By.xpath(dropDownXpath);
		scrollToElement(dropDownElement);
		driver.findElement(dropDownElement).click();
		
		// Find and select the answer from drop down list
		String answerXpath = "//div[contains(text(),'" + answer + "')]";
		By answerElement = By.xpath(answerXpath);
		scrollToElement(answerElement);
		driver.findElement(answerElement).click();
		
		// Verify that answer is selected
		String selectedAnswerXpath = "//md-select[@aria-label='dropdown']" +
				                     "//span[contains(text(), '" + answer + "')]";
		driver.findElementByXPath(selectedAnswerXpath);
	}
	
	/**
	 * Test step method to select answer from
	 * drop down list which has search input.
	 * 
	 * @param  question: partial or full test of the question
	 * @param  answer: value to be selected from drop down list
	 */
	@Step("Answer [{1}] to drop down question containing text [{0}]")
	public void answerFromDropdownWithSearch(String question, String answer) {
		
		// Find and open the drop down
		String dropDownXpath = "//md-input-container//*[contains(text(), '" + question + "')]" +
		                       "/../input";
		
		By dropDownElement = By.xpath(dropDownXpath);
		scrollToElement(dropDownElement);
		driver.findElement(dropDownElement).click();
		
		// Search and select the answer from drop down list
		By searchInputElement = new By.ByXPath("//input[@aria-label='search']");
		waitForElementPresent(searchInputElement, 3);
		driver.findElement(searchInputElement).sendKeys(answer);
		
		String answerXpath = "//p[contains(text(),'" + answer + "')]";
		By answerElement = By.xpath(answerXpath);
		scrollToElement(answerElement);
		driver.findElement(answerElement).click();
	}
	
	/**
	 * Test step method to answer radio button 
	 * question in the survey.
	 * 
	 * @param  question: partial or full test of the question
	 * @param  answer: radio button text to be selected
	 */
	@Step("Answer [{1}] to question containing text [{0}]")
	public void answerRadioButtonQuestion(String question, String answer) {
		
		String questionXpath = "//md-list-item" +
                               "//*[contains(text(), '" + question + "')]";
		
		By questionElement = By.xpath(questionXpath);
		
		if(scrollToElement(questionElement)) {

			String answerXpath = questionXpath +
					             "/..//*[contains(text(),'" + answer + "')]/..";
			
			waitForElementPresent(By.xpath(answerXpath),
								  DEFAULT_WAIT_ELEMENT_TIMEOUT);
			
			driver.findElement(By.xpath(answerXpath)).click();
			
		} else {
			
			Assert.assertTrue(false, "No question with text \"" + 
								     question + "\" found");
		}
	}
	
	/**
	 * Test step method to answer the question that 
	 * requires a number with specific range.
	 * 
	 * @param  question: partial or full test of the question
	 * @param  answer: the number to be enter
	 */
	@Step("Answer [{1}] to question containing text [{0}]")
	public void answerWithNumber(String question, String answer) {
		
		String questionXpath = "//md-list-item" +
                               "//*[contains(text(), '" + question + "')]" +
	                           "/../input";

		By questionElement = By.xpath(questionXpath);
//		Log.info("*********questionXpath=>"+questionElement.toString());
		if(scrollToElement(questionElement)) {
			
			driver.findElement(questionElement).sendKeys(answer);
			driver.hideKeyboard();
		
		} else {
		
			Assert.assertTrue(false, "No question with text \"" + 
							     	 question + "\" found");
		}
	}
	
	/**
	 * Test step method to answer the question 
	 * by type text.
	 * 
	 * @param  question: partial or full test of the question
	 * @param  answer: text to enter as answer
	 */
	@Step("Answer [{1}] to question containing text [{0}]")
	public void answerWithText(String question, String answer) {
		
		String questionXpath = "//md-list-item[@data-type='textarea']" +
		                       "//*[contains(text(), '" + question + "')]" +
				               "/../textarea";

		By questionElement = By.xpath(questionXpath);
		
		if(scrollToElement(questionElement)) {
			
			driver.findElement(questionElement).sendKeys("" + answer);
			driver.hideKeyboard();
		
		} else {
		
			Assert.assertTrue(false, "No question with text \"" + 
							     	 question + "\" found");
		}
	}
	
	/**
	 * Test step method to answer the question 
	 * by selecting multiple choices.
	 * 
	 * @param  question: partial or full test of the question
	 * @param  answers: an array of answers as text
	 */
	@Step("Answer {1} to question containing text [{0}]")
	public void answerMultipleChoices(String question, String[] answers) {
		
		String questionXpath = "//md-list-item[@data-type='checkbox']" +
		                       "//*[contains(text(), '" + question + "')]";

		By questionElement = By.xpath(questionXpath);
		
		if(scrollToElement(questionElement)) {
			
			for(int i=0; i < answers.length; i++) {
				String answerXpath = questionXpath + 
						             "/..//md-checkbox[contains(@aria-label, '" + 
						             answers[i].trim() + "')]";
				By answerElement = By.xpath(answerXpath);
				scrollToElement(answerElement);
				driver.findElement(answerElement).click();
			}
		
		} else {
		
			Assert.assertTrue(false, "No question with text \"" + 
							     	 question + "\" found");
		}
	}
	
	/**
	 * Test step method to complete the survey 
	 * and verify that it is ready to be uploaded.
	 */
	@Step("Complete the survey")
	public UploadPage completeSurvey() {
		
		driver.findElement(bottomSheetButton).click();
		driver.findElement(completeButton).click();
		Log.info("Click on element: " + completeButton);
		waitForElementPresent(completeSurveyButton,
				              DEFAULT_WAIT_ELEMENT_TIMEOUT);
		driver.findElement(completeSurveyButton).click();
		UploadPage uploadScreen = new UploadPage();
		Assert.assertTrue(uploadScreen.
				isDisplayed(DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT), 
				"Failed to complete the survey");
		return uploadScreen;
	}
	
	@Step("Answer survey questions")
	public void answerSurvey(ArrayList<String[]> surveyData, String mobileMenutoSelect) {
		
		for (String[] item : surveyData) {
			Log.info("Question: " + item[0] + "/" + item[1] + "/" + item[2] + "/" + item[3]);
			
			if(item[3].contains("Trapdoor")) {
				Log.info("BreakPOint");
			}
			
			/* Open the survey section */
			openSection(item[0]);
			
			/* Survey to Link not having Branch Number field as drop down hence converting the parameter to number field */
			if(item[2].equals("Branch Number") && mobileMenutoSelect.equals("Survey Manager")) {
				item[1] = "number";
				item[2] = "Branch";
			}
			
			/* Answer the question based on its type */
			String type = item[1].toLowerCase();
			String question = item[2];
			String answer = item[3];
			
			if (type.equals("dropdown")) {
				answerFromDropdown(question, answer);
			} else if (type.equals("dropdownsearch")) {
				answerFromDropdownWithSearch(question, answer);
			} else if (type.equals("radio")) {
				answerRadioButtonQuestion(question, answer);
			} else if (type.equals("number")) {
				answerWithNumber(question, answer);
			} else if (type.equals("multiplechoices")) {
				String[] answers = answer.split(",");
				answerMultipleChoices(question, answers);
			} else if (type.equals("text")) {
				answerWithText(question, answer);
			}
		}
	}
	
	public boolean isDisplayed(long timeout) {
		return waitForElementPresent(progressBar, timeout) != null;
	}

}

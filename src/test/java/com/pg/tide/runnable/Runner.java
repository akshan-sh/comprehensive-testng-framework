package com.pg.tide.runnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.pg.tide.pageobjects.ChatWindow;
import com.pg.tide.pageobjects.ContactUsPage;
import com.pg.tide.pageobjects.FreshnessPage;
import com.pg.tide.pageobjects.GreaseStainPage;
import com.pg.tide.pageobjects.HomePage;
import com.pg.tide.pageobjects.HowToRemoveStainsPage;
import com.pg.tide.pageobjects.SavingsCalculatorPage;
import com.pg.tide.pageobjects.SearchResultsPage;
import com.pg.tide.pageobjects.SignUpNowPGPage;
import com.pg.tide.utilities.Reporting;
import com.pg.tide.utilities.TakeScreenshot;



public class Runner {
	
	Base base = new Base(); // creating global object of base class to be used by each test method
	Assertion asserts = new Assertion();// creating global object of assertion to be used by each test method
	Reporting report = new Reporting();
	
	@BeforeTest
	public void beforeTest()
	{	
		
	}
	
	@BeforeMethod
	public void initializer() throws IOException
	{
		//initializing driver and navigating to tide hompeage 
		base.initializeDriver();
		base.navigateDriver();
		base.driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS); // setting implicit wait for 30 seconds
		base.driver.manage().window().maximize(); //maximizing browser window
		
		
	}
	
	@Test
	public void registration()
	{	//#1
		report.test = report.getExtent().createTest("Registration Test").assignAuthor("Akshansh");
		HomePage home = new HomePage(base.getDriver(), report);
		home.goToPGRegistrationPage();
		
		//switching to PG registration page
		ArrayList<String> tabs2 = new ArrayList<String>(base.getDriver().getWindowHandles());
		base.getDriver().switchTo().window(tabs2.get(1));
		
		SignUpNowPGPage now = new SignUpNowPGPage(base.getDriver(), report);
		now.signUp();
		
	}
	
	@Test
	public void login()
	{
		//#2
		report.test = report.getExtent().createTest("Login Test").assignAuthor("Akshansh");
		HomePage home = new HomePage(base.getDriver(),report);
		home.goToPGRegistrationPage();
		
		//switching to PG registration page
		ArrayList<String> tabs2 = new ArrayList<String>(base.getDriver().getWindowHandles());
		base.getDriver().switchTo().window(tabs2.get(1));
		
		SignUpNowPGPage now = new SignUpNowPGPage(base.getDriver(), report);
		now.login();
		
		
		
		
	}
	
	@Test
	public void changeLanguage()
	{	//#3
		report.test = report.getExtent().createTest("Changing Language to Canada French Test").assignAuthor("Akshansh");
		HomePage home = new HomePage(base.getDriver(), report);
		home.changeLangToFrench();
		
		
	}
	
	@Test
	public void CustomStainCheck() throws InterruptedException
	{	//#4
		report.test = report.getExtent().createTest("Checking for Custom Stain Type Test").assignAuthor("Akshansh");
		HomePage home = new HomePage(base.getDriver(), report);
		home.clickOnCantFindStain();
		
		HowToRemoveStainsPage stainPage = new HowToRemoveStainsPage(base.getDriver(), report);
		stainPage.SearchGreaseStain("Grease");
		
		GreaseStainPage greasePage = new GreaseStainPage(base.getDriver(), report);
		greasePage.isGreaseContentPresent();
		
		
	}
	
	@Test
	public void CalculateSavingsForSwitchingToColdWater()
	{	//#5
		report.test = report.getExtent().createTest("Calculate Savings on Swtiching from hot to cold water Test").assignAuthor("Akshansh");
		HomePage home = new HomePage(base.getDriver(), report);
		home.clickOnSavingsBanner();
		
		SavingsCalculatorPage savingsPage = new SavingsCalculatorPage(base.getDriver(), report);
		savingsPage.calculateSavingsForTenWashes();
		
	}
	
	@Test
	public void checkContactUsIframe() throws InterruptedException
	{	
		//#6
		report.test = report.getExtent().createTest("Check for options to contact on contactus page Test").assignAuthor("Akshansh");
		HomePage home = new HomePage(base.getDriver(), report);
		home.clickOnContactUs();
		
		ContactUsPage contactPage = new ContactUsPage(base.getDriver(), report);
		contactPage.checkIFrameVisibility();
	}
	
	@Test
	public void searchQuery()
	{
		//#7
		report.test = report.getExtent().createTest("Searching for Pods Test").assignAuthor("Akshansh");
		HomePage home = new HomePage(base.getDriver(), report);
		home.search("pods");
		
		SearchResultsPage search = new SearchResultsPage(base.getDriver(), report);
		search.checkSearchResults();
		
		
	}
	
	@Test
	public void checkPartCompanies() throws InterruptedException
	{
		//#8
		report.test = report.getExtent().createTest("Checking for Part company websites live status Test").assignAuthor("Akshansh");
		HomePage home = new HomePage(base.getDriver(), report);
		home.openPartCompanySites();
		home.verifyPartCompanySites();
	}
	
	@Test
	public void startChat() throws InterruptedException
	{
		//#9
		report.test = report.getExtent().createTest("Starting a live chat Test").assignAuthor("Akshansh");
		HomePage home = new HomePage(base.getDriver(), report);
		home.navigateToChatWindow();
		
		ChatWindow chat = new ChatWindow(base.getDriver(), report);
		chat.continueChat();
		
	}
	
	@Test
	public void browseByNeedFebreze() throws InterruptedException
	{
		//#10
		report.test = report.getExtent().createTest("Browsing by need for Freshness Test").assignAuthor("Akshansh");
		HomePage home = new HomePage(base.getDriver(), report);
		home.goToFreshnessPageThroughBrowseByNeed();
		
		FreshnessPage fresh = new FreshnessPage(base.getDriver(), report);
		fresh.isFebrezePresent();
	}
	
	/* @Test
	public void searchCricketTest() throws IOException
	{	
		// creating extent test
		ExtentTest test2 = extent.createTest("Checking for Cricket string in the search results page on second result")
				.assignAuthor("Akshansh");
		
		test2.info("navigating to bbc homepage");
		
		log.info("navigating to bbc homepage");
		
		HomePage home = new HomePage(base.driver);
		
		//READING FROM EXCEL FILE THE TEST DATA TO SEARCH Cricket
		home.search(ExcelReading.extractCell(2, 3)); // keyword Cricket present on specific cell in excel file
		
		test2.info("clicked on search and entered search keyword from EXCEL file");
		log.info("clicked on search and entered search keyword from EXCEL file");
		
		SearchResultsPage search = new SearchResultsPage(base.driver);
		
		boolean result = search.isCricketTextPresent(); //verifying cricket string on second search result
		asserts.assertTrue(result);
		
		test2.info("Verifying the presence of Cricket text in the second search result");
		log.info("Verifying the presence of Cricket text in the second search result");
		
		//passing or failing test based on result
		//Appending screenshot for both pass and failure to demonstrate working of screenshot utility
		if(result==true) {
			test2.pass("Cricket text is present in the second search result");
			test2.addScreenCaptureFromPath(TakeScreenshot.takeSs(base.driver));
			log.trace("Cricket text is present in the second search result");
		}
		else
		{
			test2.fail("Cricket text is not present in the second search result");
			test2.addScreenCaptureFromPath(TakeScreenshot.takeSs(base.driver));
			log.fatal("Cricket text is not present in the second search result");
		}
		
		
	}
	
*/
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
	   if (result.getStatus() == ITestResult.FAILURE) {
		   report.test.addScreenCaptureFromPath(TakeScreenshot.takeSs(base.getDriver()));
		   report.getExtent().flush();
		   base.getDriver().quit();
	   }        
	   else if((result.getStatus() == ITestResult.SUCCESS))
	   {
		   report.getExtent().flush();
		   base.getDriver().quit();
	   }
	   else if((result.getStatus() == ITestResult.SKIP))
	   {
		   report.test.addScreenCaptureFromPath(TakeScreenshot.takeSs(base.getDriver()));
		   report.getExtent().flush();
		   base.getDriver().quit();
	   }
		   
	}
	
	
	
	
	@AfterTest
	public void afterTest()
	{
		// report.getExtent().flush(); //flushing extent tests to report after executing all the test methods
		
	}
	
	
	
	

}

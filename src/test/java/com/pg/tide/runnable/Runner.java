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

import com.pg.tide.pageobjects.ChatWindow;
import com.pg.tide.pageobjects.ContactUsPage;
import com.pg.tide.pageobjects.FreshnessPage;
import com.pg.tide.pageobjects.GreaseStainPage;
import com.pg.tide.pageobjects.HomePage;
import com.pg.tide.pageobjects.HowToRemoveStainsPage;
import com.pg.tide.pageobjects.SavingsCalculatorPage;
import com.pg.tide.pageobjects.SearchResultsPage;
import com.pg.tide.pageobjects.SignUpNowPGPage;
import com.pg.tide.utilities.ExcelReading;
import com.pg.tide.utilities.Logging;
import com.pg.tide.utilities.Reporting;
import com.pg.tide.utilities.TakeScreenshot;



public class Runner {
	
	Base base = new Base(); // creating global object of base class to be used by each test method
	Reporting report = new Reporting();// creating global object for extent reporting
	Logging log = new Logging();
	
	
	@BeforeMethod
	public void initializer() throws IOException
	{
		//initializing driver and navigating to tide homepage 
		base.initializeDriver();
		base.navigateDriver();
		base.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // setting implicit wait for 30 seconds
		base.driver.manage().window().maximize(); //maximizing browser window
		
		
	}
	
	@Test
	public void registration() throws IOException
	{	//#1
		String name = ExcelReading.extractCell(2, 3);
		String email = ExcelReading.extractCell(3, 3);
		String password = ExcelReading.extractCell(4, 3);
		
		report.test = report.getExtent().createTest("Registration Test").assignAuthor("Akshansh");
		
		HomePage home = new HomePage(base.getDriver(), report, log);
		home.goToPGRegistrationPage();
		
		//switching to PG registration page
		ArrayList<String> tabs2 = new ArrayList<String>(base.getDriver().getWindowHandles());
		base.getDriver().switchTo().window(tabs2.get(1));
		
		SignUpNowPGPage now = new SignUpNowPGPage(base.getDriver(), report, log);
		now.signUp(name, email, password);
		
	}
	
	@Test
	public void login() throws IOException
	{
		//#2
		String email = ExcelReading.extractCell(5, 3);
		String password = ExcelReading.extractCell(6, 3);
		
		report.test = report.getExtent().createTest("Login Test").assignAuthor("Akshansh");
		
		HomePage home = new HomePage(base.getDriver(), report, log);
		home.goToPGRegistrationPage();
		
		//switching to PG registration page
		ArrayList<String> tabs2 = new ArrayList<String>(base.getDriver().getWindowHandles());
		base.getDriver().switchTo().window(tabs2.get(1));
		
		SignUpNowPGPage now = new SignUpNowPGPage(base.getDriver(), report, log);
		now.login(email, password);
		
		
		
		
	}
	
	@Test
	public void changeLanguage()
	{	//#3
		report.test = report.getExtent().createTest("Changing Language to Canada French Test").assignAuthor("Akshansh");
		
		HomePage home = new HomePage(base.getDriver(), report, log);
		home.changeLangToFrench();
		
		
	}
	
	@Test
	public void CustomStainCheck() throws InterruptedException, IOException
	{	//#4
		String greaseStain = ExcelReading.extractCell(7, 3);
		
		report.test = report.getExtent().createTest("Checking for Custom Stain Type Test").assignAuthor("Akshansh");
		
		HomePage home = new HomePage(base.getDriver(), report, log);
		home.clickOnCantFindStain();
		
		HowToRemoveStainsPage stainPage = new HowToRemoveStainsPage(base.getDriver(), report, log);
		stainPage.SearchGreaseStain(greaseStain);
		
		GreaseStainPage greasePage = new GreaseStainPage(base.getDriver(), report, log);
		greasePage.isGreaseContentPresent();
		
		
	}
	
	@Test
	public void CalculateSavingsForSwitchingToColdWaterFor10Washes() throws IOException
	{	//#5
		String numberOfWash = ExcelReading.extractCell(8, 3);
		
		report.test = report.getExtent().createTest("Calculate Savings on Swtiching from hot to cold water Test").assignAuthor("Akshansh");
		
		HomePage home = new HomePage(base.getDriver(), report, log);
		home.clickOnSavingsBanner();
		
		SavingsCalculatorPage savingsPage = new SavingsCalculatorPage(base.getDriver(), report, log);
		savingsPage.calculateSavings(numberOfWash);
		
	}
	
	@Test
	public void checkContactUsIframe() throws InterruptedException
	{	
		//#6
		report.test = report.getExtent().createTest("Check for options to contact on contactus page Test").assignAuthor("Akshansh");
		
		HomePage home = new HomePage(base.getDriver(), report, log);
		home.clickOnContactUs();
		
		ContactUsPage contactPage = new ContactUsPage(base.getDriver(), report, log);
		contactPage.checkIFrameVisibility();
	}
	
	@Test
	public void searchPodsProduct() throws IOException
	{
		//#7
		String searchQuery = ExcelReading.extractCell(9, 3);
		
		report.test = report.getExtent().createTest("Searching for Pods Test").assignAuthor("Akshansh");
		
		HomePage home = new HomePage(base.getDriver(), report, log);
		home.search(searchQuery);
		
		SearchResultsPage search = new SearchResultsPage(base.getDriver(), report, log);
		search.checkSearchResults();
		
		
	}
	
	@Test
	public void checkPartCompanies() throws InterruptedException
	{
		//#8
		report.test = report.getExtent().createTest("Checking for Part company websites live status Test").assignAuthor("Akshansh");
		
		HomePage home = new HomePage(base.getDriver(), report, log);
		home.openPartCompanySites();
		home.verifyPartCompanySites();
	}
	
	@Test
	public void startChat() throws  IOException, InterruptedException
	{
		//#9
		String firstName = ExcelReading.extractCell(10, 3);
		String lastName = ExcelReading.extractCell(11, 3);
		String email = ExcelReading.extractCell(12, 3);
		String hiMessage = ExcelReading.extractCell(13, 3);
		
		report.test = report.getExtent().createTest("Starting a live chat Test").assignAuthor("Akshansh");
		
		HomePage home = new HomePage(base.getDriver(), report, log);
		home.navigateToChatWindow();
		
		ChatWindow chat = new ChatWindow(base.getDriver(), report, log);
		chat.continueChat(firstName, lastName, email, hiMessage);
		
	}
	
	@Test
	public void browseByNeedFebreze() throws InterruptedException
	{
		//#10
		report.test = report.getExtent().createTest("Browsing by need for Freshness Test").assignAuthor("Akshansh");
		
		HomePage home = new HomePage(base.getDriver(), report, log);
		home.goToFreshnessPageThroughBrowseByNeed();
		
		FreshnessPage fresh = new FreshnessPage(base.getDriver(), report, log);
		fresh.isFebrezePresent();
	}
	
	
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
	
	
	
	
	
	
	
	
	

}

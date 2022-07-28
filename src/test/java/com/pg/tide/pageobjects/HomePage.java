package com.pg.tide.pageobjects;



import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;

import com.pg.tide.uistore.HomeUI;
import com.pg.tide.utilities.Reporting;



public class HomePage {
	
	HomeUI homeUi;
	Reporting report;
	public HomePage(WebDriver driver, Reporting report)
	{
		homeUi = new HomeUI(driver);
		this.report = report;
	}
	
	
	
	public void goToPGRegistrationPage()
	{
		closePopup();
		report.getTest().info("Clicking on Register Link on homepage");
		homeUi.getRegister().click();
		report.getTest().info("Clicking on Signup on Registration Page");
		homeUi.getSignUpLink().click();
	}
	
	public void changeLangToFrench()
	{	
		closePopup();
		report.getTest().info("Clicking on US-English on homepage to change language");
		homeUi.getChangeLang().click();
		report.getTest().info("Clicking on Canada french from the available langs in the popup");
		homeUi.getCanadaFrench().click();
		
		boolean result = homeUi.getFrenchCheck().isDisplayed();
		if(result)
		{	report.getTest().pass("Success in changing to Canada French language");
		}
		else
		{
			report.getTest().fail("Failure in changing to Canada French language");
		}
	}
	
	public void clickOnCantFindStain()
	{
		closePopup();
		report.getTest().info("Clicking on Can't find the right stain");
		homeUi.getCantFindStain().click();
	}
	
	public void clickOnSavingsBanner()
	{	closePopup();
		report.getTest().info("Clicking on the sticker banner for saving calcualtor page");
		homeUi.getSavingsStickyBanner().click();
	}
	
	public void closePopup()
	{	
		/*Actions action = new Actions(homeUi.driver);
		if(homeUi.getPopupCloser().isDisplayed())
		{	
			action.moveToElement(homeUi.getPopupCloser()).click().build().perform();
		}*/
		Actions action = new Actions(homeUi.driver);
		try {
			
			action.moveToElement(homeUi.getPopupCloser()).click().build().perform();
			report.getTest().info("Closing popup");
		}
		catch(Exception e)
		{
			report.getTest().info("No initial popup found, great");
		}
		
	}
	
	public void clickOnContactUs()
	{
		closePopup();
		report.getTest().info("Clicking on ContactUs link on top of the page");
		homeUi.getContactUs().click();
	}
	
	public void search(String query)
	{
		closePopup();
		report.getTest().info("Entering text on the search box");
		homeUi.getSearchBox().sendKeys(query);
		report.getTest().info("Clicking on the search icon");
		homeUi.getSearchIcon().click();
	}
	public void openPartCompanySites()
	{
		closePopup();
		Actions action = new Actions(homeUi.driver);
		report.getTest().info("Opening all the part company websites using control+click");
		action.moveToElement(homeUi.getPg()).keyDown(Keys.CONTROL).click().build().perform();
		action.moveToElement(homeUi.getDowny()).keyDown(Keys.CONTROL).click().build().perform();
		action.moveToElement(homeUi.getBounce()).keyDown(Keys.CONTROL).click().build().perform();
		action.moveToElement(homeUi.getPgEveryday()).keyDown(Keys.CONTROL).click().build().perform();
	}
	
	public void verifyPartCompanySites() throws InterruptedException
	{	
		Thread.sleep(3000l);
		closePopup();
		String [] arr = {"https://www.pggoodeveryday.com/","https://www.bouncefresh.com/en-us","https://downy.com/en-us","https://us.pg.com/"};
		ArrayList<String> tabs = new ArrayList<String>(homeUi.driver.getWindowHandles());
		for(int i = 1; i<tabs.size();i++)
		{
			homeUi.driver.switchTo().window(tabs.get(i));
			if(homeUi.driver.getCurrentUrl().contains(arr[i-1]))
			{
				report.getTest().info(homeUi.driver.getTitle() + "page verified now closing it");
				homeUi.driver.close();
			}
			else
			{
				System.out.println((homeUi.driver.getCurrentUrl() + "different url than expected"));
				Assert.fail();
			}

		}
	}
	
	public void navigateToChatWindow() throws InterruptedException
	{	
		closePopup();
		Thread.sleep(3000l);
		report.getTest().info("Clicking on Live chat option on top of homepage");
		homeUi.getLiveChat().click();
		
		Actions action = new Actions(homeUi.driver);
		
		try{
			if(homeUi.getChatClosed().isDisplayed())
			{	
				action.moveToElement(homeUi.getChatClosed()).build().perform();
				report.getTest().skip("Chat is closed right now, come between 10am to 6pm");
				throw new SkipException("Chat is closed right now, come between 10am to 6pm");
			}
		} catch(SkipException e) {
			throw new SkipException("Chat is closed right now, come between 10am to 6pm");
		} catch(Exception e) {
			report.getTest().fail("Something unexpected happened");
			report.getTest().fail(e.getMessage());
		}
		
		
		
		action.moveToElement(homeUi.getStartChat()).click().build().perform();
		report.getTest().pass("Successfully clicked on Start Chat");
		homeUi.getStartChat().click();
		
		ArrayList<String> tabs2 = new ArrayList<String>(homeUi.driver.getWindowHandles());
		homeUi.driver.switchTo().window(tabs2.get(1));
		

	}
	
	public void goToFreshnessPageThroughBrowseByNeed() throws InterruptedException
	{	closePopup();
	
		Actions action = new Actions(homeUi.driver);
		report.getTest().info("Scrolling down to browse by need section");
		action.moveToElement(homeUi.getBrowseByNeed()).build().perform();
		report.getTest().info("Clicking on browse by need section");
		action.moveToElement(homeUi.getBrowseByNeed()).click().build().perform();
		Thread.sleep(2000l);
		report.getTest().info("Clicking on Freshness/Scent");
		homeUi.getFreshness().click();
	}
}

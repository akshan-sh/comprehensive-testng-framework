package com.pg.tide.pageobjects;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.pg.tide.uistore.ContactUsUI;
import com.pg.tide.utilities.Reporting;





public class ContactUsPage {
	
	ContactUsUI contactUi;
	Reporting report;
	public ContactUsPage(WebDriver driver, Reporting report)
	{
		contactUi = new ContactUsUI(driver);
		this.report = report;
	}
	
	public void checkIFrameVisibility() throws InterruptedException
	{	
		try
	{
		if(contactUi.getIframe().isDisplayed())
			report.getTest().pass("Contact Optons iframe window is present");
	}
	catch(Exception e)
	{
		report.getTest().fail("Unable to locate iframe window with contact options");
		Assert.fail();
	}

		
		
	}
	
	public void closePopup()
	{
		if(contactUi.getPopupCloser().isDisplayed())
			contactUi.getPopupCloser().click();
		
		
	}
	
	
	
}

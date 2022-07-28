package com.pg.tide.pageobjects;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.pg.tide.uistore.SearchResultsUI;
import com.pg.tide.utilities.Reporting;





public class SearchResultsPage {
	
	SearchResultsUI searchUi;
	Reporting report;
	public SearchResultsPage(WebDriver driver, Reporting report)
	{
		searchUi = new SearchResultsUI(driver);
		this.report = report;
	}
	
	
	public void checkSearchResults()
	{	
		try
		{
			if(searchUi.getVerify().isDisplayed())
				report.getTest().pass("pods contained in search results");
		}
		catch(Exception e)
		{
			report.getTest().fail("pods not found in search result");
			Assert.fail("pods not found in search result");
		}
		
	}
	
	
	
}

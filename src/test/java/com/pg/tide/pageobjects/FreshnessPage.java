package com.pg.tide.pageobjects;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.pg.tide.uistore.FreshnessUI;
import com.pg.tide.utilities.Reporting;




public class FreshnessPage {
	
	FreshnessUI freshUi;
	Reporting report;
	public FreshnessPage(WebDriver driver, Reporting report)
	{
		freshUi = new FreshnessUI(driver);
		this.report = report;
	}
	
	public void isFebrezePresent()
	{	
		try
		{
			if(freshUi.getFebreze4in1().isDisplayed())
				report.getTest().pass("Febreze 4in1 is present in the results");
		}
		catch(Exception e)
		{
			report.getTest().fail("Febreze 4in1 is NOT present in the results");
			Assert.fail();
		}
			
	}
	
	
	}
	
	


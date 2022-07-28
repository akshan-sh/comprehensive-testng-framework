package com.pg.tide.pageobjects;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.pg.tide.uistore.GreaseStainUI;
import com.pg.tide.utilities.Reporting;




public class GreaseStainPage {
	
	GreaseStainUI greaseUi;
	Reporting report;
	public GreaseStainPage(WebDriver driver, Reporting report)
	{
		greaseUi = new GreaseStainUI(driver);
		this.report = report;
	}
	
	public void isGreaseContentPresent() throws InterruptedException
	{	
		try
		{
			if(greaseUi.getArticle().getText().contains("Grease"))
				report.getTest().pass("Content related to Grease stain found in the article page");
		}
		catch(Exception e)
		{
			report.getTest().fail("Content related to Grease stain NOT found in the article page");
			Assert.fail("Content related to Grease stain NOT found in the article page");
		}
		
		
	}
	
	
	
	
	
}

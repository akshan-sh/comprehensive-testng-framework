package com.pg.tide.pageobjects;



import org.openqa.selenium.WebDriver;

import com.pg.tide.uistore.HowToRemoveStainsUI;
import com.pg.tide.utilities.Reporting;




public class HowToRemoveStainsPage {
	
	HowToRemoveStainsUI stainUi;
	Reporting report;
	public HowToRemoveStainsPage(WebDriver driver, Reporting report)
	{
		stainUi = new HowToRemoveStainsUI(driver);
		this.report = report;
	}
	
	public void SearchGreaseStain(String string) throws InterruptedException
	{	
		report.getTest().info("Entering Grease in the stain search box");
		stainUi.getStainTypeBox().sendKeys(string);
		report.getTest().info("Clicking on the article from the stain search results");
		stainUi.getGreaseResult().click();
	}
	
	
	
	
	
}

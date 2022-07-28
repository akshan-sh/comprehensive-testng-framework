package com.pg.tide.pageobjects;



import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.pg.tide.uistore.SavingsCalculatorUI;
import com.pg.tide.utilities.Reporting;




public class SavingsCalculatorPage {
	
	SavingsCalculatorUI savingsUi;
	Reporting report;
	public SavingsCalculatorPage(WebDriver driver, Reporting report)
	{
		savingsUi = new SavingsCalculatorUI(driver);
		this.report = report;
	}
	
	public void calculateSavingsForTenWashes()
	{		
			savingsUi.getInputBox().sendKeys("10", Keys.ENTER);
			report.getTest().info("Entered 10 in the number of washes");
			
			try
			{
				if(savingsUi.getCostForTenWash().isDisplayed())
					report.getTest().pass("Accurate cost calculation");
			}
			catch(Exception e)
			{
				report.getTest().fail("Something went wrong");
				Assert.fail("Something went wrong");
			}
			
	}
	
	
}

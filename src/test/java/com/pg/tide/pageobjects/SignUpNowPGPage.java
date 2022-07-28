package com.pg.tide.pageobjects;



import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;

import com.pg.tide.uistore.SignUpNowPGUI;
import com.pg.tide.utilities.Reporting;




public class SignUpNowPGPage {
	
	SignUpNowPGUI nowUi;
	Reporting report;
	public SignUpNowPGPage(WebDriver driver, Reporting report)
	{
		nowUi = new SignUpNowPGUI(driver);
		this.report = report;
	}
	
	
	public void signUp()
	{	
		nowUi.getEnterName().sendKeys("Akku");
		report.getTest().info("Entered Name");
		nowUi.getEnterEmail().sendKeys("hello@tide.com");
		report.getTest().info("Entered Email");
		nowUi.getEnterPassword().sendKeys("P@ss1234");
		report.getTest().info("Entered Password");
		nowUi.getShowPassword().click();
		report.getTest().info("Password made visible");
		nowUi.getCreateAccount().click();
		report.getTest().info("Clicked on create account");
		
		try {
			if(nowUi.getAlreadyRegistered().isDisplayed())
			{
				report.getTest().fail("User already registered");
				Assert.fail("User already registered");
				
			}
			
		} catch(NoSuchElementException e ) {
			//carrying on
		}
		
		try {
			if(nowUi.getGoogleCaptchaIframe().isDisplayed())
			{
				report.getTest().skip("Skipped due to google captcha");
				throw new SkipException("Skipped due to google captcha");
				
			}
		} catch(SkipException e) {
			throw new SkipException("Skipped due to google captcha");
			
		} catch(Exception e ) {
			//carrying on
		} 
		
		try {
			if(nowUi.getThanksForSigningUp().isDisplayed())
			{
				report.getTest().pass("Signup successfull");
			}else {
				report.getTest().fail("Can't verify successfull signup");
				Assert.fail("Could not verify signup");
			}
				
		}catch(Exception e) {
			report.getTest().fail("Could not verify signup");
			Assert.fail("Could not verify signup");
		}
		
		
		
	
	}
	
	public void login()
	{
		nowUi.getLoginLink().click();
		report.getTest().info("Clicked on Login link");
		nowUi.getLoginEmail().sendKeys("hello@tide.com");
		report.getTest().info("Entered Email address");
		nowUi.getLoginPassword().sendKeys("P@ss1234");
		report.getTest().info("Entered Password");
		nowUi.getLoginButton().click();
		report.getTest().info("Clicked on Login button");
		
		try {
			if(nowUi.getSpamMarked().isDisplayed())
			{
				report.getTest().fail("System marked us as spam probably");
				Assert.fail("System marked us as spam probably");
				
			}
			
		} catch(NoSuchElementException e ) {
			//carrying on
		}
		
		try {
			if(nowUi.getGoogleCaptchaIframe().isDisplayed())
			{
				report.getTest().skip("Skipped due to google captcha");
				throw new SkipException("Skipped due to google captcha");
				
			}
		} catch(NoSuchElementException e ) {
			
		} catch(SkipException e) {
			throw new SkipException("Skipped due to google captcha"); 
		}
		
		try {
		
		
		if(nowUi.getLoggedIn().isDisplayed())
		{
			report.getTest().pass("Login is Successfull");
		}
		}
		catch(Exception e) {
		
			report.getTest().fail("Unable to verify login");
	}
		
	}
	
	
	
	
	
}

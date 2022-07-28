package com.pg.tide.pageobjects;



import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.pg.tide.uistore.ChatUI;
import com.pg.tide.utilities.Reporting;






public class ChatWindow {
	
	ChatUI chatUi;
	 Reporting report;
	public ChatWindow(WebDriver driver, Reporting report)
	{
		chatUi = new ChatUI(driver);
		this.report = report;
	}
	
	public void continueChat() throws InterruptedException
	{	
		report.getTest().info("Clicking on agreeing and continue");
		chatUi.getAgree().click();
		report.getTest().info("Entering first name");
		chatUi.getFirstName().sendKeys("golu");
		report.getTest().info("Entering last name");
		chatUi.getLastName().sendKeys("Sharma");
		report.getTest().info("Entering email address");
		chatUi.getEmail().sendKeys("teser@tide.com");
		report.getTest().info("Clicking on Chat Button");
		chatUi.getChat().click();
		
		
		
		try {
		chatUi.getChatBox().sendKeys("Hello, there!");
		}
		catch(NoSuchElementException e)
		{	
			report.getTest().fail("Agents are not available");
			Assert.fail();
		}
		catch(Exception e)
		{
			report.getTest().fail("Chat persons are busy or there is high traffic for chat service");
			Assert.fail();
			
		}
		report.getTest().info("Entering initial chat message");
		Thread.sleep(1000l);
		report.getTest().info("Sending the message typed in the chat box");
		chatUi.getSendChat().click();
		
	}
	
	
	
	
	
}

package com.pg.tide.runnable;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.pg.tide.utilities.PropertyReading;




public class Base 
{	PropertyReading gp = new PropertyReading();
	WebDriver driver;
	
	public WebDriver initializeDriver() throws IOException
	{
		System.setProperty("webdriver.chrome.driver",gp.getFromProperty("chromePath") );
		driver = new ChromeDriver();
		return driver;
	}
	
	public WebDriver navigateDriver() throws IOException
	{
		driver.get(gp.getFromProperty("tideHome"));
		return driver;
	}
	
	public WebDriver getDriver()
	{
		return this.driver;
	}
}



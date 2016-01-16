package Core;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Core.DataTypes.BrowserData;

public class BrowserUtil 
{
	public static BrowserData startChrome()
	{		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		
		System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
		
		BrowserData browserData = new DataTypes().new BrowserData();
		browserData.m_webDriver = new ChromeDriver();	
		browserData.m_browserType = "Chrome";
			
		return browserData;
	}
	
	
}

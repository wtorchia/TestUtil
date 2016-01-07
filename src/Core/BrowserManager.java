package Core;

import java.util.ArrayList;
import java.util.Collection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserManager 
{

	public class BrowserData
	{
		public WebDriver m_webDriver;
		public String m_browserType;		
	}
	
	
	private Collection<BrowserData> m_webBrowserList;
	
	public BrowserManager()
	{
		m_webBrowserList = new ArrayList<BrowserData>();
	}

	public Collection<BrowserData> getBrowserList()
	{
		return m_webBrowserList;
	}
	
	public BrowserData startChrome()
	{		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		
		System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
		
		BrowserData browserData = new BrowserData();
		browserData.m_webDriver = new ChromeDriver();	
		browserData.m_browserType = "Chrome";
		
		m_webBrowserList.add(browserData);
		
		return browserData;
	}
	

}

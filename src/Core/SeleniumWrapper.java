package Core;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Core.DataTypes.StepAction;

public class SeleniumWrapper 
{
	public static void callWebDriver(WebDriver webDriver, Core.DataTypes.StepAction action)throws Exception
	{
		if(action.m_by != null)
		{
			By by = getFindBy(action.m_by, action.m_location);	
			
			WebElement webElement = webDriver.findElement(by);
			
			Method[] methodArray = webElement.getClass().getMethods();	
			
			for(Method method : methodArray)
			{
				if(method.getName().toLowerCase().equals(action.m_action.toLowerCase()))
				{
					Class<?>[] methodParams = method.getParameterTypes();
					
					Method webElementMethod = webElement.getClass().getMethod(action.m_action, methodParams);
					
					if(methodParams.length > 0)
					{
						if (action.m_action.toLowerCase().equals("sendkeys"))
						{
							if(action.m_text != null)
							{
								Core.Utils.writeLog("Calling webelement methode : " + action.m_action);
								webElement.sendKeys(action.m_text);
							}
							else if(action.m_key != null)
							{
								Core.Utils.writeLog("Calling webelement methode : " + action.m_action);
								webElement.sendKeys(Keys.valueOf(action.m_key));
							}
						}
						else
						{
							webElementMethod.invoke(webElement, action.m_text);
						}
					}
					else
					{
						Core.Utils.writeLog("Calling webelement methode : " + action.m_action);
						webElementMethod.invoke(webElement, null);	
					}
					
					break;
				}
			}
		}
		else
		{
			Core.Utils.writeLog("Calling webdriver methode : " + action.m_action);
			callWebDriverMethode(webDriver, action);
		}
		
	}

	private static By getFindBy(String findByType, String location) throws Exception
	{
		By byObject = null;
		
		Method[] methodArray = By.class.getDeclaredMethods();		
	
		By findBy = null;
		
		for(Method method : methodArray)
		{
			if(method.getName().endsWith(findByType))
			{
				Method byMethod = By.class.getMethod(findByType, new Class[]{String.class});			
				findBy = (By) byMethod.invoke(byObject, location);	
				
				return findBy;
			}
		}		
		
		return findBy;		
	}
	
	
	private static void callWebDriverMethode(WebDriver webDriver, StepAction action) throws Exception
	{
		Method[] methodArray = webDriver.getClass().getMethods();			
		
		for(Method method : methodArray)
		{
			if(method.getName().toLowerCase().equals(action.m_action.toLowerCase()))
			{
				Method webDriverMethod = webDriver.getClass().getMethod(action.m_action, method.getParameterTypes());			
				webDriverMethod.invoke(webDriver, action.m_text);			
				
				break;
			}
		}			
	}
	
}

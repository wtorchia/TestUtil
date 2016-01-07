package Core;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Core.TaskProccesor.StepAction;

public class SeleniumHelper 
{
	public static By GetFindBy(String findByType, String location) throws Exception
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
			}
		}		
		
		return findBy;
	}
	
	public static void RunDriverCommand(WebDriver webDriver, StepAction action)throws Exception
	{
		System.out.println("========");
		System.out.println("Action: " + action.m_action);					
		System.out.println("By type: " + action.m_by);
		System.out.println("By name: " + action.m_location);					
		System.out.println("Text: " +  action.m_text);					
		System.out.println("========");	
		
		if(action.m_by != null)
		{
			By by = GetFindBy(action.m_by, action.m_location);			
		}
		else
		{
			webDriver.get(action.m_text);
		}
		
		
	}

}

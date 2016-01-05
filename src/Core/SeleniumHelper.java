package Core;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.openqa.selenium.By;

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

}

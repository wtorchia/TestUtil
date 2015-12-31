package Core;

import java.lang.reflect.Method;

import org.openqa.selenium.By;

public class SeleniumHelper {
	public static By GetFindBy(String findByType, String location) throws Exception
	{
		By byObject = null;
		Method byMethod = By.class.getMethod(findByType, new Class[]{String.class});		
		By findBy = (By) byMethod.invoke(byObject, location);
		
		return findBy;
	}

}

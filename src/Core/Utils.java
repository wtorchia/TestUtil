package Core;

public class Utils
{

	public static Boolean debug = false;
	
	public static void writeLog(String logText)
	{
		if(debug)
		{
			System.out.println(logText);
		}
		
	}
	
	
}

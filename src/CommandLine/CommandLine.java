package CommandLine;

import java.util.Scanner;

import Core.DataTypes;
import Core.DataTypes.BrowserData;
import Core.DataTypes.ThreadMessage;
import Core.TaskProccesor;
import Core.ThreadManager;
import Core.WorkerThread;

public class CommandLine {

	static String getCommand()
	{
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		return input;
	}
	
	
	public static void main(String[] args) throws Exception 
	{		
		Core.Utils.debug = true;
		
		BrowserData browserData = new DataTypes().new BrowserData();
		browserData.m_browserType = "chrome";
		
		
		WorkerThread thread1 = ThreadManager.createWorkerThread("Thread-1", browserData);
	    thread1.start();
	  
	    
	    WorkerThread thread2 = ThreadManager.createWorkerThread("Thread-2", browserData);
	    thread2.start();
	    
	    
	    Core.Utils.writeLog("Sending message to thread");
	    
    	ThreadMessage threadMessage = new DataTypes().new ThreadMessage(thread1.m_threadName, "next");
    	threadMessage.m_task = TaskProccesor.getTask("Sample");
    	ThreadManager.sendWorkerMessage(threadMessage);
    		  
    	
	    Core.Utils.writeLog("Sending message to thread");
	    
    	ThreadMessage threadMessage2 = new DataTypes().new ThreadMessage(thread2.m_threadName, "next");
    	threadMessage2.m_task = TaskProccesor.getTask("Sample");
    	ThreadManager.sendWorkerMessage(threadMessage2);
    	
	    String message = "";
	    
	    while(!message.equals("EXIT"))
	    {
	    	Core.Utils.writeLog("Enter message for thread");
	    	message = getCommand();
	    	
	    	Core.Utils.writeLog("Sending message to thread");
	    	threadMessage = new DataTypes().new ThreadMessage(thread1.m_threadName, message);
	    	
	    	threadMessage = new DataTypes().new ThreadMessage(thread2.m_threadName, message);
	    	
	    	ThreadManager.sendWorkerMessage(threadMessage);
	    }
		
		
		
    }
}

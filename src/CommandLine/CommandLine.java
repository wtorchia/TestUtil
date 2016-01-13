package CommandLine;

import java.util.ArrayList;
import java.util.Scanner;

import Core.BrowserManager;
import Core.BrowserManager.BrowserData;
import Core.SeleniumWrapper;
import Core.TaskProccesor;
import Core.TaskProccesor.StepAction;
import Core.TaskProccesor.TaskStep;

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
		
		BrowserManager browserManager = new Core.BrowserManager();
		BrowserData Chrome1  = browserManager.startChrome();
				
		TaskProccesor taskProccesor = new TaskProccesor();		
		
		ArrayList<Core.TaskProccesor.Task> taskList = taskProccesor.GetAllTasks(); 
		
		
		for(Core.TaskProccesor.Task task : taskList)
		{
			Core.Utils.writeLog("Task name: " + task.m_name);

			Core.Utils.writeLog("----------------------------");
			
			for(TaskStep step : task.m_steps)
			{
				Core.Utils.writeLog("Step: " + step.m_stepNumber);
				for(StepAction action : step.m_stepActions)
				{
					
					SeleniumWrapper.callWebDriver(Chrome1.m_webDriver, action);					
					
					//getCommand();
				}
			}
			Core.Utils.writeLog("----------------------------");
		}
				
		
		
    }
}

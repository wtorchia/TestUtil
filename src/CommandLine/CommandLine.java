package CommandLine;

import java.util.ArrayList;
import java.util.Scanner;

import Core.BrowserManager;
import Core.BrowserManager.BrowserData;
import Core.SeleniumHelper;
import Core.TaskProccesor;
import Core.TaskProccesor.StepAction;
import Core.TaskProccesor.Task;
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
		
		BrowserManager browserManager = new Core.BrowserManager();
		BrowserData Chrome1  = browserManager.startChrome();
		
		Chrome1.m_webDriver.get("http:\\www.google.com");
		
		//By by = SeleniumHelper.GetFindBy("id", "lst-ib");
		
		//WebElement thing = Chrome1.m_webDriver.findElement(by);
		
		//thing.sendKeys("TESTING");
		
		//thing.sendKeys(Keys.RETURN);		
		
		
		
		
		
		TaskProccesor taskProccesor = new TaskProccesor();		
		
		ArrayList<Task> taskList = taskProccesor.GetAllTasks(); 
		
		
		
		
		for(Task task : taskList){
			System.out.println("Task name: " + task.m_name);

			System.out.println("----------------------------");
			for(TaskStep step : task.m_steps)
			{
				System.out.println("Step: " + step.m_stepNumber);
				for(StepAction action : step.m_stepActions)
				{
					SeleniumHelper.RunDriverCommand(Chrome1.m_webDriver, action);
					getCommand();
				}
			}
			System.out.println("----------------------------");
		}
				
		
		
    }
}

package Core;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class DataTypes 
{
	public class BrowserData
	{
		public WebDriver m_webDriver;
		public String m_browserType;		
	}
	
	public class ThreadMessage
	{
		public String m_message;
		public String m_threadName;
		public Task m_task;
		
		public ThreadMessage(String threadName, String message)
		{
			m_threadName = threadName;
			m_message = message;
		}
	}
	
	public class StepAction 
	{
		public String m_action; 
		public String m_by;
		public String m_location;
		public String m_text;		
		public String m_key;
		
		StepAction(String action,String by, String location, String text, String key)
		{
			m_action = action;
			m_by = by;
			m_location = location;	
			m_text = text;
			m_key = key;
		}
	}
	
	public class TaskStep
	{
		public int m_stepNumber;
		public ArrayList<StepAction> m_stepActions;		
		
		public TaskStep(int stepNumber, ArrayList<StepAction> stepActions)
		{
			m_stepActions = stepActions;
			m_stepNumber = stepNumber;
		}
	}
	
	public class Task
	{
		public String m_name;
		public ArrayList<TaskStep> m_steps;	
		
		public Task(String name, ArrayList<TaskStep> steps)
		{
			m_name = name;
			m_steps = steps;
		}
	}

}

package Core;

import Core.DataTypes.BrowserData;
import Core.DataTypes.StepAction;
import Core.DataTypes.Task;
import Core.DataTypes.TaskStep;
import Core.DataTypes.ThreadMessage;

public class WorkerThread implements Runnable
{
	public String m_threadName;
	public BrowserData m_browserData;
	public String m_status;
	public Task m_task;
	public int m_currentStep = 0;
	public String m_message;
	
	private Thread m_thread;
	
	public WorkerThread(String threadName)
	{
		m_threadName = threadName;
		Core.Utils.writeLog("Creating " +  m_threadName );
	}

	@Override
	public void run()
	{
		Core.Utils.writeLog("Running " +  m_threadName );
		
		ThreadMessage threadMessage = null;
		
	    while(threadMessage == null || !threadMessage.m_message.equals("EXIT"))
	    {
	    	try 
	    	{
	    		if(threadMessage != null)
	    		{
	    			if(threadMessage.m_task != null)
	    			{
	    				m_task = threadMessage.m_task;
	    				m_currentStep = 0;
	    			}
	    			
	    			m_message = threadMessage.m_message;	
	    			
	    			processMessage();
	    		}	    		
	    		
	    		Thread.sleep(5000);
	    		
	    		Core.Utils.writeLog("Checking for message for " + m_threadName);
	    		
	    		threadMessage = ThreadManager.getWorkerMessage(m_threadName); 
			} 
	    	catch (Exception e) 
	    	{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		 
	    Core.Utils.writeLog("Shutting down "  + m_threadName);
	}
	
	public void start()
	{
		 Core.Utils.writeLog("Starting " +  m_threadName );
		 if (m_thread == null)
		 {
			 m_thread = new Thread (this, m_threadName);
			 m_thread.start ();
    	 
			 if(m_browserData != null)
			 {
				 Core.Utils.writeLog("Starting " + m_browserData.m_browserType  + " browser for worker thread " + m_threadName);
				 
				 switch (m_browserData.m_browserType.toLowerCase())
				 {
				 	case  "chrome": 
				 		m_browserData = BrowserUtil.startChrome();		
				 	break;
				 	
				 	default: 
				 		Core.Utils.writeLog("Unable to start browser type " + m_browserData.m_browserType);
				 	break;
				 }
				 
			 }
		 }
	}
	
	
	private void processMessage() throws Exception
	{
		Core.Utils.writeLog("Got message :" + m_message);
		
		if(m_task != null)
		{
			Core.Utils.writeLog("Got new task " + m_task.m_name);
			
			if(m_message.toLowerCase().equals("next"))
			{
				m_currentStep ++;
				runTaskStep(m_currentStep);
			}
			else if(m_message.toLowerCase().equals("full"))
			{
				runFullTask();
			}
			
		}
	}
	
	
	private void runFullTask() throws Exception
	{
		Core.Utils.writeLog("Task name: " + m_task.m_name);

		Core.Utils.writeLog("----------------------------");
		
		for(int i = 1; i <= m_task.m_steps.size(); i++)
		{
			runTaskStep(i);
		}
	
		Core.Utils.writeLog("----------------------------");
	}
	
	
	private void runTaskStep(int taskStep) throws Exception
	{
		Core.Utils.writeLog("Running step " + taskStep);
		TaskStep step = TaskProccesor.GetTaskStep(m_task, taskStep);
		
		m_currentStep = taskStep;
		
		for(StepAction action : step.m_stepActions)
		{
			SeleniumWrapper.callWebDriver(m_browserData.m_webDriver, action);						
		}
	}
	
}
 
package Core;

import java.util.ArrayList;
import java.util.Collection;

import Core.DataTypes.BrowserData;
import Core.DataTypes.ThreadMessage;

public class ThreadManager 
{
	static private Collection<WorkerThread> m_threadList = new ArrayList<WorkerThread>();
	static private Collection<ThreadMessage> m_MessageList = new ArrayList<ThreadMessage>();
	
	public static WorkerThread createWorkerThread(String threadName, BrowserData browserData)
	{
		WorkerThread workerThread = new WorkerThread(threadName);
		workerThread.m_browserData = browserData;
		
		m_threadList.add(workerThread);
		
		return workerThread;
	}
	
	
	public static ThreadMessage getWorkerMessage(String threadName)
	{
		Utils.writeLog(threadName + " is looking for a message");
		
		for(ThreadMessage threadMessage : m_MessageList)
		{
			if(threadMessage.m_threadName.equals(threadName))
			{
				Utils.writeLog("Message found !!!");
				m_MessageList.remove(threadMessage);
				return threadMessage;
			}
		}
		Utils.writeLog("No message found");
		return null;
	}
	
	public static void sendWorkerMessage(ThreadMessage threadMessage)
	{
		Utils.writeLog("Adding message for " + threadMessage.m_threadName);
		m_MessageList.add(threadMessage);
	}
	
	
	
	
}

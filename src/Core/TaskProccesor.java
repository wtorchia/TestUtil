package Core;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Core.DataTypes.StepAction;
import Core.DataTypes.Task;
import Core.DataTypes.TaskStep;


public class TaskProccesor
{
	public static String m_xmlPath = "C:\\Users\\wtorchia\\workspace\\TestUtil\\Tasks";	
	
	public static ArrayList<String> getTaskFileNames()
	{
		File files = new File(m_xmlPath);
		
		ArrayList<String> taskFiles = new ArrayList<String>(Arrays.asList(files.list()));
		
		return taskFiles;
	}
	
	
	public static ArrayList<Task> getAllTasks() throws Exception
	{
		ArrayList<String> fileList = getTaskFileNames();
		
		ArrayList<Task> taskList = new ArrayList<Task>();
		
		for(String file : fileList)
		{
			File fXmlFile = new File(m_xmlPath + "\\" + file);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();		
			
			NodeList taskNameNode = doc.getElementsByTagName("TaskName");
			
			String taskName = taskNameNode.item(0).getTextContent();
					
			//get all the steps for the file
			NodeList stepsNodeList = doc.getElementsByTagName("Step");
			
			ArrayList<TaskStep> steps = new ArrayList<TaskStep>();	
			
			for (int stepIterator = 0; stepIterator < stepsNodeList.getLength(); stepIterator++) 
			{
				Element element = (Element) stepsNodeList.item(stepIterator);
				
				//Get Step number
				int stepNumber = Integer.parseInt(element.getElementsByTagName("StepNumber").item(0).getTextContent()); 
				
				//get actions for the step
				NodeList actionsNode = element.getElementsByTagName("Actions").item(0).getChildNodes(); 
				
				ArrayList<StepAction> stepActions = new ArrayList<StepAction>();
				
				for (int actionIterator = 0; actionIterator < actionsNode.getLength(); actionIterator++) 
				{
					if(actionsNode.item(actionIterator).hasChildNodes())
					{
						String action = actionsNode.item(actionIterator).getNodeName();
						
						//Get by element if it exists
						NodeList byNode = ((Element) actionsNode.item(actionIterator)).getElementsByTagName("By");
						
						String byType = null;
						String byLocation = null;
						String text = null;
						String key = null;
						
						if(byNode.getLength() != 0)
						{
							byType = ((Element)byNode.item(0)).getElementsByTagName("Type").item(0).getTextContent().trim();
							byLocation = ((Element)byNode.item(0)).getElementsByTagName("Name").item(0).getTextContent().trim();
						}
						else
						{
							text = actionsNode.item(actionIterator).getTextContent().trim();
						}
						
						NodeList textNode = ((Element) actionsNode.item(actionIterator)).getElementsByTagName("Text");
						
						if(textNode.getLength() != 0)
						{
							text = textNode.item(0).getTextContent();					
						}
						
						NodeList keyNode = ((Element) actionsNode.item(actionIterator)).getElementsByTagName("Key");
						
						if(keyNode.getLength() != 0)
						{
							key = keyNode.item(0).getTextContent();					
						}
												
						StepAction stepAction = new DataTypes().new StepAction(action, byType, byLocation, text, key);
						stepActions.add(stepAction);					
					}
				}
				
				TaskStep taskStep = new DataTypes().new TaskStep(stepNumber,stepActions);
				steps.add(taskStep);			
			}
			
			Task task = new DataTypes().new Task(taskName, steps);
			taskList.add(task);
		}
		
		return taskList;
	}
	
	
	static public Task getTask(String taskName) throws Exception
	{
		for(Task task : getAllTasks())
		{
			if(task.m_name.equals(taskName))
			{
				return task;
			}
		}
		
		return null;
	}
	
	
	static public TaskStep GetTaskStep(Task task, int stepNumber)
	{
		for(TaskStep taskStep : task.m_steps)
		{
			if(taskStep.m_stepNumber == stepNumber)
			{
				return taskStep;
			}
		}
		
		return null;
	}	
}

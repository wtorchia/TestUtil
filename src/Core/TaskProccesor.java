package Core;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class TaskProccesor
{
	public String m_xmlPath = "C:\\Users\\wtorchia\\workspace\\TestUtil\\Tasks";
	
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
	
	
	public ArrayList<String> GetTaskFileNames()
	{
		File files = new File(m_xmlPath);
		
		ArrayList<String> taskFiles = new ArrayList<String>(Arrays.asList(files.list()));
		
		return taskFiles;
	}
	
	
	public ArrayList<Task> GetAllTasks() throws Exception
	{
		ArrayList<String> fileList = GetTaskFileNames();
		
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
												
						StepAction stepAction = new StepAction(action, byType, byLocation, text, key);
						stepActions.add(stepAction);					
					}
				}
				
				TaskStep taskStep = new TaskStep(stepNumber,stepActions);
				steps.add(taskStep);			
			}
			
			Task task = new Task(taskName, steps);
			taskList.add(task);
		}
		
		return taskList;
	}
	
	
	public TaskStep GetTaskStep()
	{
		return null;
	}
	
	
}

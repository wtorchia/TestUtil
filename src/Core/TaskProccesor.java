package Core;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class TaskProccesor
{
	public class StepAction 
	{
		public String m_action;
		public String m_by;
		public String m_location;
		
		StepAction(String action,String by, String location)
		{
			m_action = action;
			m_by = by;
			m_location = location;			
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
	
	public String m_xmlPath = "C:\\Users\\wtorchia\\workspace\\TestUtil\\Tasks";
	
	
	public ArrayList<String> GetTaskFileNames()
	{
		File files = new File(m_xmlPath);
		
		ArrayList<String> taskFiles = new ArrayList<String>(Arrays.asList(files.list()));
		
		return taskFiles;
	}
	
	
	public ArrayList<Task> GetAllTasks() throws Exception
	{
		File fXmlFile = new File(m_xmlPath + "\\sample.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		NodeList taskNameNode = doc.getElementsByTagName("TaskName");
		
		System.out.println("Task name: " + taskNameNode.item(0).getTextContent());
				
				
		System.out.println("----------------------------");
		
		NodeList stepsNodeList = doc.getElementsByTagName("Step");
		
		for (int i = 0; i < stepsNodeList.getLength(); i++) 
		{
			
			Element element = (Element) stepsNodeList.item(i);			
			
			String stepNumber = element.getElementsByTagName("StepNumber").item(0).getTextContent(); 
			
			System.out.println("Step: " + stepNumber);
			
			NodeList actionsNode = element.getElementsByTagName("Actions").item(0).getChildNodes(); 
			
			for (int j = 0; j < actionsNode.getLength(); j++) 
			{
				
				if(actionsNode.item(j).hasChildNodes())
				{
					
					System.out.println("========");
					System.out.println("Action Name: " + actionsNode.item(j).getNodeName());					
					
					NodeList byNode = ((Element) actionsNode.item(j)).getElementsByTagName("By");
					
					if(byNode.getLength() != 0)
					{
						String byType = ((Element)byNode.item(0)).getElementsByTagName("Type").item(0).getTextContent();
						String byLocation = ((Element)byNode.item(0)).getElementsByTagName("Name").item(0).getTextContent();
						
						System.out.println("By type:" + byType);
						System.out.println("By name:" + byLocation);
					
					}
					else
					{
						System.out.println("URL text: " + actionsNode.item(j).getTextContent());
					}
					
					NodeList textNode = ((Element) actionsNode.item(j)).getElementsByTagName("Text");
					
					if(textNode.getLength() != 0)
					{
						System.out.println("Text to enter: " + textNode.item(0).getTextContent());
					}
										
					
					System.out.println("========");
				}
			
				
			}
		}
	
		return null;
	}
	
	
	/*	
	public ArrayList<String> GetTaskNames()
	{
		ArrayList<String> taskList = new ArrayList<String>(); 
		
		for(String task : GetTaskFileNames())
		{
			taskList.add(task.split(".xml")[0]);
		}
				
		return taskList;
	}*/
	
	public TaskStep GetTaskStep()
	{
		return null;
	}
	
	
}

package Core;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


public class TaskProccesor
{
	
	public String m_xmlPath = "C:\\Users\\wtorchia\\workspace\\TestUtil\\XML";
	
		
	public ArrayList<String> GetTaskList()
	{
		File f = new File(m_xmlPath);
		ArrayList<String> taskFiles = new ArrayList<String>(Arrays.asList(f.list()));
		
		return taskFiles;
	}
	
	public void GetTaskStep()
	{
		
	}
	
	public void RunTaskStep()
	{
		
	}
}

package CommandLine;

import java.awt.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import Core.BrowserManager;
import Core.BrowserManager.BrowserData;
import Core.TaskProccesor.Task;
import Core.SeleniumHelper;
import Core.TaskProccesor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CommandLine {

	static String getCommand()
	{
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		return input;
	}
	
	
	
	public static void main(String[] args) throws Exception 
	{		
		
		TaskProccesor taskProccesor = new TaskProccesor();
		
		//ArrayList<String> taskNameList = taskProccesor.GetTaskNames();
		
		ArrayList<Task> taskList = taskProccesor.GetAllTasks();
		
		/*
		BrowserManager browserManager = new Core.BrowserManager();
		BrowserData Chrome1  = browserManager.startChrome();
		
		Chrome1.m_webDriver.get("http:\\www.google.com");
		
		By by = SeleniumHelper.GetFindBy("id", "lst-ib");
		
		WebElement thing = Chrome1.m_webDriver.findElement(by);
		
		thing.sendKeys("TESTING");
		
		thing.sendKeys(Keys.RETURN);		
		
		*/
		getCommand();
    }
}

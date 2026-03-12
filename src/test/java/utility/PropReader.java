package utility;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class PropReader {
	
	public static String readPropFile(String propToBeRead) throws IOException 
	{
		Properties prop=new Properties();
		
		String projectPath = System.getProperty("user.dir");
		 String configFilePath = projectPath+"\\src\\test\\resources\\Config.properties";
		
		System.out.println(projectPath);
		System.out.println(configFilePath);
		
		FileInputStream fis=new FileInputStream(configFilePath);
			
		prop.load(fis);
		
		 String value = prop.getProperty(propToBeRead);
		 System.out.println(value);
		 return value;
	}
	public static void main(String[] args) throws IOException
	{
		readPropFile("refresh_token");
		
	}

}

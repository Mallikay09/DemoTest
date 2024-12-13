package com.Module1.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/*
 * This class is used to get the file from config folder and load 
 * Next A method to get the data from the file
 * In config.properties file placed test data url and browser name
 */

public class PropertyReaderJavaClass {
	Properties p;
	public PropertyReaderJavaClass()
	{
		p=new Properties();
		File f1=new File(System.getProperty("user.dir")+"//config//config.properties");
		try {
			FileInputStream fs=new FileInputStream(f1);
			p.load(fs);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Method to get the data by passing the key
	public String getData(String key)
	{
		return p.getProperty(key);
	}

}

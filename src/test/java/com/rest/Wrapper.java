package com.rest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Wrapper {
		
	
	protected Properties prop=new Properties();
	protected FileInputStream file;
    {
        try
        {
            file = new FileInputStream("./src/test/resources/config.properties");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}

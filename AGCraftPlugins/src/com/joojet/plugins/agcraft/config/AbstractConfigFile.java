package com.joojet.plugins.agcraft.config;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.yaml.snakeyaml.Yaml;

import com.joojet.biblefetcher.api.APIKeyReader;
import com.joojet.biblefetcher.database.CreateDatabase;

public abstract class AbstractConfigFile
{
	/** Name of the config file including the extension */
	protected String configFileName;
	/** The full path in where the config file is located */
	protected String configFilePath;
	/** A hashmap containing keys and values for each config file  */
	protected HashMap <String, Object> configFileValues;
	
	/** Creates or loads in a new server config YAML file in the AGCraft directory folder
	 * 		@param configFileName - Filename of the config file */
	public AbstractConfigFile (String configFileName)
	{
		this.configFileName = configFileName;
		this.configFilePath = this.getConfigPath (this.configFileName);
		this.configFileValues = new HashMap <String, Object> ();
		
		try 
		{
			// If the file does not exist, create a new config file
			if (!APIKeyReader.checkConfigFile(this.configFilePath))
			{
				this.createConfigFile();
				System.out.println ("Created a new config file at " + this.configFilePath);
			}
			
			// Once the config file is created (or already existing), load it into the internal data structure
			this.loadConfigFile();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/** Returns a hashmap containing default keys and values used for config file creation
	 *  To be overridden by child classes with custom specifications. */
	protected abstract HashMap <String, Object> createConfigFileContents ();
	
	/** Creates a new config file initially loaded with defaults defined by
	 *  the abstract function, createConfigFileContents.
	 *  @throws IOException - File I/O error */
	protected void createConfigFile () throws IOException
	{
		Yaml yaml = new Yaml ();
		FileWriter writer = new FileWriter (this.configFilePath);
		yaml.dump(this.createConfigFileContents(), writer);
	}
	
	/** Returns the value of a key written in the config file
	 *  @param key - Value's key to be searched
	 *  @return The key's value if it exists */
	public Object getValue (String key)
	{
		return this.configFileValues.get(key);
	}
	
	/** Loads in the contents of a config file into memory and stores its data into an internal
	 *  hash map. 
	 *  @throws IOException - File I/O error */
	protected void loadConfigFile () throws IOException
	{
		Yaml yaml = new Yaml ();
		InputStream configFile = new FileInputStream (this.configFilePath);
		this.configFileValues = yaml.load(configFile);
	}
	
	
	/** Returns the location path of a database file */
	public String getConfigPath (String configFileName)
	{
		return "./" + CreateDatabase.kDirectoryPath + "/" + configFileName;
	}
}

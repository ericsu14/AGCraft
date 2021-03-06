package com.joojet.plugins.agcraft.config;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import com.joojet.biblefetcher.api.APIKeyReader;
import com.joojet.biblefetcher.database.CreateDatabase;
import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;

public abstract class AbstractConfigFile
{
	/** Name of the config file including the extension */
	protected String configFileName;
	/** The full path in where the config file is located */
	protected String configFilePath;
	/** A hashmap containing keys and values for each config file  */
	protected HashMap <String, Object> configFileValues;
	
	/** Creates a new unloaded instance of this config file class.
	 * 		@param configFileName - Filename of the config file */
	public AbstractConfigFile (String configFileName)
	{
		this.configFileName = configFileName;
		this.configFilePath = this.getConfigPath (this.configFileName);
		this.configFileValues = new HashMap <String, Object> ();
	}
	
	/** Creates or loads in a config file from the directory where it is stored */
	public void reload ()
	{
		try 
		{
			// If the file does not exist, create a new config file
			if (!APIKeyReader.checkConfigFile(this.configFilePath))
			{
				this.createConfigFile();
				AGCraftPlugin.logger.info ("Created a new config file at " + this.configFilePath);
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
		DumperOptions dumperOptions = new DumperOptions ();
		dumperOptions.setPrettyFlow(true);
		Yaml yaml = new Yaml (dumperOptions);
		FileWriter writer = new FileWriter (this.configFilePath);
		yaml.dump(this.createConfigFileContents(), writer);
	}
	
	/** Safely returns the value referenced by its key as a double.
	 *  @param - Key associated to the requested value  */
	public Double getValueAsDouble (String key)
	{
		Object value = this.getValue(key, true);
		if (value != null && value instanceof Double)
		{
			return (Double) value;
		}
		return 0.0;
	}
	
	/** Safely returns the value referenced by its key as a boolean.
	 *  @param - key associated to the requested value */
	public Boolean getValueAsBoolean (String key)
	{
		Object value = this.getValue(key, true);
		if (value != null && value instanceof Boolean)
		{
			return (Boolean) value;
		}
		return false;
	}
	
	/** Safely returns the value referenced by its key as an integer */
	public Integer getValueAsInteger (String key)
	{
		Object value = this.getValue(key, true);
		if (value != null && value instanceof Integer)
		{
			return (Integer) value;
		}
		return 0;
	}
	
	/** Returns the value of a key written in the config file
	 *  @param key - Value's key to be searched
	 *  @param outputInformation - Output true if debug information is logged to the console
	 *  @return The key's value if it exists */
	protected Object getValue (String key, boolean outputInformation)
	{
		if (this.configFileValues.containsKey(key))
		{
			Object value = this.configFileValues.get(key);
			if (outputInformation)
			{
				AGCraftPlugin.logger.config("Retrieved " + value.toString() + " for the key, " + key);
			}
			return value;
		}
		
		// Otherwise, attempt to load in a default value for this key from the generated mappings
		HashMap <String, Object> defaultMapping = this.createConfigFileContents();
		if (defaultMapping.containsKey(key))
		{
			Object value = defaultMapping.get(key);
			if (outputInformation)
			{
				AGCraftPlugin.logger.warning("Could not retrieve a value for " + key + ". Using the default value of " + value + " instead.");
			}
			return value;
		}
		// If all else fails, return NULL.
		return null;
	}
	
	/** Returns the value of a key as a list of Strings
	 * 	@param key - Value's key to be looked up
	 *  @return The key's value as a list of strings, if it is originally written in that format.
	 *  @throws RuntimeException if the key's value is not properly written as a list of strings */
	public List <String> getValueAsList (String key) throws RuntimeException
	{
		return this.convertObjectToList(this.configFileValues.get(key));
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
	
	/** Safely converts an object to a generic list of Strings.
	 *  The object must be an instance of a list for this to work,
	 *  otherwise a runtime exception will be thrown.
	 *  
	 *  This function is necessary to parse list of strings specified in the config file
	 *  while avoiding the use of suppress warnings.
	 *  
	 *  Code stolen from:
	 *     - https://stackoverflow.com/questions/14642985/type-safety-unchecked-cast-from-object-to-listmyobject
	 * 	@param value - object to be converted to a list
	 *  @throws RuntimeException if the @param value is not an instance of a list */
	protected List<String> convertObjectToList (Object value) throws RuntimeException
	{
		if (!(value instanceof List))
		{
			throw new RuntimeException ("Error! Expected a list but got a " + value.getClass().getTypeName() + " instead!");
		}
		
		Object curr;
		List <String> list = new ArrayList <String> ();

		for (int i = 0; i < ((List<?>)value).size(); ++i)
		{
			curr = ((List<?>) value).get(i);
			if (curr instanceof String)
			{
				list.add((String) curr);
			}
		}
		
		return list;
	}
	
	/** Searches a search trie for a value based on a key. If not found, throw an error and use a
	 *  default.
	 *  @param interpreter - Instance of a search term interpreter to be searched
	 *  @param key - Key used in the interpreter to search for the value
	 *  @param defaultValue - Default value to be used in the event of failure */
	public <E> E searchElementFromInterpreter (AbstractInterpreter<E> interpreter, String key, E defaultValue)
	{
		E result = interpreter.searchTrie(this.getValue(key, false).toString());
		if (result == null)
		{
			AGCraftPlugin.logger.warning ("Error: Cannot find value for " + key + ". Using default value " + defaultValue.toString() + " instead...");
			result = defaultValue;
		}
		else
		{
			AGCraftPlugin.logger.config ("Loaded variable " + result.toString() + " for " + key + "!");
		}
		return result;
	}
	
	
	/** Returns the location path of a database file */
	public String getConfigPath (String configFileName)
	{
		return "./" + CreateDatabase.kDirectoryPath + "/" + configFileName;
	}
}

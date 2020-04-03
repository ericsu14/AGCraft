package com.joojet.biblefetcher.api;

import java.util.List;
import java.util.stream.Stream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import com.joojet.biblefetcher.constants.APIName;
import com.joojet.biblefetcher.database.CreateDatabase;

public class APIKeyReader 
{
	public static final String configFileName = "config";
	public static final String configFileSuffix = ".txt";
	
	/** Creates a config file that stores API keys 
	 * @throws IOException - thrown if file creation has failed. */
	public static void createConfigFile () throws IOException
	{
		Stream <String> content = Arrays.asList (APIName.values()).stream().map(entity -> {
			return entity.name() + ": ";
		});
		
		List <String> lines = Arrays.asList(content.toArray(String[]::new));
		
		Files.write(Paths.get(getConfigFilePath()), 
				lines, 
				StandardCharsets.UTF_8, 
				StandardOpenOption.CREATE, 
				StandardOpenOption.APPEND);
		
		System.out.println ("Created a config file at " + getConfigFilePath() + ". Please update this file with the needed API keys.");
	}
	
	/** Scans the config file and returns the API key for the passed APIName constant
	 * 		@APIName - The name of the API we are getting its API key for.
	 * 		@returns The API key stored in the text file
	 * 		@throws IOException if there is an issue parsing the config file
	 * 		@throws RuntimeException if the config file is missing. This would also create a new, empty file. */
	public static String getAPIKey (APIName id) throws IOException, RuntimeException
	{
		if (!checkConfigFile())
		{
			createConfigFile();
			throw new RuntimeException ("Config file not found.");
		}
		
		List <String> lines = readFileAsList (getConfigFilePath());
		
		for (String line : lines)
		{
			String [] tokens = line.split("([:])");
			
			if (tokens[0].equalsIgnoreCase(id.name()))
			{
				String key = tokens[1].trim();
				if (key.isEmpty())
				{
					throw new RuntimeException ("API key for  " + id.name() + " not provided. Please update the key in " + getConfigFilePath());
				}
				return key;
			}
		}
		throw new RuntimeException ("API key for  " + id.name() + " not provided. Please update the key in " + getConfigFilePath());
	}
	
	/** Returns the URL for the configuration file */
	public static String getConfigFilePath ()
	{
		StringBuilder path = new StringBuilder (".\\");
		path.append(CreateDatabase.kDirectoryPath);
		path.append("\\");
		path.append(configFileName);
		path.append(configFileSuffix);
		return path.toString();
	}
	
	/** Returns true if the config file already exists in the plugin directory 
	 * 		@throws IOException - If there is a problem reading this file*/
	public static boolean checkConfigFile () throws IOException
	{
		File file = new File (getConfigFilePath());
		return file.exists();
	}
	
	private static List <String> readFileAsList (String filename) throws IOException
	{
		return Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8); 
	}
	
}

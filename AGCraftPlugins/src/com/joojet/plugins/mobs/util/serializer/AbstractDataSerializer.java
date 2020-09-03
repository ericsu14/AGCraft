package com.joojet.plugins.mobs.util.serializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.joojet.biblefetcher.database.CreateDatabase;

/** A util class that serializes and deserializes objects into files */
public class AbstractDataSerializer <E>
{
	/** File name of the serialized object */
	protected String fileName;
	/** Full path name of the serialized object */
	protected String pathName;
	
	public AbstractDataSerializer (String fileName)
	{
		this.fileName = fileName;
		this.pathName = this.generateSerializerPath(this.fileName);
	}
	
	/** Serializes the object passed into this instance
	 * into a file stored at the AGPlugins directory */
	public void serializeFile (E object)
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream (this.pathName);
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			oos.writeObject(object);
			oos.close();
			fos.close();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	/** Retrieves the object that was previously serialized into a file
	 * 	@return - Null if the file is  */
	@SuppressWarnings("unchecked")
	public E getSerializedObject ()
	{
		E object = null;
		try 
		{
			FileInputStream fis = new FileInputStream (this.pathName);
			ObjectInputStream ois = new ObjectInputStream (fis);
			object = (E) ois.readObject();
			ois.close();
			fis.close();
		}
		
		catch (IOException ioe)
		{
			System.out.println (ioe.getMessage());
		}
		catch (ClassNotFoundException c)
		{
			System.out.println (c.getMessage());
		}
		
		return object;
	}
	
	/** Returns the serialized object's file name */
	public String getFileName ()
	{
		return this.fileName;
	}
	
	/** Returns the serialized object's directory path as a string*/
	public String getPathName ()
	{
		return this.pathName;
	}
	
	/** Returns the location path of a database file */
	protected String generateSerializerPath (String serializerFileName)
	{
		return "./" + CreateDatabase.kDirectoryPath + "/" + serializerFileName;
	}
}

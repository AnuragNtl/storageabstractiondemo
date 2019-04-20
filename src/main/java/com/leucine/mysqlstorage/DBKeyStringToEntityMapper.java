package com.leucine.mysqlstorage;

import java.io.IOException;

import com.leucine.storage.SPEntity;
import com.leucine.storage.SPException;
import com.leucine.storage.StringToEntityMapper;

public class DBKeyStringToEntityMapper implements StringToEntityMapper{

	public SPEntity getFromString(String path, boolean isFile) throws SPException {
		
		try
		{
		String pathEntities[]=path.split("/");
		if(isFile && pathEntities.length==1)
		{
			DBFile file=new DBFile();
			file.setBaseId(null);
			file.setBasePathString("");
			file.setName(pathEntities[0]);
			return file;
		}
		else
		{
			DBDirectory dir=new DBDirectory();
			dir.setBaseId(null);
			dir.setBasePathString("");
			dir.setName(pathEntities[0]);
			if(pathEntities.length==1)
				return dir;
			for(int i=1;i<pathEntities.length-1;i++)
			{
				if(!dir.exists())
					throw new SPException("Directory does not exist");
				dir=(DBDirectory)dir.getItemAsDirectory(pathEntities[i]);
			}
			if(isFile)
				return (DBFile)dir.getItemAsFile(pathEntities[pathEntities.length-1]);
				else
				return (DBDirectory)dir.getItemAsDirectory(pathEntities[pathEntities.length-1]);
		}
	}
	catch(IOException ioexcepn)
	{
		throw new SPException(ioexcepn);
	}
}
}

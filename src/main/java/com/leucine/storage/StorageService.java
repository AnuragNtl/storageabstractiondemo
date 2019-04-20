package com.leucine.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class StorageService {
	private StorageProvider storageProvider;
	private static StorageService storageService;
	private StringToEntityMapper mapper;
private StorageService()throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException
{
init();
}
public StorageProvider getStorageProvider()
{
return storageProvider;
}
public static StorageService getStorageService() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException
{
	if(storageService==null)
		storageService=new StorageService();
	return storageService;
}
private void init()throws IOException,ClassNotFoundException, InstantiationException, IllegalAccessException
{
BufferedReader br=new BufferedReader(new FileReader("StorageProvider.conf"));
String mainClass=br.readLine();
String rd=null;
HashMap<String,String> options=new HashMap<String,String>();
while((rd=br.readLine())!=null)
{
	String opt[]=rd.split(" ");
	if(opt.length>=2)
	{
		options.put(opt[0],opt[1]);
	}
}
br.close();
Class<? extends StorageProvider> storageProviderClass=
(Class<? extends StorageProvider>)Class.forName(mainClass);
storageProvider=storageProviderClass.newInstance();
storageProvider.setConnectionOptions(options);
mapper=storageProvider.getEntityMapperClass().newInstance();
}
/**
 * get a directory from storage provider represented by path
 * @param path String representation of path
 * @return SPDirectory that  represents the directory
 */
public SPDirectory getDirectory(String path) throws SPException
{
	SPEntity entity=mapper.getFromString(path,false);
	if(! (entity instanceof SPDirectory))
		throw new SPException("Mapper should return a directory");
	SPDirectory dir=(SPDirectory)entity ;
	return dir;
}
/**
 * get a file from storage provider represented by path
 * @param path String representation of path
 * @return SPFile that  represents the directory
 */
public SPFile getFile(String path) throws SPException
{
	SPEntity entity=mapper.getFromString(path,true);
	if(!(entity instanceof SPFile))
		throw new SPException("Mapper should return a file");
	SPFile file=(SPFile)entity;
	return file;
}
public StringToEntityMapper getMapper()
{
	return mapper;
}
}

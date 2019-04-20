package com.leucine.filesystemstorage;

import java.io.File;
import java.io.IOException;

import com.leucine.storage.SPDirectory;
import com.leucine.storage.SPEntity;
import com.leucine.storage.SPException;
import com.leucine.storage.SPFile;

public class FileSystemDirectory extends SPDirectory{

	private File dir;
	private String s=File.separator;
	public FileSystemDirectory(){}
	public FileSystemDirectory(String id)
	{
		super(id);
		dir=new File(id);
	}
	@Override
	public SPEntity[] list() {
		File files[]=dir.listFiles();
		SPEntity entities[]=new SPEntity[files.length];
		for(int i=0;i<files.length;i++)
		{
			if(files[i].isDirectory())
			entities[i]=new FileSystemDirectory(files[i]+"");
			else
				entities[i]=new FileSystemFile(files[i]+"");
		}
	return entities;
	}

	@Override
	public SPFile getItemAsFile(String name) throws SPException {
String dirName=((dir+"").endsWith(s)?dir+"":dir+s);
		return new FileSystemFile(dirName+name);
	}

	@Override
	public SPDirectory getItemAsDirectory(String directory) throws SPException {
		String dirName=((dir+"").endsWith(s)?dir+"":dir+s);
		// TODO Auto-generated method stub
		return new FileSystemDirectory(dirName+directory);
	}

	@Override
	public boolean remove() throws IOException {
		if(dir.exists())
			return dir.delete();
		return false;
	}

	@Override
	public void create() throws IOException {
		String dirName=((dir+"").endsWith(s)?dir+"":dir+s);
		new File(dirName).mkdir();
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists() throws IOException {
		// TODO Auto-generated method stub
		return dir.exists();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return dir.getName();
	}

}

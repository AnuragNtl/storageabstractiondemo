package com.leucine.filesystemstorage;

import java.io.IOException;
import java.io.InputStream;

import com.leucine.storage.SPFile;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;

public class FileSystemFile extends SPFile{
public String filePath="";
public File file;
public FileSystemFile(){}
	public FileSystemFile(String id)
{
	super(id);
	this.filePath=id;
	this.file=new File(filePath);
}
	@Override
	public InputStream getInputStream()throws IOException {
		return new FileInputStream(new File(filePath));
	}
	@Override
	public void writeContentsFrom(InputStream inStream)throws IOException {
		DataInputStream in=new DataInputStream(inStream);
		DataOutputStream out=new DataOutputStream(new FileOutputStream(filePath));
		byte buf[]=new byte[1024];
		int lengthRead=0;
		while((lengthRead=in.read(buf,0,1024))>-1)
		{
			out.write(buf,0,lengthRead);
			out.flush();
		}
		in.close();
		out.close();
	}
	@Override
	public boolean remove() throws IOException {
		// TODO Auto-generated method stub
		if(file.exists())
			return file.delete();
		return false;
	}
	@Override
	public void create() throws IOException {
		file.createNewFile();
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean exists() throws IOException {
		// TODO Auto-generated method stub
		return file.exists();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return file.getName();
	}
	
}

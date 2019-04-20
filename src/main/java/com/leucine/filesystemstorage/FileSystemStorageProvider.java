package com.leucine.filesystemstorage;
import java.util.Map;

import com.leucine.storage.*;
public class FileSystemStorageProvider implements StorageProvider{

	public StringToEntityMapper getEntityMapper() {
		
		return null;
	}

	public Class<? extends SPFile> getFileClass() {
		// TODO Auto-generated method stub
		return FileSystemFile.class;
	}

	public Class<? extends SPDirectory> getDirectoryClass() {
		// TODO Auto-generated method stub
		return FileSystemDirectory.class;
	}

	public Class<? extends StringToEntityMapper> getEntityMapperClass() {
		// TODO Auto-generated method stub
		return FileStringToEntityMapper.class;
	}
public void setConnectionOptions(Map<String,String> options){}

}

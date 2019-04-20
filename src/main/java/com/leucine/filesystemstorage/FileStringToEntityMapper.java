package com.leucine.filesystemstorage;

import com.leucine.storage.SPEntity;
import com.leucine.storage.SPException;
import com.leucine.storage.StringToEntityMapper;

public class FileStringToEntityMapper implements StringToEntityMapper {

	public SPEntity getFromString(String path,boolean isFile) throws SPException {
		if(isFile)
			return new FileSystemFile(path);
		else
			return new FileSystemDirectory(path);
	}

}

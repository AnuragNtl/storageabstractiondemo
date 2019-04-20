package com.leucine.mysqlstorage;

import java.util.Map;

import com.leucine.storage.SPDirectory;
import com.leucine.storage.SPFile;
import com.leucine.storage.StorageProvider;
import com.leucine.storage.StringToEntityMapper;

public class DBStorageProvider implements StorageProvider {

	public static String dbDriver,dbURL,dbUser,dbPassword;
	public Class<? extends StringToEntityMapper> getEntityMapperClass() {

		return DBKeyStringToEntityMapper.class;
	}

	public Class<? extends SPFile> getFileClass() {
		return DBFile.class;
	}

	public Class<? extends SPDirectory> getDirectoryClass() {
		return DBDirectory.class;
	}
	
	public void setConnectionOptions(Map<String, String> options) {
		// TODO Auto-generated method stub
		dbDriver=options.get("dbDriver");
		dbUser=options.get("dbUser");
		dbPassword=options.get("dbPassword");
		dbURL=options.get("dbURL");
	}

}

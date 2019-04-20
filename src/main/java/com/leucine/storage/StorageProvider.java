package com.leucine.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**Implement this interface to provide custom classes that represent 
 *entities the storage service (that extend SPFile and SPDirectory,
 *and StringToEntityMapper).
 *{@link com.leucine.storage.StorageService} reads StorageProvider.conf, the first line of which
 *represents a StorageProvider in the classpath, the next lines are fed to the storage
 *provider with {@link com.leuicine.storage.StorageProvider#setConnectionOptions}
 */
public interface StorageProvider {
	/**
	 *@return the class that represents the {@link com.leucine.storage.StringToEntityMapper}
	 */
	public Class<? extends StringToEntityMapper> getEntityMapperClass();
	/**
	 * @return A class that extends the abstract class {@link com.leucine.storage.SPFile}
	 */
	public Class<? extends SPFile> getFileClass();
	/**
	 * @return A class that extends the abstract class {@link com.leucine.storage.SPDirectory}
	 */
	public Class<? extends SPDirectory> getDirectoryClass();
	/**
	{@link com.leucine.storage.StorageService} reads StorageProvider.conf, the first line of which
	 *represents a StorageProvider in the classpath, the next lines are fed to the storage
	 *provider with {@link com.leuicine.storage.StorageProvider#setConnectionOptions}
	 */
	public void setConnectionOptions(Map<String,String> options);
}

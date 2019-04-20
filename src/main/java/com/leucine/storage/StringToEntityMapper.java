package com.leucine.storage;

/**
 * Implement this interface to map your version of SPFile or SPDirectory
 * from path names such as "/tmp/File.txt"
 */
public interface StringToEntityMapper {
	/**
	 * @param path File or directory path represented through string
	 * @param isFile true if the path represented targets a file, else false.
	 * @return SPEntity ({@link com.leucine.storage.SPFile} or {@link com.leucine.storage.SPDirectory})
	 * represented by the pathname.
	 * 
	 */
public SPEntity getFromString(String path,boolean isFile)throws SPException;
}

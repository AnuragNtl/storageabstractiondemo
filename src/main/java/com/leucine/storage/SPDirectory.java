package com.leucine.storage;

import java.io.IOException;

/**
 * Implementation of directory in the storage provider.
 */
public abstract class SPDirectory extends SPEntity{
	public SPDirectory(){}
public SPDirectory(String id)
{
	super(id);
}
/**
 * Lists all files and directories contained in this directory
 * @return Array of all files and directories in this directory
 */
public abstract SPEntity[] list();

/**
 * get a file contained in this directory
 * @param name Name of the file
 * @return SPFile that represents the file
 */

public abstract SPFile getItemAsFile(String name)throws SPException;

/**
 * get a directory contained in this directory
 * @param name Name of the directory
 * @return SPDirectory that represents the directory
 */
public abstract SPDirectory getItemAsDirectory(String directory)throws SPException;
}

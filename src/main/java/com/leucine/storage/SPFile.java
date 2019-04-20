package com.leucine.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Implementation of your file in the storage service.
 * Inherit from this class to provide custom implementation of File operations in platform.
 */
public abstract class SPFile extends SPEntity{
	public SPFile(){}
public SPFile(String id)
{
	super(id);
}
/**
 * Get the InputStream to the file represented by this SPFile
* @return InputStream from which to read
 */
public abstract InputStream getInputStream()throws IOException;
/**
 * @param in InputStream from which to write
 * Write contents from InputStream to a file represented by this SPFile
*/
public abstract void writeContentsFrom(InputStream in)throws IOException;

}

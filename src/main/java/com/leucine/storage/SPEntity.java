package com.leucine.storage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *Represents an entity (File or Directory) that implement entities in the storage provider.
 *
 */
public abstract class SPEntity {
private String id;
public SPEntity(){}
public void setId(String id)
{
	this.id=id;
}
public SPEntity(String id)
{
	this.id=id;
}
public String getId() {
	return id;
}

public boolean equals(SPEntity entity)
{
	return this.id.equals(entity.getId());
}
/**
 * Removes this entity (File or Directory)
 */
public abstract boolean remove()throws IOException;
/**
 * Creates this entity (File or Directory) if it doesnt exist
 */
public abstract void create()throws IOException;
/**
 * Use this method to check if this entity already exists.
 * @return true if this entity exists
 */
public abstract boolean exists()throws IOException;
/**
 * Represents name of the entity (File or Directory)
 */

public abstract String getName();
/**
 * Exprerimental Feature: Move Entity from to a different destination
 * @dest File or directory to which to move
 */
public SPEntity move(SPEntity dest)throws IOException,SPException
{
	if(!dest.exists())
		dest.create();
	if(dest instanceof SPDirectory)
	{
		SPDirectory destDirectory=(SPDirectory)dest;
		if(this instanceof SPDirectory)
		{
			SPDirectory src=(SPDirectory)this;
			SPEntity entities[]=src.list();
			for(SPEntity entity: entities)
			{
				if(entity instanceof SPDirectory)
					entity.move(destDirectory.getItemAsDirectory(entity.getName()));
				else
					entity.move(destDirectory.getItemAsFile(entity.getName()));
			}
		}
		else if(this instanceof SPFile)
		{
			SPFile destFile=(SPFile)destDirectory.getItemAsFile(this.getName());
			destFile.writeContentsFrom(((SPFile)this).getInputStream());
		}
	}
	else if(dest instanceof SPFile)
	{
		if(this instanceof SPDirectory)
		throw new SPException("Cannot copy Folder into File");
		else if(this instanceof SPFile)
		{
			SPFile destFile=(SPFile)dest;
			destFile.writeContentsFrom(((SPFile)this).getInputStream());
		}
	}
	this.remove();
	return dest;
}
/**
 * @return true if this entity is directory
 */

public boolean isDirectory()
{
	return this instanceof SPDirectory;
}
/**
 * @return true if this entity is file
 */
public boolean isFile()
{
	return this instanceof SPFile;
}
};

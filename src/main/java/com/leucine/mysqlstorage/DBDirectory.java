package com.leucine.mysqlstorage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.leucine.storage.SPDirectory;
import com.leucine.storage.SPEntity;
import com.leucine.storage.SPException;
import com.leucine.storage.SPFile;

public class DBDirectory extends SPDirectory{

	private String baseId="";
	private String dirName="";
	private String basePathString="";
	public void setName(String fileName)
	{
		this.dirName=fileName;
	}
	@Override
	public String getName()
	{
		return dirName;
	}
	public void setBaseId(String baseId)
	{
		this.baseId=baseId;
	}
	public String getBaseId()
	{
		return baseId;
	}
	public String getBasePathString()
	{
		return basePathString;
	}
	public void setBasePathString(String basePathString)
	{
		this.basePathString=basePathString;
	}


	@Override
	public SPEntity[] list() {
		try {
			PreparedStatement statement=JDBCConnection.getJDBCConnection().
					prepareStatement("select * from entity,baseDirectory where entity.id=baseDirectory.entityId and baseDirectory.baseId=?");
			statement.setString(1,getId());
		ResultSet rs=statement.executeQuery();
		ArrayList<SPEntity> list=new ArrayList<SPEntity>();
		while(rs.next())
		{
			if(rs.getBoolean("isFile"))
			{
				DBDirectory dir=new DBDirectory();
				dir.setBaseId(getId());
				dir.setBasePathString(basePathString+"/"+dirName);
				dir.setId(rs.getString("id"));
				dir.setName(rs.getString("name"));
				list.add(dir);
			}
			else
			{
				DBFile file=new DBFile();
				file.setBaseId(getId());
				file.setBasePathString(basePathString+"/"+dirName);
				file.setName(rs.getString("name"));
				file.setId(rs.getString("id"));
				list.add(file);
			}
			return list.toArray(new SPEntity[0]);
		}
		} catch (Exception e) {
			throw new RuntimeException("Exception in listing",e);
	} 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SPFile getItemAsFile(String name) throws SPException {
		
		try
		{
		if(!this.exists())
			throw new SPException("directory doesnt exist");
		}
		catch(IOException ioexcepn)
		{
			throw new SPException(ioexcepn);
		}
		
				DBFile file=new DBFile();
				file.setBaseId(getId());
				file.setBasePathString(basePathString+"/"+dirName);
				file.setName(name);
			
		 		return file;
	}

	@Override
	public SPDirectory getItemAsDirectory(String directory) throws SPException {
		try
		{
		if(!this.exists())
			throw new SPException("directory doesnt exist");
		}
		catch(IOException ioexcepn)
		{
			throw new SPException(ioexcepn);
		}
		DBDirectory dir=new DBDirectory();
		dir.setBaseId(getId());
		dir.setBasePathString(basePathString+"/"+dirName);
		dir.setName(directory);
	
 		return dir;
	}

	@Override
	public boolean remove() throws IOException {
		// TODO Auto-generated method stub
		if(getId()!=null)
			try {
				return DBUtils.remove(this.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new IOException(e);
			}
		return false;
	}

	@Override
	public void create() throws IOException {
		// TODO Auto-generated method stub
		setId(DBUtils.create(dirName, baseId, basePathString, false));
	}

	@Override
	public boolean exists() throws IOException {
		Map<String,String> data=DBUtils.checkIfExists(getName(), baseId);
		if(data==null || data.get("isFile").equals("true"))
			return false;
		setId(data.get("id"));
		this.dirName=data.get("name");
		return true;
	}


}

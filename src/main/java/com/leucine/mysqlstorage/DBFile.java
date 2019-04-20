package com.leucine.mysqlstorage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.leucine.storage.SPFile;

public class DBFile extends SPFile{

	@Override
	public InputStream getInputStream() throws IOException  {
		// TODO Auto-generated method stub
		if(!this.exists())
			throw new IOException("File not Found");
		Connection con=JDBCConnection.getJDBCConnection();
		try
		{
		PreparedStatement statement=con.prepareStatement("select content from contents where entityId=?");
		statement.setString(1,this.getId());
		ResultSet resultSet=statement.executeQuery();
		if(resultSet.next())
			return new ByteArrayInputStream(resultSet.getBytes(1));
		else
			throw new IOException("File not found");
		}
		catch(SQLException sqlexcepn)
		{
			throw new IOException(sqlexcepn);
		}
	}

	@Override
	public void writeContentsFrom(InputStream in) throws IOException {
		try
		{
			PreparedStatement statement=JDBCConnection.getJDBCConnection()
					.prepareStatement("update contents set content=? where entityId=?");
		statement.setBinaryStream(1,in);
		statement.setString(2,this.getId());
		statement.executeUpdate();
		}
		catch(SQLException sqlexcepn)
		{
		throw new IOException(sqlexcepn);
		}
		
	}
	private String baseId="";
	private String fileName="";
	private String basePathString="";
	public void setName(String fileName)
	{
		this.fileName=fileName;
	}
	@Override
	public String getName()
	{
		return fileName;
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
		setId(DBUtils.create(this.getName(),baseId,this.getBasePathString(),true));
	}

	@Override
	public boolean exists() throws IOException {
	Map<String,String> data=DBUtils.checkIfExists(getName(), baseId);
	if(data==null || !data.get("isFile").equals("true"))
		return false;
	setId(data.get("id"));
	this.fileName=data.get("name");
	return true;
	}


}

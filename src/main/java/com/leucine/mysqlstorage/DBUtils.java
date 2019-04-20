package com.leucine.mysqlstorage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.Statement;

public class DBUtils {
public static Map<String,String> checkIfExists(String name,String baseId)throws IOException
{
	PreparedStatement statement;
	try {
		if(baseId!=null)
		{
		statement=JDBCConnection.getJDBCConnection().
		prepareStatement("select id,name,isFile from entity,baseDirectory where"
						+ " name=? and "
						+ "baseDirectory.entityId=id and baseDirectory.baseId=? and"
						+ " id=entityId");
		statement.setString(1,name);
		statement.setString(2,baseId);
		}
		else
		{
			statement=JDBCConnection.getJDBCConnection().
			prepareStatement("select id,name,isFile from entity where name=?");
		statement.setString(1,name);
		}
		ResultSet rs=statement.executeQuery();
		if(rs.next())
		{
			HashMap<String,String> data=new HashMap<String,String>();
			data.put("id",rs.getString("id"));
			data.put("name",rs.getString("name"));
			data.put("isFile",rs.getBoolean("isFile")+"");
			return data;
		}
		else
			return null;
	} catch (SQLException e) {
		throw new IOException(e);
	}
	// TODO Auto-generated method stub
}
public static String create(String name,String baseId,String basePathString,boolean isFile)throws IOException
{
	try
	{
		String id=(long)(Math.random()*Math.random()*100000)+" "+
	(long)(Math.random()*Math.random()*100000);
		PreparedStatement statement=JDBCConnection.getJDBCConnection().
				prepareStatement("insert into entity(id,name,isFile,path) values(?,?,?,?)");
		statement.setString(1,id);
		statement.setString(2,name);
		statement.setBoolean(3,isFile);
		statement.setString(4,basePathString+"/"+name);
		statement.executeUpdate();
		if(baseId!=null)
		{
			statement=JDBCConnection.getJDBCConnection().
					prepareStatement("insert into baseDirectory(entityId,baseId) values(?,?);");
			statement.setString(1,id);
			statement.setString(2,baseId);
			statement.executeUpdate();
		}
		if(isFile)
		{
			statement=JDBCConnection.getJDBCConnection().
					prepareStatement("insert into contents(entityId,content) values(?,?)");
			statement.setString(1,id);
			statement.setString(2,"");
			statement.executeUpdate();
		}
		return id;
	}
	catch(SQLException sqlexcepn)
	{
		throw new IOException(sqlexcepn);
	}

}
public static boolean remove(String id) throws SQLException, IOException
{
	PreparedStatement statement=JDBCConnection.getJDBCConnection().
			prepareStatement("delete from entity where id=?");
	statement.setString(1,id);
	statement.executeUpdate();
	return true;
}
}

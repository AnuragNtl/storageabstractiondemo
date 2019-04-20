package com.leucine.mysqlstorage;

import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {
Connection con=null;
private static JDBCConnection connection;
	private JDBCConnection()throws IOException
{
		try
		{
		Class.forName(DBStorageProvider.dbDriver);
	con=DriverManager.getConnection(DBStorageProvider.dbURL,DBStorageProvider.dbUser
			,DBStorageProvider.dbPassword);
	String init="create table entity(id varchar(80),name text,isFile boolean,path varchar(767) unique,primary key(id));create table contents(entityId varchar(80),content blob,foreign key (entityId) references entity(id));create table baseDirectory(entityId varchar(80),baseId varchar(80),foreign key(entityId) references entity(id),foreign key (baseId) references entity(id));";
	String initQueries[]=init.split(";");
	Statement statement=con.createStatement();
	con.setAutoCommit(false);
	statement.addBatch(initQueries[0]);
	statement.addBatch(initQueries[1]);
	statement.addBatch(initQueries[2]);
	statement.executeBatch();
	con.commit();
	con.setAutoCommit(true);
		}
		catch(BatchUpdateException buexcepn){}
		catch(Throwable t)
		{
			throw new IOException(t);
		}
}
	public static Connection getJDBCConnection()throws IOException
	{
		if(connection==null)
			connection=new JDBCConnection();
		return connection.con;
	}
}

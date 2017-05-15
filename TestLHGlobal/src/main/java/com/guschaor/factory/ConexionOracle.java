package com.guschaor.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class ConexionOracle 
{
	final String usuario 	= "system";
	final String password 	= "oracle";
	final String host 		= "190.84.48.243";
	final String puerto 	= "49161";
	final String sid 		= "xe";
	final String driver 	= "oracle.jdbc.driver.OracleDriver";
	private String ulrjdbc = "jdbc:oracle:thin:" + usuario + "/" + password + "@" + host + ":" + puerto + ":" + sid;
	private Connection connection;
	
	private final static Logger log = Logger.getLogger(ConexionOracle.class);
	
	public ConexionOracle() 
	{
		// TODO Auto-generated constructor stub
	}

	public void hacerConexion() throws Exception
	{
		try
		{
			Class.forName(driver).newInstance();
			setConnection((Connection) DriverManager.getConnection(ulrjdbc));
			log.info("Se realiza satisfactoriamente la conexión a base de datos");
			
		}catch (Exception e) 
		{
			log.error("Error al intentar crear la conexión de base de datos.\n"+e.getMessage());
			throw new Exception(e);
		}
		
	}


	public ResultSet ejecutarQuery(String query) throws Exception
	{
		ResultSet resultSet = null;
		try
		{
			hacerConexion();
			resultSet = getConnection().createStatement().executeQuery(query);
		}catch (Exception e) 
		{
			log.error("Error al intentar ejecutar el query: .\n"+query+"\n"+e.getMessage());
			throw new Exception(e);
		}
		return resultSet;
	}
	
	public int ejecutarUpdate(String query) throws Exception
	{
		int result = 0;
		try
		{
			hacerConexion();
			result = getConnection().createStatement().executeUpdate(query);
		}catch (Exception e) 
		{
			log.error("Error al intentar realizar el update: .\n"+query+"\n"+e.getMessage());
			throw new Exception(e);
		}
		return result;
	}
	
	
	public Connection getConnection() throws Exception
	{
		return connection;
	}

	public void setConnection(Connection connection) 
	{
		this.connection = connection;
	}	
}

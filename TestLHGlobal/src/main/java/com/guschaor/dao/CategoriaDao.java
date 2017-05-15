package com.guschaor.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.guschaor.entity.Categoria;
import com.guschaor.factory.ConexionOracle;

public class CategoriaDao implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(CategoriaDao.class);
	ConexionOracle conexion = new ConexionOracle();
	public CategoriaDao()
	{
	}
	
	public List<Categoria> leerCategorias() throws Exception
	{
		List<Categoria> categorias = null;
		String query = "SELECT categoriaId, nombre, estado FROM categorias";
		ResultSet resultSet = null;
		try
		{
			resultSet = conexion.ejecutarQuery(query);
			if(resultSet != null)
			{
				categorias = new ArrayList<Categoria>();
				while(resultSet.next())
				{
					categorias.add(new Categoria(resultSet.getLong("categoriaId"),
							resultSet.getString("nombre"), resultSet.getInt("estado")));
				}
			}
		}
		catch (Exception e) 
		{
			log.error("Error intentando consultar las categorías \n"+e.getMessage());
			throw e;
		}
		finally 
		{
			conexion.getConnection().close();
		}
		return categorias;
	}
	
	public String insertarCategoria(Categoria categoria)
	{
		String resultMessage = "";
		String query = "INSERT INTO categorias (categoriaId, nombre, estado) "
				+ "VALUES (sqcategorias.nextval,'"+categoria.getNombre()+"',"
				+categoria.getEstado()+")";
		int result = 0;
		try
		{
			result = conexion.ejecutarUpdate(query);
			if(result > 0)
			{
				resultMessage = "Se insertó la categoría "+categoria.getNombre();
			}
			else
			{
				resultMessage = "No se insertó ninguna categoría";
			}
			
		}
		catch (Exception e) 
		{
			log.error("Error intentando insertar la categoría \n"+e.getMessage());
			resultMessage = "Error intentando insertar la categoría";
		}
		finally 
		{
			try 
			{
				conexion.getConnection().commit();
				conexion.getConnection().close();
			} catch (Exception e) {
				log.error("Error intentando cerrar la conexión "+e.getMessage());
				resultMessage = "Error intentnado cerrar la conexión";
			}
		}
		return resultMessage;
	}
	
	public String actualizarCategoria(Categoria categoria)
	{
		String resultMessage = "";
		String query = "UPDATE categorias SET nombre = '"+categoria.getNombre()+
				"', estado = "+categoria.getEstado()+" WHERE categoriaId="+categoria.getCategoriaId();
		int result = 0;
		try
		{
			result = conexion.ejecutarUpdate(query);
			if(result > 0)
			{
				resultMessage = "Se actualizó la categoría "+categoria.getNombre();
			}
			else
			{
				resultMessage = "No se actualizó ninguna categoría";
			}
			
		}
		catch (Exception e) 
		{
			log.error("Error intentando actualizar la categoría \n"+e.getMessage());
			resultMessage = "Error intentando actualizar la categoría";
		}
		finally 
		{
			try 
			{
				conexion.getConnection().commit();
				conexion.getConnection().close();
			} catch (Exception e) {
				log.error("Error intentando cerrar la conexión "+e.getMessage());
				resultMessage = "Error intentnado cerrar la conexión";
			}
		}
		return resultMessage;
	} 
	
	public String eliminarCategoria(Categoria categoria)
	{
		String resultMessage = "";
		String query = "DELETE FROM categorias WHERE categoriaId="+categoria.getCategoriaId();
		int result = 0;
		try
		{
			result = conexion.ejecutarUpdate(query);
			if(result > 0)
			{
				resultMessage = "Se eliminó la categoría "+categoria.getNombre();
			}
			else
			{
				resultMessage = "No se eliminó ninguna categoría";
			}
			
		}
		catch (Exception e) 
		{
			log.error("Error intentando eliminar la categoría \n"+e.getMessage());
			resultMessage = "Error intentando eliminar la categoría";
		}
		finally 
		{
			try 
			{
				conexion.getConnection().commit();
				conexion.getConnection().close();
			} catch (Exception e) {
				log.error("Error intentando cerrar la conexión "+e.getMessage());
				resultMessage = "Error intentnado cerrar la conexión";
			}
		}
		return resultMessage;
	} 
	

		
}

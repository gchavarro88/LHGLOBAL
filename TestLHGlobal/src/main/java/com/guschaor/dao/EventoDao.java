package com.guschaor.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.guschaor.entity.Categoria;
import com.guschaor.entity.Evento;
import com.guschaor.factory.ConexionOracle;

public class EventoDao implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(EventoDao.class);
	ConexionOracle conexion = new ConexionOracle();
	public EventoDao()
	{
	}
	
	public List<Evento> leerEventos(Categoria categoria) throws Exception
	{
		List<Evento> eventos = null;
		String query = "SELECT eventoId, nombre, fecha, lugar, categoriaId FROM eventos WHERE categoriaId = "+categoria.getCategoriaId();
		ResultSet resultSet = null;
		try
		{
			resultSet = conexion.ejecutarQuery(query);
			if(resultSet != null)
			{
				eventos = new ArrayList<Evento>();
				while(resultSet.next())
				{
					eventos.add(new Evento(resultSet.getLong("eventoId"), resultSet.getLong("categoriaId"),
							resultSet.getString("nombre"), resultSet.getDate("fecha"),
							resultSet.getString("lugar")));
				}
			}
		}
		catch (Exception e) 
		{
			log.error("Error intentando consultar los eventos de una categoría \n"+e.getMessage());
			throw e;
		}
		finally 
		{
			conexion.getConnection().close();
		}
		return eventos;
	}
	
	public String insertarEvento(Evento evento)
	{
		String resultMessage = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String query = "INSERT INTO eventos (eventoId, categoriaId, nombre, fecha, lugar) "
				+ "VALUES (sqeventos.nextval,"+evento.getCategoriaId()+",'"+evento.getNombre()+"',"
				+"(date '"+dateFormat.format(evento.getFecha())+"'),'"+evento.getLugar()+"')";
		int result = 0;
		try
		{
			result = conexion.ejecutarUpdate(query);
			if(result > 0)
			{
				resultMessage = "Se insertó el evento "+evento.getNombre();
			}
			else
			{
				resultMessage = "No se insertó ningún evento";
			}
			
		}
		catch (Exception e) 
		{
			log.error("Error intentando insertar el evento \n"+e.getMessage());
			resultMessage = "Error intentando insertar el evento";
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
	
	public String actualizarEvento(Evento evento)
	{
		String resultMessage = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String query = "UPDATE eventos SET nombre = '"+evento.getNombre()+
				"',fecha = (date '"+dateFormat.format(evento.getFecha())+"'), lugar = '"+evento.getLugar()+"' "
						+ "WHERE eventoId="+evento.getEventoId();
		int result = 0;
		try
		{
			result = conexion.ejecutarUpdate(query);
			if(result > 0)
			{
				resultMessage = "Se actualizó el evento "+evento.getNombre();
			}
			else
			{
				resultMessage = "No se actualizó ningún evento";
			}
			
		}
		catch (Exception e) 
		{
			log.error("Error intentando actualizar el evento \n"+e.getMessage());
			resultMessage = "Error intentando actualizar el evento";
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
	
	public String eliminarEvento(Evento evento)
	{
		String resultMessage = "";
		String query = "DELETE FROM eventos WHERE eventoId ="+evento.getEventoId();
		int result = 0;
		try
		{
			result = conexion.ejecutarUpdate(query);
			if(result > 0)
			{
				resultMessage = "Se eliminó el evento "+evento.getNombre();
			}
			else
			{
				resultMessage = "No se eliminó ningún evento";
			}
			
		}
		catch (Exception e) 
		{
			log.error("Error intentando eliminar el evento \n"+e.getMessage());
			resultMessage = "Error intentando eliminar el evento";
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

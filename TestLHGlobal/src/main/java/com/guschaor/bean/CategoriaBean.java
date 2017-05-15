package com.guschaor.bean;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.guschaor.dao.CategoriaDao;
import com.guschaor.dao.EventoDao;
import com.guschaor.entity.Categoria;
import com.guschaor.entity.Evento;

@ManagedBean
@ViewScoped
public class CategoriaBean implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Categoria> listaCategorias;
	private List<Categoria> categoriasFiltradas;
	private Categoria categoriaSeleccionada;
	CategoriaDao categoriaDao = new CategoriaDao();
	EventoDao eventoDao	= new EventoDao();
	private List<Evento> listaEventos;
	private List<Evento> eventosFiltrados;
	private Evento eventoSeleccionado;
	private final static Logger log = Logger.getLogger(CategoriaBean.class);
	
	public CategoriaBean()
	{
		limpiarCategoria();
		leerCategorias();
	}

	public void leerCategorias()
	{
		try
		{
			setListaCategorias(getCategoriaDao().leerCategorias());
		}
		catch (Exception e) 
		{
			log.error("Error intentando consutlar las categorías\n"+e.getMessage());
			error("Operación fallida", "No se pudieron consultar las categorías");
		}
	}
	
	public void leerEventos()
	{
		try
		{
			setListaEventos(getEventoDao().leerEventos(getCategoriaSeleccionada()));
		}
		catch (Exception e) 
		{
			log.error("Error intentando consutlar los eventos\n"+e.getMessage());
			error("Operación fallida", "No se pudieron consultar los eventos");
		}
	}
	
	public String obtenerEstado(int estado)
	{
		String result = "";
		if(estado == 0)
		{
			result = "INACTIVA";
		}
		else
		{
			result = "ACTIVA";
		}
		return result;
	}
	
	public void persistirCategoria()
	{
		if(getCategoriaSeleccionada().getCategoriaId() != null && getCategoriaSeleccionada().getCategoriaId() > 0)
		{
			actualizarCategoria(getCategoriaSeleccionada());
		}
		else
		{
			insertarCategoria(getCategoriaSeleccionada());
		}
	}
	
	public void persistirEvento()
	{
		if(getEventoSeleccionado().getEventoId() != null && getEventoSeleccionado().getEventoId() > 0)
		{
			actualizarEvento(getEventoSeleccionado());
		}
		else
		{
			insertarEvento(getEventoSeleccionado());
		}
	}
	
	public void actualizarCategoria(Categoria categoria)
	{
		try 
		{
			if(categoria != null)
			{
				String mensaje = ""; 
				mensaje = getCategoriaDao().actualizarCategoria(categoria);
				info("Operación Exitosa", mensaje);
				log.info(mensaje);
				limpiarCategoria();
				leerCategorias();
			}
		} catch (Exception e) 
		{
			log.error("Error al intentar actualizar la categoría \n"+e.getMessage());
			error("Operación Fallida", e.getMessage());
		}
	}
	
	public void actualizarEvento(Evento evento)
	{
		try 
		{
			if(evento != null)
			{
				String mensaje = ""; 
				mensaje = getEventoDao().actualizarEvento(evento);
				info("Operación Exitosa", mensaje);
				log.info(mensaje);
				limpiarEvento();
				leerEventos();
			}
		} catch (Exception e) 
		{
			log.error("Error al intentar actualizar el evento \n"+e.getMessage());
			error("Operación Fallida", e.getMessage());
		}
	}
	
	public void insertarCategoria(Categoria categoria)
	{
		try 
		{
			if(categoria != null)
			{
				String mensaje = ""; 
				mensaje = getCategoriaDao().insertarCategoria(categoria);
				info("Operación Exitosa", mensaje);
				log.info(mensaje);
				limpiarCategoria();
				leerCategorias();
			}
		} catch (Exception e) 
		{
			log.error("Error al intentar insertar la categoría \n"+e.getMessage());
			error("Operación Fallida", e.getMessage());
		}
	}
	
	public void insertarEvento(Evento evento)
	{
		try 
		{
			if(evento != null)
			{
				String mensaje = ""; 
				evento.setCategoriaId(getCategoriaSeleccionada().getCategoriaId());
				mensaje = getEventoDao().insertarEvento(evento);
				info("Operación Exitosa", mensaje);
				log.info(mensaje);
				limpiarEvento();
				leerEventos();
			}
		} catch (Exception e) 
		{
			log.error("Error al intentar insertar el evento \n"+e.getMessage());
			error("Operación Fallida", e.getMessage());
		}
	}
	
	public void eliminarCategoria(Categoria categoria)
	{
		try 
		{
			if(categoria != null)
			{
				String mensaje = ""; 
				mensaje = getCategoriaDao().eliminarCategoria(categoria);
				info("Operación Exitosa", mensaje);
				log.info(mensaje);
				limpiarCategoria();
				leerCategorias();
			}
		} catch (Exception e) 
		{
			log.error("Error al intentar eliminar la categoría \n"+e.getMessage());
			error("Operación Fallida", e.getMessage());
		}
	}
	
	public void eliminarEvento(Evento evento)
	{
		try 
		{
			if(evento != null)
			{
				String mensaje = ""; 
				mensaje = getEventoDao().eliminarEvento(evento);
				info("Operación Exitosa", mensaje);
				log.info(mensaje);
				limpiarEvento();
				leerEventos();
			}
		} catch (Exception e) 
		{
			log.error("Error al intentar eliminar el evento \n"+e.getMessage());
			error("Operación Fallida", e.getMessage());
		}
	}
	
	public void cargarCategoria(Categoria categoria)
	{
		if(categoria != null)
		{
			setCategoriaSeleccionada(categoria);
		}
	}
	
	public void cargarEvento(Evento evento)
	{
		if(evento != null)
		{
			setEventoSeleccionado(evento);
		}
	}
	
	public void mostrarEventosPorCategoria(Categoria categoria)
	{
		cargarCategoria(categoria);
		leerEventos();
		limpiarEvento();
	}
	
	public void limpiarCategoria()
	{
		setCategoriaSeleccionada(new Categoria());
	}
	
	public void limpiarEvento()
	{
		setEventoSeleccionado(new Evento());
	}
	
	public String obtenerFormato(Date date)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}
	
	public void info(String title, String message) 
	{
        FacesContext.getCurrentInstance().addMessage(null, 
        		new FacesMessage(FacesMessage.SEVERITY_INFO, title, message));
    }
     
    public void warn(String title, String message) 
    {
        FacesContext.getCurrentInstance().addMessage(null, 
        		new FacesMessage(FacesMessage.SEVERITY_WARN, title, message));
    }
     
    public void error(String title, String message) 
    {
        FacesContext.getCurrentInstance().addMessage(null, 
        		new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message));
    }
     
    public void fatal(String title, String message) 
    {
        FacesContext.getCurrentInstance().addMessage(null, 
        		new FacesMessage(FacesMessage.SEVERITY_FATAL, title, message));
    }
	
	public List<Categoria> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<Categoria> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public Categoria getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}

	public void setCategoriaSeleccionada(Categoria categoriaSeleccionada) {
		this.categoriaSeleccionada = categoriaSeleccionada;
	}

	public CategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(CategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	public List<Categoria> getCategoriasFiltradas() {
		return categoriasFiltradas;
	}

	public void setCategoriasFiltradas(List<Categoria> categoriasFiltradas) {
		this.categoriasFiltradas = categoriasFiltradas;
	}

	public EventoDao getEventoDao() {
		return eventoDao;
	}

	public void setEventoDao(EventoDao eventoDao) {
		this.eventoDao = eventoDao;
	}

	public List<Evento> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<Evento> listaEventos) {
		this.listaEventos = listaEventos;
	}

	public List<Evento> getEventosFiltrados() {
		return eventosFiltrados;
	}

	public void setEventosFiltrados(List<Evento> eventosFiltrados) {
		this.eventosFiltrados = eventosFiltrados;
	}

	public Evento getEventoSeleccionado() {
		return eventoSeleccionado;
	}

	public void setEventoSeleccionado(Evento eventoSeleccionado) {
		this.eventoSeleccionado = eventoSeleccionado;
	}
	
	
	
}

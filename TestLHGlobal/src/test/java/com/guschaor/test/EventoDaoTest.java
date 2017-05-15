package com.guschaor.test;

import java.util.Date;
import java.util.List;

import com.guschaor.dao.CategoriaDao;
import com.guschaor.dao.EventoDao;
import com.guschaor.entity.Categoria;
import com.guschaor.entity.Evento;

import junit.framework.TestCase;

public class EventoDaoTest extends TestCase {

	EventoDao eventoDao = new EventoDao();
	CategoriaDao categoriaDao = new CategoriaDao();

	public void testLeerEventos() {
		try 
		{
			Categoria categoria = new Categoria();
			categoria.setNombre("CAP");
			categoria.setEstado(1);
			String mensaje = categoriaDao.insertarCategoria(categoria);
			List<Evento> listaEventos = eventoDao.leerEventos(categoria);
			assertTrue((listaEventos != null));
		} catch (Exception e) {
			fail("Error al consultar los eventos");
			e.printStackTrace();
		}
	}

	public void testInsertarEvento() {
		try 
		{
			Categoria categoria = encontrarCategoria(categoriaDao.leerCategorias(), "CAP");
			Evento evento = new Evento();
			evento.setNombre("CAP");
			evento.setFecha(new Date());
			evento.setLugar("PAC");
			evento.setCategoriaId(categoria.getCategoriaId());
			String mensaje = eventoDao.insertarEvento(evento);
			assertEquals("El evento fue insertado", "Se insertó el evento "+evento.getNombre(), mensaje);

		} catch (Exception e) 
		{
			fail("Error al insertar un evento");
		}
	}

	public void testActualizarEvento() {
		try 
		{
			Categoria categoria = encontrarCategoria(categoriaDao.leerCategorias(), "CAP");
			Evento evento = new Evento();
			evento.setNombre("CAP");
			evento.setFecha(new Date());
			eventoDao.insertarEvento(evento);
			List<Evento> listaeventos = eventoDao.leerEventos(categoria);
			evento = encontrarEvento(listaeventos, evento.getNombre());
			evento.setLugar("OCT");
			String mensaje = eventoDao.actualizarEvento(evento);
			assertEquals("El evento fue actualizado", "Se actualizó el evento " + evento.getNombre(), mensaje);
		} 
		catch (Exception e) 
		{
			fail("Error al actualizar un evento");
		}
	}

	public void testEliminarEvento() {
		try {
			Categoria categoria = encontrarCategoria(categoriaDao.leerCategorias(), "CAP");
			Evento evento = new Evento();
			evento.setNombre("CAP");
			evento.setFecha(new Date());
			eventoDao.insertarEvento(evento);
			List<Evento> listaeventos = eventoDao.leerEventos(categoria);
			evento = encontrarEvento(listaeventos, evento.getNombre());
			String mensaje = eventoDao.eliminarEvento(evento);
			categoriaDao.eliminarCategoria(categoria);
			assertEquals("El evento fue eliminado", "Se eliminó el evento " + evento.getNombre(), mensaje);
		} catch (Exception e) 
		{
			fail("Error al eliminar una categoría");
		}
	}
	
	public Evento encontrarEvento(List<Evento> Eventos, String nombre)
	{
		Evento result = null;
		for(Evento Evento: Eventos)
		{
			if(Evento.getNombre().equals(nombre))
			{
				result = Evento;
				break;
			}
		}
		return result;
	}


	public Categoria encontrarCategoria(List<Categoria> categorias, String nombre)
	{
		Categoria result = null;
		for(Categoria categoria: categorias)
		{
			if(categoria.getNombre().equals(nombre))
			{
				result = categoria;
				break;
			}
		}
		return result;
	}
}

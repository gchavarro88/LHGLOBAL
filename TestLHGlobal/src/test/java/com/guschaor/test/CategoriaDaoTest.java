package com.guschaor.test;

import java.util.List;

import com.guschaor.dao.CategoriaDao;
import com.guschaor.entity.Categoria;

import junit.framework.TestCase;

public class CategoriaDaoTest extends TestCase {

	CategoriaDao categoriaDao = new CategoriaDao();

	public void testLeerCategorias() {
		try {
			List<Categoria> listaCategorias = categoriaDao.leerCategorias();
			assertTrue((listaCategorias != null));
		} catch (Exception e) {
			fail("Error al consultar las categorías");
			e.printStackTrace();
		}
	}

	public void testInsertarCategoria() {
		try {
			Categoria categoria = new Categoria();
			categoria.setNombre("CAP");
			categoria.setEstado(1);
			String mensaje = categoriaDao.insertarCategoria(categoria);
			assertEquals("La categoría fue insertada", "Se insertó la categoría " + categoria.getNombre(), mensaje);

		} catch (Exception e) 
		{
			fail("Error al insertar una categoría");
		}
	}

	public void testActualizarCategoria() {
		try {
			Categoria categoria = new Categoria();
			categoria.setNombre("CAP");
			List<Categoria> listaCategorias = categoriaDao.leerCategorias();
			categoria = encontrarCategoria(listaCategorias, categoria.getNombre());
			categoria.setEstado(0);
			String mensaje = categoriaDao.actualizarCategoria(categoria);
			assertEquals("La categoría fue actualizada", "Se actualizó la categoría " + categoria.getNombre(), mensaje);
		} 
		catch (Exception e) 
		{
			fail("Error al actualizar una categoría");
		}
	}

	public void testEliminarCategoria() {
		try {
			Categoria categoria = new Categoria();
			categoria.setNombre("CAP");
			categoria.setEstado(1);
			List<Categoria> listaCategorias = categoriaDao.leerCategorias();
			categoria = encontrarCategoria(listaCategorias, categoria.getNombre());
			String mensaje = categoriaDao.eliminarCategoria(categoria);
			assertEquals("La categoría fue eliminada", "Se eliminó la categoría " + categoria.getNombre(), mensaje);
		} catch (Exception e) 
		{
			fail("Error al eliminar una categoría");
		}
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

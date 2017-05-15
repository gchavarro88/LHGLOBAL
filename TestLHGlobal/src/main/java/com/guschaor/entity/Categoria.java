package com.guschaor.entity;

import java.io.Serializable;

public class Categoria implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long categoriaId;
	private String nombre;
	private int estado;
	
	public Categoria()
	{
	}
	
	public Categoria(Long categoriaId, String nombre, int estado) {
		super();
		this.categoriaId = categoriaId;
		this.nombre = nombre;
		this.estado = estado;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}

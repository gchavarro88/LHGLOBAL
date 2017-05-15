package com.guschaor.entity;

import java.io.Serializable;
import java.util.Date;

public class Evento implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long eventoId;
	private Long categoriaId;
	private String nombre;
	private Date fecha;
	private String lugar;
	
	public Evento()
	{
	}
	
	

	public Evento(Long eventoId, Long categoriaId, String nombre, Date fecha, String lugar) 
	{
		this.eventoId = eventoId;
		this.categoriaId = categoriaId;
		this.nombre = nombre;
		this.fecha = fecha;
		this.lugar = lugar;
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

	public Long getEventoId() {
		return eventoId;
	}



	public void setEventoId(Long eventoId) {
		this.eventoId = eventoId;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public String getLugar() {
		return lugar;
	}



	public void setLugar(String lugar) {
		this.lugar = lugar;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}

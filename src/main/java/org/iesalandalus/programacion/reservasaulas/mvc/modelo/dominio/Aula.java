package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Aula {

	private String nombre;
	
	// Constructor que acepta un nombre de aula como parámetro
	public Aula(String nombre) {
		setNombre(nombre);
	}
	
	// Constructor copia
	public Aula(Aula aula) {
		if(aula == null) {
			throw new NullPointerException("ERROR: No se puede copiar un aula nula.");
		} else {
			setNombre(aula.getNombre());
		}
	}

	// Devuelve el nombre del aula
	public String getNombre() {
		return nombre;
	}

	// Establece el nombre del aula
	private void setNombre(String nombre) {
		
		if(nombre == null) {
			throw new NullPointerException("ERROR: El nombre del aula no puede ser nulo.");
		} else if(nombre.equals("")) {
			throw new IllegalArgumentException("ERROR: El nombre del aula no puede estar vacío.");
		} else {
			this.nombre = nombre;
		}
	}

	// Métodos hashCode/equals
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(nombre, other.nombre);
	}

	// Método toString que nos muestra el nombre del aula
	@Override
	public String toString() {
		return "nombre Aula=" + nombre;
	}
	
}

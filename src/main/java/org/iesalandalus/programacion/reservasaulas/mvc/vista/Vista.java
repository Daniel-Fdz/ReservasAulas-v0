package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Vista {
	
	private static final String ERROR = "ERROR: No hay reservas.";
	private static final String NOMBRE_VALIDO = "Daniel";
	private static final String CORREO_VALIDO = "daniel@mail.com";
	private Controlador controlador;
	
	public Vista() {
		Opcion.setVista(this);
	}
	
	public void setControlador(Controlador controlador) {
		if(controlador == null) {
			throw new NullPointerException("ERROR: El controlador no puede ser nulo.");
		} else {
			this.controlador = controlador;
		}
	}
	
	public void comenzar() {
		Consola.mostrarCabecera("Programa para la gestión de reservas de espacios del IES Al-Ándalus");
		int ordinalOpcion;
		
		do 
		{
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
	
	public void salir() {
		Consola.mostrarCabecera("El programa se ha cerrado.\n");
		controlador.terminar();
	}
	
	public void insertarAula() {
		Consola.mostrarCabecera("Insertar un aula");
		
		try {
			controlador.insertarAula(Consola.leerAula());
			System.out.println("Se ha insertado el aula.\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void borrarAula() {
		Consola.mostrarCabecera("Eliminar un aula");
		
		try {
			controlador.borrarAula(Consola.leerAula());
			System.out.println("Se ha eliminado el aula.\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void buscarAula() {
		Consola.mostrarCabecera("Buscar un aula");
		
		try {
			controlador.buscarAula(Consola.leerAula());
		} catch(IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarAulas() {
		String[] aulas = controlador.representarAulas();
		Consola.mostrarCabecera("Listado de aulas");
		
		if(aulas.length == 0) {
			System.out.println("No hay aulas reservadas.\n");
		} else {
			for(String aula : aulas) {
				System.out.println(aula);
			}
		}
	}
	
	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar un profesor");
		
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Se ha insertado el profesor.\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void borrarProfesor() {
		Consola.mostrarCabecera("Eliminar un profesor");
		
		try {
			controlador.borrarProfesor(Consola.leerProfesor());
			System.out.println("Se ha eliminado el profesor.\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar un profesor");
		
		try {
			controlador.buscarProfesor(Consola.leerProfesor());
		} catch(IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarProfesores() {
		String[] profesores = controlador.representarProfesores();
		Consola.mostrarCabecera("Listado de profesores");
		
		if(profesores.length == 0) {
			System.out.println("No hay profesores.\n");
		} else {
			for(String profesor : profesores) {
				System.out.println(profesor);
			}
		}
	}
	
	public void realizarReserva() {
		Consola.mostrarCabecera("Realizar una reserva");
		
		try {
			controlador.realizarReserva(leerReserva(Consola.leerProfesor()));
			System.out.println("Se ha realizado la reserva.\n");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private Reserva leerReserva(Profesor profesor) {
		Consola.mostrarCabecera("Leer una reserva");
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		return new Reserva(profesor, Consola.leerAula(), permanencia);
	}
	
	public void anularReserva() {
		Consola.mostrarCabecera("Anular una reserva");
		
		try {
			controlador.anularReserva(leerReserva(Consola.leerProfesor()));
			System.out.println("Se ha anulado la reserva.\n");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarReservas() {
		String[] reservas = controlador.representarReservas();
		Consola.mostrarCabecera("Listado de reservas");
		
		if(reservas.length == 0) {
			System.out.println("No hay reservas.\n");
		} else {
			for(String reserva : reservas) {
				System.out.println(reserva);
			}
		}
	}
	
	public void listarReservasAula() {
		Reserva[] reservas = controlador.getReservasAula(Consola.leerAula());
		Consola.mostrarCabecera("Listado de reservas por aula");
		
		if(reservas.length == 0) {
			System.out.println("No hay aulas reservadas.\n");
		} else {
			for(Reserva reserva : reservas) {
				System.out.println(reserva);
			}
		}
	}
	
	public void listarReservasProfesor() {
		Reserva[] reservas = controlador.getReservasProfesor(Consola.leerProfesor());
		Consola.mostrarCabecera("Listado de reservas por profesor");
		
		if(reservas.length == 0) {
			System.out.println("No hay aulas reservadas.\n");
		} else {
			for(Reserva reserva : reservas) {
				System.out.println(reserva);
			}
		}
	}
	
	public void listarReservasPermanencia() {
		Reserva[] reservas = controlador.getReservasPermanencia(new Permanencia(Consola.leerDia(), Consola.leerTramo()));
		Consola.mostrarCabecera("Listado de reservas por permanencia");
		
		if(reservas.length == 0) {
			System.out.println("No hay aulas reservadas.\n");
		} else {
			for(Reserva reserva : reservas) {
				System.out.println(reserva);
			}
		}
	}
	
	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("Disponibilidad de las reservas");
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		
		if(controlador.consultarDisponibilidad(Consola.leerAula(), permanencia)) {
			System.out.println("El aula consultada se encuentra disponible.\n");
		} else {
			System.out.println("El aula consultada no se encuentra disponible.\n");
		}
	}
}

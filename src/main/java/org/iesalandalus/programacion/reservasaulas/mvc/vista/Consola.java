package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Consola() {
		
	}
	
	public static void mostrarMenu() {
		for(Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}
	
	public static void mostrarCabecera(String cadena) {
		String separador =  "";
		
		for(int i = 0; i < cadena.length(); i++) {
			separador += "+";
		}
		
		System.out.println("");
		System.out.println(separador);
		System.out.println(cadena);
		System.out.println(separador);
		System.out.println("");
	}
	
	public static int elegirOpcion() {
		int ordinal = 0;
		do {
			System.out.println("Por favor, elija una opción: ");
			ordinal = Entrada.entero();
		} while(!Opcion.esOrdinalValido(ordinal));
		return ordinal;
	}
	  //  try catch
	public static Aula leerAula() {
		return new Aula(leerNombreAula());
	}
	
	public static String leerNombreAula() {
		System.out.print("Introduzca el nombre del aula: ");
		return Entrada.cadena();
	}
	
	public static Profesor leerProfesor() {
		String nombre = leerNombreProfesor();
		System.out.print("Introduzca el correo del profesor: ");
		String correo = Entrada.cadena();
		System.out.print("Introduzca el teléfono del profesor: ");
		String telefono = Entrada.cadena();
		
		if(telefono == null || telefono == "") {
			return new Profesor(nombre, correo);
		} else {
			return new Profesor(nombre, correo, telefono);
		}
	}
	
	public static String leerNombreProfesor() {
		System.out.print("Introduzca el nombre del profesor: ");
		return Entrada.cadena();
	}
	
	public static Tramo leerTramo() {
		int eligeOpcion = 0;
		do {
			System.out.println("Introduzca el tramo (\"1\" para la mañana o \"2\" para la tarde): ");
			eligeOpcion = Entrada.entero();
		} while(eligeOpcion != 1 && eligeOpcion != 2);
		
		if(eligeOpcion == 1) {
			return Tramo.MANANA;
		} else {
			return Tramo.TARDE;
		}
		
	}
	
	public static LocalDate leerDia() {
		boolean fechaCorrecta = false;
		LocalDate fecha = null;
		
		do {
			try {
				System.out.print("Introduzca la fecha (dd/mm/aaaa): ");
				fecha = LocalDate.parse(Entrada.cadena(), FORMATO_DIA);
				fechaCorrecta = true;
			} catch(DateTimeParseException e) {
				System.out.println(e.getMessage());
			}
		} while(!fechaCorrecta);
		
		return fecha;
	}
}

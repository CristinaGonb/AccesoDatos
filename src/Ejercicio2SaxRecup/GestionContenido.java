package Ejercicio2SaxRecup;

import java.util.Scanner;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionContenido extends DefaultHandler {
	private static Scanner teclado = new Scanner(System.in);
	private String etiquetaActual, nomDpto;
	private int contadorEmpleados, sueldo, sueldoMenor = 0;
	private boolean esDpto = true, esEmpl = true;

	public GestionContenido() {

	}

	public void startDocument() {

	}

	public void endDocument() {
	}

	public void startElement(String nameSpace, String nombre, String nombreC, Attributes atts) {
		etiquetaActual = nombreC;
		// Dentro de dpto
		if (etiquetaActual.equals("departamento")) {
			esDpto = true;
		}
		// Dentro de empleado
		if (etiquetaActual.equals("empleado")) {
			contadorEmpleados++;
		}
		if (etiquetaActual.equals("empleado")) {
			sueldo = Integer.parseInt(atts.getValue("salario"));
		}
	}

	public void endElement(String uri, String nombre, String nombreC) {
		etiquetaActual = nombreC;
		if (etiquetaActual.equals("departamento")) {
			System.out.println("Departamento de " + nomDpto + " Empleados " + contadorEmpleados);
		}
	}

	public void characters(char[] xml, int charInicio, int longitud) throws SAXException {
		String cadena = new String(xml, charInicio, longitud);
		cadena = cadena.replaceAll("[\t\n]", ""); // eliminamos los saltos de linea y tabuladores
		if (cadena.length() == 0) {
			return;
		}
		if (etiquetaActual.equals("nombre") && esDpto) {
			nomDpto = cadena;
			esDpto = false;

		}

		if (sueldoMenor < sueldo) {
			sueldoMenor = sueldo;

			System.out.println("El sueldo menor entre todos los empleados es " + sueldoMenor);
		}

	}
}

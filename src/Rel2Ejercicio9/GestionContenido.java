package Rel2Ejercicio9;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionContenido extends DefaultHandler {
	private static final String ARCHIVO_UNIVERSIDAD = "universidad.txt";
	private static Scanner teclado = new Scanner(System.in);
	private String etiquetaActual;
	private BufferedWriter filtroEscritura;
	private String nombreDpto, nombreEmpleado;
	private boolean esDpto = false, esEmpleado = false;
	private int contador;
	private double media, salario = 0, salarioEmp;

	@Override
	public void startDocument() throws SAXException {
		try {
			filtroEscritura = new BufferedWriter(new FileWriter(ARCHIVO_UNIVERSIDAD, true));
		} catch (IOException e) {
			throw new SAXException(e.getMessage());
		}
	}

	@Override
	public void endDocument() throws SAXException {
		try {
			filtroEscritura.close();
		} catch (IOException e) {
			throw new SAXException(e.getMessage());
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		// Almaceno la ultima cadena que llego para saber en que punto estoy
		etiquetaActual = qName;

		if (etiquetaActual.equals("departamento")) {
			esDpto = true;
		}
		if (etiquetaActual.equals("empleado")) {
			// Almaceno valor del salario en la variable salarioEmp
			salarioEmp = Double.parseDouble(attributes.getValue("salario"));
			// Acumulador donde suma el salario de cada dpto en cada vuelta
			media = media + salarioEmp;
			contador++;
		}
		if (etiquetaActual.equals("empleado")) {
			media = media + salarioEmp;
			contador++;

			media = media + salarioEmp;
			contador++;
			}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		etiquetaActual = qName;
		// En la ultima etiqueta de cada departamento, calcula la media
		if (etiquetaActual.equals("departamento")) {
			// Almaceno en media la media del salario de cada dpto
			media = media / contador;
			System.out.println("Media de sueldo: " + media);
			// Los dejo en 0 para que en cada vuelta del dpto no acumule
			media = 0;
			contador = 0;
		}

		if (etiquetaActual.equals("departamento")) {

			System.out.println("La persona que mas cobra es " + nombreEmpleado);
			salario=0;

			media = media / contador;
			System.out.println("Media de sueldo: " + media);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contenido = new String(ch, start, length);

		contenido = contenido.replaceAll("[\t\n]", " ").trim();

		if (contenido.length() == 0)
			return;

		if (etiquetaActual.equals("nombre") && esDpto == true) {
			nombreDpto = contenido;
			System.out.println(nombreDpto);
			esDpto = false;
		}


		if (etiquetaActual.equals("nombre") && esEmpleado == true) {
			if (salarioEmp > salario) {
				salario=salarioEmp;
				nombreEmpleado = contenido;
			}
			esEmpleado = false;
		}
	}
}

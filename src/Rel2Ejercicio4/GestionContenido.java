package Rel2Ejercicio4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 * Crear un fichero alumnosRepetidores.txt que guarde los alumnos repetidores(2 o mas para repetir)
 * Crear un directorio con el nombre de los alumnos que no repiten
 */
public class GestionContenido extends DefaultHandler {
	private static final String ALUMNOS_REPETIDORES = "alumnosRepetidores.txt";
	private static Scanner teclado = new Scanner(System.in);
	private String etiquetaActual;
	private int asignaturasPendientes;
	private boolean repetidor = false;
	private BufferedWriter filtroEscritura;
	private String nombre, dni;

	@Override
	public void startDocument() throws SAXException {
		try {
			filtroEscritura = new BufferedWriter(new FileWriter(ALUMNOS_REPETIDORES, true));
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

		if (etiquetaActual.equals("alumno")) {
			asignaturasPendientes = Integer.parseInt(attributes.getValue("pendientes"));
		}
	}

	//Ejecuto una vez por cada alumno
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		etiquetaActual = qName;
		// Cuando este en el final de la etiqueta alumno, creo el documento
		if (etiquetaActual.equals("alumno")) {
			//Con lo que tengo guardado, preguntamos las asignaturas pendientes
			if (asignaturasPendientes >= 2) {
				try {
					filtroEscritura.write(dni + " " + nombre + "\n");
				} catch (IOException e) {
					throw new SAXException(e.getMessage());
				}
			} else {
				File directorioAlumnos = new File(nombre);
				directorioAlumnos.mkdirs();
			}
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contenido = new String(ch, start, length);

		contenido = contenido.replaceAll("[\t\n]", " ").trim();

		if (contenido.length() == 0)
			return;

		// Obtengo datos
		if (etiquetaActual.equals("dni")) {
			dni = contenido;
		}

		if (etiquetaActual.equals("nombre")) {
			nombre = contenido;
		}
	}
}

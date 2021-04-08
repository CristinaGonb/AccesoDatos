package Rel2Ejercicio4;

import java.io.BufferedWriter;
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

	// Arranca una vez cuando empieza
	@Override
	public void startDocument() throws SAXException {
		try {
			filtroEscritura = new BufferedWriter(new FileWriter(ALUMNOS_REPETIDORES));
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

	// Arranca con el inicio de la etiqueta

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		//// Almaceno la ultima cadena que llego para saber en que punto estoy
		etiquetaActual = qName;

		if (etiquetaActual.equals("alumno")) {
			asignaturasPendientes = Integer.parseInt(attributes.getValue("pendientes"));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		etiquetaActual = "";

	}

	// Recoge la cadena que llegue
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contenido = new String(ch, start, length);

		contenido = contenido.replaceAll("[\t\n]", " ").trim();

		if (contenido.length() == 0)
			return;

		if (etiquetaActual.equals("nombre") && asignaturasPendientes > 2) {
			try {
				filtroEscritura.write(contenido + "\n");
			} catch (IOException e) {
				throw new SAXException(e.getMessage());
			}
		}

	}

}

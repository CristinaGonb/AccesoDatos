package Rel2Ejercicio3;

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

public class GestionContenido extends DefaultHandler {
	private static Scanner teclado = new Scanner(System.in);
	private String etiquetaActual;
	private String equipoLocal, equipoVisitante;
	private int golLocal, golVisitante;
	private boolean esLocal = true;
	private BufferedWriter filtroEscritura;

	@Override
	public void startDocument() throws SAXException {
		try {
			filtroEscritura = new BufferedWriter(new FileWriter("EquipoGanador.txt", true));
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
		// Para que almacene el primer equipo de la etiqueta partido en la variable
		// local
		if (etiquetaActual.equals("partido")) {
			esLocal = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		etiquetaActual = qName;

		if (etiquetaActual.equals("partido")) {
			if (golLocal > golVisitante) {
				EscribirResultadoFichero(equipoLocal);
			}
			if (golVisitante > golLocal) {
				EscribirResultadoFichero(equipoVisitante);
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contenido = new String(ch, start, length);

		contenido = contenido.replaceAll("[\t\n]", " ").trim();

		if (contenido.length() == 0)
			return;

		if (etiquetaActual.equals("equipo")) {
			if (esLocal) {
				equipoLocal = contenido;
			} else {
				equipoVisitante = contenido;
			}
		}

		if (etiquetaActual.equals("goles")) {
			if (esLocal) {
				golLocal = Integer.parseInt(contenido);
				esLocal = false;
			} else {
				golVisitante = Integer.parseInt(contenido);
			}
		}
	}

	public void EscribirResultadoFichero(String equipo) throws SAXException {
		try {
			filtroEscritura.write(equipo);
		} catch (IOException e) {
			throw new SAXException(e.getMessage());
		}
	}
}

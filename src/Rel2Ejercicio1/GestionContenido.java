package Rel2Ejercicio1;

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
	private static Scanner teclado = new Scanner(System.in);
	private String etiquetaActual;
	private int edadM,edadH,contadorMujer=0,contadorHombre=0,mediaHombre,mediaMujer;
	private int edad,edadMayor = 0;
	private String nombre,nombreMayor,tipo;

	@Override
	public void startDocument() throws SAXException {

	}

	@Override
	public void endDocument() throws SAXException {
		//System.out.println("El nombre de la personas de mayor edad es " + nombreMayor+ " con la edad de "+edadMayor);
		
		mediaMujer=edadM/contadorMujer;
		mediaHombre=edadH/contadorHombre;
		
		System.out.println("Media edad Mujer: "+mediaMujer);
		System.out.println("Media edad Hombre: "+mediaHombre);
		
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		// Almaceno la ultima cadena que llego para saber en que punto estoy
		etiquetaActual = qName;
		
		//Recogo el tipo de atributo de persona en la variable tipo
		if(etiquetaActual.equals("persona")) {
			tipo=attributes.getValue("tipo");
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		etiquetaActual = "";
		
		

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contenido = new String(ch, start, length);

		contenido = contenido.replaceAll("[\t\n]", " ").trim();

		if (contenido.length() == 0)
			return;

		/*
		 * --------------------EJERCICIO 1--------------------------------------
		if (etiquetaActual.equals("nombre")) {
			nombre = contenido;
		}
			
		if (etiquetaActual.equals("edad")) {
			edad = Integer.parseInt(contenido);
			if (edad > edadMayor) {
				edadMayor = edad;
				nombreMayor=nombre;
			}
		}*/
		
		//-----------------------EJERCICIO 2------------------------------------
		switch (etiquetaActual) {
		case "edad":
			if(tipo.equals("M")) {
				edadM=Integer.parseInt(contenido)+edadM;
				contadorMujer++;
			}else {
				edadH=Integer.parseInt(contenido)+edadH;
				contadorHombre++;
			}
			break;

		}
	}
}

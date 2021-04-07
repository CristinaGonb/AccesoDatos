package Ejercicio2SaxExamen;

import java.util.Scanner;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionContenido extends DefaultHandler {
	private static Scanner teclado = new Scanner(System.in);
	private String etiquetaActual;
	private String periodo;
	private int horaIntroducida;
	private int mayorHumedadRelativa = 0;

	public GestionContenido(int hora) {
		this.horaIntroducida = hora;
	}

	// Arranca una vez cuando empieza
	// paso 1
	@Override
	public void startDocument() throws SAXException {

	}

	// paso 5

	@Override
	public void endDocument() throws SAXException {
		System.out.println("El máximo valor de la humedad relativa es "+mayorHumedadRelativa);
	}

	// Arranca con el inicio de la etiqueta
	// paso 2
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		//// Almaceno la ultima cadena que llego para saber en que punto estoy
		etiquetaActual = qName;

		if (etiquetaActual.equals("temperatura")) {
			periodo = attributes.getValue("periodo");
			// periodo = Integer.parseInt(attributes.getValue("periodo"));
		}
	}

	// paso 4
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		// En este metodo preferiblemente se usa para reiniciar las variables
		etiquetaActual = "";
		periodo = null;
		
	}

	// Recoge la cadena que llegue
	// paso 3
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contenido = new String(ch, start, length);

		contenido = contenido.replaceAll("[\t\n]", " ").trim();

		if (contenido.length() == 0)
			return;

		switch (etiquetaActual) {
		case "temperatura":
			if (periodo != null && periodo.equals(String.valueOf(horaIntroducida))) {
				System.out.println("La temperatura prevista a las " + horaIntroducida + " es" + contenido);
			}
			break;
		case "humedad_relativa":
			int humedad=Integer.parseInt(contenido);
			if(humedad > mayorHumedadRelativa) {
				mayorHumedadRelativa=humedad;
			}
			
			break;
		}

	}

}

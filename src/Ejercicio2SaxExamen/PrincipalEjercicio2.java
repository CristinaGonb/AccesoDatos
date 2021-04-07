package Ejercicio2SaxExamen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class PrincipalEjercicio2
{

	private static final String NOMBRE_FICHERO = "eltiempohoy.xml";
	private static Scanner teclado = new Scanner(System.in); 

	
	public static void main(String[] args) 
	{
		
		try {
			leerXMLConSax(NOMBRE_FICHERO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void leerXMLConSax(String nombreFichero) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException
	{
		int hora;
		do {
			System.out.println("Introduce la hora(10-23): ");
			hora = Integer.parseInt(teclado.nextLine());
		} while (hora < 10 || hora > 23);
		
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = parserFactory.newSAXParser();
		XMLReader reader = parser.getXMLReader();
		reader.setContentHandler(new GestionContenido(hora));
		reader.parse(new InputSource(nombreFichero));
		
	}



}

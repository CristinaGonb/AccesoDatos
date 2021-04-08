package Rel2Ejercicio4;

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


public class PrincipalEjercicio4
{

	private static final String NOMBRE_FICHERO = "alumnos.xml";
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
		
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = parserFactory.newSAXParser();
		XMLReader reader = parser.getXMLReader();
		reader.setContentHandler(new GestionContenido());
		reader.parse(new InputSource(nombreFichero));
		
	}



}

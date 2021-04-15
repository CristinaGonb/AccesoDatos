package Ejercicio3Ex13NovDom;


import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class PrincipalEjercicio3 {

	
	private static final String FICHERO_ORIGINAL = "universidad.xml";
	private static final String FICHERO_RESULTADO = "universidadNuevo.xml";
	
	public static void main(String[] args) {

	
		try {
			//Creamos el arbol xml
			Document arbolDocumento = crearArbolDOM();
			//modificamos el arbol
			modificarArbolDo(arbolDocumento);
			
			transformarDOMEnXml(arbolDocumento);
			System.out.println("Documento " + FICHERO_RESULTADO+ " creado correctamente.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void modificarArbolDo(Document arbol) {
		
	}
	
	private static void transformarDOMEnXml(Document arbolDocumento)
			throws TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		Source source = new DOMSource(arbolDocumento);
		Result result = new StreamResult(FICHERO_RESULTADO);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, result);
	}

	private static Document crearArbolDOM() throws ParserConfigurationException, SAXException, IOException {
		// Extraemos el arbol de nuestro documento XML.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document arbolDocumento = builder.parse(new File(FICHERO_ORIGINAL));
		return arbolDocumento;
	}

}

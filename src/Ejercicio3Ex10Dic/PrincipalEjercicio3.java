package Ejercicio3Ex10Dic;


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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class PrincipalEjercicio3 {

	
	private static final String FICHERO_ORIGINAL = "eltiempohoy.xml";
	private static final String FICHERO_RESULTADO = "eltiempohoycambio.xml";
	
	public static void main(String[] args) {

	
		try {

			Document arbolDocumento = crearArbolDOM();
			modificarArbolDOM(arbolDocumento);
			transformarDOMEnXml(arbolDocumento);
			System.out.println("Documento " + FICHERO_RESULTADO+ " creado correctamente.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void modificarArbolDOM(Document arbol)throws IOException {
		Node raizDocumento;
		Element elementoViento,elementoNuevo,elementoDireccion; //Guarda en cada vuelta
		NodeList listaViento,listaDireccion; //for
		int periodo;
		String direccion;
		
		raizDocumento=arbol.getFirstChild();
		//Guardamos en una lista los elementos viento
		listaViento=((Element) raizDocumento).getElementsByTagName("viento");
		
		for (int i = 0; i < listaViento.getLength(); i++) {
			elementoViento=(Element) listaViento.item(i);
			
		//Creo atributo periodo como etiqueta
			
			//Obtengo valor periodo
			periodo=Integer.parseInt(elementoViento.getAttribute("periodo"));
			
			//creo etiqueta nueva
			elementoNuevo=arbol.createElement("periodo");
			//añado al arbol
			elementoViento.appendChild(elementoNuevo);
			//añado valor
			elementoNuevo.setTextContent(String.valueOf(periodo));
			//elimino atributo periodo
			elementoViento.removeAttribute("periodo");
			
		//Creo etiqueta direccion como atributo
			
			//almaceno en una lista la direccion de cada elemento viento
			listaDireccion=elementoViento.getElementsByTagName("direccion");
			//recorro cada direccion de la lista
			elementoDireccion=(Element) listaDireccion.item(0);
			//almaceno en direccion el valor de la etiqueta
			direccion=elementoDireccion.getTextContent();
			//añado el atributo
			elementoViento.setAttribute("direccion", direccion.toString());
			//Elimino etiqueta direccion
			elementoViento.removeChild(elementoDireccion);
		}
		
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

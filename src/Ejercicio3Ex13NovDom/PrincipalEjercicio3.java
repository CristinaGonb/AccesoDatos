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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PrincipalEjercicio3 {

	private static final String FICHERO_ORIGINAL = "universidad.xml";
	private static final String FICHERO_RESULTADO = "universidadNuevo.xml";

	public static void main(String[] args) {

		try {
			// Creamos el arbol xml
			Document arbolDocumento = crearArbolDOM();
			// modificamos el arbol
			modificarArbolDo(arbolDocumento);

			transformarDOMEnXml(arbolDocumento);
			System.out.println("Documento " + FICHERO_RESULTADO + " creado correctamente.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void modificarArbolDo(Document arbol) throws IOException {
		Element elementoDepartamento, elementoEmpleado, elementoNuevoMedia, elementoPuesto; // guarda en cada vuelta
		Node raizDocumento;
		NodeList listaDepartamento, listaEmpleado, listaPuesto;// for
		int salarioTotal = 0, contador = 0, contadorProfesor = 0;
		double media;

		// Todos los elementos departamento del arbol
		raizDocumento = arbol.getFirstChild();
		// Almaceno los dpto en una lista para luego recorrerla y buscar la informacion
		// que necesito
		listaDepartamento = ((Element) raizDocumento).getElementsByTagName("departamento");

		for (int i = 0; i < listaDepartamento.getLength(); i++) {
			elementoDepartamento = (Element) listaDepartamento.item(i);

			// Guardo en una lista el empleado de cada dpto
			listaEmpleado = elementoDepartamento.getElementsByTagName("empleado");

			for (int j = 0; j < listaEmpleado.getLength(); j++) {
				elementoEmpleado = (Element) listaEmpleado.item(j);
				// Almaceno en la variable el salario de cada empleado
				salarioTotal = salarioTotal + Integer.parseInt(elementoEmpleado.getAttribute("salario"));
				contador++;

				// Guardo en una lista los elementos que sean puesto y los recorro
				listaPuesto = elementoEmpleado.getElementsByTagName("puesto");
				for (int j2 = 0; j2 < listaPuesto.getLength(); j2++) {
					elementoPuesto = (Element) listaPuesto.item(j2);

					// Cuenta los puestos que son profesor
					if (elementoPuesto.getTextContent().equals("Profesor")) {
						contadorProfesor++;
					}
				}
				
			}
			// Calculo la media del salario de los empleado de cada dpto
			media = salarioTotal / contador;
			salarioTotal = 0;
			contador = 0;
			

			// Añado atributo al dpto
			// elementoDepartamento.setAttribute("salarioMedio",String.valueOf(media));

			// 1 FORMA Crear como etiqueta el salarioMedio

			// Creo una etiqueta nueva de salarioMedio
			elementoNuevoMedia = arbol.createElement("salarioMedio");

			// añado al arbol(hago el elementoNuevo hijo del elementoDpto)
			elementoDepartamento.appendChild(elementoNuevoMedia);

			// añado valor
			elementoNuevoMedia.setTextContent(String.valueOf(media));

			/*
			 * 2 FORMA
			 * 
			 * //Creo una etiqueta nueva de salarioMedio
			 * elementoNuevoMedia=arbol.createElement("salarioMedio");
			 * 
			 * //añado al arbol(hago el elementoNuevo hijo del elementoDpto)
			 * elementoDepartamento.appendChild(elementoNuevoMedia);
			 * 
			 * //creo el nodo de texto Node
			 * textoSalario=arbol.createTextNode(String.valueOf(media));
			 * 
			 * //añadimos hijo a elementoNuevoMedia
			 * elementoNuevoMedia.appendChild(textoSalario);
			 */

			// añadir atributo tamaño a dpto dependiendo del numero de profesores >2 Grande
			// <=2 Pequeño

			if (contadorProfesor <= 2) {
				elementoDepartamento.setAttribute("tamaño", "PEQUEÑO");
			} else {
				elementoDepartamento.setAttribute("tamaño", "GRANDE");
			}
			contadorProfesor=0;
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

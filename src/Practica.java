

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Practica {

	public static void main(String[] args) {

		// Para comprobar si existe el archivo que quiero crear
		File archivo = new File("todasPersonas.obj");
		if (archivo.exists()) {
			System.out.println("El archivo ya existe "+archivo);
		} else {
			LecturaYEscrituraPersonas();
			leerFicheroTodasPersonas();
		}
	}

	private static void LecturaYEscrituraPersonas() {
		String linea;
		String[] arrayLinea;
		Persona persona;
		boolean finFichero=false;

		try (BufferedReader lectura = new BufferedReader(new FileReader("personas.txt"));

				ObjectInputStream lecturaObjeto = new ObjectInputStream(new FileInputStream("personas.obj"));
				ObjectOutputStream escrituraObjeto = new ObjectOutputStream(
						new FileOutputStream("todasPersonas.obj"));) {

			//Leer el txt y lo escriba
			linea = lectura.readLine();
			while (linea != null) {
				arrayLinea = linea.split(";");
				// almaceno la informacion del txt en persona
				persona = new Persona(arrayLinea[0], arrayLinea[1]);
				escrituraObjeto.writeObject(persona);

				linea = lectura.readLine();
			}

			//Leer el obj y lo escriba
			while (!finFichero) {
				try {
					persona = (Persona) lecturaObjeto.readObject();
					escrituraObjeto.writeObject(persona);
				} catch (EOFException e) {
					finFichero = true;
				} catch (ClassNotFoundException e) {
					System.out.println("Error al leer el fichero");
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentran algunos de los ficheros " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer el archivo" + e.getMessage());
		}
	}

	private static void leerFicheroTodasPersonas() {
		boolean finDeFichero = false;
		Persona persona;

		try (ObjectInputStream filtroLecturaObj = new ObjectInputStream(new FileInputStream("todasPersonas.obj"))) {

			while (!(finDeFichero)) {

				try {
					persona = (Persona) filtroLecturaObj.readObject();
					System.out.println(persona + "\n");
				} catch (EOFException e) {
					finDeFichero = true;
				} catch (ClassNotFoundException e) {
					System.out.println("Error al leer el fichero");
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}
}
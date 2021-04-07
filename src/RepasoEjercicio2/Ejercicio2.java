package RepasoEjercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Ejercicio2 {
	private static final String ALUMNOSORDENADO = "alumnoOrdenado.txt";
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		String nombre;

		System.out.println("Introduce el nombre del fichero de texto que deseas leer: ");
		nombre = teclado.nextLine();

		crearFicheroOrdenado(nombre);

	}

	public static void crearFicheroOrdenado(String nombre) {
		// Almaceno en file el nombre del fichero
		File fichero = new File(nombre);
		ArrayList<String> listaOrdenada = new ArrayList<String>();

		try (BufferedReader lectura = new BufferedReader(new FileReader(fichero));
				BufferedWriter escritura = new BufferedWriter(new FileWriter(ALUMNOSORDENADO))) {

			String linea;

			linea = lectura.readLine();
			while (linea != null) {
				// Añado arrayLinea que leo de mi lista
				listaOrdenada.add(linea);
				linea = lectura.readLine();

			}
			// Ordeno la lista por orden alfabetico de menor a mayor
			Collections.sort(listaOrdenada, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					String[] nombreCompletoUno = o1.split(" ");
					String[] nombreCompletoDos = o2.split(" ");
					return nombreCompletoUno[1].compareTo(nombreCompletoDos[1]);
				}
			});
			for (String nombresOrdenados : listaOrdenada) {
				escritura.write(nombresOrdenados+"\n");
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error no se encuentra el archivo introducido " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo " + e.getMessage());
			e.printStackTrace();
		}
	}

}

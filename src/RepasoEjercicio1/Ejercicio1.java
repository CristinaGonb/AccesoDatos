package RepasoEjercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {

	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {


		System.out.println("Introduce el nombre del archivo que deseas buscar: ");
		String archivo ="./"+ teclado.nextLine();

		File directorio = new File(archivo);

		if (directorio.exists()) {
			File[] listarArchivos = directorio.listFiles();

			for (int i = 0; i < listarArchivos.length; i++) {
				if (listarArchivos[i].isFile() && listarArchivos[i].getName().endsWith(".txt")) {
					//System.out.println(archivo+"/"+listarArchivos[i].getName());
					lecturaYEscrituraTxt(archivo+"/"+listarArchivos[i].getName());
				}
			}
		}else {
			System.out.println("No se encuentra un directorio con ese nombre ");
		}
	}

	private static void lecturaYEscrituraTxt(String archivo) {
		try (BufferedReader lectura = new BufferedReader(new FileReader(archivo));
				BufferedWriter escritura = new BufferedWriter(new FileWriter("FicheroTotal.txt", true))) {

			String linea;

			linea = lectura.readLine();
			while (linea != null) {
				escritura.write(linea+ "\n");
				linea = lectura.readLine();
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al leer archivo " + e.getMessage());
			e.printStackTrace();
		}
	}

}

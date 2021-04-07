package Rel1Ejerc1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ejercicio1 {
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		boolean salir = false;
		int opcion;

		while (!salir) {
			menu();
			System.out.println("Introduce la opcion del menu que deseas realizar: ");
			opcion = Integer.parseInt(teclado.nextLine());

			switch (opcion) {
			case 1:
				crearDirectorio();
				break;
			case 2:
				crearFichero();
				break;
			case 3:
				eliminarFichero();
				break;
			case 4:
				mostrarFicherosDeUnaCarpeta();
				break;
			case 5:
				salir = true;
				break;

			}
		}

	}

	public static void mostrarFicherosDeUnaCarpeta() {
		String nombre;
		ArrayList <String> ficherosQueContieneLaCarpeta= new ArrayList<String>();
		
		System.out.println("Introduce el nombre de la carpeta que deseas consultar: ");
		nombre = teclado.nextLine();

		File carpeta = new File(nombre);
		// Si es una carpeta y existe
		if (carpeta.isDirectory() && carpeta.exists()) {
			// Almaceno en un array de lista lo que contiene la carpeta
			File[] listarArchivos = carpeta.listFiles();

			// Recorremos el interior de la carpeta
			for (int i = 0; i < listarArchivos.length; i++) {
				//Primero almaceno el nombre de todos los ficheros en un array
				if (listarArchivos[i].isFile() && listarArchivos[i].getName().endsWith(".txt")) {
					ficherosQueContieneLaCarpeta.add(listarArchivos[i].getName());
				}
			}
			//Ordeno por nombre(A-Z)
			//Si ordenamos por algo en especifico, utilizamos comparator
			Collections.sort(ficherosQueContieneLaCarpeta);
			for (String archivo : ficherosQueContieneLaCarpeta) {
				System.out.println(archivo);
				
			}
		}
	}

	public static void eliminarFichero() {
		String nombre;
		System.out.println("Introduce el nombre del fichero que deseas borrar: ");
		nombre = teclado.nextLine();

		// Almacenamos el nombre del fichero
		File fichero = new File(nombre);

		if (fichero.isFile() && !fichero.exists()) {
			System.out.println("Error,el fichero no existe " + nombre);
		} else {
			if (fichero.delete()) {
				System.out.println("Fichero borrado correctamente. ");
			} else {
				System.out.println("No se puede borrar el fichero.");
			}
		}
	}

	public static void crearFichero() {
		String nombre, info;
		System.out.println("Introduce el nombre del fichero: ");
		nombre = teclado.nextLine();

		// Almacenamos el nombre del fichero
		File fichero = new File(nombre);

		if (fichero.isFile() && fichero.exists()) {
			System.out.println("Error, ya existe el fichero " + nombre);
		} else {
			// Si no existe, solicitamos informacion para almacenarla en el fichero
			System.out.println("Introduce la informacion que desea almacenar en el fichero: ");
			info = teclado.nextLine();
			// Creamos el fichero
			try (BufferedWriter modoEscritura = new BufferedWriter(new FileWriter(nombre));) {
				// Pasamos al fichero la informacion recogida por teclado
				modoEscritura.write(info);
				System.out.println("Fichero creado correctamente");
			} catch (IOException e) {
				System.out.println("Error al escribir el fichero " + e.getMessage());
				e.printStackTrace();

			}
		}
	}

	public static void crearDirectorio() {
		String nombre;
		boolean creado;

		System.out.println("Introduce el nombre del directorio: ");
		nombre = teclado.nextLine();

		// Almacenamos el nombre del directorio
		File directorio = new File(nombre);

		if (directorio.isDirectory() && directorio.exists()) {
			System.out.println("Error, ya existe el directorio " + nombre);
		} else {
			creado = directorio.mkdirs();
			// Comprobamos si se ha creado correctamente a través del flag de creado
			if (creado) {
				System.out.println("Directorio creado correctamente");
			} else {
				System.out.println("Error al crear el directorio " + nombre);
			}
		}
	}

	public static void menu() {
		System.out.println("#### MENU ####");
		System.out.println("1. Crear directorio ");
		System.out.println("2. Crear fichero de texto ");
		System.out.println("3. Borrar fichero de texto ");
		System.out.println("4. Mostrar los ficheros que contiene una carpeta ");
		System.out.println("5. Salir ");
	}

}

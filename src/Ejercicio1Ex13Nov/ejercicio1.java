package Ejercicio1Ex13Nov;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ejercicio1 {
private static final String FICHERO_INSTRUCCIONES="instrucciones.bin";
private static final String FICHERO_ERRORES="errores.txt";

	public static void main(String[] args) {
		boolean finFichero = false;
		int codigo = 0;
		String cadena;

		leerFicheroBinario(finFichero);
	}

	private static void leerFicheroBinario(boolean finFichero) {
		int codigo;
		String cadena;
		try (DataInputStream lectura = new DataInputStream(new FileInputStream(FICHERO_INSTRUCCIONES));) {
			while (!(finFichero)) {
				try {
					codigo = lectura.readInt();
					cadena = lectura.readUTF();

					// Cuando leo el archivo
					tratarFichero(cadena, codigo);

				} catch (EOFException e) {
					finFichero = true;
				}
			}
		} catch (FileNotFoundException e) {
			archivoError("No se ha encontrado el archivo " + e.getMessage());
		} catch (IOException e1) {
			archivoError("Error al leer el archivo " + e1.getMessage());
		}
	}

	private static void tratarFichero(String cadena, int codigo) {
		if (cadena != null) {
			File archivo = new File(cadena);
			
			switch (codigo) {
			case 1:
				if (archivo.isDirectory()) {
					archivoError("Error, ya existe la carpeta " + cadena);
				} else {
					archivo.mkdirs();
				}
				break;
				
			case 2:
				if (archivo.isFile()) {
					archivoError("Error, ya existe el archivo " + cadena);
				} else {
					try {
						archivo.createNewFile();
					} catch (IOException e) {
						archivoError("Error al crear el archivo " + e.getMessage());
					}
				}
				break;
			}
		} else {
			archivoError("Error, la cadena está vacía ");
		}
	}

	private static void archivoError(String cadena) {
		try (BufferedWriter escritura = new BufferedWriter(new FileWriter(new File(FICHERO_ERRORES), true));) {
			escritura.write(cadena + " \n");
		} catch (Exception e) {
			System.out.println("Error al crear el archivo de errores " + e.getMessage());
		}
	}
}

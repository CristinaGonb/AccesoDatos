package Ejercicio1Ex10Dic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ejercicio1 {

	public static void main(String[] args) {
		boolean finFichero = false;
		String nombreYApellidos;
		String direccion;
		int numeroCarta = 1;

		try (DataInputStream lectura = new DataInputStream(new FileInputStream("clientes.bin"));) {

			File carpeta = new File("CartasAClientes");

			if (!carpeta.exists()) {
				carpeta.mkdirs();
			} else {
				System.out.println("Ya existe la carpeta " + carpeta);
			}

			try {
				while (!finFichero) {
					nombreYApellidos = lectura.readUTF();
					direccion = lectura.readUTF();
					crearCarta(nombreYApellidos, direccion, numeroCarta);
					numeroCarta++; // Para aumentar el numero de cartas en cada vuelta
				}
			} catch (EOFException e) {
				finFichero = true;
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo " + e.getMessage());
		} catch (IOException e1) {
			System.out.println("Error al leer el archivo " + e1.getMessage());
		}
	}

	public static void crearCarta(String nombre, String direccion, int numeroCarta) {
		String linea;

		try (BufferedReader lecturaTxt = new BufferedReader(new FileReader("carta.txt"));
				BufferedWriter escrituraTxt = new BufferedWriter(new FileWriter("CartasAClientes/carta" + numeroCarta + ".txt"))) {

			linea = lecturaTxt.readLine();
			while (linea != null) {

				if (linea.contains("XXX")) {
					linea.replaceAll("XXX", nombre);
				}
				if (linea.contains("YYY")) {
					linea.replaceAll("YYY", direccion);
				}
				escrituraTxt.write(linea);
				linea = lecturaTxt.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer el archivo " + e.getMessage());
		}
	}
}

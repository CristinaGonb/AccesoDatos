package Ejercicio1Ex22En;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Principal {

	public static void main(String[] args) {
		String linea;
		String [] arrayLinea;
		boolean finFichero=false;
		
		
		try (BufferedReader lectura = new BufferedReader(new FileReader("notas.txt"));
				DataOutputStream escritura= new DataOutputStream(new FileOutputStream("Suspensos.dat"))){
			
			linea=lectura.readLine();
			while(linea != null) {
				linea.split(";");
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

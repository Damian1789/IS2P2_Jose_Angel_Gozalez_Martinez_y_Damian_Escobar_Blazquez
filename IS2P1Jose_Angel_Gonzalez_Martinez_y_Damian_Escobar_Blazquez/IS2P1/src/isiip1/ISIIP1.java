package isiip1;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package is2p1;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Scanner;
/**
 *
 * @author Damian
 */

//public class IS2P1 {

    /**
     * @param args the command line arguments
     */
/* EMPIEZA EN COMENTARIO
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        leeFichero("C:/Users/Damian/Desktop/Dio.txt");
    }
    
    public static void leeFichero(String archivo) throws FileNotFoundException, IOException{
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            System.out.println(cadena);
            //System.out.println();
    }
        b.close();                      //parece opcional
}
}
*/

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ISIIP1 {

	public static void main(String[] args) throws IOException {
		System.out.println("Bienvenido.");
		
		int x;
		boolean f=true;
		Scanner escribir = new Scanner(System.in);
		
                System.out.println("Eliga el precio maximo que puede tener un cliente");
		Metodos l = new Metodos(escribir.nextInt());
                
		while(f) {
			System.out.println("Eliga el valor asociado a lo que quiere hacer. ");
			System.out.println("1. Registrar un nuevo miembro.");
			System.out.println("2. Registrar una nueva motocicleta. ");
			System.out.println("3. Registrar una cesion ");
			System.out.println("4. Listar en pantalla los miembros con motos en posesión  ");
			System.out.println("5. Listar todas las motos ");
			System.out.println("6. Mostrar las cesiones realizadas ");
			System.out.println("7. Salir del programa. ");
			x = escribir.nextInt();
			switch(x) {
				case 1:
					l.registrarMiembro("C:/Archivos/Socios.txt");
					break;
					
				case 2:
					l.registrarMoto("C:/Archivos/Motos.txt", 
							"C:/Archivos/Socios.txt");
					break;
				
				case 3:
					l.registraCesion("C:/Archivos/Socios.txt", 
                                                "C:/Archivos/Motos.txt", 
                                                "C:/Archivos/Cesiones.txt");
					break;
					
				case 4:
					l.leerTodo("C:/Archivos/Socios.txt");
					System.out.println();
					break;
				case 5:
					l.leerMotosyDuenyo("C:/Archivos/Socios.txt");
					System.out.println();
					break;
					
				case 6:
					l.muestraCesiones("C:/Archivos/Cesiones.txt");
					break;
					
				case 7:
					System.out.println("Gracias y adios");	
					f=false;
					break;
					
				default:
					System.out.println("El valor elegido no funciona en el menu.");
					System.out.println("Porfavor eliga otro.");
					System.out.println("");
					break;
			}
		}
		System.out.println("adios");
	}
}

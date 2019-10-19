package isiip1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package is2p1;

/**
 *
 * @author Damian
 */
//public class Metodos {
    
//}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Metodos {
	FileReader fileR;
	FileWriter fileW;
	BufferedReader lineafileR;
	BufferedWriter lineafileW;
	Scanner x = new Scanner(System.in);
	StringTokenizer token;
	int precioMAX;
	
	public Metodos(int p) throws IOException {
		precioMAX=p;
	}
	
	public void registrarMiembro(String directorio) throws IOException {
		fileR = new FileReader(directorio);
		lineafileR = new BufferedReader(fileR);
		fileW = new FileWriter(directorio,true);
		lineafileW = new BufferedWriter(fileW);
		int aux;
		
		String linea = null ,auxiliar;                                  // creamos 2 strings: linea que empieza en null y auxiliar que es otro string
		while((auxiliar = lineafileR.readLine())!=null)linea=auxiliar;  // entra cuando llega a la última línea y en "linea" metemos la ultima linea
		
		token =new StringTokenizer(linea);                              // cada token almacena una parte de la línea
		aux=Integer.parseInt(token.nextToken().trim())+1;               // almacena como un entero la palabra de la linea y le suma una (porque será un nuevo usuario), lo almacena en aux
		fileW.write("\n");                                              // nos vamos a una nueva línea 
		fileW.write("   ");                                     
		fileW.write(Integer.toString(aux));                             // escribimos el id del nuevo miembro
        fileW.write("		");                             
		System.out.println("Introduzca el nombre del nuevo miembro:");  // pedimos amablemente que se introduzca 
		fileW.write(x.nextLine());                                      // se introduce el nombre del nuevo miembro en su columna
        fileW.write("		");                           
		fileW.write("0");                               
        fileW.write("		");                           
		fileW.write("0");
        fileW.write("		");                             
		fileW.write("Ninguna");
		
		lineafileW.close();                                             // lo cerramos por si las moscas
		lineafileR.close();
		fileR.close();
		fileW.close();
	}
	
	public void registrarMoto(String directorioM, String directorioU) throws IOException {
		fileR = new FileReader(directorioU);
		lineafileR = new BufferedReader(fileR);
		
		String nombreM, codigoU;                                        // nombreM alamcenará el nombre de la moto y codigoU almacenará el id del usuario
		int precio,cont = (-2);
		
		System.out.println("Seleccione a quien le quiere asociar la moto poniendo su codigo: ");
		codigoU=x.next();                                               // alamecenamos el id del usuario en codigoU
		while(lineafileR.readLine() != null)cont++;                     // comprueba si existe le usuario
		if(cont<Integer.parseInt(codigoU)) {                            // mirando linea a línea si está en el fichero
			System.out.println("El codigo de usuario no existe en la base de datos");
			return;
		}
		fileR.close();
		lineafileR.close();
		
		System.out.println("Escriba el nombre de la moto:");
		nombreM=x.next();                                               // almacenamos el nombre de la moto en nombreM
		
		System.out.println("Escriba el precio de la moto:");
		precio=x.nextInt();                                             // almacenamos el precio de la moto en precio
		
		if(comprobacion(directorioU, precio, codigoU)) {                // comprobamos si el usuario puede conseguir la moto sin exceder 6000 euros en motos
		System.out.println("Precio valido: ");
		introducirMoto(directorioM,precio,nombreM);                     // introducimos la moto
		relacionarMotoUsuario(precio,directorioU,nombreM,codigoU);      // la asociamos con su usuario
		}else {
			System.out.println("El precio supera lo permitido");    // si el usuario se pasa de 6000 en motos te avisa
		}
	}
	
	public boolean comprobacion(String directorio, int precio,String codigoU) throws IOException { // comprueba si el precio de la moto es correcto
		fileR = new FileReader(directorio);
		lineafileR = new BufferedReader(fileR);
		String linea;
		lineafileR.readLine();
		while((linea=lineafileR.readLine()) != null) {                          // va linea a linea
			token =new StringTokenizer(linea);
			if(token.nextToken().equals(codigoU)) {                         // cuando encuentra al usuario con el que queremos asociar la moto, entonces entra
				token.nextToken();
				token.nextToken();
				if((Integer.parseInt(token.nextToken())+precio)<precioMAX) { // comprobamos que no se pasa de 6000 euros al adquiror la moto
					return true;                                    // si no se pasa devuelve true
				}
			}
		}
		return false;                                                           // en cualuier otro caso devuelve false
	}
	
	public void introducirMoto(String directorio, int precio, String nombre) throws IOException { // este método inserta una moto en el directorio, con todos los datos de la moto
		fileW = new FileWriter(directorio,true);
		lineafileW = new BufferedWriter(fileW);
		
		fileW.write("\n");                                      // vamos a una nueva línea
		fileW.write(nombre);                                    // escribimos el nombre de la moto
		System.out.println("Escriba los caballos de la moto:"); 
		fileW.write("	");
		fileW.write(x.next());                                  // se insertan por teclado los caballos de la moto
		fileW.write("	");
		fileW.write(Integer.toString(precio));                  // se escribe el precio de la moto
		System.out.println("Escriba los costes de mantenimiento de la moto:"); 
		fileW.write("	");
		fileW.write(x.next());                                  // se insertan por teclado los costes de mantenimiento de la moto
		lineafileW.close();                                     // los cerramos por si acaso
		fileW.close();
	}
	
	public void relacionarMotoUsuario(int precio, String directorio, String nombreM, String codigoU) throws IOException { // PREGUNTAR A JA
		fileR = new FileReader(directorio);
		lineafileR = new BufferedReader(fileR);
		
		String linea, aux;
		int auxiliar;
		ArrayList<String> copia = new ArrayList<String>();
		
		while((linea=lineafileR.readLine()) != null) {
			token =new StringTokenizer(linea);
			if(token.nextToken().equals(codigoU)) {
				token =new StringTokenizer(linea);
                                    
				//linea=token.nextToken()+"			"+token.nextToken();
                                    linea="   " + token.nextToken()+"		"+token.nextToken(); 
				auxiliar=Integer.parseInt(token.nextToken())+1;
				linea=linea+"		"+Integer.toString(auxiliar);
				auxiliar=Integer.parseInt(token.nextToken())+precio;
				linea=linea+"		"+Integer.toString(auxiliar)+"		";
				while(token.hasMoreElements()) {
					aux=token.nextToken();
					if(!aux.equals("Ninguna")) {
					linea=linea+aux+" ";
					}
				}
				linea=linea+nombreM;
			}
			copia.add(linea);
			copia.add("\n");
		}
		
		lineafileR.close();
		fileR.close();
		
		fileW = new FileWriter(directorio);
		lineafileW = new BufferedWriter(fileW);
		
		while(copia.size()!=1) {
		fileW.write(copia.get(0));
		copia.remove(0);
		}
		
		lineafileW.close();
		fileW.close();
	}
	
	public void leerTodo(String directorio) throws IOException {    // lee todas las líneas del archivo
		
		String linea;
		fileR = new FileReader(directorio);
		lineafileR = new BufferedReader(fileR);
		
		while(( linea = lineafileR.readLine())!=null) {         // lee todas las líneas, una a una
			token =new StringTokenizer(linea);
			token.nextToken();
			token.nextToken();
			if(!token.nextToken().equals("0")) {
				System.out.println(linea);              // imprime la línea entera
			}
		}
		lineafileR.close();
		fileR.close();
	}
	
	public void leerMotosyDuenyo(String directorio) throws IOException {
		
		String linea,duenyo;
		fileR = new FileReader(directorio);
		lineafileR = new BufferedReader(fileR);
		
		while(( linea = lineafileR.readLine())!=null) {
			
			token = new StringTokenizer(linea);
			token.nextToken();
			duenyo=token.nextToken();                        // almaecenamos el dueño en "dueño"
			token.nextToken();
			
			if(!token.nextToken().equals("0")) {            // PREGUNTAR A JA si tiene más de 0 motos
				System.out.print(duenyo);                // imrimimos el dueño
				System.out.print("----->");
				System.out.println(token.nextToken());  // `REGUNTAR A JA imprimimos su moto
				
				while(token.hasMoreTokens()) {          // en el caso de que tenga más de una moto, mientras tenga motos por imprimir entrará
					System.out.print(duenyo);        // muestra dueño
					System.out.print("----->");
					System.out.println(token.nextToken()); // muestra su moto
				}
			}
		}
		lineafileR.close();                                     // cerramos por si acaso
		fileR.close();
	}
        public void registraCesion(String directorioU, String directorioM, String directorioC) throws IOException {
                String codigoU1, codigoU2, nombreM;
                int precio;
                //comprobamos que el usuario que cede existe
                //comprobamos que el usuario al que cedemos existe
                //comprobamos que la moto existe
                // comprobamso que el que cede tiene al menos una moto
                // si tras la cesion se queda sin moto hay que poner ninguna
                System.out.println("Introduzca el codigo del usuario que cesa");        // pedimos un usuario por linea de comandos
                                            // lo guardamos en codigoU1
                codigoU1 = x.nextLine();
                
                if (compruebaUsuario(codigoU1, directorioU)){                           //comprobamos que existe el usuario
                    if (tieneAlgo(codigoU1, directorioU)){                              // comprobamos si tiene alguna moto
                        System.out.println("Introduzca el codigo del usuario al que recibe la cesion");// pedimos un usuario por linea de comandos
                        codigoU2 = x.nextLine();                                            // lo guardamos en codigoU2
                        if (compruebaUsuario(codigoU2, directorioU)){                       //comprobamos que exite el usuario
                            System.out.println("Introduzca el nombre de la moto a ceder ");
                            nombreM = x.nextLine();
                            if (compruebaMoto(nombreM, codigoU1, directorioU)){             // comprobamos que el usuario que cesa tiene esa moto
                                precio = obtenPrecio(nombreM, directorioM);                 // sacamos el precio de la moto
                                if (comprobacion(directorioU, precio, codigoU2)){           // comprobamos que el usuario 2 no excederá de precioMAX al recibir la moto                                                                                  
                                                                            
                                    fileW = new FileWriter(directorioC, true);
                                    lineafileW = new BufferedWriter(fileW);                                
                                    fileW.write("\n");
                                    fileW.write(codigoU1);                                  // escribimos el nombre del que cede
                                    fileW.write(" cede a ");
                                    fileW.write(codigoU2);
                                    fileW.write(" la moto ");
                                    fileW.write(nombreM);
                                    fileW.write(" de precio ");
                                    fileW.write(Integer.toString(precio));
                                    fileW.write(" con fecha");
                                    System.out.println("Escriba la fecha en la que se realiza la cesion:");
                                    fileW.write(" ");
                                    fileW.write(x.next());
                                    
                                    lineafileW.close();                                             // lo cerramos por si las moscas                                                                        
                                    fileW.close();	
                                    
                                    System.out.println("Cesion realizada con exito");
                                    
                                    
                                    eliminaMoto(directorioU, codigoU1, nombreM, directorioM);
                                    anyadeMoto(directorioU, codigoU2, nombreM, directorioM);
                                }
                                else
                                    System.out.println("El miembro que recibira la cesion no puede hacerlo debido a que excedera la cifra de 6 "+precioMAX+" euros en motos");
                            
                            }
                            else
                                System.out.println("El miembro que ha dicho no tiene esa moto, por lo que es imposible realizar la cesion");
                        }
                        else
                            System.out.println("No existe el miembro");
                    }
                    else
                        System.out.println("el miembro no tiene motos, por lo que no puede cesar ninguna");
                }
                else
                    System.out.println("No existe el miembro");
        }
        
        public void registraCesion(String directorioU, String directorioM, String directorioC, String codigoU1, String moto) throws IOException {
                String codigoU2;
                int precio;
                //comprobamos que el usuario al que cedemos existe
                //comprobamos que la moto existe
                // comprobamso que el que cede tiene al menos una moto
                // si tras la cesion se queda sin moto hay que poner ninguna
                if (tieneAlgo(codigoU1, directorioU)){                                   // comprobamos si tiene alguna moto
                    System.out.println("Introduzca el codigo del usuario al que recibe "); // pedimos un usuario por linea de comandos
                    System.out.println("la moto "+moto+" en la cesion");
                    codigoU2 = x.next();                                                 // lo guardamos en codigoU2
                    if (compruebaUsuario(codigoU2, directorioU) && !codigoU1.equals(codigoU2)){                        //comprobamos que exite el usuario
                        precio = obtenPrecio(moto, directorioM);                         // sacamos el precio de la moto
                        if (comprobacion(directorioU, precio, codigoU2)){                // comprobamos que el usuario 2 no excederá precioMAX al recibir la moto                                                                                  
                                                                            
                            fileW = new FileWriter(directorioC, true);
                            lineafileW = new BufferedWriter(fileW);                                
                            fileW.write("\n");
                            fileW.write(codigoU1);                                          // escribimos el nombre del que cede
                            fileW.write(" cede a ");
                            fileW.write(codigoU2);
                            fileW.write(" la moto ");
                            fileW.write(moto);
                            fileW.write(" de precio ");
                            fileW.write(Integer.toString(precio));
                            fileW.write(" con fecha");
                            System.out.println("Escriba la fecha de hoy:");
                            fileW.write(" ");
                            fileW.write(x.next());
                                    
                            lineafileW.close();                                             // lo cerramos por si las moscas                                                                        
                            fileW.close();	
                                    
                            System.out.println("Cesion realizada con exito");
                                    
                                    
                            eliminaMoto(directorioU, codigoU1, moto, directorioM);
                            anyadeMoto(directorioU, codigoU2, moto, directorioM);
                        }else{
                            System.out.println("El miembro que recibira la cesion no puede hacerlo debido a que excedera la cifra de "+precioMAX+" euros en motos");
                            registraCesion(directorioU, directorioM, directorioC, codigoU1, moto);
                            }
                    }else{
                        System.out.println("No existe el miembro a ceder la moto");
                        registraCesion(directorioU, directorioM, directorioC, codigoU1, moto);
                    }
                }else{
                    System.out.println("el miembro no tiene motos");
                }
        }
        
        public boolean compruebaUsuario(String codigoU, String directorioU) throws IOException{
                fileR = new FileReader(directorioU);
		lineafileR = new BufferedReader(fileR);    
                String linea;
		lineafileR.readLine();
		while((linea=lineafileR.readLine()) != null) {                          // va linea a linea
			token = new StringTokenizer(linea);
			if(token.nextToken().equals(codigoU)) {                         // si encuentra al usuario es que existe
                            return true;                                                // y devolvemos true
                        }
                }
                return false;                                                           // si no encuentra el codigo de ese usuario es que no existe o no está registrado, por lo que devolvemos false
        }
        public boolean tieneAlgo(String codigoU, String directorioU) throws IOException{
                fileR = new FileReader(directorioU);
		lineafileR = new BufferedReader(fileR);    
                String linea;
                String cero = "0";
		lineafileR.readLine();
		while((linea=lineafileR.readLine()) != null) {                  // va linea a linea
			token =new StringTokenizer(linea);
                        if(token.nextToken().equals(codigoU)) {                 // si encuentra al usuario es que existe
                            token.nextToken();
                            if(!token.nextToken().equals(cero)){                // si tiene alguna moto para cesar
                                return true;                                    // devolvemos true
                            }                                               
                        }
                }
            return false;                                                       // si no tiene motos devolvemos false
        }
        public boolean compruebaMoto(String codigoM, String codigoU1, String directorioU) throws IOException{
            
                fileR = new FileReader(directorioU);
		lineafileR = new BufferedReader(fileR);    
                String linea;
		lineafileR.readLine();
                int nMotos;
		while((linea=lineafileR.readLine()) != null) {                          // va linea a linea
			token =new StringTokenizer(linea);
			if(token.nextToken().equals(codigoU1)) {                        // una vez encontramos al usuario
                            token.nextToken();
                            nMotos = Integer.parseInt(token.nextToken()); //guardamos el número de motos del que cede en nMotos                                
                            if(nMotos>0) {                                  // comprobamos si tiene más de 0 motos
                                token.nextToken();     					    // borrar, es solo una comprbacion mia
                                while (nMotos >0){                          // mientras queden motos por comprobar
                                    if (token.nextToken().equals(codigoM)){ // si encontramos la moto a ceder
                                       return true;                         // devolvemos true
                                    }
                                    else
                                        nMotos--;                 
                                }
                            }
                        }
                
                }
            return false;        // si no encuentra el codigo de ese usuario es que no existe o no está registrado, por lo que devolvemos false
        }
        
        
        public int obtenPrecio(String codigoM, String directorioM) throws IOException{
            
                fileR = new FileReader(directorioM);
		lineafileR = new BufferedReader(fileR);    
                String linea;
		lineafileR.readLine();
		while((linea=lineafileR.readLine()) != null) {                          // va linea a linea
			token =new StringTokenizer(linea);
			if(token.nextToken().equals(codigoM)) {                        // una vez encontramos la moto
                            token.nextToken();                            
                            return Integer.parseInt(token.nextToken());
                        }
                }
            return 80000;
            
        }
        
        
        public void muestraCesiones(String directorioC) throws IOException{
            String linea;
		fileR = new FileReader(directorioC);
		lineafileR = new BufferedReader(fileR);
		
		while(( linea = lineafileR.readLine())!=null) {         // lee todas las líneas, una a una
                    	System.out.println(linea);              // imprime la línea entera
			
                } 
		System.out.println();
		lineafileR.close();
		fileR.close();
		
        }
        
        public void eliminaMoto(String directorioU, String codigoU1, String nombreM, String directorioM) throws FileNotFoundException, IOException{
                String linea, aux;
                String vacio = "";                        
                int precio;                                                     // aquí irá el precio final de la resta
                int precioM = obtenPrecio(nombreM, directorioM);                // almacena el precio de la moto, el que debemos restar
                boolean miPrimeraMoto = true;
                fileR = new FileReader(directorioU);
		lineafileR = new BufferedReader(fileR);                     
		lineafileR.readLine();
                ArrayList<String> copia = new ArrayList<String>();		
                int nMotos;
		while((linea=lineafileR.readLine()) != null) {            // va linea a linea
			token =new StringTokenizer(linea);
                        linea=vacio;
                        aux = token.nextToken();
			if(aux.equals(codigoU1)) {                        // una vez encontramos al usuario 
                            linea=vacio;                                  // limpiamos la linea para poder reescribirla
                            linea = linea + aux;                          // lo guardamos en linea
                            linea = linea + "   " + token.nextToken();    // guardamos su nombre también
                            nMotos = Integer.parseInt(token.nextToken()); // guardamos el número de motos del que cede en nMotos  
                            aux = String.valueOf(nMotos-1);               // le quitamos una moto al que cesa                            
                            linea = linea +  "  " + aux;                  // lo almacenamos en linea
                            precio = Integer.parseInt(token.nextToken()) - precioM; //sacamos el precio de sus motos pero quitando el de la moto que pierde
                            linea = linea + "   " + precio; //añadimos elnuevo precio de sus motos                            
                            if (nMotos == 1){                             // si solo tenía una moto, como se la hemos quitado debemos poner que ya no tiene ninguna
                                linea = linea + "   " + "Ninguna";        // ya no tiene ninguna moto                                
                                }
                            else
                                while(token.hasMoreElements()) {                    // si tiene más de una moto
                                    aux = token.nextToken();
                                    if ((aux.equals(nombreM))&&(miPrimeraMoto)){
                                        miPrimeraMoto = false;
                                    }
                                    else{
                                        linea = linea + " " + aux;        // alamcenamos el nombre de las demás motos
                                    }
                            }
                        }
                        else {                            
                            linea = linea + "   " + aux;                        // almacenamos el id del miembro                           
                            linea = linea + "   " + token.nextToken();          // almacenamos el nombre del miembro
                            linea = linea + "   " + token.nextToken();          // almaenamos el numero de motos
                            linea = linea + "   " + token.nextToken();          // almacenamos el precio de las motos
                            linea = linea + "   " + token.nextToken();          // alamcenamos el nombre de la moto
                            while(token.hasMoreElements()) {                    // si tiene más de una moto
                                linea = linea + " " + token.nextToken();        // alamcenamoe el nombre de las demás motos
                            }
                            
                        }
                            
                        
                    copia.add(linea);
                    copia.add("\n");  
                    linea=vacio;
                }
                lineafileR.close();                                     // cerramos por si acaso
                fileR.close();
                
                fileW = new FileWriter(directorioU);
                lineafileW = new BufferedWriter(fileW);
		
                fileW.write("NªSocio		Nombre		NªMotos		Importe		Motos");
                fileW.write("\n");
                
		while(copia.size()!=1) {
		fileW.write(copia.get(0));
		copia.remove(0);
                }
                
                lineafileW.close();
                fileW.close();
        }
        
        public void anyadeMoto(String directorioU, String codigoU, String nombreM, String directorioM) throws FileNotFoundException, IOException{ //añadeMoto
                String linea, aux;
                String vacio = "";                        
                int precio;                                                     // aquí irá el precio final de la resta
                int precioM = obtenPrecio(nombreM, directorioM);                // almacena el precio de la moto, el que debemos restar
                String ayudante;
                boolean ayudante2 = false;
                fileR = new FileReader(directorioU);
                lineafileR = new BufferedReader(fileR);                     
                lineafileR.readLine();
                ArrayList<String> copia = new ArrayList<String>();		
                int nMotos;
                while((linea=lineafileR.readLine()) != null) {            // va linea a linea
                	token =new StringTokenizer(linea);
                        linea=vacio;
                        aux = token.nextToken();
                        if(aux.equals(codigoU)) {                        // una vez encontramos al usuario 
                            linea=vacio;                                  // limpiamos la linea para poder reescribirla
                            linea = linea + "   ";                        // un espacio para cuadrar las lineas
                            linea = linea + aux;                          // lo guardamos en linea
                            linea = linea + "		" + token.nextToken();    // guardamos su nombre también
                            nMotos = Integer.parseInt(token.nextToken()); // guardamos el número de motos del que cede en nMotos  
                            aux = String.valueOf(nMotos+1);               // le quitamos una moto al que cesa                            
                            linea = linea +  "		" + aux;                  // lo almacenamos en linea
                            precio = Integer.parseInt(token.nextToken()) + precioM; //sacamos el precio de sus motos pero sumando el de la moto que gana
                            linea = linea + "		" + precio;       //añadimos el nuevo precio de sus motos                            
                            linea = linea + "	        ";
                            while(token.hasMoreElements()) {                    // mientras tenga motos
                                aux = token.nextToken();
                                ayudante = aux;
                                if (ayudante.equals("Ninguna")){
                                    ayudante2=true;
                                }
                                if (!aux.equals("Ninguna")){
                                    linea = linea + aux + " " ;        // alamcenamos el nombre de todas sus motos    
                                }
                                                                                                              
                                                                        
                            }
                            if(ayudante2){
                                linea = linea  + nombreM;      // por último le añadimos la moto que gana  
                            }
                            else{
                                linea = linea + nombreM;      // por último le añadimos la moto que gana 
                            }
                             
                                                        
                        }
                        else {                            
                            linea = linea + "   " + aux;                        // almacenamos el id del miembro                           
                            linea = linea + "		" + token.nextToken();          // almacenamos el nombre del miembro
                            linea = linea + "		" + token.nextToken();          // almaenamos el numero de motos
                            linea = linea + "		" + token.nextToken();          // almacenamos el precio de las motos
                            linea = linea + "		" + token.nextToken();          // alamcenamos el nombre de la moto
                            while(token.hasMoreElements()) {                    // si tiene más de una moto
                                linea = linea + " " + token.nextToken();        // alamcenamoe el nombre de las demás motos
                            }
                            
                        }
                            
                        
                    copia.add(linea);
                    copia.add("\n");  
                    linea=vacio;
                }
                lineafileR.close();                                   
                fileR.close();
                
                fileW = new FileWriter(directorioU);
                lineafileW = new BufferedWriter(fileW);
		
                fileW.write("NªSocio		Nombre		NªMotos		Importe		Motos");
                fileW.write("\n");
                
                while(copia.size()!=1) {
                	fileW.write(copia.get(0));
                	copia.remove(0);
                }
                
                lineafileW.close();
                fileW.close();
        }
        
        /**
     *
     * @param directorioM
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void anyadeImporte(String directorioM) throws FileNotFoundException, IOException{                    
                String linea, nombreM, aux;
                String vacio = "";
                int importe, precio;
                ArrayList<String> copia = new ArrayList<String>();		
                System.out.println("Dime el nombre de la moto a la que anyadir el importe");
                nombreM = x.next();                
                if(existeMoto(directorioM, nombreM)){
                    System.out.println("Dime el importe a anyadir");
                    importe = x.nextInt();
                    fileR = new FileReader(directorioM);
                    lineafileR = new BufferedReader(fileR);
                    lineafileR.readLine();
                    while((linea=lineafileR.readLine()) != null) {      // va linea a linea
                        token =new StringTokenizer(linea);
                        aux =token.nextToken(); 
                        if(aux.equals(nombreM)){                        // si encontramos la moto
                            linea = vacio;
                            linea = linea + aux;                        // almacenamos el nombre de la moto
                            linea = linea + "           " + token.nextToken();  // almacenamos los caballos de la moto
                            linea = linea + "		" + token.nextToken();  // almacenamos el precio de la moto
                            precio = importe + Integer.parseInt(token.nextToken());
                            linea = linea + "		" + precio;             // alamcenamos el coste del mantenimiento de la moto
                        
                        }
                        else{
                            linea = vacio;
                            linea = linea + aux;                        // almacenamos el nombre de la moto
                            linea = linea + "           " + token.nextToken();  // almacenamos los caballos de la moto
                            linea = linea + "		" + token.nextToken();  // almacenamos el precio de la moto
                            linea = linea + "		" + token.nextToken();  // alamcenamos el coste del mantenimiento de la moto                           
                        }
                        copia.add(linea);
                        copia.add("\n");  
                        linea=vacio;    
                    }
                    lineafileR.close();                                     // cerramos por si acaso                                
                    fileR.close();
                    
                    fileW = new FileWriter(directorioM);
                    lineafileW = new BufferedWriter(fileW);
		                    
                    fileW.write("Nombre		Caballos	precio		Mantenimiento");
                    fileW.write("\n");
                
                    while(copia.size()!=1) {
                	fileW.write(copia.get(0));
                	copia.remove(0);
                    }
                    
                    lineafileW.close();
                    fileW.close();
                }
                else
                    System.out.println("No existe la moto mencionada, lo lamento");
        }
        
    /**
     *
     * @param directorioM
     * @param nombreM
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean existeMoto(String directorioM, String nombreM)throws FileNotFoundException, IOException{
            fileR = new FileReader(directorioM);
            lineafileR = new BufferedReader(fileR);String linea;
            lineafileR.readLine();
            while((linea=lineafileR.readLine()) != null) {          // va linea a linea
		token =new StringTokenizer(linea);
		if(token.nextToken().equals(nombreM)) {             // si encontramos la moto
                    lineafileR.close();                             // cerramos por si acaso                                
                    fileR.close();
                    return true;                                    // devolvemos true
                } 
            }
            lineafileR.close();                                     // cerramos por si acaso                                
            fileR.close();
            return false;                                           // si no la encontramso devolvemos false
        }
        
        public void eliminarUsuario(String directorioU, String directorioM, String directorioC) throws IOException{
            String id;
            System.out.println("introduzca el id del usuario que quieres borrar");
            id=x.next();
            
            if(compruebaUsuario(id, directorioU)){                             //comprobamos si el usuario existe
             transferirMotos(directorioU,directorioM, directorioC, id);        //si funciona correctamente se acaba borrando al usuario
             System.out.println("3");
             eliminarEnSocios(directorioU,id);                                 //elimina al socio
            }else{
                eliminarUsuario(directorioU, directorioM, directorioC);
            }
        }
        
        public void transferirMotos(String directorioU, String directorioM, String directorioC, String idU) throws FileNotFoundException, IOException{
            fileR = new FileReader(directorioU);
            lineafileR = new BufferedReader(fileR);
            ArrayList<String> aux = new ArrayList<String>();
            String linea;
            
            while((linea=lineafileR.readLine())!=null){
                token = new StringTokenizer(linea);
                if(token.nextToken().equals(idU)){
                    token.nextToken();token.nextToken();token.nextToken();                  //token hasta llegar a motos
                    while(token.hasMoreElements()){
                        aux.add(token.nextToken());
                    }
                    while(!aux.isEmpty()){
                        registraCesion(directorioU, directorioM, directorioC, idU, aux.get(0));
                        aux.remove(0);
                    }
                    break;
                }
            }
        }
        
        public void eliminarEnSocios(String d, String id) throws FileNotFoundException, IOException{
            String linea;
            fileR = new FileReader(d);
            lineafileR = new BufferedReader(fileR);
            ArrayList copia = new ArrayList();
            boolean aux=false;
            
            while((linea=lineafileR.readLine())!=null){
                token = new StringTokenizer(linea);
                if(!token.nextToken().equals(id)){              //añade cada linea de texto excepto
                    copia.add(linea);                           //la del usuario a borrar
                }                                               //que la salta
            }
            
            lineafileR.close();                                   
            fileR.close();
                
            fileW = new FileWriter(d);
            lineafileW = new BufferedWriter(fileW);
                
            while(copia.size()!=0) {
                if(aux){
                    fileW.write("\n");
                }else{
                    aux=true;
                }
              	fileW.write((String) copia.get(0));
               	copia.remove(0);
                }
                
            lineafileW.close();
            fileW.close();
            
            System.out.println("el usuario fue borrado con exito");
        }
}


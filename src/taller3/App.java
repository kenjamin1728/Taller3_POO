package taller3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		Sistema sistema = new Sistemaimpl();
		leerHechizos(sistema);
		leerMagos(sistema);
		submenu(scanner, sistema);
		

	}
	
	public static void leerMagos(Sistema sistema)
	{
		try (Scanner s = new Scanner(new File("Magos.txt")))
		{
			while(s.hasNextLine())
			{
				
				String linea = s.nextLine();
				String[] part = linea.split(";");
				String nombre = part[0];
				String[] part2 = part[1].split("\\|");
				String[] hechizos = new String[part2.length];
				for(int i = 0 ; i<part2.length ; i++)
				{
					String hechizo = part2[i];
					hechizos[i]=hechizo;
				}
				sistema.crearMago(nombre,hechizos);
				
			}
		} catch (FileNotFoundException e) 
		{
			System.out.println("Archivo no encontrado");
		}
	}
	
	
	public static void leerHechizos(Sistema sistema )  
	{
		try (Scanner s = new Scanner(new File("Hechizos.txt")))
		{
			while(s.hasNextLine())
			{
				String linea = s.nextLine();
				String[] part = linea.split(";");
				String nombre = part[0];
				String tipo = part[1];
				int daño = Integer.parseInt(part[2]);
				if(tipo.equalsIgnoreCase("fuego"))
				{
					int duracionQuemadura = Integer.parseInt(part[3]);
					sistema.crearHechizoFuego(nombre, daño, duracionQuemadura);
				}else if (tipo.equalsIgnoreCase("Agua"))
				{
					String[] part2 = part[3].split(",");
					
					int cantidadHeal = Integer.parseInt(part2[0]);
					int presionAgua = Integer.parseInt(part2[1]);
					sistema.crearHechizoAgua(nombre, daño, cantidadHeal, presionAgua);
				}else if (tipo.equalsIgnoreCase("Tierra"))
				{
					int mejoraDefensa = Integer.parseInt(part[3]);
					sistema.crearHechizoTierra(nombre, daño, mejoraDefensa);
				}else if (tipo.equalsIgnoreCase("Planta"))
				{
					String[] part2 = part[3].split(",");
					
					int duracionStun = Integer.parseInt(part2[0]);
					int Cantplantas = Integer.parseInt(part2[1]);
					sistema.crearHechizoPlanta(nombre, daño, duracionStun, Cantplantas);
				}
			}
			
		} catch (FileNotFoundException e) 
		{
			System.out.println("Archivo no encontrado");
			
		}
		
	}

	public static void mostrarHechizos(Sistema sistema)
	{
		String[] mensaje = sistema.entregarHechizos();
		int j = 0;
		for(int i = 0 ; i<mensaje.length ; i++)
		{
			j++;
			System.out.println(j + ") " + mensaje[i]);
			
		}
	}

	public static void mostrarHechizosxPuntaje(Sistema sistema)
	{
		List<Double> puntajes = sistema.entregarPuntuacionHechizos();
		String[] mensaje = sistema.entregarNombreHechizo();
		for(int i = 0 ; i<mensaje.length ; i++)
		{
			System.out.println(mensaje[i] + " " + "Puntaje:" + puntajes.get(i) );
			
		}
	}

	public static void mostrarTop10(Sistema sistema)
	{
		List<Double> puntajes = sistema.entregarPuntuacionHechizos();
		String[] mensaje = sistema.entregarNombreHechizo();
		List<Integer> indices = sistema.buscarTop10Hechizos();
		int i = 0;
		System.out.println("Los mejores Hechizos son:");
		for(Integer indice : indices)
		{
			i++;
			System.out.println("[" + i + "] " +mensaje[indice] + " " + puntajes.get(indice) );
		}
	}

	public static void mostrarMago(Sistema sistema)
	{
		String[] mensaje = sistema.entregarMago();
		int j = 0;
		for(int i = 0 ; i<mensaje.length ; i++)
		{
			j++;
			System.out.println(j + ") " + mensaje[i]);
			
		}
	}

	public static void mostrarMagosxPuntaje(Sistema sistema)
	{
		List<Integer> puntajes = sistema.entregarPuntuacionMagos();
		String[] mensaje = sistema.entregarNombreMagos();
		for(int i = 0 ; i<mensaje.length ; i++)
		{
			System.out.println(mensaje[i] + " " + "Puntaje:" + puntajes.get(i) );
			
		}
	}
	
	public static void mostrarTop3(Sistema sistema)
	{
		List<Integer> puntajes = sistema.entregarPuntuacionMagos();
		String[] mensaje = sistema.entregarNombreMagos();
		List<Integer> indices = sistema.buscarTop3Magos();
		int i = 0;
		System.out.println("Los mejores magos son:");
		for(Integer indice : indices)
		{
			i++;
			System.out.println("[" + i + "] " +mensaje[indice] + " " + puntajes.get(indice) );
		}
	}
	

	public static void añadirMago(Scanner scanner)
	{
		boolean valido = false;
		while(!valido)
		{
			
			System.out.println("ingrese un mago en el siguiente formato:");
			System.out.println("nombreMago;Hechizo1|Hechizo2|...");
			String Mago = scanner.nextLine();
			
			if(verificarMago(Mago))
			{
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("Magos.txt", true))) {
					writer.write(Mago);
					writer.newLine();
					System.out.println("¡Mago agregado con éxito!");
					valido = true;
				} catch (IOException e) {
					System.out.println("Ocurrió un error al escribir en el archivo.");
					e.printStackTrace();
				}
			}
		}

	}
	
	public static boolean verificarMago(String Mago)
	{
		String[] parts = Mago.split(";");
		if(parts.length == 2)
		{
			String[] parts2 = parts[1].split("\\|");
			if(parts2.length>1)
			{
				return true;
			}else
			{
				System.out.println("Ingrese el | correspondiente:");
			}
		}else
		{
			System.out.println("Ingrese el ; correspondiente:");
		}
		
		return false;
	}
	//terminar metodo
	public static void eliminarMago(Scanner scanner, Sistema sistema)
	{
		mostrarMago(sistema);
	    
	    System.out.println("ingresa el numero del mago que desea eliminar");
	    int opcion = Integer.parseInt(scanner.nextLine());
	    
	    File archivo = new File("Magos.txt");
	    List<String> lineas = new ArrayList<>();
	    int contadorLineas = 0;
	    

	    try (Scanner lectorArchivo = new Scanner(archivo)) {
	        while (lectorArchivo.hasNextLine()) {
	            String linea = lectorArchivo.nextLine();
	            contadorLineas++;
	            
	           
	            if (contadorLineas == opcion) {
	               
	            } else {
	                lineas.add(linea);
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Ocurrió un error al leer el archivo.");
	        e.printStackTrace();
	        return; 
	    }
	    
	    
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) {
	        for (String l : lineas) {
	            writer.write(l);
	            writer.newLine();
	        }
	        System.out.println("¡Mago eliminado con éxito!");
	    } catch (IOException e) {
	        System.out.println("Ocurrió un error al escribir en el archivo.");
	        e.printStackTrace();
	    }
	}
	

	public static void modificarMago(Scanner scanner, Sistema sistema)
	{
		mostrarMago(sistema);
		boolean valido = false;
	    while (!valido) 
	    {
	    	System.out.println("ingresa el numero que del mago que desea modificar");
	    	int opcion = Integer.parseInt(scanner.nextLine());
	    	
	        System.out.println("Modifica el mago siguiendo el formato:");
	        System.out.println("nombreMago;Hechizo1|Hechizo2|...");
	        String nuevoMago = scanner.nextLine();
	        
	        if (verificarMago(nuevoMago)) 
	        {
	            File archivo = new File("Magos.txt");
	            List<String> lineas = new ArrayList<>();
	            int contadorLineas = 0;
	            
	          
	            try (Scanner lectorArchivo = new Scanner(archivo)) 
	            {
	                while (lectorArchivo.hasNextLine()) 
	                {
	                    String linea = lectorArchivo.nextLine();
	                    contadorLineas++;
	                    
	                    
	                    if (contadorLineas == opcion) 
	                    {
	                        lineas.add(nuevoMago); 
	                    } else 
	                    {
	                        lineas.add(linea); 
	                    }
	                }
	            } catch (IOException e) 
	            {
	                System.out.println("Ocurrió un error al leer el archivo.");
	                e.printStackTrace();
	                break;
	            }
	            
	            if(opcion > 0 && opcion <= contadorLineas)
	            {
	            	
	            	try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) 
	            	{
	            		for (String l : lineas) 
	            		{
	            			writer.write(l);
	            			writer.newLine();
	            		}
	            		System.out.println("¡Texto modificado con éxito!");
	            		valido = true; 
	            	} catch (IOException e) 
	            	{
	            		System.out.println("Ocurrió un error al escribir en el archivo.");
	            		e.printStackTrace();
	            	}
	            }else
	            {
	            	System.out.println("El número de Mago ingresado no existe en el registro.");
	                System.out.println("El archivo actual solo tiene " + contadorLineas + " hechizos.\n");
	            }
	            
	        }
	    }
	}

	//terminar metodo
	public static void añadirHechizo(Scanner scanner, Sistema sistema)
	{
		boolean valido = false;
		while(!valido)
		{
			
			System.out.println("ingrese un hechizo en el siguiente formato:");
			System.out.println("Formato fuego/tierra");
			System.out.println("Nombre;tipo;Daño;DuracionQuemadura");
			System.out.println("Nombre;tipo;Daño;MejoraDefensa");
			System.out.println("Formato agua/planta");
			System.out.println("NombreHechizo;Tipo;Daño;CantidadHeal,PresionDelAgua");
			System.out.println("NombreHechizo;Tipo;Daño;DuracionStunt,Cantplantas");
			

			String Hechizo = scanner.nextLine();
			
			if(verificarHechizo(Hechizo))
			{
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("Hechizos.txt", true))) {
					writer.write(Hechizo);
					writer.newLine();
					System.out.println("¡Hechizo agregado con éxito!");
					valido = true;
				} catch (IOException e) {
					System.out.println("Ocurrió un error al escribir en el archivo.");
					e.printStackTrace();
				}
			}
		}
	}
	public static boolean verificarHechizo(String hechizo) {
		String[] parts = hechizo.split(";");
		if(parts.length == 4)
		{
			
			String tipo = parts[1];
		try
		{
			Integer.parseInt(parts[2]);
		}catch(Exception e)
		{
			System.out.println("Ingrese numeros en el apartado de daño");
			return false;
		}
		
			
			if(tipo.equalsIgnoreCase("fuego") || tipo.equalsIgnoreCase("tierra"))
			{
				try
				{
					Integer.parseInt(parts[3]);
					return true;
				}catch(Exception e)
				{
					System.out.println("Ingrese numeros en el apartado de DuracionQuemadura/MejoraDefensa");
				}
				return false;
			}else if(tipo.equalsIgnoreCase("agua") || tipo.equalsIgnoreCase("planta"))
			{
				String[] parts2 = parts[3].split(",");
				if(parts2.length == 2)
				{
					try
					{
						 Integer.parseInt(parts2[0]);
						 Integer.parseInt(parts2[1]);
						return true;
					}catch(Exception e)
					{
						System.out.println("Ingrese numeros en el apartado de CantidadHeal/DuracionStunt");
						System.out.println("Ingrese numeros en el apartado de PresiondelAgua/Cantplantas");

					}
					return false;
				}else
				{
					System.out.println("Formato incorrecto para el tipo seleccionado falta ,");
				}
			}else
			{
				System.out.println("Tipo incorrecto");

			}
		}else
		{
			System.out.println("Formato incorrecto faltan ;");
		}
		return false;
	}

	public static void eliminarHechizo(Scanner scanner, Sistema sistema)
	{
	mostrarHechizos(sistema);
	    
	    System.out.println("ingresa el numero del hechizo que desea eliminar");
	    int opcion = Integer.parseInt(scanner.nextLine());
	    
	    File archivo = new File("Hechizos.txt");
	    List<String> lineas = new ArrayList<>();
	    int contadorLineas = 0;
	    

	    try (Scanner lectorArchivo = new Scanner(archivo)) {
	        while (lectorArchivo.hasNextLine()) {
	            String linea = lectorArchivo.nextLine();
	            contadorLineas++;
	            
	           
	            if (contadorLineas == opcion) {
	               
	            } else {
	                lineas.add(linea);
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Ocurrió un error al leer el archivo.");
	        e.printStackTrace();
	        return; 
	    }
	    
	    
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) {
	        for (String l : lineas) {
	            writer.write(l);
	            writer.newLine();
	        }
	        System.out.println("¡Hechizo eliminado con éxito!");
	    } catch (IOException e) {
	        System.out.println("Ocurrió un error al escribir en el archivo.");
	        e.printStackTrace();
	    }
	}
	
	
	public static void modificarHechizo(Scanner scanner, Sistema sistema)
	{
		mostrarHechizos(sistema);
		boolean valido = false;
	    while (!valido) 
	    {
	    	System.out.println("ingresa el numero que del hechizo que desea modificar");
	    	int opcion = Integer.parseInt(scanner.nextLine());
	        System.out.println("Modifica el hechizo siguiendo el formato:");
	        System.out.println("Formato fuego/tierra");
			System.out.println("Nombre;tipo;Daño;DuracionQuemadura");
			System.out.println("Nombre;tipo;Daño;MejoraDefensa");
			System.out.println("Formato agua/planta");
			System.out.println("NombreHechizo;Tipo;Daño;CantidadHeal,PresionDelAgua");
			System.out.println("NombreHechizo;Tipo;Daño;DuracionStunt,Cantplantas");
	        String nuevoHechizo = scanner.nextLine();
	        
	        if (verificarHechizo(nuevoHechizo)) 
	        {
	            File archivo = new File("Hechizos.txt");
	            List<String> lineas = new ArrayList<>();
	            int contadorLineas = 0;
	            
	          
	            try (Scanner lectorArchivo = new Scanner(archivo)) 
	            {
	                while (lectorArchivo.hasNextLine()) 
	                {
	                    String linea = lectorArchivo.nextLine();
	                    contadorLineas++;
	                    
	                    
	                    if (contadorLineas == opcion) 
	                    {
	                        lineas.add(nuevoHechizo); 
	                    } else 
	                    {
	                        lineas.add(linea); 
	                    }
	                }
	            } catch (IOException e) 
	            {
	                System.out.println("Ocurrió un error al leer el archivo.");
	                e.printStackTrace();
	                break;
	            }
	            if(opcion > 0 && opcion <= contadorLineas)
	            {
	            	
	            	
	            	try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) 
	            	{
	            		for (String l : lineas) 
	            		{
	            			writer.write(l);
	            			writer.newLine();
	            		}
	            		System.out.println("¡Texto modificado con éxito!");
	            		valido = true; 
	            	} catch (IOException e) 
	            	{
	            		System.out.println("Ocurrió un error al escribir en el archivo.");
	            		e.printStackTrace();
	            	}
	            }else 
	            {
	                System.out.println("El número de hechizo ingresado no existe en el registro.");
	                System.out.println("El archivo actual solo tiene " + contadorLineas + " hechizos.\n");
	            }
         	}
	    }
	}
	
	
	public static void submenu(Scanner scanner, Sistema sistema)
	{
		boolean valido = false;
		while (!valido)
		{
			
			System.out.println("Bienvenido al sistema de Magitos UCN");
			System.out.println("Seleccione que menu desea ingresar");
			System.out.println("1) Menu Administrado");
			System.out.println("2) Menu Analista");
			System.out.println("3) Salir");
			
			try 
			{
				int opcion = Integer.parseInt(scanner.nextLine());
				if (opcion<1 || opcion>3)
				{
					throw new Exception();
				}
				switch(opcion)
				{
				case 1:
					menuAdministrador(scanner, sistema);
					break;
				case 2:
					menuAnalista(scanner, sistema);
					break;
				case 3: 
					valido = true;
					break;
				}
			}catch(Exception e)
			{
				System.out.println("Ingrese una opcion valida");
			}
			
			
		}
		
	}
	
	public static void menuAdministrador(Scanner scanner, Sistema sistema)
	{
		boolean valido = false;
		while (!valido)
		{
			System.out.println("Que desea hacer");
			System.out.println("1) Agregar Mago");
			System.out.println("2) Modificar Mago");
			System.out.println("3) Eliminar Mago");
			System.out.println("4) Agregar Hechizo");
			System.out.println("5) Modificar Hechizo");
			System.out.println("6) Eliminar  Hechizo");
			System.out.println("7) Regresar");
			
			try 
			{
				int opcion = Integer.parseInt(scanner.nextLine());
				if (opcion<1 || opcion>7)
				{
					throw new Exception();
				}
				switch(opcion)
				{
				case 1:
					añadirMago(scanner);
					break;
				case 2:
					modificarMago(scanner, sistema);
					break;
				case 3: 
					eliminarMago(scanner, sistema);
					break;
				case 4:
					añadirHechizo(scanner, sistema);
					break;
				case 5:
					modificarHechizo(scanner, sistema);
					break;
				case 6:
					eliminarHechizo(scanner, sistema);
					break;
				case 7:
					valido = true;
					break;
				}
			}catch(Exception e)
			{
				System.out.println("Ingrese una opcion valida");
			}
			
		}

	}
	
	public static void menuAnalista(Scanner scanner, Sistema sistema)
	{
		boolean valido = false;
		while (!valido)
		{
			System.out.println("Que desea hacer");
			System.out.println("1) Top 10 Mejores Hechizos");
			System.out.println("2) Top 3 Mejores Magos");
			System.out.println("3) Mostrar todos los Hechizos");
			System.out.println("4) Mostrar todos los Magos");
			System.out.println("5) Mostrar todos los Hechizos junto a su puntuacion");
			System.out.println("6) Mostrar todos los magos junto a su puntuacion");
			System.out.println("7) Regresar");
			
			try 
			{
				int opcion = Integer.parseInt(scanner.nextLine());
				if (opcion<1 || opcion>7)
				{
					throw new Exception();
				}
				switch(opcion)
				{
				case 1:
					mostrarTop10(sistema);
					break;
				case 2:
					mostrarTop3(sistema);
					break;
				case 3: 
					mostrarHechizos(sistema);
					break;
				case 4:
					mostrarMago(sistema);
					break;
				case 5:
					mostrarHechizosxPuntaje(sistema);
					break;
				case 6:
					mostrarMagosxPuntaje(sistema);
					break;
				case 7:
					valido = true;
					break;
				}
			}catch(Exception e)
			{
				System.out.println("Ingrese una opcion valida");
			}
			
		}

	}
	
}


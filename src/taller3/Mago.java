package taller3;

import java.util.ArrayList;
import java.util.List;

public class Mago 
{
	private String nombre;
	private List<Hechizos> hechizos = new ArrayList<>();
	
	
	public Mago(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public void agregarHechizo(Hechizos hechizo)
	{
		hechizos.add(hechizo);
	}

	public String getNombre() {
		return nombre;
	}

	public List<Hechizos> getHechizos() {
		return hechizos;
	}
	
	public String mostrarHechizos()
	{
		String print = "";
		for(Hechizos hechizo : hechizos)
		{
			print += hechizo.getNombre() +"|";
		}
		return print;
	}
	
}

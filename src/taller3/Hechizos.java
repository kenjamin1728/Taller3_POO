package taller3;

public abstract class Hechizos 
{
	protected String nombre;
	protected int daño;
	
	
	protected Hechizos(String nombre, int daño) 
	{
		this.nombre = nombre;
		this.daño = daño;
	}

	
	
	
	public String getNombre() {
		return nombre;
	}




	public int getDaño() {
		return daño;
	}




	public abstract void acceptar(HechizosVisitor visitor);


	public abstract String mostrarHechizo();
	
		
	
}

package taller3;

public class Fuego extends Hechizos 
{
	private int duracionQuemadura;

	public Fuego(String nombre, int daño, int duracionQuemadura) 
	{
		super(nombre, daño);
		this.duracionQuemadura=duracionQuemadura;
	}

	@Override
	public void acceptar(HechizosVisitor visitor) 
	{
		visitor.visitar(this);
	}

	public int getDuracionQuemadura() 
	{
		return duracionQuemadura;
	}

	@Override
	public String mostrarHechizo() 
	{
		return getNombre() + "|" + getDaño() + "|" + getDuracionQuemadura() + "|";
	}

}

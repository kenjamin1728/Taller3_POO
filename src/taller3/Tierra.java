package taller3;

public class Tierra extends Hechizos 
{
	private int mejoraDefensa;
	
	public Tierra(String nombre, int daño, int mejoraDefensa) 
	{
		super(nombre, daño);
		this.mejoraDefensa = mejoraDefensa;
	}

	@Override
	public void acceptar(HechizosVisitor visitor) 
	{
		visitor.visitar(this);
	}

	
	
	public int getMejoraDefensa() {
		return mejoraDefensa;
	}

	@Override
	public String mostrarHechizo() 
	{
		return getNombre() + "|" + getDaño() + "|" + getMejoraDefensa() + "|";
	}

}

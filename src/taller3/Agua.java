package taller3;

public class Agua extends Hechizos 
{
	private int cantidadHeal;
	private double presionAgua;
	
	public Agua(String nombre, int daño, int cantidadHeal, double presionAgua) 
	{
		super(nombre, daño);
		this.cantidadHeal=cantidadHeal;
		this.presionAgua = presionAgua;
	}

	@Override
	public void acceptar(HechizosVisitor visitor) 
	{
		visitor.visitar(this);
	}

	
	
	public int getCantidadHeal() {
		return cantidadHeal;
	}

	public double getPresionAgua() {
		return presionAgua;
	}

	@Override
	public String mostrarHechizo() {
		
		return getNombre() + "|" + getDaño() + "|" + getCantidadHeal() + "|" + getPresionAgua() + "|";
	}

}

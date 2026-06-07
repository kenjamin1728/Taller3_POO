package taller3;

public class Planta extends Hechizos 
{
	private int duracionStun;
	private double cantPlantas;
	
	public Planta(String nombre, int daño, int duracionStun, double cantPlantas) 
	{
		super(nombre, daño);
		this.duracionStun = duracionStun;
		this.cantPlantas = cantPlantas;
	}

	@Override
	public void acceptar(HechizosVisitor visitor) 
	{
		visitor.visitar(this);
	}

	
	
	public int getDuracionStun() {
		return duracionStun;
	}

	public double getCantPlantas() {
		return cantPlantas;
	}

	@Override
	public String mostrarHechizo() 
	{
		return getNombre() + "|" + getDaño() + "|" + getDuracionStun() + "|" + getCantPlantas() + "|";
	}

}

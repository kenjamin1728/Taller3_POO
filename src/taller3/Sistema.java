package taller3;
//benjamin Mundaca 221712102 ICCI


import java.util.List;

public interface Sistema 
{
	public void crearHechizoFuego(String nombre,int daño,int duracionQuemadura);
	public void crearHechizoAgua(String nombre, int daño , int cantidadHeal, int presionAgua);
	public void crearHechizoTierra(String nombre, int daño , int mejoraDefensa);
	public void crearHechizoPlanta(String nombre, int daño, int duracionStun, int cantPlantas);
	public void agregarHechizo(Hechizos hechizo);  
	public String[] entregarHechizos();
	public List<Double> entregarPuntuacionHechizos();
	public String[] entregarNombreHechizo();
	public List<Integer> buscarTop10Hechizos();
	public void crearMago(String nombre, String[] hechizo);
	public String[] entregarMago();
	public List<Integer> entregarPuntuacionMagos();
	public String[] entregarNombreMagos();
	public List<Integer> buscarTop3Magos();
}

package taller3;
//benjamin Mundaca 221712102 ICCI


import java.util.ArrayList;
import java.util.List;

public class HechizosVisitor implements Visitor 
{
	private List<Double> puntajes = new ArrayList<>(); 
	private double puntajeTemp;
	@Override
	public void visitar(Fuego f) 
	{
		double puntaje = f.getDaño()*f.getDuracionQuemadura();
		puntajes.add(puntaje);
		puntajeTemp = puntaje;
	}

	@Override
	public void visitar(Agua a) 
	{
		double puntaje = (a.getDaño() + a.getCantidadHeal() + a.getPresionAgua())*2;
		puntajes.add(puntaje);
		puntajeTemp = puntaje;
	}

	@Override
	public void visitar(Tierra t) 
	{
		double puntaje = (t.getDaño()* t.getMejoraDefensa())/2;
		puntajes.add(puntaje);
		puntajeTemp = puntaje;
	}

	@Override
	public void visitar(Planta p) 
	{
		double puntaje = p.getDaño() + (p.getDuracionStun() * p.getCantPlantas());
		puntajes.add(puntaje);
		puntajeTemp = puntaje;
	}

	public List<Double> getPuntajes() {
		return puntajes;
	}

	public double getPuntajeTemp() {
		return puntajeTemp;
	}
	
	

	
	

}

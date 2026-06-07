package taller3;

import java.util.ArrayList;
import java.util.List;

public class Sistemaimpl implements Sistema
{
	private List<Hechizos> hechizos = new ArrayList<>();
	private List<Mago> magos = new ArrayList<>();

	@Override
	public void crearHechizoFuego(String nombre, int daño, int duracionQuemadura) 
	{
		Fuego f = new Fuego(nombre, daño, duracionQuemadura);
		this.agregarHechizo(f);
		
	}

	@Override
	public void crearHechizoAgua(String nombre, int daño, int cantidadHeal, int presionAgua) 
	{
		Agua a = new Agua(nombre, daño, cantidadHeal, presionAgua);
		this.agregarHechizo(a);
		
	}

	@Override
	public void crearHechizoTierra(String nombre, int daño, int mejoraDefensa) 
	{
		Tierra t = new Tierra(nombre, daño, mejoraDefensa);
		this.agregarHechizo(t);
		
	}

	@Override
	public void crearHechizoPlanta(String nombre, int daño, int duracionStun, int cantPlantas) 
	{
		Planta p = new Planta(nombre, daño, duracionStun, cantPlantas);
		this.agregarHechizo(p);
		
	}

	@Override
	public void agregarHechizo(Hechizos hechizo) 
	{
		hechizos.add(hechizo);
		
	}

	@Override
	public String[] entregarHechizos()
	{
		String[] print = new String[hechizos.size()];
		int i = 0;
		for(Hechizos hechizo : hechizos)
		{
			print[i]=hechizo.mostrarHechizo();
			i++;
		}
		
		return print;
		
	}

	@Override
	public List<Double> entregarPuntuacionHechizos() 
	{
		HechizosVisitor visitor = new HechizosVisitor();
		for(Hechizos H : hechizos)
		{
			H.acceptar(visitor);
		}
		return visitor.getPuntajes();
	}

	@Override
	public String[] entregarNombreHechizo() 
	{
		String[] print = new String[hechizos.size()];
		int i = 0;
		for(Hechizos hechizo : hechizos)
		{
			print[i]=hechizo.getNombre();
			i++;
		}
		return print;
	}

	@Override
	public List<Integer> buscarTop10Hechizos() 
	{
		List<Double> puntuaciones = entregarPuntuacionHechizos();
		List<Integer> Mayores = new ArrayList<>();
		for(int i = 0; i<10 ; i++)
		{
			int indice = 0;
			Double mayor = -999999.9;
			
			for(int j = 0 ; j<puntuaciones.size() ; j++)
			{
				if (puntuaciones.get(j) > mayor)
				{
					indice = j;
					mayor = puntuaciones.get(j);
				}
			}
			Mayores.add(indice);
			puntuaciones.set(indice, -1.0);
		}
		
		return Mayores;
	}

	@Override
	public void crearMago(String nombre,String[] hechizos) 
	{
		Mago mago = new Mago(nombre);
		for(int i = 0; i<hechizos.length ; i++)
		{
			
			for(int j = 0; j<this.hechizos.size() ; j++)
			{
				if(this.hechizos.get(j).getNombre().equalsIgnoreCase(hechizos[i]))
				{
					mago.agregarHechizo(this.hechizos.get(j));
				}
			}
		}
		magos.add(mago);
		
	}

	@Override
	public String[] entregarMago() 
	{
		String[] print = new String[magos.size()];
		int i = 0;
		for(Mago mago : magos)
		{
			print[i]=mago.getNombre() + "|" + mago.mostrarHechizos();
			i++;
		}
		
		return print;
		
		
	}

	@Override
	public List<Integer> entregarPuntuacionMagos() 
	{
		HechizosVisitor visitor = new HechizosVisitor();
		List<Integer> puntuacionesxMago = new ArrayList<>();
		for(int i = 0 ; i<magos.size() ; i++)
		{
			int puntuacion = 0;
			Mago actual = magos.get(i);
			for(int j = 0; j<actual.getHechizos().size() ; j++)
			{
				Hechizos hechizoActual = actual.getHechizos().get(j);
				hechizoActual.acceptar(visitor);
				puntuacion += visitor.getPuntajeTemp();
			}
			puntuacionesxMago.add(puntuacion);
		}
		return puntuacionesxMago;
	}

	@Override
	public String[] entregarNombreMagos() 
	{
		String[] print = new String[magos.size()];
		int i = 0;
		for(Mago mago : magos)
		{
			print[i]=mago.getNombre();
			i++;
		}
		
		return print;
		
	}

	@Override
	public List<Integer> buscarTop3Magos() 
	{
		List<Integer> puntuaciones = entregarPuntuacionMagos();
		List<Integer> Mayores = new ArrayList<>();
		for(int i = 0; i<3 ; i++)
		{
			int indice = 0;
			int mayor = -9999999;
			
			for(int j = 0 ; j<puntuaciones.size() ; j++)
			{
				if (puntuaciones.get(j) > mayor)
				{
					indice = j;
					mayor = puntuaciones.get(j);
				}
			}
			Mayores.add(indice);
			puntuaciones.set(indice, -1);
		}
		
		return Mayores;
	}
		
		
	



	

}

package taller3;

public interface Visitor 
{
public void visitar(Fuego f);
public void visitar(Agua a);
public void visitar(Tierra t);
public void visitar(Planta p);

}

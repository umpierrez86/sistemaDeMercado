package Dominio;

import java.io.Serializable;

/**
 *
 * @author Luciano Umpierrez
 */
public class Dueño extends Persona implements Serializable{
    //private Persona persona;
    private int añosExp;

    public Dueño(int añosExp, String nombre, int edad) {
        super(nombre,edad);
        this.añosExp = añosExp;
    }
    
    public Dueño(String nombre){
        super(nombre);
    }

    public int getAñosExp() {
        return añosExp;
    }

    public void setAñosExp(int unosAñosExp) {
        this.añosExp = unosAñosExp;
    }
    
    @Override
    public boolean equals(Object obj){
        Dueño due = (Dueño)obj;
        return due.getNombre().equalsIgnoreCase(this.getNombre());
    }
}

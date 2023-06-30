package Dominio;

import java.io.Serializable;
import java.util.*;
import java.util.Observable;

/**
 *
 * @author Luciano Umpierrez
 */
public class RegistrarDueño extends Observable implements Serializable {

    private ArrayList<Dueño> dueños;

    public ArrayList<Dueño> getDueños() {
        return dueños;
    }

    public void setDueños(ArrayList<Dueño> dueños) {
        this.dueños = dueños;
    }
    
    public RegistrarDueño(){
        dueños = new ArrayList<>();
    }

    public boolean registrarDueños(int añosExp, String nombre,int edad) {
        boolean ingresoExitoso = false;
        Dueño dueño = new Dueño(añosExp,nombre,edad);
        ArrayList<Dueño> dueños = this.getDueños();
        if(!existeDueño(nombre)){
            dueños.add(dueño);
            this.setDueños(dueños);
            ingresoExitoso = true;
        }
        setChanged();
        notifyObservers(dueño);
        return ingresoExitoso;
    }

    public boolean existeDueño(String nombre) {
        boolean repetido = false;
        Dueño dueño = new Dueño(nombre);
        ArrayList<Dueño> dueños = getDueños();
        
        if(dueños != null && dueños.contains(dueño)){
            repetido = true;
        }
        
        return repetido;
    }

    public Dueño retornarDueño(String nombre) {
        boolean encontrado = false;
        int pos = 0;
        for (int i = 0; i < dueños.size() && !encontrado; i++) {
            if (dueños.get(i).getNombre().equals(nombre)) {
                encontrado = true;
                pos = i;
            }
        }
        return dueños.get(pos);
    }
}

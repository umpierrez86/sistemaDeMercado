/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.io.Serializable;


/**
 *
 * @author Luciano Umpierrez
 */
public class Puesto implements Serializable {
    private String ID;
    private Dueño dueño;
    private String ubicacion;
    private int cantEmpleados;

    public Puesto(String ID, Dueño dueño, String ubicacion, int cantEmpleados) {
        this.ID = ID;
        this.dueño = dueño;
        this.ubicacion = ubicacion;
        this.cantEmpleados = cantEmpleados;
    }

    public String getID() {
        return ID;
    }
    
    public void setID(String unID) {
        this.ID = unID;
    }

    public Dueño getDueño() {
        return dueño;
    }

    public void setDueño(Dueño unDueño) {
        this.dueño = unDueño;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String unaUbicacion) {
        this.ubicacion = unaUbicacion;
    }

    public int getCantEmpleados() {
        return cantEmpleados;
    }

    public void setCantEmpleados(int unaCantEmpleados) {
        this.cantEmpleados = unaCantEmpleados;
    }
    
    @Override
    public String toString(){
        return this.getID();
    }
    
    @Override
    public boolean equals(Object obj){
        Puesto puesto = (Puesto)obj;
        return puesto.getID().equalsIgnoreCase(this.getID());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.io.Serializable;
import java.util.*;
import java.util.Observable;


/**
 *
 * @author Luciano Umpierrez
 */
public class RegistroPuesto extends Observable implements Serializable {

    private HashMap<String, Puesto> listaPuestos;

    public HashMap<String, Puesto> getListaPuestos() {
        return listaPuestos;
    }

    public RegistroPuesto() {
        listaPuestos = new HashMap<>();
    }

    public boolean existeID(String id) {
        return this.getListaPuestos().containsKey(id);
    }

    public void setListaPuestos(HashMap<String, Puesto> listaPuestos) {
        this.listaPuestos = listaPuestos;
    }

    public void RegistrarPuesto(String identificacion, Dueño dueño, String ubi, int cantEmpleados) {
        Puesto puesto = new Puesto(identificacion, dueño, ubi, cantEmpleados);
        listaPuestos.put(identificacion, puesto);
        setChanged();
        notifyObservers(puesto);
    }

}

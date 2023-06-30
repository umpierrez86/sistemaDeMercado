/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Luciano Umpierrez
 */
public class Mayorista implements Serializable {

    private String rut;
    private String direccion;
    private String nombre;
    private ArrayList<Producto> listaProductos;

    public ArrayList<Producto> getListaProductos() {
        return this.listaProductos;
    }

    private void setListaProductos(ArrayList<Producto> unaListaProductos) {
        this.listaProductos = unaListaProductos;
    }

    public void eliminarProducto(String nombre) {
        Iterator<Producto> iterator = listaProductos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getNombre().equals(nombre)) {
                iterator.remove();
                return;
            }
        }
    }

    public String getRut() {
        return rut;
    }

    private void setRut(String unRut) {
        this.rut = unRut;
    }

    public String getDireccion() {
        return direccion;
    }

    private void setDireccion(String unaDireccion) {
        this.direccion = unaDireccion;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String unNombre) {
        this.nombre = unNombre;
    }

    public Mayorista(String unRut, String unNombre, String unaDireccion, ArrayList<Producto> productosMayorista) {
        this.setNombre(unNombre);
        this.setDireccion(unaDireccion);
        this.setRut(unRut);
        this.setListaProductos(productosMayorista);
    }
    @Override
    public String toString() {
        return this.getRut();
    }
}

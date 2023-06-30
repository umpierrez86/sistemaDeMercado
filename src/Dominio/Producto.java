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
public class Producto implements Comparable, Serializable {
    private String nombre;
    private String descripcion;
    private String tipo;
    private String formaVenta;
    private String foto;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String unaDescripcion) {
        this.descripcion = unaDescripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String unNombre) {
        this.nombre = unNombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String unTipo) {
        this.tipo = unTipo;
    }

    public String getFormaVenta() {
        return formaVenta;
    }

    public void setFormaVenta(String unaFormaVenta) {
        this.formaVenta = unaFormaVenta;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String unaFoto) {
        this.foto = unaFoto;
    }

    public Producto(String nombre,String descripcion, String tipo, String formaVenta, String foto) {
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setTipo(tipo);
        this.setFormaVenta(formaVenta);
        this.setFoto(foto);
    }
    
    @Override
    public String toString(){
        return this.getNombre();
    }
    
    @Override
    public int compareTo(Object unProducto){
        Producto otroProducto = (Producto) unProducto;
        return this.getNombre().compareTo(otroProducto.getNombre());
    }
    
    @Override
    public boolean equals(Object obj){
        Producto prod = (Producto) obj;
        return this.getNombre().equals(prod.getNombre());
    }
}

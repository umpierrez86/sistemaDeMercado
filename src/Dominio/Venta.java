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
public class Venta extends Operacion implements Serializable {
    private int cantidad;
    private int precio;
    //private static int numeroVenta;
    private int numeroEstaVenta;
    private Producto producto;
    private Puesto puesto;
    
    public Puesto getPuesto(){
        return this.puesto;
    }
    
    private void setPuesto(Puesto unPuesto){
        this.puesto = unPuesto;
    }

    public int getNumeroEstaVenta(){
        return this.numeroEstaVenta;
    }
    
    private void setNumeroEstaVenta(int numero){
        this.numeroEstaVenta = numero;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    private void setCantidad(int unaCantidad) {
        this.cantidad = unaCantidad;
    }

    public int getPrecio() {
        return precio;
    }

    private void setPrecio(int unPrecio) {
        this.precio = unPrecio;
    }

    public Producto getProducto() {
        return producto;
    }

    private void setProducto(Producto unProducto) {
        this.producto = unProducto;
    }
    
    public Venta(int unaCantidad, int unPrecio, Producto unProducto, Puesto unPuesto){
        //this.numeroVenta++;
        int numeroVenta = Operacion.getNumeroMovimiento();
        this.setCantidad(unaCantidad);
        this.setPrecio(unPrecio);
        this.setProducto(unProducto); 
        this.setPuesto(unPuesto);
        this.setNumeroEstaVenta(numeroVenta);
    }
    
}

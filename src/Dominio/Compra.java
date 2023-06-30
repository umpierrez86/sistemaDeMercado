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
public class Compra extends Operacion implements Serializable{
    private int cantidad;
    private int precio;
    private int cantStock;

    private int numeroEstaCompra;
    private Producto producto;
    private Mayorista mayorista;
    private Puesto puesto;
    
    public int getCantStock() {
        return cantStock;
    }

    public void setCantStock(int cantStock) {
        this.cantStock = cantStock;
    }

    public Compra(int unaCantidad, int unPrecio,int unaCantidadStock ,Producto unProducto,
            Mayorista unMayorista, Puesto unPuesto) {

        int numeroMovimiento = Operacion.getNumeroMovimiento();
        this.setNumeroEstaCompra(numeroMovimiento);
        this.setCantidad(unaCantidad);
        this.setPrecio(unPrecio);
        this.setCantStock(unaCantidadStock);
        this.setProducto(unProducto);
        this.setMayorista(unMayorista);
        this.setPuesto(unPuesto);
    }

    public int getNumeroEstaCompra() {
        return this.numeroEstaCompra;
    }

    private void setNumeroEstaCompra(int numero) {
        this.numeroEstaCompra = numero;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int unaCantidad) {
        this.cantidad = unaCantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int unPrecio) {
        this.precio = unPrecio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto unProducto) {
        this.producto = unProducto;
    }

    public Mayorista getMayorista() {
        return mayorista;
    }

    public void setMayorista(Mayorista unMayorista) {
        this.mayorista = unMayorista;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto unPuesto) {
        this.puesto = unPuesto;
    }

    @Override
    public String toString() {
        return "" + this.getCantidad();
    }

}

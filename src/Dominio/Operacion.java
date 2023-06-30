/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.util.HashMap;

/**
 *
 * @author Luciano Umpierrez
 */
public class Operacion implements Comparable<Operacion> {

    private static int numeroMovimiento;
    private int numero;

    public int getNumero() {
        return numero;
    }

    private void setNumero(int unNumero) {
        numero = unNumero;
    }

    public static int getNumeroMovimiento() {
        return numeroMovimiento;
    }

    public Operacion() {
        numeroMovimiento++;
        setNumero(numeroMovimiento);
    }

    /*
        Función que devuelve un mapa con el total vendido o comprado para
        cada producto
    */
    public HashMap<Producto, Integer> ingresarAMapa(Producto unProducto, Operacion unaOperacion,
            HashMap<Producto, Integer> mapa, String tipo) {

        HashMap<Producto, Integer> totalVendidosOComprados = mapa;
        Integer total = 0;
        // Si el producto ha sido registrado como una compra o venta
        // se sumara esta compra o venta al total ya existente
        if (totalVendidosOComprados.containsKey(unProducto)) {
            total = totalVendidosOComprados.get(unProducto);
        }
        if (tipo.equals("venta")) {
            Venta unaVenta = (Venta) unaOperacion;
            total += unaVenta.getCantidad() * unaVenta.getPrecio();
        } else {
            Compra unaCompra = (Compra) unaOperacion;
            total += unaCompra.getCantStock() * unaCompra.getPrecio();
        }

        totalVendidosOComprados.put(unProducto, total);
        return totalVendidosOComprados;
    }

    @Override
    public int compareTo(Operacion operacion) {
        return this.getNumero() - operacion.getNumero();
    }
}

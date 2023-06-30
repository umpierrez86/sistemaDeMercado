/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observer;
import java.util.Observable;


/**
 *
 * @author Luciano Umpierrez
 */
public class RegistroVenta extends Observable implements Serializable {
    private HashMap<Integer, Venta> ventas;
    private int precioMinimo;
    private int precioMaximo;
    private int numeroVentaMaximo;
    private int numeroVentaMinimo;

    public int getNumeroVentaMinimo() {
        return numeroVentaMinimo;
    }

    public void setNumeroVentaMinimo(int unNumeroVentaMinimo) {
        this.numeroVentaMinimo = unNumeroVentaMinimo;
    }

    public int getNumeroVentaMaximo() {
        return numeroVentaMaximo;
    }

    public void setNumeroVentaMaximo(int unNumeroVentaMaximo) {
        this.numeroVentaMaximo = unNumeroVentaMaximo;
    }

    public int getPrecioMinimo() {
        return this.precioMinimo;
    }

    private void setPrecioMinimo(int unPrecio) {
        this.precioMinimo = unPrecio;
    }

    public int getPrecioMaximo() {
        return this.precioMaximo;
    }

    private void setPrecioMaximo(int unPrecio) {
        this.precioMaximo = unPrecio;
    }

    public HashMap<Integer, Venta> getVentas() {
        return ventas;
    }

    public void setVentas(HashMap<Integer, Venta> ventas) {
        this.ventas = ventas;
    }

    public RegistroVenta() {
        this.ventas = new HashMap<>();
    }

    public int registrarVenta(int unaCantidad, int unPrecio, Producto unProducto,
            Puesto unPuesto) {

        Venta venta = new Venta(unaCantidad, unPrecio, unProducto, unPuesto);
        int numero = venta.getNumeroEstaVenta();
        HashMap<Integer, Venta> ventasNuevas = this.getVentas();
        ventasNuevas.put(numero, venta);
        this.setVentas(ventas);
        if (this.precioMaximo == 0 || this.precioMaximo < unPrecio) {
            this.precioMaximo = unPrecio;
        }
        if (this.precioMinimo == 0 || this.precioMinimo > unPrecio) {
            this.precioMinimo = unPrecio;
        }
        this.setNumeroVentaMaximo(numero);
        if (this.getNumeroVentaMinimo() == 0) {
            this.setNumeroVentaMinimo(numero);
        }
        this.setChanged();
        this.notifyObservers();
        return numero;
    }

    // Cantidad monetaria total vendida de cada producto
    public HashMap<Producto, Integer> totalVendidoProducto() {
        HashMap<Producto, Integer> totalVendidos = new HashMap<>();
        if (!this.getVentas().isEmpty()) {
            HashMap<Integer, Venta> lasVentas = this.getVentas();
            Iterator<Map.Entry<Integer, Venta>> it = lasVentas.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Venta> entrada = it.next();
                Venta venta = entrada.getValue();
                Producto producto = venta.getProducto();
                String tipo = "venta";
                totalVendidos = venta.ingresarAMapa(producto, venta,
                        totalVendidos, tipo);

            }

        }
        return totalVendidos;
    }

    // Cantidad vendida en kilo e unidad
    public int[] cantidadVendida(Producto productoSeleccionado) {
        int[] cantidadVendida = new int[4];
        HashMap<Integer, Venta> mapaVentas = this.getVentas();
        if (!mapaVentas.isEmpty()) {
            mapaVentas.forEach((clave, venta) -> {
                Producto producto = venta.getProducto();
                if (productoSeleccionado.equals(producto)) {
                    if (producto.getFormaVenta().equals("Kilogramo")) {
                        cantidadVendida[0] += venta.getCantidad();
                    } else {
                        cantidadVendida[1] += venta.getCantidad();
                    }
                    int precioMaximo = cantidadVendida[2];
                    int precioMinimo = cantidadVendida[3];
                    if (venta.getPrecio() < precioMinimo || precioMinimo == 0) {
                        cantidadVendida[2] = venta.getPrecio();
                    }
                    if (venta.getPrecio() > precioMaximo || precioMaximo == 0) {
                        cantidadVendida[3] = venta.getPrecio();
                    }
                }
            });
        }
        return cantidadVendida;
    }

    // Devuelve puestos con los precios minimo y maximo de un producto
    public ArrayList<Puesto>[] puestosPrecioMinimoyMaximo(int precioMinimo,
            int precioMaximo, Producto productoSeleccionado) {
        ArrayList<Puesto> puestosConPrecioMinimo = new ArrayList<>();
        ArrayList<Puesto> puestosConPrecioMaximo = new ArrayList<>();
        ArrayList<Puesto>[] arrayPuestos = new ArrayList[2];
        HashMap<Integer, Venta> mapaVentas = this.getVentas();
        if (!mapaVentas.isEmpty()) {
            mapaVentas.forEach((clave, venta) -> {
                Producto producto = venta.getProducto();
                if (productoSeleccionado.equals(producto)) {
                    Puesto puesto = venta.getPuesto();
                    int precio = venta.getPrecio();
                    if (precio == precioMinimo
                            && !puestosConPrecioMaximo.contains(puesto)) {
                        puestosConPrecioMaximo.add(puesto);
                    }
                    if (precio == precioMaximo
                            && !puestosConPrecioMinimo.contains(puesto)) {
                        puestosConPrecioMinimo.add(puesto);
                    }
                }
            });
        }
        arrayPuestos[0] = puestosConPrecioMaximo;
        arrayPuestos[1] = puestosConPrecioMinimo;

        return arrayPuestos;
    }

    public ArrayList<Puesto> retornarPuestos() {
        ArrayList<Venta> ventas = new ArrayList<>(this.getVentas().values());
        ArrayList<Puesto> puestos = new ArrayList<>();
        Iterator<Venta> it = ventas.iterator();
        while (it.hasNext()) {
            Venta venta = it.next();
            puestos.add(venta.getPuesto());
        }
        return puestos;
    }

    public ArrayList<Venta> retornarVentas(ArrayList<Puesto> puestos) {
        ArrayList<Venta> ventasARetornar = new ArrayList<>();
        ArrayList<Venta> ventas = new ArrayList<>(this.getVentas().values());
        Iterator<Venta> it = ventas.iterator();
        while (it.hasNext()) {
            Venta venta = it.next();
            if (puestos.contains(venta.getPuesto())) {
                ventasARetornar.add(venta);
            }
        }
        return ventasARetornar;
    }

}

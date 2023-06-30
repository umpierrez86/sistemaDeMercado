package Dominio;

import AreaConsultas.GenerarArchivo;
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
public class RegistroCompra extends Observable implements Serializable {

    HashMap<Puesto, ArrayList<Compra>> compras;
    private int numeroCompraMaximo;
    private ArrayList<Observer> observadores;

    public void setNumeroCompraMaximo(int unNumeroCompraMaximo) {
        this.numeroCompraMaximo = unNumeroCompraMaximo;
    }

    public int getNumeroCompraMaximo() {
        return numeroCompraMaximo;
    }

    public RegistroCompra() {
        this.compras = new HashMap<>();
        this.observadores = new ArrayList<>();
    }
    /*
        Encuentra la compra relacionada a la venta que se acaba de hacer y se 
        reduce el stock.
     */
    public void reducirCantidadCompra(int cantidadAReducir, Producto producto, Puesto puesto) {
        HashMap<Puesto, ArrayList<Compra>> compras = getCompras();
        ArrayList<Compra> listaCompras = compras.get(puesto);
        Iterator<Compra> iterator = listaCompras.iterator();
        boolean encontrado = false;
        while (iterator.hasNext() && !encontrado) {
            Compra compra = iterator.next();
            if (compra.getProducto().equals(producto)) {
                int cantidadActual = compra.getCantidad() - cantidadAReducir;
                compra.setCantidad(cantidadActual);
                encontrado = true;
            }
        }
    }

    public HashMap<Puesto, ArrayList<Compra>> getCompras() {
        return this.compras;
    }

    private void setCompras(HashMap<Puesto, ArrayList<Compra>> unasCompras) {
        this.compras = unasCompras;
    }

    // Se registra una compra asociada al puesto que la hizo
    public void registrarUnaCompra(Puesto unPuesto, Compra unaCompra) {
        HashMap<Puesto, ArrayList<Compra>> compras = getCompras();
        if (!compras.isEmpty() && compras.containsKey(unPuesto)) {
            compras.get(unPuesto).add(unaCompra);
        } else {
            ArrayList<Compra> nuevasCompras = new ArrayList<>();
            nuevasCompras.add(unaCompra);
            compras.put(unPuesto, nuevasCompras);
        }
        this.setNumeroCompraMaximo(Compra.getNumeroMovimiento());
        this.setCompras(compras);
        setChanged();
        notifyObservers();
    }

    /*
        Devuelve un hasmap con el total que se compro de cada producto 
        que se encuentra como clave
     */
    public HashMap<Producto, Integer> totalCompradoProducto() {
        HashMap<Producto, Integer> totalComprado = new HashMap<>();
        if (!this.getCompras().isEmpty()) {
            HashMap<Puesto, ArrayList<Compra>> lasCompras = this.getCompras();
            Iterator<Map.Entry<Puesto, ArrayList<Compra>>> it = lasCompras.entrySet().iterator();
            // Itero sobre los diferentes ArrayList de compra
            while (it.hasNext()) {
                Map.Entry<Puesto, ArrayList<Compra>> entrada = it.next();
                ArrayList<Compra> listaCompras = entrada.getValue();
                Iterator<Compra> iterator = listaCompras.iterator();
                // Itero sobre un ArrayList de compra en especifico
                while (iterator.hasNext()) {
                    Compra compra = iterator.next();
                    Producto producto = compra.getProducto();
                    String tipo = "compra";
                    totalComprado = compra.ingresarAMapa(producto, compra, totalComprado,
                            tipo);
                }
            }

        }
        return totalComprado;
    }

    // Devuelve la cantidad comprada en kilograma e unidad para cada producto
    public int[] cantidadComprada(Producto productoSeleccionado) {
        int[] cantidadComprada = new int[2];
        HashMap<Puesto, ArrayList<Compra>> mapaCompras = this.getCompras();
        if (!mapaCompras.isEmpty()) {
            mapaCompras.forEach((clave, arrayCompra) -> {
                Iterator<Compra> it = arrayCompra.iterator();
                while (it.hasNext()) {
                    Compra compra = it.next();
                    Producto producto = compra.getProducto();
                    if (productoSeleccionado.equals(producto)) {
                        if (producto.getFormaVenta().equals("Kilogramo")) {
                            cantidadComprada[0] += compra.getCantStock();
                        } else {
                            cantidadComprada[1] += compra.getCantStock();
                        }
                    }
                }
            });
        }
        return cantidadComprada;
    }

    public ArrayList<Puesto> retornarPuesto() {
        ArrayList<Puesto> puestos = new ArrayList<>(this.getCompras().keySet());
        return puestos;
    }

    public ArrayList<Compra> retornarCompras(ArrayList<Puesto> puestos) {
        ArrayList<Compra> compras = new ArrayList<>();
        Iterator<Map.Entry<Puesto, ArrayList<Compra>>> it = this.getCompras().entrySet().iterator();
        // Recorro los diferentes ArrayList de compras.
        while (it.hasNext()) {
            Map.Entry<Puesto, ArrayList<Compra>> entrada = it.next();
            ArrayList<Compra> listaCompras = entrada.getValue();
            for (Compra compra : listaCompras) {
                if (puestos.contains(compra.getPuesto())) {
                    compras.add(compra);
                }
            }
        }
        return compras;
    }
}

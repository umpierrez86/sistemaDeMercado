/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Observable;

/**
 *
 * @author Luciano Umpierrez
 */
public class RegistroMayorista extends Observable implements Serializable {

    private ArrayList<Mayorista> listaMayoristas;

    public RegistroMayorista() {
        this.listaMayoristas = new ArrayList<>();
    }

    public ArrayList<Mayorista> getListaMayoristas() {
        return listaMayoristas;
    }

    public boolean existeRut(String rut) {
        boolean encontrado = false;
        if (listaMayoristas != null) {
            for (int i = 0; i < listaMayoristas.size() && !encontrado; i++) {
                if (listaMayoristas.get(i).getRut().equals(rut)) {
                    encontrado = true;
                }
            }
        }
        return encontrado;
    }

    public String nombreProducto(ArrayList<Producto> listaProductos, int pos) {
        if (listaProductos != null) {
            return listaProductos.get(pos).getNombre();
        } else {
            return "";
        }
    }

    public Mayorista obtenerMayorista(String rut) {
        int i = 0;
        int pos = 0;
        while (i < this.listaMayoristas.size()) {
            if (this.listaMayoristas.get(i).getRut().equals(rut)) {
                pos = i;
                i = this.listaMayoristas.size();
            } else {
                i++;
            }
        }
        if (i == this.listaMayoristas.size()) {
            return this.listaMayoristas.get(pos);
        } else {
            return null;
        }
    }

    public int posicionMayorista(String rut) {
        int i = 0;
        int pos = 0;
        while (i < this.listaMayoristas.size()) {
            if (this.listaMayoristas.get(i).getRut().equals(rut)) {
                pos = i;
                i = this.listaMayoristas.size();
            } else {
                i++;
            }
        }
        return pos;
    }

    public void eliminarProductoMayorista(String rut, Producto producto) {
        for (int i = 0; i < this.listaMayoristas.size(); i++) {
            if (this.listaMayoristas.get(i).getRut().equals(rut)) {
                this.listaMayoristas.get(i).eliminarProducto(producto.getNombre());
                break;
            }
        }
    }

    public boolean validarRut(String rut) {
        String patron = "^\\d{8}[^\\w\\s]\\w$";
        // El patrón "^\\d{8}[^\\w\\s]\\w$" indica:
        // - ^ : Inicio de la cadena
        // - \\d{8} : 8 dígitos
        // - [^\\w\\s] : Un carácter que no sea alfanumérico ni espacio
        // - \\w : Un carácter alfanumérico
        // - $ : Fin de la cadena

        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(rut);

        return matcher.matches();
    }

    /*public void actualizarMayorista(String rut, String nombre, String direccion, ArrayList<Producto> listaProductosMayorista) {

    }*/

    public void setListaMayoristas(ArrayList<Mayorista> listaMayoristas) {
        this.listaMayoristas = listaMayoristas;
    }

    public void RegistrarMayorista(String rut, String nombre, String direccion, ArrayList<Producto> listaProductosMayorista) {
        Mayorista mayorista = new Mayorista(rut, nombre, direccion, listaProductosMayorista);
        listaMayoristas.add(mayorista);
        setChanged();
        notifyObservers(mayorista);
    }

}

package Dominio;
import AreaRegistros.RegistrarMayorista;
import java.io.Serializable;
import java.util.*;
import java.util.Observable;

/**
 *
 * @author Luciano Umpierrez
 */
public class RegistroProducto extends Observable implements Serializable {
    //private RegistrarMayorista registrarMayorista;
    private ArrayList<Producto> listaProductos;

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void agregarObservador(Observer observador) {
        addObserver(observador);
    }

    public boolean productoYaRegistrado(String nombre) {
        boolean encontrado = false;
        for (int i = 0; i < listaProductos.size() && !encontrado; i++) {
            if (listaProductos.get(i).getNombre().equals(nombre)) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    private void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public RegistroProducto() {
        this.listaProductos = new ArrayList<>();
    }

    public void registrarProducto(String unNombre, String unaDescripcion,
            String unTipo, String unaFormaDeVenta, String unaFoto) {
        Producto producto = new Producto(unNombre, unaDescripcion, unTipo,
                unaFormaDeVenta, unaFoto);
        ArrayList<Producto> productos = getListaProductos();
        productos.add(producto);
        this.setListaProductos(productos);
        setChanged();
        notifyObservers(producto);
    }
}

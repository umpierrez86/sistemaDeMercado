/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import AreaMovimiento.VentanaEmergenteVenta;
/**
 *
 * @author Luciano Umpierrez
 */
public class ProcesoProducto implements ActionListener {
    private VentanaEmergenteVenta ventavaVenta;
    
    public VentanaEmergenteVenta getVentanaVenta(){
        return this.ventavaVenta;
    }
    
    private void setVentanaVenta(VentanaEmergenteVenta ventana){
        this.ventavaVenta = ventana;
    }
    
    public ProcesoProducto(VentanaEmergenteVenta ventana){
        this.setVentanaVenta(ventana);
    }
    
    /*
        Se obtiene el botón en donde se hizo click y el objeto compra almacenado 
        en él. En la ventana se adjudica esta compra para saber el stock 
        disponible y se habre la ventana para terminar la venta
    */
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // este código se ejecutará al presionar el botón, obtengo cuál botón
        JButton cual = ((JButton) e.getSource());
        // código a completar según el botón presionado
        Compra compra = (Compra) cual.getClientProperty("miCompra");
        VentanaEmergenteVenta ventana = this.getVentanaVenta();
        ventana.setCompra(compra);
        ventana.setVisible(true);
    }
    
}

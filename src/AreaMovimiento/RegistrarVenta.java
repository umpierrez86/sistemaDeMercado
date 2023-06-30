package AreaMovimiento;

import Dominio.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.*;
import AreaConsultas.GenerarArchivo;
import AreaMovimiento.VentanaEmergenteVenta;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Observer;
import java.util.Observable;
import javax.swing.JLabel;
import AreaMovimiento.VentanaEmergenteVenta;
/**
 *
 * @author Luciano Umpierrez
 */
public class RegistrarVenta extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form VentaCompra
     */
    public RegistrarVenta(RegistroCompra unRegistroCompra, RegistroPuesto unRegistroPuesto,
            RegistroVenta unRegistroVenta) {
        initComponents();
        this.setTitle("Registrar venta");
        this.compras = unRegistroCompra;
        this.puestos = unRegistroPuesto;
        this.ventas = unRegistroVenta;
        compras.addObserver(this);
        this.ventanaVenta = new VentanaEmergenteVenta(ventas,compras,
                puestos);
        objetosAPantalla();
    }

    public void objetosAPantalla() {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
        for (String puesto : puestos.getListaPuestos().keySet()) {
            if (compras.getCompras().containsKey(puestos.getListaPuestos().get(puesto))) {
                comboBoxModel.addElement(puesto);
            }
        }
        this.cbPuestos.setModel(comboBoxModel);
    }
    @Override
    public void update(Observable o, Object arg){
      objetosAPantalla();  
      crearGrilla();
    }
    public void crearGrilla() {
        String id = (String) this.cbPuestos.getSelectedItem();
        if (id != null && !id.equals("")) {
            final Puesto puesto = this.puestos.getListaPuestos().get(id);
            ArrayList<Compra> unasCompras = compras.getCompras().get(puesto);
            if (unasCompras != null) {
                HashMap<Producto, Compra> mapaCompras = new HashMap<>();
                Iterator<Compra> it = unasCompras.iterator();
                while (it.hasNext()) {
                    Compra unaCompra = it.next();
                    if (mapaCompras.containsKey(unaCompra.getProducto())) {
                        Compra primCompra = mapaCompras.get(unaCompra.getProducto());
                        primCompra.setCantidad(unaCompra.getCantidad() + primCompra.getCantidad());
                        mapaCompras.put(primCompra.getProducto(), primCompra);
                    } else {
                        mapaCompras.put(unaCompra.getProducto(), unaCompra);
                    }
                }
                // ArrayList con todas las compras sin repetir
                ArrayList<Compra> comprasFinal = new ArrayList<>(mapaCompras.values());
                // Ordenar las compras por tipo de producto (frutas primero, luego verduras)
                Collections.sort(comprasFinal, new Comparator<Compra>() {
                    @Override
                    public int compare(Compra compra1, Compra compra2) {
                        String tipo1 = compra1.getProducto().getTipo();
                        String tipo2 = compra2.getProducto().getTipo();
                        return tipo1.compareTo(tipo2);
                    }
                });
                int filasEnPanel = 2;
                int cantArticulos = unasCompras.size();
                int columnasEnPanel = (int) Math.ceil((double) cantArticulos / filasEnPanel);
                GridLayout gridLayout = new GridLayout(filasEnPanel, columnasEnPanel);
                this.panelProductos.setLayout(gridLayout);
                this.panelProductos.removeAll();
                this.panelProductos.revalidate();
                this.panelProductos.repaint();
                
                // Creación de grilla con productos a vender
                for (int i = 0; i < comprasFinal.size(); i++) {
                    Compra compra = comprasFinal.get(i);
                    ImageIcon imagenIcono = new ImageIcon(compra.getProducto().getFoto());
                    // Cambiar el tamaño de la imagen a 60x60 píxeles
                    ImageIcon imagenRedimensionada = new ImageIcon(imagenIcono.getImage().getScaledInstance(
                            60, 60, java.awt.Image.SCALE_SMOOTH));
                    JButton nuevo = new JButton(imagenRedimensionada);
                    final int cantidadDisponible = compra.getCantidad();
                    // nuevo.addActionListener(new ProcesoProducto());
                    final RegistroVenta registro = this.ventas;
                    final Producto producto = compra.getProducto();
                    // se le agrega objeto compra al boton
                    nuevo.putClientProperty("miCompra", compra);
                    ProcesoProducto nuevoProceso = new ProcesoProducto(ventanaVenta);
                    nuevo.addActionListener(nuevoProceso);
                    nuevo.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            nuevo.setToolTipText(producto.getNombre());
                            // lblNombreProducto.setText(producto.getNombre());
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            nuevo.setToolTipText(null);
                            // lblNombreProducto.setText("");
                        }
                    });
                    panelProductos.add(nuevo);
                }
            } else {
                JOptionPane.showMessageDialog(this, 
                        "No hay compras para el puesto seleccionado", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public ArrayList<Producto> ordenar() {
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPanel = new javax.swing.JPanel();
        cbPuestos = new javax.swing.JComboBox();
        lblPuesto = new javax.swing.JLabel();
        panelProductos = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        pnlPanel.setLayout(null);

        cbPuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPuestosActionPerformed(evt);
            }
        });
        pnlPanel.add(cbPuestos);
        cbPuestos.setBounds(20, 50, 72, 22);

        lblPuesto.setText("Puesto");
        pnlPanel.add(lblPuesto);
        lblPuesto.setBounds(20, 30, 60, 16);

        panelProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelProductosMouseEntered(evt);
            }
        });
        panelProductos.setLayout(new java.awt.GridLayout(1, 0));
        pnlPanel.add(panelProductos);
        panelProductos.setBounds(20, 90, 510, 270);

        getContentPane().add(pnlPanel);
        pnlPanel.setBounds(0, 0, 550, 370);

        setSize(new java.awt.Dimension(760, 559));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbPuestosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbPuestosActionPerformed
        // TODO add your handling code here:
        this.crearGrilla();
    }// GEN-LAST:event_cbPuestosActionPerformed

    private void panelProductosMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_panelProductosMouseEntered
        // TODO add your handling code here:
    }// GEN-LAST:event_panelProductosMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbPuestos;
    private javax.swing.JLabel lblPuesto;
    private javax.swing.JPanel panelProductos;
    private javax.swing.JPanel pnlPanel;
    // End of variables declaration//GEN-END:variables
    private RegistroCompra compras;
    private RegistroPuesto puestos;
    private RegistroVenta ventas;
    private JLabel lblNombreProducto;
    private VentanaEmergenteVenta ventanaVenta;
}

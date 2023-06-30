package AreaMovimiento;

import Dominio.*;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import java.util.Observer;
import java.util.Observable;
import AreaConsultas.GenerarArchivo;
import javax.swing.*;

/**
 *
 * @author Luciano Umpierrez
 */
public class RegistrarCompra extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form RegistrarCompra
     */
    public RegistrarCompra(RegistroMayorista unRegistroMayorista,
            RegistroPuesto unRegistroPuesto, RegistroCompra unRegistroCompras,
            RegistroVenta unRegistroVenta) {

        initComponents();
        this.setTitle("Registrar compra");
        lstPuesto.setModel(new DefaultListModel<Puesto>());
        this.mayoristas = unRegistroMayorista;
        this.puestos = unRegistroPuesto;
        this.compras = unRegistroCompras;
        this.registroVenta = unRegistroVenta;
        this.generarArchivo = new GenerarArchivo(unRegistroCompras, unRegistroVenta,
                unRegistroPuesto);
        puestos.addObserver(this);
        mayoristas.addObserver(this);
        // objetoAPantalla();
    }

    public void objetoAPantalla() {
        DefaultListModel<Puesto> model = (DefaultListModel<Puesto>) lstPuesto.getModel();
        model.clear();

        for (Puesto puesto : puestos.getListaPuestos().values()) {
            model.addElement(puesto);
        }

        Mayorista[] arrayMayoristas = mayoristas.getListaMayoristas().toArray(
                new Mayorista[mayoristas.getListaMayoristas().size()]);
        lstMayorista.setListData(arrayMayoristas);
    }

    @Override
    public void update(Observable o, Object arg) {
        objetoAPantalla();   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstPuesto = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstMayorista = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstProducto = new javax.swing.JList();
        txtPrecio = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUnidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnComprar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jScrollPane1.setViewportView(lstPuesto);

        lstMayorista.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstMayoristaValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstMayorista);

        lstProducto.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstProductoValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(lstProducto);

        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        jLabel1.setText("Precio");

        jLabel2.setText("Cantidad");

        txtUnidad.setEditable(false);
        txtUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnidadActionPerformed(evt);
            }
        });

        jLabel3.setText("Unidad");

        btnComprar.setText("Comprar");
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                .createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout
                                                .createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                48,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane1,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                93,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtPrecio,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                71,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(69, 69, 69)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        false)
                                                                .addComponent(txtUnidad,
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        101,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(jScrollPane2,
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        0,
                                                                        Short.MAX_VALUE))
                                                        .addComponent(jLabel3))
                                                .addGap(55, 55, 55)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jScrollPane3,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                0,
                                                                Short.MAX_VALUE)
                                                        .addComponent(txtCantidad,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                92,
                                                                Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout
                                                .createSequentialGroup()
                                                .addContainerGap(
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)
                                                .addComponent(btnComprar)))
                                .addGap(26, 26, 26)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane3,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel1))
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtUnidad,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPrecio,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCantidad,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addComponent(btnComprar)
                                .addContainerGap(41, Short.MAX_VALUE)));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 460, 320);

        setSize(new java.awt.Dimension(480, 331));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lstMayoristaValueChanged(javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_lstMayoristaValueChanged
        // TODO add your handling code here:
        Mayorista mayo = (Mayorista) this.lstMayorista.getSelectedValue();

        if (mayo != null) {
            Producto[] productos = mayo.getListaProductos().toArray(
                    new Producto[mayo.getListaProductos().size()]);
            this.lstProducto.setListData(productos);
        }
    }// GEN-LAST:event_lstMayoristaValueChanged

    private void lstProductoValueChanged(javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_lstProductoValueChanged
        // TODO add your handling code here:
        Producto producto = (Producto) this.lstProducto.getSelectedValue();
        if (producto != null) {
            String unidad = producto.getFormaVenta();
            this.txtUnidad.setText(unidad);
        }
        /*
         * else {
         * mostrarError("Debe seleccionar un producto.");
         * }
         */
    }// GEN-LAST:event_lstProductoValueChanged

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtPrecioActionPerformed

    }// GEN-LAST:event_txtPrecioActionPerformed

    private void txtUnidadActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtUnidadActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtUnidadActionPerformed

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnComprarActionPerformed
        // TODO add your handling code here:
        try {
            Puesto puesto = (Puesto) this.lstPuesto.getSelectedValue();
            Producto producto = (Producto) this.lstProducto.getSelectedValue();
            Mayorista mayorista = (Mayorista) this.lstMayorista.getSelectedValue();
            String precioTxt = this.txtPrecio.getText();
            String cantidadTxt = this.txtCantidad.getText();

            if (puesto != null && producto != null && !precioTxt.equals("")
                    && !cantidadTxt.equals("")) {
                int precio = Integer.parseInt(precioTxt);
                int cantidadAComprar = Integer.parseInt(cantidadTxt);
                Compra nuevaCompra = new Compra(cantidadAComprar, precio, cantidadAComprar,producto,
                        mayorista, puesto);
                this.compras.registrarUnaCompra(puesto, nuevaCompra);
                int numeroCompra = nuevaCompra.getNumeroEstaCompra();
                String mensaje = "Compra registrada con exito. Su numero de compra es "
                                    + numeroCompra + ".";
                JOptionPane.showMessageDialog(null, mensaje,
                        "Exito", JOptionPane.PLAIN_MESSAGE);
                this.generarArchivo.ObjetoaPantalla();
                this.txtPrecio.setText("");
                this.txtPrecio.setText("");
                this.txtCantidad.setText("");
                this.txtUnidad.setText("");
                this.lstProducto.clearSelection();
                this.lstMayorista.clearSelection();
                this.lstPuesto.clearSelection();

            } else {
                mostrarError("Debe completar todos los campos. Seleccione un puesto,"
                        + " mayorista y producto con la unidad que desea y precio");
            }
        } catch (InputMismatchException e) {
            mostrarError("Los campos unidad y precio deben contener solo numeros");
        }
        // this.generarArchivo.ObjetoaPantalla();
    }// GEN-LAST:event_btnComprarActionPerformed

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList lstMayorista;
    private javax.swing.JList lstProducto;
    private javax.swing.JList lstPuesto;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtUnidad;
    // End of variables declaration//GEN-END:variables
    private RegistroMayorista mayoristas;
    private RegistroPuesto puestos;
    private RegistroCompra compras;
    private GenerarArchivo generarArchivo;
    private RegistroVenta registroVenta;
}

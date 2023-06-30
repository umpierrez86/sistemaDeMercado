/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AreaConsultas;

import Dominio.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultListModel;
import java.util.Observer;
import java.util.Observable;

/**
 *
 * @author Luciano Umpierrez
 */
public class GenerarArchivo extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form GenerarArchivo
     */
    public GenerarArchivo(RegistroCompra unRegistroCompra, RegistroVenta unRegistroVenta,
            RegistroPuesto unRegistroPuesto) {
        initComponents();
        this.setTitle("Generación de archivo");
        this.observadores = new ArrayList<>();
        this.registroCompras = unRegistroCompra;
        this.registroVentas = unRegistroVenta;
        this.registroPuestos = unRegistroPuesto;
        this.puestosSeleccionados = new ArrayList<>();
        comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addElement("Compra");
        comboBoxModel.addElement("Venta");
        comboBoxModel.addElement("Todos");
        this.cbTipoDeMovimiento.setModel(comboBoxModel);
        unRegistroVenta.addObserver(this);
        unRegistroCompra.addObserver(this);

    }

    @Override
    public void update(Observable observable, Object argument) {
        ObjetoaPantalla();
    }

    public void ObjetoaPantalla() {
        String tipo = (String) this.cbTipoDeMovimiento.getSelectedItem();
        Puesto[] puestos = puestosAPantalla(tipo);
        actualizarPuestos(puestos);
    }

    public Puesto[] puestosAPantalla(String tipo) {
        ArrayList<Puesto> puestos = new ArrayList<>();
        ArrayList<String> ids = new ArrayList<>();

        if (tipo.equalsIgnoreCase("Compra")) {
            puestos = this.registroCompras.retornarPuesto();
        } else if (tipo.equalsIgnoreCase("Venta")) {
            puestos = this.registroVentas.retornarPuestos();
        } else {
            puestos.addAll(this.registroVentas.retornarPuestos());
            puestos.addAll(this.registroCompras.retornarPuesto());
        }
        ArrayList<Puesto> puestosNuevos = new ArrayList<>();
        for (Puesto puesto : puestos) {
            if (!ids.contains(puesto.getID())) {
                puestosNuevos.add(puesto);
                ids.add(puesto.getID());
            }
        }

        return puestosNuevos.toArray(new Puesto[puestosNuevos.size()]);
    }

    public void actualizarPuestos(Puesto[] puestos) {
        // Actualizar la lista de puestos
        DefaultListModel<Puesto> model = new DefaultListModel<>();
        for (Puesto puesto : puestos) {
            model.addElement(puesto);
        }
        lstPuestos.setModel(model);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void grabacionDeArchivo(int indiceDesde, int indiceHasta,
            String tipo) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaActual = fechaHoraActual.format(formato);
        ArchivoGrabacion grabacion = new ArchivoGrabacion("DetalleMovimientos.txt");
        grabacion.grabarLinea(fechaActual);

        ArrayList<Operacion> operaciones = new ArrayList<>();
        if (tipo.equalsIgnoreCase("Venta")) {
            operaciones.addAll(this.registroVentas.retornarVentas(puestosSeleccionados));
        } else if (tipo.equalsIgnoreCase("Compra")) {
            operaciones.addAll(this.registroCompras.retornarCompras(puestosSeleccionados));
        } else {
            operaciones.addAll(this.registroVentas.retornarVentas(puestosSeleccionados));
            operaciones.addAll(this.registroCompras.retornarCompras(puestosSeleccionados));
        }
        Collections.sort(operaciones);
        Iterator<Operacion> it = operaciones.iterator();
        int cantMovimientos = 0;
        // Recorro hasta que no hay mas operaciones o se llego a la cantidad
        // que el usuario pidio
        while (it.hasNext() && cantMovimientos <= (indiceHasta - indiceDesde)) {
            Operacion op = it.next();
            String linea = "";
            // Identifico que tipo de operación es
            if (op instanceof Venta) {
                Venta venta = (Venta) op;
                if (venta.getNumeroEstaVenta() >= indiceDesde && venta.getNumeroEstaVenta() <= indiceHasta) {
                    linea = String.format("%d#%s#%s#V#%d#%d",
                            venta.getNumeroEstaVenta(), venta.getProducto().getNombre(),
                            venta.getPuesto().getID(), venta.getCantidad(), venta.getPrecio());
                }
            } else if (op instanceof Compra) {
                Compra compra = (Compra) op;
                if (compra.getNumeroEstaCompra() >= indiceDesde && compra.getNumeroEstaCompra() <= indiceHasta) {
                    linea = String.format("%d#%s#%s#C#%d#%d",
                            compra.getNumeroEstaCompra(), compra.getProducto().getNombre(),
                            compra.getPuesto().getID(), compra.getCantidad(), compra.getPrecio());
                }
            }
            cantMovimientos++;
            grabacion.grabarLinea(linea);
        }
        grabacion.grabarLinea("La cantidad de movimientos fueron: " + cantMovimientos);
        grabacion.cerrar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cbTipoDeMovimiento = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstPuestos = new javax.swing.JList();
        btnTodos = new javax.swing.JButton();
        btnCrear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDesde = new javax.swing.JTextField();
        txtHasta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cbTipoDeMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoDeMovimientoActionPerformed(evt);
            }
        });

        lstPuestos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstPuestosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstPuestos);

        btnTodos.setText("Todos");
        btnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosActionPerformed(evt);
            }
        });

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tipode de movimiento");

        jLabel2.setText("Desde");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Puestos");

        jLabel4.setText("Hasta");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Rango");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbTipoDeMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel5)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(32, 32, 32)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75,
                                        Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnCrear)
                                        .addComponent(btnTodos)
                                        .addComponent(jLabel3)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(cbTipoDeMovimiento,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel5)
                                                .addGap(12, 12, 12)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                .createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED,
                                                        32, Short.MAX_VALUE)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTodos)
                                .addGap(18, 18, 18)
                                .addComponent(btnCrear)
                                .addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 46, Short.MAX_VALUE)));

        setSize(new java.awt.Dimension(467, 352));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnTodosActionPerformed
        // TODO add your handling code here:
        int indiceComienzo = 0;
        int indiceFin = this.lstPuestos.getModel().getSize() - 1;
        if (indiceFin >= 0) {
            this.lstPuestos.setSelectionInterval(indiceComienzo, indiceFin);
        }
    }// GEN-LAST:event_btnTodosActionPerformed

    private void lstPuestosValueChanged(javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_lstPuestosValueChanged
        // TODO add your handling code here:
        this.puestosSeleccionados = new ArrayList<>(this.lstPuestos.getSelectedValuesList());
    }// GEN-LAST:event_lstPuestosValueChanged

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCrearActionPerformed
        // TODO add your handling code here:
        try {
            String tipoDeMovimiento = (String) this.cbTipoDeMovimiento.getSelectedItem();
            String desde = this.txtDesde.getText();
            String hasta = this.txtHasta.getText();
            int indiceDesde = 0;
            int indiceHasta = 0;
            int indiceMayor = 0;
            int indiceMenor = this.registroVentas.getNumeroVentaMinimo();
            if (tipoDeMovimiento.equalsIgnoreCase("Venta")) {
                indiceMayor = this.registroVentas.getNumeroVentaMaximo();
            } else if (tipoDeMovimiento.equalsIgnoreCase("Compra")) {
                indiceMayor = this.registroCompras.getNumeroCompraMaximo();
            } else {
                indiceMayor = Math.max(this.registroVentas.getNumeroVentaMaximo(),
                        this.registroCompras.getNumeroCompraMaximo());
            }
            String mensaje = "El número debe ser mayor a cero y "
                    + "menor o igual a " + indiceMayor;
            if (!desde.equals("")) {
                indiceDesde = Integer.parseInt(desde);
                if (indiceDesde < 1 || indiceDesde > indiceMayor) {
                    throw new Exception(mensaje);
                }
            }
            if (!hasta.equals("")) {
                indiceHasta = Integer.parseInt(hasta);
                if (indiceHasta <= 0 || indiceDesde > indiceMayor) {
                    throw new Exception(mensaje);
                }
            }

            if (this.puestosSeleccionados.isEmpty()) {
                throw new Exception("Debe seleccionar al menos un puesto");
            }
            if (indiceDesde == 0) {
                throw new Exception("El rango debe ser entre " + indiceMenor
                        + "y " + indiceMayor);
            }
            if (indiceHasta == 0) {
                throw new Exception("El rango debe ser entre " + indiceMenor
                        + "y " + indiceMayor);
            }
            grabacionDeArchivo(indiceDesde, indiceHasta, tipoDeMovimiento);
        } catch (InputMismatchException e) {
            mostrarError("Debe ingresar los valores desde y hasta en "
                    + "formato numerico");
        } catch (Exception e) {
            mostrarError(e.getMessage());
        }

    }// GEN-LAST:event_btnCrearActionPerformed

    private void cbTipoDeMovimientoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbTipoDeMovimientoActionPerformed
        String tipo = (String) this.cbTipoDeMovimiento.getSelectedItem();
        Puesto[] puestos = puestosAPantalla(tipo);
        this.lstPuestos.setListData(puestos);
    }// GEN-LAST:event_cbTipoDeMovimientoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnTodos;
    private javax.swing.JComboBox<String> cbTipoDeMovimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstPuestos;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtHasta;
    // End of variables declaration//GEN-END:variables
    private RegistroCompra registroCompras;
    private RegistroVenta registroVentas;
    private RegistroPuesto registroPuestos;
    private ArrayList<Puesto> puestosSeleccionados;
    private DefaultComboBoxModel<String> comboBoxModel;
    private List<Observer> observadores;
}

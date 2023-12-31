/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AreaConsultas;

import Dominio.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import java.util.Observer;
import java.util.Observable;
/**
 *
 * @author Luciano Umpierrez 
 */
public class ConsultaPuesto extends javax.swing.JFrame implements Observer{

    /**
     * Creates new form ConsultaPuesto
     */
    public ConsultaPuesto(RegistroPuesto unRegistroPuesto) {
        initComponents();
        this.setTitle("Consulta puesto");
        this.registroPuesto = unRegistroPuesto;
        this.registroPuesto.addObserver(this);
        //crearTablaPuestos();
    }

    public void crearTablaPuestos() {
        HashMap<String, Puesto> puestos = registroPuesto.getListaPuestos();
        DefaultTableModel modelo = (DefaultTableModel) tablaPuestos.getModel();
        ArrayList<Puesto> listaPuestos = new ArrayList<>(puestos.values());
        modelo.setRowCount(0);
        for (Puesto puesto : listaPuestos) {
            Object[] fila = {puesto.getID(), puesto.getDueño().getNombre(), puesto.getUbicacion(), puesto.getCantEmpleados()};
            modelo.addRow(fila);
        }
    }
    @Override
    public void update(Observable o, Object arg){
        if(o instanceof RegistroPuesto && arg instanceof Puesto){
            crearTablaPuestos();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPuestos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        tablaPuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Dueño", "Ubicacion", "Cant.Empleados"
            }
        ));
        jScrollPane1.setViewportView(tablaPuestos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 10, 452, 310);

        setSize(new java.awt.Dimension(489, 384));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPuestos;
    // End of variables declaration//GEN-END:variables
    private RegistroPuesto registroPuesto;
}

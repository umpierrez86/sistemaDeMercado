/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MenuPrincipal;

import AreaRegistros.*;
import AreaMovimiento.*;
import AreaConsultas.*;
import Dominio.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Luciano Umpierrez
 */
public class MenuPrincipal extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
        this.setTitle("Menú principal");
        this.registroCompra = new RegistroCompra();
        this.registroDueño = new RegistrarDueño();
        this.registroMayorista = new RegistroMayorista();
        this.registroProducto = new RegistroProducto();
        this.registroPuesto = new RegistroPuesto();
        this.registroVenta = new RegistroVenta();
        this.eleccion = new EleccionDeInicio();
        this.ventanaEleccion = new RecuperarDatos(eleccion, this);
        this.eleccion.addObserver(this);
        this.areaRegistros = new AreaRegistros(registroMayorista, registroDueño,
                registroPuesto, registroProducto);
        this.areaMovimiento = new AreaMovimiento(registroPuesto, registroMayorista,
                registroCompra, registroVenta);
        this.areaConsulta = new AreaConsulta(registroDueño,
                registroPuesto, registroProducto,
                registroVenta, registroCompra,
                registroMayorista);

        /*
         * escucha en botón de cerrar para que al hacer click en x se guarden los
         * datos de la sesión
         */
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    guardarDatos();
                } catch (IOException ex) {
                    mostrarError("No se pudieron gurdar los datos correctamente");
                }

            }
        });
        this.ventanaEleccion.setVisible(true);
    }

    /*
     * funcion que guarda los datos de la sesion en un archivo llamado salida.ser
     */
    public void guardarDatos() throws IOException {
        FileOutputStream ff = new FileOutputStream("salida.ser");
        BufferedOutputStream bb = new BufferedOutputStream(ff);
        ObjectOutputStream out = new ObjectOutputStream(bb);
        try {
            if (!registroCompra.getCompras().isEmpty()) {
                out.writeObject(registroCompra);
            }
            if (!registroDueño.getDueños().isEmpty()) {
                out.writeObject(registroDueño);
            }
            if (!registroMayorista.getListaMayoristas().isEmpty()) {
                out.writeObject(registroMayorista);
            }
            if (!registroProducto.getListaProductos().isEmpty()) {
                out.writeObject(registroProducto);
            }
            if (!registroPuesto.getListaPuestos().isEmpty()) {
                out.writeObject(registroPuesto);
            }
            if (!registroVenta.getVentas().isEmpty()) {
                out.writeObject(registroVenta);
            }
        } catch (IOException ex) {
        }
        out.close();
    }

    /* Recuperar los datos gurdados de la sesion pasada */
    public void recuperarDatos() throws IOException, ClassNotFoundException {
        FileInputStream ff = new FileInputStream("salida.ser");
        BufferedInputStream bb = new BufferedInputStream(ff);
        ObjectInputStream in = new ObjectInputStream(bb);
        boolean hayMasObjetos = true;
        boolean seRecuperoAlgo = false;
        while (hayMasObjetos) {
            try {
                Object obj = in.readObject();
                if (obj instanceof RegistroCompra) {
                    this.registroCompra = (RegistroCompra) obj;
                } else if (obj instanceof RegistrarDueño) {
                    this.registroDueño = (RegistrarDueño) obj;
                } else if (obj instanceof RegistroMayorista) {
                    this.registroMayorista = (RegistroMayorista) obj;
                } else if (obj instanceof RegistroProducto) {
                    this.registroProducto = (RegistroProducto) obj;
                } else if (obj instanceof RegistroPuesto) {
                    this.registroPuesto = (RegistroPuesto) obj;
                } else {
                    this.registroVenta = (RegistroVenta) obj;
                }
                if (!seRecuperoAlgo) {
                    seRecuperoAlgo = true;
                }
            } catch (IOException | ClassNotFoundException e) {
                hayMasObjetos = false;
            } finally {
                if (!seRecuperoAlgo) {
                    mostrarError("No hay datos para recuperar. El sistema "
                            + "comenzara de cero");
                }
            }
        }
        in.close();
        /*
         * Se debe volver a inicializar las clases de area con los nuevos
         * objetos traidos
         */
        this.areaRegistros = new AreaRegistros(registroMayorista,
                registroDueño, registroPuesto,
                registroProducto);
        this.areaMovimiento = new AreaMovimiento(registroPuesto,
                registroMayorista, registroCompra,
                registroVenta);
        this.areaConsulta = new AreaConsulta(registroDueño,
                registroPuesto, registroProducto,
                registroVenta, registroCompra,
                registroMayorista);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this,
                mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Carga los datos de productos.txt
    public void cargarProductos() {
        ArchivoLectura lectura = new ArchivoLectura("./ImagenesOblig/productos.txt");
        int exitos = 0;
        int errores = 0;
        while (lectura.hayMasLineas()) {
            StringTokenizer st = new StringTokenizer(lectura.linea(), "@");
            String nombre = st.nextToken();
            String descripcion = st.nextToken();
            String tipo = st.nextToken();
            String ventaPor = st.nextToken();
            String nombreImagen = st.nextToken();
            Path traerImagen = Paths.get("./ImagenesOblig/"+nombreImagen);
            String imagen = traerImagen.toString();
            if (tipo.equalsIgnoreCase("fruta")
                    || tipo.equalsIgnoreCase("verdura")) {
                if (ventaPor.equalsIgnoreCase("unidad")
                        || ventaPor.equalsIgnoreCase("kilogramo")) {
                    if (!descripcion.equals("")
                            && !this.registroProducto.productoYaRegistrado(nombre)) {
                        this.registroProducto.registrarProducto(nombre,
                                descripcion, tipo,
                                ventaPor, imagen);
                        exitos++;
                    } else {
                        errores++;
                    }
                } else {
                    errores++;
                }
            } else {
                errores++;
            }
        }
        lectura.cerrar();
        String mensaje = "Se ingresaron correctamente " + exitos + " productos y hubo "
                + errores + " errores";
        JOptionPane.showMessageDialog(this,
                mensaje, "Error", JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenuPrincipal = new javax.swing.JPanel();
        btnAreaRegistros = new javax.swing.JButton();
        btnAreaMovimientos = new javax.swing.JButton();
        btnAreaConsultas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        btnAreaRegistros.setText("Area de registros");
        btnAreaRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAreaRegistrosActionPerformed(evt);
            }
        });

        btnAreaMovimientos.setText("Area de movimientos");
        btnAreaMovimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAreaMovimientosActionPerformed(evt);
            }
        });

        btnAreaConsultas.setText("Area de consultas");
        btnAreaConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAreaConsultasActionPerformed(evt);
            }
        });

        jLabel1.setText("¡Bienvenido!");

        jLabel3.setText("Eliga una opcion");

        javax.swing.GroupLayout panelMenuPrincipalLayout = new javax.swing.GroupLayout(panelMenuPrincipal);
        panelMenuPrincipal.setLayout(panelMenuPrincipalLayout);
        panelMenuPrincipalLayout.setHorizontalGroup(
                panelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelMenuPrincipalLayout.createSequentialGroup()
                                .addGroup(panelMenuPrincipalLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnAreaMovimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 152,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelMenuPrincipalLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelMenuPrincipalLayout.createSequentialGroup()
                                                        .addGap(95, 95, 95)
                                                        .addGroup(panelMenuPrincipalLayout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(
                                                                        panelMenuPrincipalLayout.createSequentialGroup()
                                                                                .addComponent(jLabel2)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(jLabel3))
                                                                .addGroup(panelMenuPrincipalLayout
                                                                        .createSequentialGroup()
                                                                        .addGap(27, 27, 27)
                                                                        .addComponent(jLabel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                79,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(panelMenuPrincipalLayout.createSequentialGroup()
                                                        .addGap(81, 81, 81)
                                                        .addComponent(btnAreaRegistros,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 152,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(btnAreaConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 152,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(107, Short.MAX_VALUE)));
        panelMenuPrincipalLayout.setVerticalGroup(
                panelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelMenuPrincipalLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGroup(panelMenuPrincipalLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelMenuPrincipalLayout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(jLabel2))
                                        .addGroup(panelMenuPrincipalLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)))
                                .addGap(18, 18, 18)
                                .addComponent(btnAreaRegistros)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAreaMovimientos)
                                .addGap(18, 18, 18)
                                .addComponent(btnAreaConsultas)
                                .addContainerGap(55, Short.MAX_VALUE)));

        getContentPane().add(panelMenuPrincipal);
        panelMenuPrincipal.setBounds(0, 0, 340, 240);

        setSize(new java.awt.Dimension(355, 264));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAreaRegistrosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAreaRegistrosActionPerformed
        this.areaRegistros.setVisible(true);
    }// GEN-LAST:event_btnAreaRegistrosActionPerformed

    private void btnAreaMovimientosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAreaMovimientosActionPerformed
        this.areaMovimiento.setVisible(true);

    }// GEN-LAST:event_btnAreaMovimientosActionPerformed

    private void btnAreaConsultasActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAreaConsultasActionPerformed
        this.areaConsulta.setVisible(true);
    }// GEN-LAST:event_btnAreaConsultasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAreaConsultas;
    private javax.swing.JButton btnAreaMovimientos;
    private javax.swing.JButton btnAreaRegistros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel panelMenuPrincipal;
    // End of variables declaration//GEN-END:variables
    private AreaRegistros areaRegistros;
    private AreaMovimiento areaMovimiento;
    private AreaConsulta areaConsulta;
    private RegistroCompra registroCompra;
    private RegistroProducto registroProducto;
    private RegistroPuesto registroPuesto;
    private RegistroMayorista registroMayorista;
    private RegistrarDueño registroDueño;
    private RegistroVenta registroVenta;
    private EleccionDeInicio eleccion;
    private RecuperarDatos ventanaEleccion;

    /*
     * Función que escucha cuando hay un cambio en elección. Cuando lo hay,
     * es porque el usuario defincio como quiere comenzar el programa y decide
     * que datos traer
     */
    @Override
    public void update(Observable o, Object arg) {
        String seleccion = ((EleccionDeInicio) o).getEleccion();
        String error = "No se pudieron gurdar los datos correctamente";
        if (!seleccion.equals("") && !seleccion.equals("Vacio")) {
            if (seleccion.equals("Recuperar")) {
                try {
                    recuperarDatos();
                } catch (IOException ex) {
                    mostrarError("No fue posible traer los datos");
                } catch (ClassNotFoundException ex) {
                    mostrarError("No fue posible traer los datos");
                }
            } else if (seleccion.equals("Cargar")) {
                this.cargarProductos();
            }
        }
    }
}

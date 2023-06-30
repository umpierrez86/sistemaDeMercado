/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 * @author Luciano Umpierrez
 */
public class ArchivoLectura {
    private Scanner leer;
    private String linea;

    public ArchivoLectura(String unNombre) {
        try {
            leer = new Scanner(Paths.get(unNombre));
        } catch (IOException e) {
            System.exit(1);
        }
    }

    public boolean hayMasLineas() {
        boolean hay = false;
        linea = null;
        if (leer.hasNext()) {
            linea = leer.nextLine();
            hay = true;
        }
        return hay;
    }

    public String linea() {
        return linea;
    }

    public void cerrar() {
        leer.close();
    }
}

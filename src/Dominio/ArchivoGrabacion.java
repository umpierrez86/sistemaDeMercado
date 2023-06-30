package Dominio;

import java.io.FileNotFoundException;
import java.util.Formatter;

/**
 *
 * @author Luciano Umpierrez
 */
public class ArchivoGrabacion {
    private Formatter out;
    
    public ArchivoGrabacion(String unNombre){
        try{
            out = new Formatter(unNombre);
        }catch(FileNotFoundException e){
            System.exit(1);
        }
    }
    
    public void grabarLinea(String linea){
        out.format("%s%n", linea);
    }
    
    public void cerrar(){
        out.close();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.util.Observable;

/**
 *
 * @author Luciano Umpierrez
 * 
 */

/*
    Esta clase se usa para cunando el programa comienza, guradar la selección de
    como quiere el usuario iniciar, con o sin datos.
*/
public class EleccionDeInicio extends Observable {
    private String eleccion;
    
    public String getEleccion(){
        return eleccion;
    }
    
    public void setEleccion(String unaEleccion){
        this.eleccion = unaEleccion;
        setChanged();
        notifyObservers();
    }
}

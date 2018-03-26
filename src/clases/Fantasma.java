/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Image;
import pruebapacman.Board;

/**
 *
 * @author mac
 */
public class Fantasma extends Personaje{
    private boolean eating;
    
    public Fantasma(Image[] imagenes, Board tablero, String name) {
        super(imagenes, tablero, name);
        this.eating = false;
    }
    public void setEating(boolean eating){
        this.eating = eating;
    }
    public boolean getEating(){
        return this.eating;
    }
    
}

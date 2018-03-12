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
 * @author Juan Ernesto Ramos
 */
public class Personaje {
    private Image imagen;
    private Board tablero;
    private String name;
    private int posx;
    private int dirx;
    private int posy;
    private int diry;
    private int speed;
    public Personaje(Image imagen, Board tablero, String name){
        this.imagen = imagen;
        this.tablero = tablero;
        this.name = name;
    }
    public void setPosx(int posx){
        this.posx = posx;
    }
    public void setPosy(int posy){
        this.posy = posy;
    }
    public void setDirx(int dirx){
        this.dirx = dirx;
    }
    public void setDiry(int diry){
        this.diry = diry;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public int getPosx(){
        return this.posx;
    }
    public int getPosy(){
        return this.posy;
    }
    public int getDirx(){
        return this.dirx;
    }
    public int getDiry(){
        return this.diry;
    }
    public int getSpeed(){
        return this.speed;
    }
    public Image getImage(){
        return this.imagen;
    }
    public void setImage(Image imagen){
        this.imagen = imagen;
    }
    public String getName(){
        return this.name;
    }
}

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
    private Image imagenes[];
    private Board tablero;
    private String name;
    private int posx;
    private int dirx;
    private int posy;
    private int diry;
    private int speed;
    private boolean visible;
    public Personaje(Image[] imagenes, Board tablero, String name){
        this.imagenes = imagenes;
        this.tablero = tablero;
        this.name = name;
        this.visible = true;
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
    public void setVisible(boolean visible){
        this.visible = visible;
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
    public boolean getVisible(){
        return this.visible;
    }
    public Image[] getImages(){
        return this.imagenes;
    }
    public void setImages(Image[] imagenes){
        this.imagenes = imagenes;
    }
    public String getName(){
        return this.name;
    }
}

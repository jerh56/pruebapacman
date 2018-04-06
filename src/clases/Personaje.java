package clases;

import pruebapacman.Board;

/**
 *
 * @author Juan Ernesto Ramos
 */
public class Personaje {
    private Board tablero;
    private String name;
    private Animation currentAnimation;
    private int posx;
    private int dirx;
    private int posy;
    private int diry;
    private int speed;
    private boolean visible;
    public Personaje(Animation animation, Board tablero, String name){
        this.currentAnimation = animation;
        this.tablero = tablero;
        this.name = name;
        this.visible = true;
    }

    public void update() {
        this.currentAnimation.update();
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
    public void setCurrentAnimation(Animation animation) {
        this.currentAnimation = animation;
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
    public Animation getCurrentAnimation() {
        return this.currentAnimation;
    }
    public boolean getVisible(){
        return this.visible;
    }
    public String getName(){
        return this.name;
    }
}

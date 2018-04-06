package clases;

import pruebapacman.Board;

/**
 *
 * @author mac
 */
public class Fantasma extends Personaje{
    private boolean eating;

    public Fantasma(Animation animation, Board tablero, String name) {
        super(animation, tablero, name);
        this.eating = false;
    }

    public void setEating(boolean eating){
        this.eating = eating;
    }
    public boolean getEating(){
        return this.eating;
    }
    
}

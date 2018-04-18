package clases;

import pruebapacman.Board;

/**
 *
 * @author mac
 */
public class Fantasma extends Personaje{
    private boolean eaten;

    public Fantasma(Animation animation, Board tablero, String name) {
        super(animation, tablero, name);
        this.eaten = false;
    }

    public void setEaten(boolean eating){
        this.eaten = eating;
    }
    public boolean getEaten(){
        return this.eaten;
    }
    
}

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

    public void setEaten(boolean eaten){
        this.eaten = eaten;
    }
    public boolean getEaten(){
        return this.eaten;
    }
    
}

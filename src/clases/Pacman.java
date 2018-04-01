package clases;

import pruebapacman.Board;

public class Pacman extends Personaje {
    private int health;

    public Pacman(Animation animation, Board tablero, String name) {
        super(animation, tablero, name);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

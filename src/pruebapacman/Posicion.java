package pruebapacman;

public enum Posicion {
    PRIMERO(1),
    ULTIMO(2);

    private final int pos;

    Posicion(int pos) {
        this.pos = pos;
    }

    public int getValue() { return pos; }

    public Posicion getPosicion(int pos) {
        if(pos < 1 || pos > 2) return null;

        return pos == 1 ? Posicion.PRIMERO : Posicion.ULTIMO;
    }
}

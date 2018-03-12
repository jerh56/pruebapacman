package pruebapacman;

/**
 *
 * @author Carlos Contreras
 */
public class Persona {
    private String nombre;
    private String apellido;
    private int edad;
    private Posicion posicion;

    public Persona(String nombre, String apellido, int edad, Posicion pos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.posicion = pos;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion pos) {
        this.posicion = pos;
    }
    
    
    
}

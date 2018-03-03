/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebapacman;

import javax.swing.JOptionPane;

/**
 *
 * @author Alumno
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hola Mundo");
        System.out.println("Mensaje escrito por Brandon");
        JOptionPane.showMessageDialog(null, "Hola Mundo");
        System.out.println("Cambio 7");
        System.out.println("Mensaje escrito por Carlos");
        System.out.println("Commit por Armando");
        Persona alumno1 = new Persona("Carlos", "Contreras", 19);
        System.out.println("El alumno " + alumno1.getNombre() + " " + alumno1.getApellido() + " tiene " + alumno1.getEdad() + " años.");
        
        
        
    }
    
}

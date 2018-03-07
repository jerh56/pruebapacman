/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebapacman;

import javax.swing.JFrame;
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
        System.out.println("hola por wendy");
        System.out.println("Hola soy el diseñador");
        System.out.println("._.");
        Persona alumno1 = new Persona("Carlos", "Contreras", 19);
        System.out.println("El alumno " + alumno1.getNombre() + " " + alumno1.getApellido() + " tiene " + alumno1.getEdad() + " años.");
        System.out.println("█║▌│█│║▌║││█║▌║▌║ "); // Carlos Contreras
        System.out.println("Este Pac-Man va directo a Steam");
        System.out.println("Alguien aquí sabe programar PIC con XC8+MPLAB X? Se agradece cualquier pista xD -Armando Payán"); 
        
       /*
        Ahora, un Nyan Cat en ASCII :D
        */ 
       
        System.out.println("░░░░░░░▄▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▄░░░░░░");
        System.out.println("░░░░░░█░░▄▀▀▀▀▀▀▀▀▀▀▀▀▀▄░░█░░░░░");
        System.out.println("░░░░░░█░█░▀░░░░░▀░░▀░░░░█░█░░░░░");
        System.out.println("░░░░░░█░█░░░░░░░░▄▀▀▄░▀░█░█▄▀▀▄░");
        System.out.println("█▀▀█▄░█░█░░▀░░░░░█░░░▀▄▄█▄▀░░░█░");
        System.out.println("▀▄▄░▀██░█▄░▀░░░▄▄▀░░░░░░░░░░░░▀▄");
        System.out.println("░░▀█▄▄█░█░░░░▄░░█░░░▄█░░░▄░▄█░░█");
        System.out.println("░░░░░▀█░▀▄▀░░░░░█░██░▄░░▄░░▄░███");
        System.out.println("░░░░░▄█▄░░▀▀▀▀▀▀▀▀▄░░▀▀▀▀▀▀▀░▄▀░");
        System.out.println("░░░░█░░▄█▀█▀▀█▀▀▀▀▀▀█▀▀█▀█▀▀█░░░");
        System.out.println("░░░░▀▀▀▀░░▀▀▀░░░░░░░░▀▀▀░░▀▀░░░░");
        System.out.println("====================================================");
        System.out.println("==            Espero les haya gustado             ==");
        System.out.println("====================================================");
        
        JOptionPane.showMessageDialog(null, "Adios Mundo :D");
        
    }

}

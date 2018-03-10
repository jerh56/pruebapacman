package pruebapacman;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.*;

public class Principal extends JFrame {

    public Principal() {
        add(new Board());

        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Ahora existe la clase Board para el tablero");
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
        System.out.println("El alumno ");
        for(char c : ((alumno1.getNombre() + " ").concat(alumno1.getApellido())).toCharArray()) {
            System.out.println(c);
        }
        System.out.println("tiene " + alumno1.getEdad() + " años.");
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
        
        
        System.out.println("░░░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░██████░░░░░░");
        System.out.println("░░░░██████████░░░░");
        System.out.println("░░░████████████░░░");
        System.out.println("░░█████░░████░░█░░");
        System.out.println("░░█████░░████░░█░░");
        System.out.println("░████████████░░░█░");
        System.out.println("░███████████░░░░█░");
        System.out.println("░██████████░░░░░█░");
        System.out.println("░█████████░░░░░░█░");
        System.out.println("░█████░░░░░░░░░░█░");
        System.out.println("░████░░░░░░░░░░░█░");
        System.out.println("░░███░░░░██░░░░█░░");
        System.out.println("░░███░░░░██░░░██░░");
        System.out.println("░░░███░░░░░░░██░░░");
        System.out.println("░░░░████░░░░██░░░░");
        System.out.println("░░░░░░██████░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░░░");
        
        System.out.println("░░█▀░░░░░░░░░░░▀▀███████░░░░░");
        System.out.println("░░█▌░░░░░░░░░░░░░░░▀██████░░░");
        System.out.println("░█▌░░░░░░░░░░░░░░░░███████▌░░");
        System.out.println("░█░░░░░░░░░░░░░░░░░████████░░");
        System.out.println("▐▌░░░░░░░░░░░░░░░░░▀██████▌░░");
        System.out.println("░▌▄███▌░░░░▀████▄░░░░▀████▌░░");
        System.out.println("▐▀▀▄█▄░▌░░░▄██▄▄▄▀░░░░████▄▄░");
        System.out.println("▐░▀░░═▐░░░░░░══░░▀░░░░▐▀░▄▀▌▌ ");
        System.out.println("▐░░░░░▌░░░░░░░░░░░░░░░▀░▀░░▌▌ ");
        System.out.println("▐░░░▄▀░░░▀░▌░░░░░░░░░░░░▌█░▌▌");
        System.out.println("░▌░░▀▀▄▄▀▀▄▌▌░░░░░░░░░░▐░▀▐▐░ ");
        System.out.println("░▌░░▌░▄▄▄▄░░░▌░░░░░░░░▐░░▀▐░░");
        System.out.println("░█░▐▄██████▄░▐░░░░░░░░█▀▄▄▀░░");
        System.out.println("░▐░▌▌░░░░░░▀▀▄▐░░░░░░█▌░░░░░░");
        System.out.println("░░█░░▄▀▀▀▀▄░▄═╝▄░░░▄▀░▌░░░░░░ ");
        System.out.println("░░░▌▐░░░░░░▌░▀▀░░▄▀░░▐░░░░░░░");
        System.out.println("░░░▀▄░░░░░░░░░▄▀▀░░░░█░░░░░░░ ");
        System.out.println("░░░▄█▄▄▄▄▄▄▄▀▀░░░░░░░▌▌░░░░░░");
        System.out.println("░░▄▀▌▀▌░░░░░░░░░░░░░▄▀▀▄░░░░░");
        System.out.println("▄▀░░▌░▀▄░░░░░░░░░░▄▀░░▌░▀▄░░░");
        System.out.println("░░░░▌█▄▄▀▄░░░░░░▄▀░░░░▌░░░▌▄▄ ");
        System.out.println("░░░▄▐██████▄▄░▄▀░░▄▄▄▄▌░░░░▄░ ");
        System.out.println("░░▄▌████████▄▄▄███████▌░░░░░▄ ");
        System.out.println("░▄▀░██████████████████▌▀▄░░░░");
        System.out.println("▀░░░█████▀▀░░░▀███████░░░▀▄░░");
        System.out.println("░░░░▐█▀░░░▐░░░░░▀████▌░░░░▀▄░");
        System.out.println("░░░░░░▌░░░▐░░░░▐░░▀▀█░░░░░░░▀");
        System.out.println("░░░░░░▐░░░░▌░░░▐░░░░░▌░░░░░░░");
        System.out.println("░╔╗║░╔═╗░═╦═░░░░░╔╗░░╔═╗░╦═╗░");
        System.out.println("░║║║░║░║░░║░░░░░░╠╩╗░╠═╣░║░║░");
        System.out.println("░║╚╝░╚═╝░░║░░░░░░╚═╝░║░║░╩═╝░");
        
        System.out.println("Holis aca Jesús con un intento de pixel art, el segundo si fue copiado X'd");

        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("░╔╗║╦═║░║░░╔═░╔╦╗╔═╗╔═░║░║░");
        System.out.println("░║║║╠░║║║░░╚═╗║║║╠═╣╚═╗╠═╣░");
        System.out.println("░║╚╝╩═╚╩╝░░══╝║║║║░║══╝║░║░");
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░");

        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░████░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░████░░░░░░░░░░░░");
        System.out.println("░░░░░░░░██░░░░░░████████░░░░░░░░");
        System.out.println("░░░░░░░░██░░░░░░████████░░░░░░░░");
        System.out.println("░░░░░░████░░░░░░██████████░░░░░░");
        System.out.println("░░░░░░████░░░░░░██████████░░░░░░");
        System.out.println("░░░░██████░░░░░░████████████░░░░");
        System.out.println("░░░░██████░░░░░░████████████░░░░");
        System.out.println("░░░░██████░░░░░░████████████░░░░");
        System.out.println("░░░░██████░░░░░░████████████░░░░");
        System.out.println("░░████████░░░░░░██████████████░░");
        System.out.println("░░████████░░░░░░██████████████░░");
        System.out.println("░░████████░░░░░░██████████████░░");
        System.out.println("░░████████░░░░░░██████████████░░");
        System.out.println("░░████████░░░░░░██████████████░░");
        System.out.println("░░████████░░░░░░██████████████░░");
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("░░░░██████░░░░░░████████████░░░░");
        System.out.println("░░░░██████░░░░░░████████████░░░░");
        System.out.println("░░░░░░████░░░░░░██████████░░░░░░");
        System.out.println("░░░░░░████░░░░░░██████████░░░░░░");
        System.out.println("░░░░░░░░██░░░░░░████████░░░░░░░░");
        System.out.println("░░░░░░░░██░░░░░░████████░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░████░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░████░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        
        System.out.println("Ahí va otro commit.");
        EventQueue.invokeLater(() -> {
            Principal ex = new Principal();
            ex.setVisible(true);
        });
        
        
        System.out.println("los ultimos seran los primeros");
        System.out.println("Test contribuidor");
        System.out.println("Test contribuidor 2");
        System.out.println("Test contribuidor 3 - Solo autor");
        System.out.println("Test contribuidor 4 Solo commiter");
    }

}
package pruebapacman;

/**
 *
 * Esta clase permite generar el objeto Tablero del juego Probando
 *
 * @author Juan Ernesto
 */
import clases.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private Dimension d;
    private final Font smallFont = new Font("Helvetica", Font.BOLD, 14);

    private final Color dotColor = new Color(255, 255, 255);
    private Color mazeColor;

    private boolean inGame = false;
    private boolean eatingGhost = false;
    private boolean dying = false;

    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int MAX_GHOSTS = 4; // TODO: Aumentar la cantidad de fantasmas
    private final int PACMAN_SPEED = 6;
    private final int REG_ZONE_X = 4;
    private final int REG_ZONE_Y = 4;

    private int superPacmanCount = 0;
    private int pacmanAnimPoints = 0;
    private boolean dirChanged = false;
    private final int POINTS_EAT_GHOST = 200;

    //private int pacAnimCount = PAC_ANIM_DELAY;
    //private int ghostsAnimCount = GHOSTS_ANIM_COUNT;
    //private int pacAnimDir = 1;
    //private int ghostsAnimDir = 1;
    //private int pacmanAnimPos = 0;
    //private int pacmanAnimPosDie = 0; // para animar cuando pacman muere
    //private int pacmanAnimPoints = 0;
    //private int ghostsAnimPos = 0; // Para animar los fantasmas
    private int N_GHOSTS = 4;
    private int score;
    private int[] dx, dy;
    private Pacman pacman;
    private ArrayList<Fantasma> fantasmas = new ArrayList<>(); // array list para crear los objetos Fantasma
    private int acumPointsEat = 0;
    private int whatEatGhost = -1;
/////    private Image ghost; //imagen png del fantasma
////    private Image clydeGhost;
////    private Image blinkyGhost;
////    private Image inkyGhost;
////    private Image pinkyGhost;
//    private Image[] pacmanUp = new Image[3];
//    private Image[] pacmanRight = new Image[3];
//    private Image[] pacmanDown = new Image[3];
//    private Image[] pacmanLeft = new Image[3];
//    private Image[] pacmanLeftDie = new Image[6];
//    private Image pacman1;

    private Image ghostEyes;
    private Image ghostScared;
    private Image fruit;

    // Descripción de la variables:
    // pacman.getPosx(), pacman.getPosy() son las posiciones en los dos ejes
    // pacman.getDirx(), pacman.getDiry() es la aceleracion en los dos ejes
    private int req_dx, req_dy, view_dx, view_dy;
    // con bolita
    // 19 (10011) = ┌*
    // 22 (10110) = *┐
    // 25 (11001) = └*
    // 28 (11100) = *┘

    // sin bolita
    // 1 (00001) = linea izq
    // 2 (00010) = Linea superior
    // 4 (00100) = Linea derecha
    // 8 (01000) = Linea inferior
    // 3 (00011) = ┌
    // 6 (00110) =  ┐
    // 9 (01001) = └
    // 12 (01100) =  ┘
    // 15 (01111) = ┌ ┘ (cuadro cerrado sin bolita)
    // 11  (01011) = (cuadro abierto por la derecha)
    // 13  (01101) = (cuadro abierto por arriba)
    // 14  (01110) = (cuadro abierto por izq)
//    private final short levelData[] = {
//        19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
//        21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
//        21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
//        21, 0, 0, 0, 17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20,
//        17, 18, 18, 18, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 20,
//        17, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 16, 24, 20,
//        25, 16, 16, 16, 24, 24, 28, 0, 25, 24, 24, 16, 20, 0, 21,
//        1, 17, 16, 20, 0, 0, 0, 0, 0, 0, 0, 17, 20, 0, 21,
//        1, 17, 16, 16, 18, 18, 22, 0, 19, 18, 18, 16, 20, 0, 21,
//        1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
//        1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
//        1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20, 0, 21,
//        1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 21,
//        1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,
//        9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 25, 24, 24, 24, 28
//    };
    private final short levelData4[] = {
        51, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 54,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 16, 24, 16, 24, 16, 16, 16, 16, 16, 20,
        17, 16, 24, 24, 16, 20, 1, 0, 0, 17, 16, 16, 16, 16, 20,
        17, 20, 0, 0, 17, 16, 1, 0, 0, 17, 16, 16, 16, 16, 20,
        17, 16, 18, 18, 0, 16, 26, 26, 26, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 21, 0, 0, 0, 21, 16, 16, 16, 16, 20,
        17, 24, 24, 16, 24, 24, 22, 0, 19, 24, 24, 16, 24, 24, 20,
        21, 0, 0, 21, 0, 0, 21, 0, 21, 0, 0, 21, 0, 0, 21,
        25, 22, 0, 17, 26, 18, 24, 26, 24, 18, 26, 20, 0, 19, 28,
        0, 21, 0, 21, 0, 21, 0, 0, 0, 21, 0, 21, 0, 21, 0,
        19, 24, 26, 28, 0, 25, 22, 0, 19, 28, 0, 25, 26, 24, 22,
        21, 0, 0, 0, 0, 0, 21, 0, 21, 0, 0, 0, 0, 0, 21,
        25, 26, 26, 26, 26, 26, 24, 26, 24, 26, 26, 26, 26, 26, 28
    };

    private final short levelData[] = {
        //      1   2   3   4   5   6   7   8   9   10  11  12  13  14  15
        /*1*/35, 26, 18, 26, 26, 26, 26, 18, 26, 26, 26, 26, 18, 26, 54,
        /*2*/ 21, 0, 21, 0, 0, 0, 0, 21, 0, 0, 0, 0, 21, 0, 21,
        /*3*/ 17, 26, 16, 26, 26, 18, 26, 24, 26, 18, 26, 26, 16, 26, 20,
        /*4*/ 21, 0, 21, 0, 0, 21, 0, 0, 0, 21, 0, 0, 21, 0, 21,
        /*5*/ 21, 0, 21, 0, 19, 24, 26, 18, 26, 24, 22, 0, 21, 0, 21,
        /*6*/ 21, 0, 17, 26, 20, 0, 0, 21, 0, 0, 17, 26, 20, 0, 21,
        /*7*/ 21, 0, 21, 0, 21, 0, 0, 21, 0, 0, 21, 0, 21, 0, 21,
        /*8*/ 17, 26, 20, 0, 17, 26, 26, 48, 26, 26, 20, 0, 17, 26, 20,
        /*9*/ 21, 0, 21, 0, 21, 0, 0, 21, 0, 0, 21, 0, 21, 0, 21,
        /*10*/ 21, 0, 17, 26, 20, 0, 0, 21, 0, 0, 17, 26, 20, 0, 21,
        /*11*/ 21, 0, 21, 0, 25, 18, 26, 24, 26, 18, 28, 0, 21, 0, 21,
        /*12*/ 21, 0, 21, 0, 0, 21, 0, 0, 0, 21, 0, 0, 21, 0, 21,
        /*13*/ 17, 26, 16, 26, 26, 24, 26, 66, 26, 24, 26, 26, 16, 26, 20,
        /*14*/ 21, 0, 21, 0, 0, 0, 0, 21, 0, 0, 0, 0, 21, 0, 21,
        /*15*/ 57, 26, 24, 26, 26, 26, 26, 24, 26, 26, 26, 26, 24, 26, 60,};

    private final short levelData3[] = {
        //      1   2   3   4   5   6   7   8   9   10  11  12  13  14  15
        /*1*/35, 26, 18, 26, 26, 26, 26, 18, 26, 26, 26, 26, 18, 26, 54,
        /*2*/ 21, 0, 21, 0, 0, 0, 0, 21, 0, 0, 0, 0, 21, 0, 21,
        /*3*/ 17, 26, 16, 26, 26, 18, 26, 24, 26, 18, 26, 26, 16, 26, 20,
        /*4*/ 21, 0, 21, 0, 0, 21, 0, 0, 0, 21, 0, 0, 21, 0, 21,
        /*5*/ 21, 0, 21, 0, 19, 24, 26, 18, 26, 24, 22, 0, 21, 0, 21,
        /*6*/ 21, 0, 17, 26, 20, 0, 0, 21, 0, 0, 17, 26, 20, 0, 21,
        /*7*/ 21, 0, 21, 0, 21, 0, 0, 21, 0, 0, 21, 0, 21, 0, 21,
        /*8*/ 17, 26, 20, 0, 17, 26, 26, 48, 26, 26, 20, 0, 17, 26, 20,
        /*9*/ 21, 0, 21, 0, 21, 0, 0, 21, 0, 0, 21, 0, 21, 0, 21,
        /*10*/ 21, 0, 17, 26, 20, 0, 0, 21, 0, 0, 17, 26, 20, 0, 21,
        /*11*/ 21, 0, 21, 0, 25, 18, 26, 24, 26, 18, 28, 0, 21, 0, 21,
        /*12*/ 21, 0, 21, 0, 0, 21, 0, 0, 0, 21, 0, 0, 21, 0, 21,
        /*13*/ 17, 26, 16, 26, 26, 24, 26, 66, 26, 24, 26, 26, 80, 26, 20,
        /*14*/ 21, 0, 21, 0, 0, 0, 0, 21, 0, 0, 0, 0, 21, 0, 21,
        /*15*/ 57, 26, 24, 26, 26, 26, 26, 24, 26, 26, 26, 26, 24, 26, 60,};

    private final short levelData2[] = {
        //      1   2   3   4   5   6   7   8   9   10  11  12  13  14  15 
        /*1*/35, 26, 26, 26, 18, 26, 26, 26, 18, 26, 18, 18, 18, 26, 38,
        /*2*/ 21, 11, 2, 14, 21, 3, 10, 6, 21, 0, 25, 16, 28, 0, 21,
        /*3*/ 17, 22, 5, 19, 20, 5, 7, 5, 21, 0, 0, 21, 0, 0, 21,
        /*4*/ 17, 20, 5, 17, 20, 5, 13, 5, 21, 0, 23, 45, 23, 0, 21,
        /*5*/ 17, 20, 13, 17, 20, 9, 10, 12, 21, 0, 17, 18, 20, 0, 21,
        /*6*/ 17, 24, 26, 24, 24, 18, 26, 26, 24, 18, 24, 16, 16, 18, 28,
        /*7*/ 21, 3, 10, 10, 14, 21, 0, 0, 0, 21, 0, 25, 16, 28, 4,
        /*8*/ 21, 5, 19, 26, 26, 20, 0, 15, 0, 21, 0, 0, 29, 0, 4,
        /*9*/ 21, 5, 21, 11, 6, 21, 0, 0, 0, 21, 0, 0, 0, 0, 4,
        /*10*/ 21, 5, 25, 78, 5, 21, 0, 39, 0, 21, 0, 23, 0, 23, 4,
        /*11*/ 21, 9, 10, 10, 12, 21, 0, 21, 0, 21, 0, 17, 18, 20, 4,
        /*12*/ 17, 26, 26, 26, 26, 24, 26, 16, 26, 24, 26, 24, 24, 24, 22,
        /*1*/ 21, 19, 18, 18, 18, 18, 18, 16, 18, 18, 18, 18, 18, 22, 21,
        /*2*/ 21, 73, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 76, 21,
        /*3*/ 41, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 44,};

    // TODO: Agregar una forma en la que un espacio abierto en el borde te mande al otro extremo de la pantalla
    private final int validSpeeds[] = {1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;

    private int currentSpeed = 3;
    private short[] screenData;
    private Timer timer;

    public Board() {

        loadImages();
        initVariables();
        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());

        setFocusable(true);

        setBackground(Color.black);
        setDoubleBuffered(true);
    }

    private void initVariables() {

        screenData = new short[N_BLOCKS * N_BLOCKS];
        mazeColor = new Color(5, 100, 5);
        d = new Dimension(500, 500); // esta variable solamente se usa para crear un rectangulo negro
        //region Inicializar Pacman
        pacman = new Pacman(new Animation(AnimationEnum.PACMAN_NORMAL_LEFT), this, "Pacman");
        //endregion

        //region Inicializar fantasmas
        // Cargar las 2 imagenes que pertenecen a cada fantasma a la animacion
        Animation animations[] = new Animation[4];
        for (int i = 0; i < 4; i++) {
            animations[i] = new Animation(new Image[2], 5);
        }
        int arrPos = 0;

        for (GhostType ghost : GhostType.values()) {
            Image gImages[] = animations[arrPos].getImages();
            String name = ghost.name().toLowerCase();
            gImages[0] = new ImageIcon("images/" + name + " v1.png").getImage();
            gImages[1] = new ImageIcon("images/" + name + " v2.png").getImage();

            animations[arrPos++].setImages(gImages);
        }

        // aquí se agregan los objetos fantasma al array list
        fantasmas.add(new Fantasma(new Animation(AnimationEnum.CLYDE_NORMAL_RIGHT), this, "Clyde"));
        fantasmas.add(new Fantasma(new Animation(AnimationEnum.BLINKY_NORMAL_RIGHT), this, "Blinky"));
        fantasmas.add(new Fantasma(new Animation(AnimationEnum.INKY_NORMAL_RIGHT), this, "Inky"));
        fantasmas.add(new Fantasma(new Animation(AnimationEnum.PINKY_NORMAL_RIGHT), this, "Pinky"));
        //endregion

        // esto agregará los fantasmas restantes según el nivel de dificultad
        // hasta llegar al máximo de fantasmas
//        ghost_x = new int[MAX_GHOSTS];
//        ghost_dx = new int[MAX_GHOSTS];
//        ghost_y = new int[MAX_GHOSTS];
//        ghost_dy = new int[MAX_GHOSTS];
//        ghostSpeed = new int[MAX_GHOSTS];
        dx = new int[4];
        dy = new int[4];

        timer = new Timer(40, this);
        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        initGame();
    }

    private void playGame(Graphics2D g2d) {

        if (dying) {

            if (pacman.getCurrentAnimation().getCurrentFrame() == 0) {
                Sound.SIREN.stop();
                timer.stop();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                }
                timer.start();
                Sound.PACMAN_DEATH.play();
            }
            pacman.update();
            drawPacmanDie(g2d);
            // timer.wait(1000);
            if (pacman.getCurrentAnimation().isFinished()) {
                death();
                timer.stop();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                }
                timer.start();
                Sound.SIREN.loop();
            }
        } else {
            if (whatEatGhost == -1) {
                movePacman();
            }
            drawPacman(g2d);
            moveGhosts(g2d);
            checkMaze();
        }
    }

    private void showIntroScreen(Graphics2D g2d) {

        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);

        String s = "Press s to start.";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (SCREEN_SIZE - metr.stringWidth(s)) / 2, SCREEN_SIZE / 2);
    }

    private void drawScore(Graphics2D g) {

        int i;
        String s;

        g.setFont(smallFont);
        g.setColor(new Color(96, 128, 255));
        s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

        for (i = 0; i < pacman.getHealth(); i++) {
            g.drawImage(new ImageIcon("images/left3.png").getImage(), i * 28 + 8, SCREEN_SIZE + 1, this);
        }
    }

    private void checkMaze() {

        short i = 0;
        boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished) {

            if ((screenData[i] & 48) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) {

            score += 50;

            if (N_GHOSTS < MAX_GHOSTS) {
                N_GHOSTS++;
            }

            if (currentSpeed < maxSpeed) {
                currentSpeed++;
            }

            initLevel();
        }
    }

    private void death() {

        pacman.setHealth(pacman.getHealth() - 1);
        pacman.setCurrentAnimation(new Animation(AnimationEnum.PACMAN_NORMAL_LEFT));
        if (pacman.getHealth() == 0) {
            inGame = false;
            Sound.SIREN.stop();
        }

        continueLevel();
    }

    private void moveGhosts(Graphics2D g2d) {
        short i;
        int pos;
        int count;
        int currentDirX = -1;
        fantasmas.get(0).update();
        fantasmas.get(1).update();
        fantasmas.get(2).update();
        fantasmas.get(3).update();

        for (i = 0; i < N_GHOSTS; i++) {
            if (fantasmas.get(i).getPosx() % BLOCK_SIZE == 0 && fantasmas.get(i).getPosy() % BLOCK_SIZE == 0) {
                pos = fantasmas.get(i).getPosx() / BLOCK_SIZE + N_BLOCKS * (int) (fantasmas.get(i).getPosy() / BLOCK_SIZE);
                count = 0;
                // Si el fantasma ha sido comido y está sin regenerarse
                if (fantasmas.get(i).getEaten()) {
                    List<Integer> xymove;
                    xymove = fantasmas.get(i).getNextMove();
                    dx[count] = xymove.get(0);
                    dy[count] = xymove.get(1);
                    // Cuando el desplazamiento sea 0 en X y Y, el fantasma se regenera
                    if (dx[count] == 0 && dy[count] == 0) {
                        fantasmas.get(i).setEaten(false);
                    }
                } else {
                    // Si el fantasma no ha sido comido pero SuperPacman está activado
                    if (eatingGhost && fantasmas.get(i).getEaten() == false) {
                        if ((screenData[pos] & 1) == 0 && fantasmas.get(i).getDirx() != 1 && fantasmas.get(i).getPosx() <= pacman.getPosx()) {
                            dx[count] = -1;
                            dy[count] = 0;
                            count++;
                        }

                        if ((screenData[pos] & 2) == 0 && fantasmas.get(i).getDiry() != 1 && fantasmas.get(i).getPosy() <= pacman.getPosy()) {
                            dx[count] = 0;
                            dy[count] = -1;
                            count++;
                        }

                        if ((screenData[pos] & 4) == 0 && fantasmas.get(i).getDirx() != -1 && fantasmas.get(i).getPosx() >= pacman.getPosx()) {
                            dx[count] = 1;
                            dy[count] = 0;
                            count++;
                        }

                        if ((screenData[pos] & 8) == 0 && fantasmas.get(i).getDiry() != -1 && fantasmas.get(i).getPosy() >= pacman.getPosy()) {
                            dx[count] = 0;
                            dy[count] = 1;
                            count++;
                        }
                        superPacmanCount++;
                        if (superPacmanCount > 50) {
                            eatingGhost = false;
                            superPacmanCount = 0;
                            Sound.GHOST_SCARED.stop();
                        }

                    } else {
                        // Superpacman no ha sido activado y el fantasma está regenerado
                        if ((screenData[pos] & 1) == 0 && fantasmas.get(i).getDirx() != 1) {
                            dx[count] = -1;
                            dy[count] = 0;
                            count++;
                        }

                        if ((screenData[pos] & 2) == 0 && fantasmas.get(i).getDiry() != 1) {
                            dx[count] = 0;
                            dy[count] = -1;
                            count++;
                        }

                        if ((screenData[pos] & 4) == 0 && fantasmas.get(i).getDirx() != -1) {
                            dx[count] = 1;
                            dy[count] = 0;
                            count++;
                        }

                        if ((screenData[pos] & 8) == 0 && fantasmas.get(i).getDiry() != -1) {
                            dx[count] = 0;
                            dy[count] = 1;
                            count++;
                        }

                    }
                }
                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        //ghost_dx[i] = 0;
                        fantasmas.get(i).setDirx(0);
                        //ghost_dy[i] = 0;
                        fantasmas.get(i).setDiry(0);
                    } else {
                        if (fantasmas.get(i).getEaten()){
                            fantasmas.get(i).setDirx(dx[count]);
                            //ghost_dy[i] = dy[count];
                            fantasmas.get(i).setDiry(dy[count]);
                        }
                        else{
                            fantasmas.get(i).setDirx(-fantasmas.get(i).getDirx());
                            fantasmas.get(i).setDiry(-fantasmas.get(i).getDiry());
                        }                        
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }
                    // Se guarda la dirección horizonta (x) actual del fantasma
                    // para saber si no ha cambiado desde la última vez
                    // esto permite que no se pierda la animación
                    currentDirX = fantasmas.get(i).getDirx();
                    //ghost_dx[i] = dx[count];
                    fantasmas.get(i).setDirx(dx[count]);
                    //ghost_dy[i] = dy[count];
                    fantasmas.get(i).setDiry(dy[count]);
                }

            }
            fantasmas.get(i).setPosx(fantasmas.get(i).getPosx() + (fantasmas.get(i).getDirx() * fantasmas.get(i).getSpeed()));
            fantasmas.get(i).setPosy(fantasmas.get(i).getPosy() + (fantasmas.get(i).getDiry() * fantasmas.get(i).getSpeed()));
            if (fantasmas.get(i).getDirx() != currentDirX && fantasmas.get(i).getDirx() != 0) { // se revisa si no ha cambiado de dirección o está detenido el fantasma
                if (fantasmas.get(i).getDirx() == -1) {  // Si la dirección es IZQ
                    switch (i) {
                        case 0:
                            fantasmas.get(i).setCurrentAnimation(new Animation(AnimationEnum.CLYDE_NORMAL_LEFT));
                            break;
                        case 1:
                            fantasmas.get(i).setCurrentAnimation(new Animation(AnimationEnum.BLINKY_NORMAL_LEFT));
                            break;
                        case 2:
                            fantasmas.get(i).setCurrentAnimation(new Animation(AnimationEnum.INKY_NORMAL_LEFT));
                            break;
                        case 3:
                            fantasmas.get(i).setCurrentAnimation(new Animation(AnimationEnum.PINKY_NORMAL_LEFT));
                            break;
                    }

                } else {
                    // si no es DER 
                    switch (i) {
                        case 0:
                            fantasmas.get(i).setCurrentAnimation(new Animation(AnimationEnum.CLYDE_NORMAL_RIGHT));
                            break;
                        case 1:
                            fantasmas.get(i).setCurrentAnimation(new Animation(AnimationEnum.BLINKY_NORMAL_RIGHT));
                            break;
                        case 2:
                            fantasmas.get(i).setCurrentAnimation(new Animation(AnimationEnum.INKY_NORMAL_RIGHT));
                            break;
                        case 3:
                            fantasmas.get(i).setCurrentAnimation(new Animation(AnimationEnum.PINKY_NORMAL_RIGHT));
                            break;
                    }

                }
            }
            if (fantasmas.get(i).getEaten() == false && eatingGhost == false) {
                drawGhost(g2d, i, fantasmas.get(i).getCurrentAnimation().getCurrentFrame());
            }
            if (fantasmas.get(i).getEaten() == true) {
                drawEatenGhost(g2d, i); // si el fantasma ya está comido
            }
            if (fantasmas.get(i).getEaten() == false && eatingGhost == true) {
                drawScaredGhost(g2d, i); // si el fantasma ya está comido
            }
            if (pacman.getPosx() > (fantasmas.get(i).getPosx() - 12) && pacman.getPosx() < (fantasmas.get(i).getPosx() + 12)
                    && pacman.getPosy() > (fantasmas.get(i).getPosy() - 12) && pacman.getPosy() < (fantasmas.get(i).getPosy() + 12)
                    && inGame && eatingGhost == false && fantasmas.get(i).getEaten() == false) {
                dying = true;
                pacman.setCurrentAnimation(new Animation(AnimationEnum.PACMAN_DIE));
            }
            if (pacman.getPosx() > (fantasmas.get(i).getPosx() - 12) && pacman.getPosx() < (fantasmas.get(i).getPosx() + 12)
                    && pacman.getPosy() > (fantasmas.get(i).getPosy() - 12) && pacman.getPosy() < (fantasmas.get(i).getPosy() + 12)
                    && inGame && eatingGhost == true && fantasmas.get(i).getEaten() == false) {

                Sound.GHOST_SCARED.stop();
                Sound.GHOST_EATEN.play();
                whatEatGhost = i;
                fantasmas.get(i).setEaten(true);
                fantasmas.get(i).setPath(screenData, fantasmas.get(i).getPosx() / BLOCK_SIZE, fantasmas.get(i).getPosy() / BLOCK_SIZE, 14, 0, N_BLOCKS);

                //  pos = fantasmas.get(i).getPosx() / BLOCK_SIZE + N_BLOCKS * (int) (fantasmas.get(i).getPosy() / BLOCK_SIZE);
                //SearchPath.depthPath(screenData, fantasmas.get(i).getPosx() / BLOCK_SIZE, fantasmas.get(i).getPosy() / BLOCK_SIZE, 14,0, path, path2, path3, N_BLOCKS);
                //System.out.println(path);
                //System.out.println(path2);
                //System.out.println(path3);
                Sound.GHOST_SCARED.loop();
                score = score + acumPointsEat;
                fantasmas.get(i).setVisible(false);
            }
        }

    }

    private void drawGhost(Graphics2D g2d, int i, int frame) {
        // para dibujar el fantasma se toma la imagen del objeto
        g2d.drawImage(fantasmas.get(i).getCurrentAnimation().getImages()[frame], fantasmas.get(i).getPosx(), fantasmas.get(i).getPosy(), this);
        //g2d.drawImage(fantasmas.get(i).getImages()[frame], fantasmas.get(i).getPosx(), fantasmas.get(i).getPosy(), this);  
        //g2d.drawImage(ghost, x, y, this);
    }

    private void drawEatenGhost(Graphics2D g2d, int i) {
        // para dibujar el fantasma se toma la imagen del objeto
        if (fantasmas.get(i).getVisible()) {
            g2d.drawImage(ghostEyes, fantasmas.get(i).getPosx(), fantasmas.get(i).getPosy(), this);
        }
        //g2d.drawImage(ghost, x, y, this);
    }

    private void drawScaredGhost(Graphics2D g2d, int i) {
        // para dibujar el fantasma se toma la imagen del objeto
        //if (fantasmas.get(i).getVisible()){
        g2d.drawImage(ghostScared, fantasmas.get(i).getPosx(), fantasmas.get(i).getPosy(), this);
        //}
        //g2d.drawImage(ghost, x, y, this);
    }

    private void movePacman() {

        pacman.update();
        int pos;
        short ch;
        boolean flag = false;
        if (req_dx == -pacman.getDirx() && req_dy == -pacman.getDiry()) {
            // checar si cambio la direccion
            dirChanged = (req_dx != pacman.getDirx() || req_dy != pacman.getDiry());
            flag = true;
            pacman.setDirx(req_dx);
            pacman.setDiry(req_dy);
            view_dx = pacman.getDirx();
            view_dy = pacman.getDiry();
        }

        if (pacman.getPosx() % BLOCK_SIZE == 0 && pacman.getPosy() % BLOCK_SIZE == 0) {
            pos = pacman.getPosx() / BLOCK_SIZE + N_BLOCKS * (int) (pacman.getPosy() / BLOCK_SIZE);
            ch = screenData[pos];

            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);
                score++;
                Sound.PACMAN_MUNCH.play();
            }
            // si se come el cuadro de super poder
            if ((ch & 32) != 0) {
                screenData[pos] = (short) (ch & 15);
                score = score + 10;
                superPacmanCount = 0;
                eatingGhost = true;
                acumPointsEat = 200;
                Sound.GHOST_SCARED.loop();
            }

            // Si se come una fruta
            if ((ch & 64) != 0) {
                screenData[pos] = (short) (ch & 15);
                score += 200;
                Sound.PACMAN_MUNCH.play();
                // TODO: Poner sonido cuando se come una fruta
            }

            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    // checar si cambio la direccion
                    if (!flag) {
                        dirChanged = ((req_dx != pacman.getDirx() || req_dy != pacman.getDiry()));
                    }
                    pacman.setDirx(req_dx);
                    pacman.setDiry(req_dy);
                    view_dx = pacman.getDirx();
                    view_dy = pacman.getDiry();
                }
            }

            // Check for standstill
            if ((pacman.getDirx() == -1 && pacman.getDiry() == 0 && (ch & 1) != 0)
                    || (pacman.getDirx() == 1 && pacman.getDiry() == 0 && (ch & 4) != 0)
                    || (pacman.getDirx() == 0 && pacman.getDiry() == -1 && (ch & 2) != 0)
                    || (pacman.getDirx() == 0 && pacman.getDiry() == 1 && (ch & 8) != 0)
                    || (pacman.getDirx() == -1 && pacman.getDiry() == 0 && (ch & 1) != 0)) {
                pacman.setDirx(0);
                pacman.setDiry(0);
            }
        }
        pacman.setPosx(pacman.getPosx() + PACMAN_SPEED * pacman.getDirx());
        pacman.setPosy(pacman.getPosy() + PACMAN_SPEED * pacman.getDiry());
    }

    // dibujar a pacman
    private void drawPacman(Graphics2D g2d) {
        //TODO: Recordar el frame en que se quedo la animacion
        if (whatEatGhost == -1) {
            if (dirChanged) {
                int frame = pacman.getCurrentAnimation().getCurrentFrame();

                if (view_dx == -1) {
                    pacman.setCurrentAnimation(new Animation(AnimationEnum.PACMAN_NORMAL_LEFT));
                } else if (view_dx == 1) {
                    pacman.setCurrentAnimation(new Animation(AnimationEnum.PACMAN_NORMAL_RIGHT));
                } else if (view_dy == -1) {
                    pacman.setCurrentAnimation(new Animation(AnimationEnum.PACMAN_NORMAL_UP));
                } else {
                    pacman.setCurrentAnimation(new Animation(AnimationEnum.PACMAN_NORMAL_DOWN));
                }
            }
            g2d.drawImage(pacman.getCurrentAnimation().getImages()[pacman.getCurrentAnimation().getCurrentFrame()],
                    pacman.getPosx() + 1, pacman.getPosy() + 1, this);
        } else {
            if (pacmanAnimPoints <= 6) {
                pacmanAnimPoints++;
                g2d.drawString(String.valueOf(acumPointsEat), pacman.getPosx() + 2, pacman.getPosy() + 14);
            } else {
                pacmanAnimPoints = 0;
                timer.stop();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                }
                timer.start();
                fantasmas.get(whatEatGhost).setVisible(true);
                whatEatGhost = -1;
                acumPointsEat = acumPointsEat + POINTS_EAT_GHOST;
                Sound.GHOST_SCARED.loop();
            }

        }
    }

    // este método hace la animación de cuando Pacman muere
    private void drawPacmanDie(Graphics2D g2d) {
        g2d.drawImage(pacman.getCurrentAnimation().getImages()[pacman.getCurrentAnimation().getCurrentFrame()], pacman.getPosx() + 1, pacman.getPosy() + 1, this);
    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(mazeColor);
                g2d.setStroke(new BasicStroke(5));

                if ((screenData[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) {
                    g2d.setColor(dotColor);
                    g2d.fillRect(x + 11, y + 11, 2, 2);
                }

                // Dibuja el cuadro de poder de super pacman
                if ((screenData[i] & 32) != 0) {
                    g2d.setColor(dotColor);
                    g2d.fillRect(x + 5, y + 5, 8, 8);
                }
                // Dibuja el cuadro de poder de super pacman
                if ((screenData[i] & 64) != 0) {
                    //g2d.setColor(dotColor);
                    //g2d.fillRect(x + 2, y + 2, 12, 12);
                    g2d.drawImage(fruit, x + 3, y + 3, this);
                }

                i++;
            }
        }
    }

    private void initGame() {
        pacman.setHealth(3);
        score = 0;
        initLevel();
        N_GHOSTS = 4;
        currentSpeed = 3;
    }

    private void initLevel() {

        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData2[i];
        }

        continueLevel();
    }

    private void continueLevel() {
//      short i;
        int dx = 1;
        int random;
        // se configuran los fantasmas del array list
        for (Fantasma oFantasma : fantasmas) {
            // Ubicación Spawn
            oFantasma.setPosx(4 * BLOCK_SIZE);
            oFantasma.setPosy(4 * BLOCK_SIZE);
            oFantasma.setDiry(0);
            oFantasma.setDirx(dx);
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));
            if (random > currentSpeed) {
                random = currentSpeed;
            }
            oFantasma.setSpeed(validSpeeds[random]);
            oFantasma.setEaten(false);
        }

        pacman.setPosx(7 * BLOCK_SIZE);
        pacman.setPosy(10 * BLOCK_SIZE);
        pacman.setDirx(0);
        pacman.setDiry(0);
        req_dx = 0;
        req_dy = 0;
        view_dx = -1;
        view_dy = 0;
        dying = false;
        eatingGhost = false; // indica si pacman puede comer fantasmas
        superPacmanCount = 0;
        Sound.GHOST_SCARED.stop();
    }

    private void loadImages() {
        ghostEyes = new ImageIcon("images/ghost eyes.png").getImage();
        ghostScared = new ImageIcon("images/ghost scared.png").getImage();
        fruit = new ImageIcon("images/ceresa.png").getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        drawScore(g2d);

        if (inGame) {
            playGame(g2d);
        } else {
            Sound.SIREN.stop();
            showIntroScreen(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (inGame) {
                if (key == KeyEvent.VK_LEFT) {
                    req_dx = -1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    req_dx = 1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_UP) {
                    req_dx = 0;
                    req_dy = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    req_dx = 0;
                    req_dy = 1;
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    inGame = false;
                } else if (key == KeyEvent.VK_PAUSE) {
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                }
            } else {
                if (key == 's' || key == 'S') {
                    //Sound.PACMAN_BEGINNING.play();
                    inGame = true;
                    initGame();
                    Sound.SIREN.loop(); // inicia a sonar la sirena del juego
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == Event.LEFT || key == Event.RIGHT
                    || key == Event.UP || key == Event.DOWN) {
                req_dx = 0;
                req_dy = 0;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }
}

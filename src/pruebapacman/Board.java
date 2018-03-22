package pruebapacman;

/**
 *
 * Esta clase permite generar el objeto Tablero del juego Probando
 *
 * @author Juan Ernesto
 */
import clases.Fantasma;
import clases.Sound;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import jssc.SerialPortEventListener;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

public class Board extends JPanel implements ActionListener {

    private Dimension d;
    private final Font smallFont = new Font("Helvetica", Font.BOLD, 14);

    private final Color dotColor = new Color(255, 255, 255);
    private Color mazeColor;

    private boolean inGame = false;
    private boolean dying = false;

    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int PAC_ANIM_DELAY = 1;  // la velocidad de la animación de pacman
    private final int PACMAN_ANIM_COUNT = 4;
    private final int MAX_GHOSTS = 7;
    private final int PACMAN_SPEED = 6;

    public int puntos;

    private int pacAnimCount = PAC_ANIM_DELAY;
    private int pacAnimDir = 1;
    private int pacmanAnimPos = 0;
    private int pacmanAnimPosDie = 0; // para animar cuando pacman muere
    private int N_GHOSTS = 4;
    private int pacsLeft;
    private int[] dx, dy;
    public int score;
    private ArrayList<Fantasma> fantasmas = new ArrayList<Fantasma>(); // array list para crear los objetos Fantasma

    private Image ghost; //imagen png del fantasma
    private Image clydeGhost;
    private Image blinkyGhost;
    private Image inkyGhost;
    private Image pinkyGhost;
    private Image[] pacmanUp = new Image[3];
    private Image[] pacmanRight = new Image[3];
    private Image[] pacmanDown = new Image[3];
    private Image[] pacmanLeft = new Image[3];
    private Image[] pacmanLeftDie = new Image[6];
    private Image pacman1;

    public SerialPortEventListener listener;

    // Descripción de la variables:
    // pacman_x, pacman_y son las posiciones en los dos ejes
    private int pacman_x, pacman_y, pacmand_x, pacmand_y;
    private int req_dx, req_dy, view_dx, view_dy;
    // con bolita
    // 19 (10011) = ┌*
    // 22 (10110) = *┐
    // 25 (11001) = └*
    // 28 (11100) = *┘

    // sin bolita
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
    private final short levelData[] = {
        51, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 54,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 24, 24, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 20, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 18, 18, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 32, 16, 20,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        57, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 60
    };

//        21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
//        21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
//        21, 0, 0, 0, 17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20,
//        17, 18, 18, 18, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 20,
//        17, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 16, 24, 20,
//        25, 16, 16, 16, 24, 24, 28, 0, 25, 24
//            ,
//        
//        24, 16, 20, 0, 21,
//        1, 17, 16, 20, 0, 0, 0, 0, 0, 0, 0, 17, 20, 0, 21,
//        1, 17, 16, 16, 18, 18, 22, 0, 19, 18, 18, 16, 20, 0, 21,
//        1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
//        1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
//        1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20, 0, 21,
//        1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 21,
//        1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,
//        9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 25, 24, 24, 24, 28
//    };
    private final int validSpeeds[] = {1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;

    private int currentSpeed = 3;
    private short[] screenData;
    private Timer timer;

    public Board() {

        loadImages();
        initVariables();
        initBoard();
        arduinoScoreSerial();
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
        d = new Dimension(400, 400); // esta variable solamente se usa para crear un rectangulo negro
        // aquí se agregan los objetos fantasma al array list
        fantasmas.add(new Fantasma(clydeGhost, this, "Clyde"));
        fantasmas.add(new Fantasma(blinkyGhost, this, "Blinky"));
        fantasmas.add(new Fantasma(pinkyGhost, this, "Pinky"));
        fantasmas.add(new Fantasma(inkyGhost, this, "Inky"));
        // esto agregará los fantasmas restantes según el nivel de dificultad
        // hasta llegar al máximo de fantasmas
        for (int i = 5; i <= MAX_GHOSTS; i++) {
            fantasmas.add(new Fantasma(ghost, this, "Fantasma X"));
        }
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

    private void doAnim() {

        pacAnimCount--;

        if (pacAnimCount <= 0) {
            pacAnimCount = PAC_ANIM_DELAY;
            pacmanAnimPos = pacmanAnimPos + pacAnimDir;

            if (pacmanAnimPos == (PACMAN_ANIM_COUNT - 1) || pacmanAnimPos == 0) {
                pacAnimDir = -pacAnimDir;
            }
        }
    }

    private void playGame(Graphics2D g2d) {

        if (dying) {
            if (pacmanAnimPosDie == 0) {
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
            pacmanAnimPosDie++;
            drawPacmanDie(g2d);
            // timer.wait(1000);
            if (pacmanAnimPosDie == 5) {
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

            movePacman();
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

        for (i = 0; i < pacsLeft; i++) {
            g.drawImage(pacmanLeft[1], i * 28 + 8, SCREEN_SIZE + 1, this);
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

        pacsLeft--;
        pacmanAnimPosDie = 0;
        if (pacsLeft == 0) {
            inGame = false;
            Sound.SIREN.stop();
        }

        continueLevel();
    }

    private void moveGhosts(Graphics2D g2d) {

        short i;
        int pos;
        int count;

//        for (i = 0; i < N_GHOSTS; i++) {
//            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
//                pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);
//
//                count = 0;
//
//                if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) {
//                    dx[count] = -1;
//                    dy[count] = 0;
//                    count++;
//                }
//
//                if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) {
//                    dx[count] = 0;
//                    dy[count] = -1;
//                    count++;
//                }
//
//                if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) {
//                    dx[count] = 1;
//                    dy[count] = 0;
//                    count++;
//                }
//
//                if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) {
//                    dx[count] = 0;
//                    dy[count] = 1;
//                    count++;
//                }
//
//                if (count == 0) {
//
//                    if ((screenData[pos] & 15) == 15) {
//                        ghost_dx[i] = 0;
//                        ghost_dy[i] = 0;
//                    } else {
//                        ghost_dx[i] = -ghost_dx[i];
//                        ghost_dy[i] = -ghost_dy[i];
//                    }
//
//                } else {
//
//                    count = (int) (Math.random() * count);
//
//                    if (count > 3) {
//                        count = 3;
//                    }
//
//                    ghost_dx[i] = dx[count];
//                    ghost_dy[i] = dy[count];
//                }
//
//            }
//
//            ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
//            ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);
//            drawGhost(g2d, ghost_x[i] + 1, ghost_y[i] + 1, i);
//
//            if (pacman_x > (ghost_x[i] - 12) && pacman_x < (ghost_x[i] + 12)
//                    && pacman_y > (ghost_y[i] - 12) && pacman_y < (ghost_y[i] + 12)
//                    && inGame) {
//
//                dying = true;
//            }
//        }
        for (i = 0; i < N_GHOSTS; i++) {
            if (fantasmas.get(i).getPosx() % BLOCK_SIZE == 0 && fantasmas.get(i).getPosy() % BLOCK_SIZE == 0) {
                pos = fantasmas.get(i).getPosx() / BLOCK_SIZE + N_BLOCKS * (int) (fantasmas.get(i).getPosy() / BLOCK_SIZE);

                count = 0;

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

                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        //ghost_dx[i] = 0;
                        fantasmas.get(i).setDirx(0);
                        //ghost_dy[i] = 0;
                        fantasmas.get(i).setDiry(0);
                    } else {
                        //ghost_dx[i] = -fantasmas.get(i).getDirx();
                        fantasmas.get(i).setDirx(-fantasmas.get(i).getDirx());
                        //ghost_dy[i] = -ghost_dy[i];
                        fantasmas.get(i).setDiry(-fantasmas.get(i).getDiry());
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    //ghost_dx[i] = dx[count];
                    fantasmas.get(i).setDirx(dx[count]);
                    //ghost_dy[i] = dy[count];
                    fantasmas.get(i).setDiry(dy[count]);
                }

            }

            //ghost_x[i] = fantasmas.get(i).getPosx() + (ghost_dx[i] * fantasmas.get(i).getSpeed());
            fantasmas.get(i).setPosx(fantasmas.get(i).getPosx() + (fantasmas.get(i).getDirx() * fantasmas.get(i).getSpeed()));
            //ghost_y[i] = fantasmas.get(i).getPosy() + (ghost_dy[i] * fantasmas.get(i).getSpeed());
            fantasmas.get(i).setPosy(fantasmas.get(i).getPosy() + (fantasmas.get(i).getDiry() * fantasmas.get(i).getSpeed()));

            //drawGhost(g2d, fantasmas.get(i).getPosx() + 1, fantasmas.get(i).getPosy() + 1, i);
            drawGhost(g2d, i);

            if (pacman_x > (fantasmas.get(i).getPosx() - 12) && pacman_x < (fantasmas.get(i).getPosx() + 12)
                    && pacman_y > (fantasmas.get(i).getPosy() - 12) && pacman_y < (fantasmas.get(i).getPosy() + 12)
                    && inGame) {

                dying = true;
            }
        }

    }

    private void drawGhost(Graphics2D g2d, int i) {
        // para dibujar el fantasma se toma la imagen del objeto
        g2d.drawImage(fantasmas.get(i).getImage(), fantasmas.get(i).getPosx(), fantasmas.get(i).getPosy(), this);
        //g2d.drawImage(ghost, x, y, this);
    }

    private void movePacman() {

        int pos;
        short ch;

        if (req_dx == -pacmand_x && req_dy == -pacmand_y) {
            pacmand_x = req_dx;
            pacmand_y = req_dy;
            view_dx = pacmand_x;
            view_dy = pacmand_y;
        }

        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE);
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
            }

            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                    view_dx = pacmand_x;
                    view_dy = pacmand_y;
                }
            }

            // Check for standstill
            if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0)
                    || (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0)
                    || (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0)
                    || (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        }
        pacman_x = pacman_x + PACMAN_SPEED * pacmand_x;
        pacman_y = pacman_y + PACMAN_SPEED * pacmand_y;
    }

    private void drawPacman(Graphics2D g2d) {
        Image pacmanImgs[];
        if (view_dx == -1) {
            pacmanImgs = pacmanLeft;
        } else if (view_dx == 1) {
            pacmanImgs = pacmanRight;
        } else if (view_dy == -1) {
            pacmanImgs = pacmanUp;
        } else {
            pacmanImgs = pacmanDown;
        }

        Image img = pacmanAnimPos == 0 ? pacman1 : pacmanImgs[pacmanAnimPos - 1];
        g2d.drawImage(img, pacman_x + 1, pacman_y + 1, this);
    }

    // este método hace la animación de cuando Pacman muere
    private void drawPacmanDie(Graphics2D g2d) {
        Image pacmanImgs[];
        if (view_dx == -1) {
            pacmanImgs = pacmanLeftDie;
        } else if (view_dx == 1) {
            pacmanImgs = pacmanLeftDie;
        } else if (view_dy == -1) {
            pacmanImgs = pacmanLeftDie;
        } else {
            pacmanImgs = pacmanLeftDie;
        }

        Image img = pacmanImgs[pacmanAnimPosDie];
        g2d.drawImage(img, pacman_x + 1, pacman_y + 1, this);
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

                i++;
            }
        }
    }

    private void initGame() {
        pacsLeft = 3;
        score = 0;
        initLevel();
        N_GHOSTS = 4;
        currentSpeed = 3;
    }

    private void initLevel() {

        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }

        continueLevel();
    }

    private void continueLevel() {
//      short i;
        int dx = 1;
        int random;
        // se configuran los fantasmas del array list
        for (Fantasma oFantasma : fantasmas) {
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
        }

//        for (i = 0; i < N_GHOSTS; i++) {
//            ghost_y[i] = 4 * BLOCK_SIZE;
//            ghost_x[i] = 4 * BLOCK_SIZE;
//            ghost_dy[i] = 0;
//            ghost_dx[i] = dx;
//            dx = -dx;
//            random = (int) (Math.random() * (currentSpeed + 1));
//
//            if (random > currentSpeed) {
//                random = currentSpeed;
//            }
//
//            ghostSpeed[i] = validSpeeds[random];
//        }
        pacman_x = 7 * BLOCK_SIZE;
        pacman_y = 11 * BLOCK_SIZE;
        pacmand_x = 0;
        pacmand_y = 0;
        req_dx = 0;
        req_dy = 0;
        view_dx = -1;
        view_dy = 0;
        dying = false;
    }

    private void loadImages() {
        ghost = new ImageIcon("images/ghost.png").getImage();
        clydeGhost = new ImageIcon("images/clyde v1.png").getImage(); // carga la imagen para este tipo de fastasma
        blinkyGhost = new ImageIcon("images/blinky v1.png").getImage();
        inkyGhost = new ImageIcon("images/inky v1.png").getImage();
        pinkyGhost = new ImageIcon("images/pinky v1.png").getImage();
        pacman1 = new ImageIcon("images/pacman.png").getImage();

        for (int i = 0; i < 3; i++) {
            int n = i + 1;
            pacmanUp[i] = new ImageIcon("images/up" + n + ".png").getImage();
            pacmanRight[i] = new ImageIcon("images/right" + n + ".png").getImage();
            pacmanDown[i] = new ImageIcon("images/down" + n + ".png").getImage();
            pacmanLeft[i] = new ImageIcon("images/left" + n + ".png").getImage();
        }

        for (int i = 0; i < 6; i++) {
            int n = i + 1;
            pacmanLeftDie[i] = new ImageIcon("images/leftdie" + n + ".png").getImage();
        }

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
        doAnim();

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
                    Sound.PACMAN_BEGINNING.play();
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

    public void arduinoScoreSerial() {

        puntos = score;

        PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
        this.listener = new SerialPortEventListener() {
            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                try {
                    if (ino.isMessageAvailable()) {
                        ino.sendByte(puntos);
                    }
                } catch (SerialPortException ex) {
                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ArduinoException ex) {
                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        ;
        };
        
         try {
            ino.arduinoRXTX("COM4", 9600, listener);
        } catch (ArduinoException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (puntos >= 0) {
            try {
                ino.sendByte(puntos);
            } catch (ArduinoException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SerialPortException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

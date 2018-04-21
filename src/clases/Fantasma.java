package clases;

import java.util.ArrayList;
import java.util.List;
import pruebapacman.Board;

/**
 *
 * @author mac
 */
public class Fantasma extends Personaje{
    private boolean eaten;
    //private int [] path;
    private List<Integer> path = new ArrayList<Integer>();
    private final List<Integer> path2 = new ArrayList<Integer>();
    private final List<Integer> path3 = new ArrayList<Integer>();
    private int idPos;

    public Fantasma(Animation animation, Board tablero, String name) {
        super(animation, tablero, name);
        this.eaten = false;
        this.idPos = -1;
    }

    public void setEaten(boolean eaten){
        this.eaten = eaten;
    }
    public void setPath(short [] maze, int x, int y, int xdest, int ydest, int N_BLOCKS){
        SearchPath.depthPath(maze,x, y, xdest,ydest, path2, path3, this.path, N_BLOCKS);
        this.idPos = this.path.size() - 1;
        //System.out.println(this.path);
    }
    public boolean getEaten(){
        return this.eaten;
    }
    public List<Integer> getNextMove(){
        List<Integer> xymove = new ArrayList<Integer>();
        if (idPos >= 1){
            xymove.add(this.path.get(idPos - 1));
            xymove.add(this.path.get(idPos));
            this.idPos -= 2;
        }
        else
        {
            idPos = -1;
            this.path.clear();
        }
        return xymove;
    }
                  
}

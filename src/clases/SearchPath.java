/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.List;
/**
 *
 * @author mac
 */
public class SearchPath {
    public static boolean depthPath(short [] maze, int x, int y, int xdest, int ydest, List<Integer> path, List<Integer> path2, List<Integer> path3, int N_BLOCKS) {
        if (x < 0 || y < 0) {
            return false;
        }

        if (x > (N_BLOCKS-1) || y > (N_BLOCKS-1)) {
            return false;
        }
        int pos = (y * N_BLOCKS) + x;
        //short ch = maze[pos];

        if (y == ydest && x == xdest) {
            path.add(x);
            path.add(y);
            path2.add(pos);
            path.add(0);
            path.add(0);
            return true;
        }
        // fue visitado la posición
        if ((maze[pos] & 128) == 0) {
            maze[pos] = (short) (maze[pos] | 128);

            int dx = 0;
            int dy = 0;
            if ((maze[pos] & 1) == 0) {
                dx = -1;
                dy = 0;
                if (depthPath(maze, x + dx, y + dy, xdest, ydest, path, path2, path3, N_BLOCKS)) {
                    path.add(x);
                    path.add(y);
                    path2.add(pos);
                    path3.add(dx);
                    path3.add(dy);
                    return true;
                }
            }

            if ((maze[pos] & 4) == 0) {
                dx = 1;
                dy = 0;
                if (depthPath(maze, x + dx, y + dy,xdest, ydest, path, path2 , path3, N_BLOCKS)) {
                    path.add(x);
                    path.add(y);
                    path2.add(pos);
                    path3.add(dx);
                    path3.add(dy);
                    return true;
                }
            }

            if ((maze[pos] & 2) == 0) {
                dx = 0;
                dy = -1;
                if (depthPath(maze, x + dx, y + dy, xdest, ydest, path, path2 , path3, N_BLOCKS)) {
                    path.add(x);
                    path.add(y);
                    path2.add(pos);
                    path3.add(dx);
                    path3.add(dy);
                    return true;
                }
            }

            if ((maze[pos] & 8) == 0) {
                dx = 0;
                dy = 1;
                if (depthPath(maze, x + dx, y + dy, xdest, ydest, path, path2 , path3, N_BLOCKS)) {
                    path.add(x);
                    path.add(y);
                    path2.add(pos);
                    path3.add(dx);
                    path3.add(dy);
                    return true;
                }

            }

        }
        return false;
    }
}

package Tank.main.pojo.zhangai;

import java.util.Vector;

/**
 * @author 林桂平
 */
public class Wall implements  Runnable{
    private int x;
    private int y;
    private int row;
    private int col;
    private Vector<Clay> clays=new Vector<>();

    public Wall(int x, int y, int row, int col) {
        this.x = x;
        this.y = y;
        this.row = row;
        this.col = col;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                clays.add(new Clay(x+15*i,y+15*j));

            }
        }
        for (int i = 0; i < clays.size(); i++) {
            new Thread(clays.get(i)).start();
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Vector<Clay> getClays() {
        return clays;
    }

    public void setClays(Vector<Clay> clays) {
        this.clays = clays;
    }

    @Override
    public void run() {

    }
}

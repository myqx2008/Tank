package Tank.main.pojo.tank;

import Tank.main.pojo.Shot;

import java.awt.*;
import java.io.Serializable;
import java.util.Vector;

/**
 * @author 林桂平
 */
@SuppressWarnings({"all"})
public class Tank implements Serializable {

    private static final long serialVersionUID=1L;
    private int x;
    private int y;
    private int state;
    private Color color;
    private boolean live = true;
    private Vector<Shot> shots=new Vector();

    public void putShot() {
        Shot shot = new Shot();
        if (getState() == 0) {

            shot.setX(getX() + 18);
            shot.setY(getY() - 4);
            shot.setState(0);
        } else if (getState() == 1) {

            shot.setX(getX() + 18);
            shot.setY(getY() + 60);
            shot.setState(1);
        } else if (getState() == 2) {

            shot.setX(getX() + 60);
            shot.setY(getY() + 18);
            shot.setState(2);
        } else if (getState() == 3) {

            shot.setX(getX() - 4);
            shot.setY(getY() + 18);
            shot.setState(3);
        }
        shots.add(shot);
        new Thread(shot).start();
    }

    public Vector<Shot> getShots() {
        return shots;
    }

    public void setShots(Vector<Shot> shots) {
        this.shots = shots;
    }


    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }


    public Tank(int x, int y, int state, Color color) {
        this.x = x;
        this.y = y;
        this.state = state;
        this.color = color;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}

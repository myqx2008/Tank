package Tank.main.pojo;

import java.io.Serializable;

/**
 * @author 林桂平
 */
public class Shot implements Runnable, Serializable {
    private int x;
    private int y;
    private int state;
    private boolean ShotLive=true;

    public boolean isShotLive() {
        return ShotLive;
    }

    public void setShotLive(boolean shotLive) {
        ShotLive = shotLive;
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

    @Override
    public void run() {
        //子弹速度和监视存货
        while (true) {
            if(!ShotLive){
                break;
            }
            if (x < 50 || x > 1100 || y < 20 || y > 850) {

                ShotLive=false;
                break;
            }
            if (getState() == 0) {
                this.y -= 10;
            } else if (getState() == 1) {
                this.y += 10;
            } else if (getState() == 2) {
                this.x += 10;
            } else if (getState() == 3) {
                this.x -= 10;
            }  else {
                break;
            }
            System.out.println(Thread.currentThread().getName()+"子弹现场工作中");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName()+"子弹线程结束");
    }
}

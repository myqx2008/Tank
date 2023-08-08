package Tank.main.pojo.zhangai;

/**
 * @author 林桂平
 */
//障碍物土
public class Clay implements Runnable{
    private int x;
    private int y;
    private boolean live=true;
    public Clay(int x, int y) {
        this.x = x;
        this.y = y;
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

    @Override
    public void run() {

    }
}

package Tank.main.pojo;

import java.time.LocalDateTime;

/**
 * @author 林桂平
 */
//爆炸特效
@SuppressWarnings({"all"})
public class Ikun implements Runnable{
    private int x;
    private int y;
    private int oldTime=2023;
    private int newTime;
    private boolean live=true;
    private static int kill=0;

    public static int getKill() {
        return kill;
    }

    public static void setKill(int kill) {
        Ikun.kill = kill;
    }

    public Ikun(int x, int y) {
        this.x = x;
        this.y = y;
        kill++;
        oldTime= LocalDateTime.now().getSecond();
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

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+"敌人坦克爆炸在工作");
            if(oldTime!=2023){
                newTime=LocalDateTime.now().getSecond();
                if(newTime-oldTime>2){
                    oldTime=2023;
                    live=false;
                    break;
                }
            }
        }
        System.out.println(Thread.currentThread().getName()+"敌人坦克爆炸结束工作");
    }
}

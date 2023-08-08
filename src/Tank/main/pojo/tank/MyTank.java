package Tank.main.pojo.tank;

import java.awt.*;


/**
 * @author 林桂平
 */
@SuppressWarnings({"all"})
public class MyTank extends Tank implements Runnable{


    public MyTank(int x, int y, int state, Color color) {
        super(x, y, state, color);
    }
    private boolean live1=true;

    public boolean isLive1() {
        return live1;
    }

    public void setLive1(boolean live1) {
        this.live1 = live1;
    }

    @Override
    public String toString() {
        return "MyTank{" +
                "live1=" + live1 +
                '}';
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+"我的坦克在工作");



            //
            if(getY()<=20){
                setY(20);
            }

            if(getY()>=700){
                setY(700);
            }

            if(getX()>=1000){
                setX(1000);
            }
            if(getX()<56){
                setX(56);
            }
            //我的坦克阵亡
            if(!(isLive())){
                break;
            }
            //敌人全部坦克阵亡
            if(!(isLive1())){
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName()+"我的坦克结束工作");
    }
}

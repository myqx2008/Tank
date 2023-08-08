package Tank.main.pojo.tank;

import java.awt.*;

/**
 * @author 林桂平
 */
//敌人坦克
@SuppressWarnings({"all"})
public class EnemyTank extends Tank implements Runnable {

    private boolean change=true;

    public boolean isChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }

    public EnemyTank(int x, int y, int state, Color color) {
        super(x, y, state, color);

    }

    @Override
    public void run() {
        int x=0;
        int y=0;
        while(true){

            if(!(isLive())){

                break;
            }
            //50*10ms改变一次方向
            if(x==30){

                setState((int)(Math.random()*4));

                x=0;
                setChange(true);
            }
            if(y==10){
                putShot();
                y=0;
            }
            //50ms根据方向改变距离，并且会监视坐标是否越界
            if(change){
                switch (getState()){
                    case 0:
                        setY(getY()-2);
                        if(getY()<=26){
                            setY(26);
                        }
                        break;
                    case 1:
                        setY(this.getY()+2);
                        if(getY()>=864){
                            setY(864);
                        }
                        break;
                    case 2:
                        setX(getX()+2);
                        if(getX()>=1096){
                            setX(1096);
                        }
                        break;
                    case 3:
                        setX(getX()-2);
                        if(getX()<56){
                            setX(56);
                        }
                }
            }




            x++;
            y++;
            //50ms
            try {
                Thread.currentThread().sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+"敌人坦克在工作");
        }
        System.out.println(Thread.currentThread().getName()+"敌人坦克结束");
    }
}

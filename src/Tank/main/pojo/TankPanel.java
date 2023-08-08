package Tank.main.pojo;

import Tank.main.pojo.tank.EnemyTank;
import Tank.main.pojo.tank.MyTank;
import Tank.main.pojo.tank.Tank;
import Tank.main.pojo.zhangai.Clay;
import Tank.main.pojo.zhangai.River;
import Tank.main.pojo.zhangai.Steel;
import Tank.main.pojo.zhangai.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Vector;

/**
 * @author 林桂平
 */
@SuppressWarnings({"all"})
public class TankPanel extends JPanel implements KeyListener,Runnable {

    private River river;
    private River river2;
    private River river3;
    private Steel steel;
    private Steel steel2;
    private Steel steel3;
    private Steel steel4;
    private Wall wall;
    private Vector<Ikun> ikuns=new Vector<>();

    private MyTank myTank ;
    private  Vector<EnemyTank> enemyTranks=new Vector();
    private int enemyTranksSize=5;
    private static int speed=5;
    private static boolean loop=true;
    int kil=0;
    public int getEnemyTranksSize() {
        return enemyTranksSize;
    }

    public void setEnemyTranksSize(int enemyTranksSize) {
        this.enemyTranksSize = enemyTranksSize;
    }

    public MyTank getMyTank() {
        return myTank;
    }

    public void setMyTank(MyTank myTank) {
        this.myTank = myTank;
    }


    //初始化信息
    public void Initialize(){
        river=new River(150,320,700,100);
        steel=new Steel(150,120,700,100);

        wall=new Wall(150,520,40,6);
        setMyTank(new MyTank(1000,700,2,Color.cyan));
        new Thread(getMyTank()).start();
        for (int i = 0; i < getEnemyTranks().size(); i++) {
            enemyTranks.get(i).setLive(false);
        }
        enemyTranks.clear();
        EnemyTank tank1=new EnemyTank(55,25,1,Color.yellow);
        EnemyTank tank2=new EnemyTank(955,25,1,Color.yellow);
        EnemyTank tank3=new EnemyTank(255,240,2,Color.yellow);
        EnemyTank tank4=new EnemyTank(455,240,3,Color.yellow);
        EnemyTank tank5=new EnemyTank(155,440,2,Color.yellow);
        enemyTranks.add(tank1);
        enemyTranks.add(tank2);
        enemyTranks.add(tank3);
        enemyTranks.add(tank4);
        enemyTranks.add(tank5);
        new Thread(tank1).start();
        new Thread(tank2).start();
        new Thread(tank3).start();
        new Thread(tank5).start();
        new Thread(tank4).start();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //等待键盘输入命令执行不同操作
            if(loop){
                g.setFont(new Font("黑体",Font.HANGING_BASELINE,25));
                g.drawString("l键继续上一局游戏，n键开始下一局游戏",200,300);
            }
            else {
                if(myTank.isLive()){
                        g.fillRect(50,20,1000,750);
                        drawTank(myTank,g);
                        drawRiver(river,g);
                        drawSteel(steel,g);
                        drawWall(wall,g);
                        //建立敌人坦克
                        for (int i = 0; i < enemyTranks.size(); i++) {
                            drawTank(enemyTranks.get(i),g);
                            drawShots(enemyTranks.get(i),g);
                            if(!(enemyTranks.get(i).isLive())){
                                enemyTranks.remove(i);
                            }
                        }
                        g.setColor(Color.red);
                        //画我的坦克的子弹
                        for (int i = 0; i < myTank.getShots().size(); i++) {
                            drawShots(myTank,g);
                            if(!(myTank.getShots().get(i).isShotLive())){
                                myTank.getShots().remove(i);
                            }
                        }
                        //画ikun
                        g.setFont(new Font("宋体",Font.BOLD,10));
                        for (int i = 0; i < ikuns.size(); i++) {
                            drawIkun(ikuns.get(i),g);
                            if(!(ikuns.get(i).isLive())){
                                ikuns.remove(i);
                            }
                        }
                        //记录信息
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("黑体",Font.BOLD,30));
                        g.drawString("你当了"+kil*2.5+"年练习生",1050,50);
                        g.setFont(new Font("黑体",Font.HANGING_BASELINE,35));
                        g.drawString("Q键可以暂停练习哦baby",1050,400);
                        g.setFont(new Font("黑体",Font.BOLD,40));
                        g.drawString("×"+kil,1150,120);
                        drawTank(new Tank(1080,80,0,Color.yellow),g);
                     if(enemyTranks.size()==0){
                         g.setFont(new Font("黑体",Font.HANGING_BASELINE,25));
                         g.drawString("你是一个真正的man，n键继续练习",200,300);
                         g.drawString("退出游戏请在m键后点×",200,350);
                    }
                }
                else{
                    for (int i = 0; i <enemyTranks.size() ; i++) {
                        enemyTranks.get(i).setLive(false);
                    }
                    Image image=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/lose.PNG"));
                    g.drawImage(image, 100,50 , 800,600,this);
                    g.setFont(new Font("黑体",Font.HANGING_BASELINE,25));
                    g.drawString("下次再一起打球",200,700);

                }
            }
    }
    //绘画爆炸特效
    public void drawIkun(Ikun ikun, Graphics g){
        Image image=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/boom.jpg"));
        g.drawImage(image, ikun.getX(), ikun.getY(), 40,40,this);
        g.drawString("快要爆炸",ikun.getX(),ikun.getY()+50);//对应左下角
    }
    public void drawRiver(River river,Graphics g){
        g.setColor(Color.blue);
        g.fill3DRect(river.getX(),river.getY(),river.getRow(),river.getCol(),true);
    }
    public void drawSteel(Steel steel,Graphics g){
        g.setColor(Color.darkGray);
        g.fill3DRect(steel.getX(),steel.getY(),steel.getRow(),steel.getCol(),true);
    }
    public void drawWall(Wall wall,Graphics g){
        g.setColor(Color.ORANGE);
        Clay clay;
        for (int i = 0; i < wall.getClays().size(); i++) {
            clay=wall.getClays().get(i);
            g.fill3DRect(clay.getX(),clay.getY(),15,15,true);
        }

    }
    //根据传入的坦克绘画其模型
    public void drawTank(Tank tank,Graphics g){
        g.setColor(tank.getColor());
        switch (tank.getState()){
            case 0://上
                g.fill3DRect(tank.getX(),tank.getY(),10,60,false);
                g.fill3DRect(tank.getX()+30,tank.getY(),10,60,false);
                g.fill3DRect(tank.getX()+10,tank.getY()+10,20,40,false);
                g.fill3DRect(tank.getX()+18,tank.getY(),4,30,true);
                g.fillOval(tank.getX()+13,tank.getY()+23,14,14);
                break;
            case 1://下
                g.fill3DRect(tank.getX(),tank.getY(),10,60,false);
                g.fill3DRect(tank.getX()+30,tank.getY(),10,60,false);
                g.fill3DRect(tank.getX()+10,tank.getY()+10,20,40,false);
                g.fill3DRect(tank.getX()+18,tank.getY()+30,4,30,true);
                g.fillOval(tank.getX()+13,tank.getY()+23,14,14);
                break;
            case 2://右
                g.fill3DRect(tank.getX(),tank.getY(),60,10,false);
                g.fill3DRect(tank.getX(),tank.getY()+30,60,10,false);
                g.fill3DRect(tank.getX()+10,tank.getY()+10,40,20,false);
                g.fill3DRect(tank.getX()+30,tank.getY()+18,30,4,true);
                g.fillOval(tank.getX()+23,tank.getY()+13,14,14);
                break;
            case 3://左
                g.fill3DRect(tank.getX(),tank.getY(),60,10,false);
                g.fill3DRect(tank.getX(),tank.getY()+30,60,10,false);
                g.fill3DRect(tank.getX()+10,tank.getY()+10,40,20,false);
                g.fill3DRect(tank.getX(),tank.getY()+18,30,4,true);
                g.fillOval(tank.getX()+23,tank.getY()+13,14,14);
        }

    }

    public Vector<EnemyTank> getEnemyTranks() {
        return enemyTranks;
    }

    public void setEnemyTranks(Vector<EnemyTank> enemyTranks) {
        this.enemyTranks = enemyTranks;
    }


    //有字符输出
    @Override
    public void keyTyped(KeyEvent e) {

    }
    //按下
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S){
            myTank.setState(1);
            myTank.setY(myTank.getY()+speed);
        }else if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W){
            myTank.setState(0);
            myTank.setY(myTank.getY()-speed);
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D){
            myTank.setState(2);
            myTank.setX(myTank.getX()+speed);
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A){
            myTank.setState(3);
            myTank.setX(myTank.getX()-speed);
        }else if(e.getKeyCode()==KeyEvent.VK_J){
            myTank.putShot();}//发射子弹
        else if(e.getKeyCode()==KeyEvent.VK_N){
            Initialize();
            loop=false;
            kil=0;//重新初始化坦克信息和kil击杀数和loop信息
        }else if(e.getKeyCode()==KeyEvent.VK_L){
            try {
                read();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            loop=false;//读取保存的上一把信息
        }else if(e.getKeyCode()==KeyEvent.VK_Q){
            save();
            loop=true;//保存这一把游戏信息覆盖上一把游戏信息
        }else if(e.getKeyCode()==KeyEvent.VK_M){
            myTank.setLive(false);//胜利后退出游戏，关闭我的坦克线程
        }

        this.repaint();//重绘
    }
    public void drawShots(Tank tank,Graphics g){
        for (int i = 0; i < tank.getShots().size(); i++) {
            if(tank.getShots().get(i).isShotLive()){
            g.fillOval(tank.getShots().get(i).getX(),tank.getShots().get(i).getY(),4,4);}
        }
    }



    //save保存数据
    public void save(){
        for (int i = 0; i < enemyTranks.size(); i++) {
            for (int j = 0; j < enemyTranks.get(i).getShots().size(); j++) {
                enemyTranks.get(i).getShots().get(j).setShotLive(false);
                //保存信息时关闭敌人子弹的线程
            }
        }
        ObjectOutputStream stream;
        try {
            stream = new ObjectOutputStream(new FileOutputStream("e:\\Tank\\tank.dat"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            //1.写入击杀数
            stream.writeInt(kil);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            //2.写入我的坦克对象
            stream.writeObject(myTank);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < enemyTranksSize-kil; i++) {
            try {
            //3.依次写入敌人坦克对象
                stream.writeObject(enemyTranks.get(i));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            //4.释放
            stream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void read() throws IOException, ClassNotFoundException {
        ObjectInputStream stream;

        try {
            stream=new ObjectInputStream(new FileInputStream("e:\\Tank\\tank.dat"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //读取击杀数,我的坦克，敌人坦克
        int j=stream.readInt();
        try {
            setMyTank((MyTank) stream.readObject());
            new Thread(myTank).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //将存放敌人坦克的数据置空，否则再次生成时会有上一次的坦克对象
        enemyTranks=new Vector<EnemyTank>();
        //依次开启敌人坦克，我的坦克，敌人坦克子弹，我的坦克子弹现场
        for (int i = 0; i < enemyTranksSize-j; i++) {
            EnemyTank tank=(EnemyTank) stream.readObject();
            enemyTranks.add(tank);
            new Thread(tank).start();
            for (int k = 0; k < tank.getShots().size(); k++) {
                tank.getShots().get(k).setShotLive(true);
                new Thread(tank.getShots().get(k)).start();
            }
        }
        for (int i = 0; i < myTank.getShots().size(); i++) {
            myTank.getShots().get(i).setShotLive(true);
            new Thread(myTank.getShots().get(i)).start();
        }
        try {
            stream.close();
            //stream2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //释放
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        EnemyTank e1;
        EnemyTank e2;
        while (true){
            //休眠
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //监视myTnk
            for (int i = 0; i < enemyTranks.size(); i++) {
                for (int j = 0; j < enemyTranks.get(i).getShots().size(); j++) {
                    //我的坦克与敌人坦克子弹相碰
                    if((enemyTranks.get(i).getShots().get(j).getState()==0)||(enemyTranks.get(i).getShots().get(j).getState()==1)){
                        if(((enemyTranks.get(i).getShots().get(j).getX()>=(myTank.getX()-8))&&enemyTranks.get(i).getShots().get(j).getX()<=(myTank.getX()+40))&&((enemyTranks.get(i).getShots().get(j).getY()>=(myTank.getY()-8))&&enemyTranks.get(i).getShots().get(j).getY()<=(myTank.getY()+60))){
                            myTank.setLive(false);
                            enemyTranks.get(i).getShots().get(j).setShotLive(false);
                        }
                    }
                    else {
                        if(((enemyTranks.get(i).getShots().get(j).getX()>=(myTank.getX()-8))&&enemyTranks.get(i).getShots().get(j).getX()<=(myTank.getX()+60))&&((enemyTranks.get(i).getShots().get(j).getY()>=(myTank.getY()-8))&&enemyTranks.get(i).getShots().get(j).getY()<=(myTank.getY()+40))){
                            myTank.setLive(false);
                            enemyTranks.get(i).getShots().get(j).setShotLive(false);

                        }
                    }
                    //监视敌人子弹与钢的碰撞
                    steels(enemyTranks.get(i),steel,j);
                    //监视敌人坦克子弹与墙的碰撞
                    walls(enemyTranks.get(i),wall,j);
                }

                //监视敌人坦克与河与钢与墙
                switch (enemyTranks.get(i).getState()){
                    case 0:
                        if(riverBonk01(enemyTranks.get(i),river)){
                            enemyTranks.get(i).setY(river.getY()+river.getCol());
                        }
                        if(steelBonk01(enemyTranks.get(i),steel)){
                            enemyTranks.get(i).setY(steel.getY()+steel.getCol());
                        }
                        wallBonk01(enemyTranks.get(i),wall);
                        break;
                    case 1:
                        if(riverBonk01(enemyTranks.get(i),river)){
                            enemyTranks.get(i).setY(river.getY()-60);
                        }
                        if(steelBonk01(enemyTranks.get(i),steel)){
                            enemyTranks.get(i).setY(steel.getY()-60);
                        }
                        wallBonk01(enemyTranks.get(i),wall);
                        break;
                    case 2:
                        if(riverBonk23(enemyTranks.get(i),river)){
                            enemyTranks.get(i).setX(river.getX()-60);
                        }
                        if(steelBonk23(enemyTranks.get(i),steel)){
                            enemyTranks.get(i).setX(steel.getX()-60);
                        }
                        wallBonk23(enemyTranks.get(i),wall);
                        break;
                    case 3:
                        if(riverBonk23(enemyTranks.get(i),river)){
                            enemyTranks.get(i).setX(river.getX()+river.getRow());
                        }
                        if(steelBonk23(enemyTranks.get(i),steel)){
                            enemyTranks.get(i).setX(steel.getX()+steel.getRow());
                        }
                        wallBonk23(enemyTranks.get(i),wall);
                }

                //监视敌人坦克重叠
                for (int j = 1; j+i <enemyTranks.size() ; j++) {
                    e1=enemyTranks.get(i);
                    e2=enemyTranks.get(i+j);
                    if((e1.getState()==0||e1.getState()==1)&&(e2.getState()==0||e2.getState()==1)){
                        if((e1.getX()<=e2.getX()+40&&e1.getX()>=e2.getX()-40)&&(e1.getY()<=e2.getY()+60&&e1.getY()>=e2.getY()-60)){
                            enemyTranks.get(i).setChange(false);
                            enemyTranks.get(i+j).setChange(false);

                        }
                    }else if((e1.getState()==2||e1.getState()==3)&&(e2.getState()==0||e2.getState()==1)){
                        if((e1.getX()<=e2.getX()+40&&e1.getX()>=e2.getX()-60)&&(e1.getY()<=e2.getY()+60&&e1.getY()>=e2.getY()-40)){
                            enemyTranks.get(i).setChange(false);
                            enemyTranks.get(i+j).setChange(false);
                        }
                    }else if((e1.getState()==0||e1.getState()==1)&&(e2.getState()==2||e2.getState()==3)){
                        if((e1.getX()<=e2.getX()+60&&e1.getX()>=e2.getX()-40)&&(e1.getY()<=e2.getY()+40&&e1.getY()>=e2.getY()-60)){
                            enemyTranks.get(i).setChange(false);
                            enemyTranks.get(i+j).setChange(false);
                        }
                    }else {
                        if((e1.getX()<=e2.getX()+60&&e1.getX()>=e2.getX()-60)&&(e1.getY()<=e2.getY()+40&&e1.getY()>=e2.getY()-40)){
                            enemyTranks.get(i).setChange(false);
                            enemyTranks.get(i+j).setChange(false);
                        }
                    }
                }
                //我的坦克与敌人坦克相撞
                if((((enemyTranks.get(i).getX()>=myTank.getX()-60)&&(enemyTranks.get(i).getX()<=myTank.getX()+60))&&((enemyTranks.get(i).getY()>=myTank.getY()-60)&&(enemyTranks.get(i).getY()<=myTank.getY()+60)))){
                   myTank.setLive(false);
                }

            }
            //监视我的坦克与河和钢与墙

            switch (myTank.getState()){
                case 0:
                    if(riverBonk01(myTank,river)){
                        myTank.setY(river.getY()+river.getCol());
                    }
                    if(steelBonk01(myTank,steel)){
                        myTank.setY(steel.getY()+steel.getCol());
                    }
                    wallBonk01(myTank,wall);
                    break;
                case 1:
                    if(riverBonk01(myTank,river)){
                        myTank.setY(river.getY()-60);
                    }
                    if(steelBonk01(myTank,steel)){
                        myTank.setY(steel.getY()-60);
                    }
                    wallBonk01(myTank,wall);
                    break;
                case 2:
                    if(riverBonk23(myTank,river)){
                        myTank.setX(river.getX()-60);
                    }
                    if(steelBonk23(myTank,steel)){
                        myTank.setX(steel.getX()-60);
                    }
                    wallBonk23(myTank,wall);
                    break;
                case 3:
                    if(riverBonk23(myTank,river)){
                        myTank.setX(river.getX()+river.getRow());
                    }
                    if(steelBonk23(myTank,steel)){
                        myTank.setX(steel.getX()+steel.getRow());
                    }
                wallBonk23(myTank,wall);
            }

            //监视敌人坦克与我的子弹
            for (int i = 0; i < myTank.getShots().size(); i++) {
                for (int j = 0; j < enemyTranks.size(); j++) {
                    if(enemyTranks.get(j).getState()==0||enemyTranks.get(j).getState()==1)
                    {
                        if(((myTank.getShots().get(i).getX()>=(enemyTranks.get(j).getX()-8))&&myTank.getShots().get(i).getX()<=(enemyTranks.get(j).getX()+40))&&((myTank.getShots().get(i).getY()>=(enemyTranks.get(j).getY()-8))&&myTank.getShots().get(i).getY()<=(enemyTranks.get(j).getY()+60))){

                            enemyTranks.get(j).setLive(false);
                            myTank.getShots().get(i).setShotLive(false);
                            Ikun ikun = new Ikun(enemyTranks.get(j).getX(), enemyTranks.get(j).getY()+10);
                            ikuns.add(ikun);
                            kil++;
                            Thread thread = new Thread(ikun);
                            thread.start();
                        }
                    }
                    else {
                        if(((myTank.getShots().get(i).getX()>=(enemyTranks.get(j).getX()-8))&&myTank.getShots().get(i).getX()<=(enemyTranks.get(j).getX()+60))&&((myTank.getShots().get(i).getY()>=(enemyTranks.get(j).getY()-8))&&myTank.getShots().get(i).getY()<=(enemyTranks.get(j).getY()+40))){
                            enemyTranks.get(j).setLive(false);
                            myTank.getShots().get(i).setShotLive(false);
                            Ikun ikun = new Ikun(enemyTranks.get(j).getX()+10, enemyTranks.get(j).getY());
                            ikuns.add(ikun);
                            kil++;
                            Thread thread = new Thread(ikun);
                            thread.start();
                        }
                    }
                }
                //监视我的坦克子弹与钢的碰撞
                steels(myTank,steel,i);
                //监视我的坦克与墙的碰撞
                walls(myTank,wall,i);
            }
            //查看是否与其他敌人坦克碰撞

            //重绘
            this.repaint();
            System.out.println(Thread.currentThread().getName()+"重绘中");
        }

    }
    public void steels(Tank tank,Steel steel,int n){
        if(((tank.getShots().get(n).getX()>steel.getX()-8) &&  (tank.getShots().get(n).getX()<steel.getX()+steel.getRow()))  &&  ((tank.getShots().get(n).getY()>steel.getY()-8) &&  (tank.getShots().get(n).getY()<steel.getY()+steel.getCol()))){
            tank.getShots().get(n).setShotLive(false);
        }
    }
    public void walls(Tank tank,Wall wall,int n){
        for (int k = 0; k < wall.getClays().size(); k++) {
            if(((tank.getShots().get(n).getX()>wall.getClays().get(k).getX()-8) &&  (tank.getShots().get(n).getX()<wall.getClays().get(k).getX()+15))  &&  ((tank.getShots().get(n).getY()>wall.getClays().get(k).getY()-8) &&  (tank.getShots().get(n).getY()<wall.getClays().get(k).getY()+15))){
                tank.getShots().get(n).setShotLive(false);
                wall.getClays().remove(k);
            }
        }
    }
    public boolean riverBonk01(Tank tank,River river){
        if((tank.getY()<river.getY()+river.getCol()&&tank.getY()+60>river.getY())&&((tank.getX()>river.getX()-40)&&(tank.getX()<river.getX()+river.getRow()))){
            return true;
        }else return false;
    }
    public boolean steelBonk01(Tank tank,Steel steel){
        if((tank.getY()<steel.getY()+steel.getCol()&&tank.getY()+60>steel.getY())&&((tank.getX()>steel.getX()-40)&&(tank.getX()<steel.getX()+steel.getRow()))){
            return true;
        }else return false;
    }
    public boolean riverBonk23(Tank tank,River river){
        if((tank.getX()+60>river.getX()&&tank.getX()<river.getX()+river.getRow())&&(tank.getY()>river.getY()-40&&tank.getY()<river.getY()+river.getCol())){
            return true;
        }else return false;
    }
    public boolean steelBonk23(Tank tank,Steel steel){
        if((tank.getX()+60>steel.getX()&&tank.getX()<steel.getX()+steel.getRow())&&(tank.getY()>steel.getY()-40&&tank.getY()<steel.getY()+steel.getCol())){
            return true;
        }else return false;
    }
    public void wallBonk23(Tank tank,Wall wall){
        for (int j = 0; j < wall.getClays().size(); j++) {
            if((tank.getX()+60>wall.getClays().get(j).getX()&&tank.getX()<wall.getClays().get(j).getX()+15)&&(tank.getY()>wall.getClays().get(j).getY()-40&&tank.getY()<wall.getClays().get(j).getY()+15)){
                if(tank.getState()==2){
                    tank.setX(tank.getX()-5);
                }
                else {
                    tank.setX(tank.getX()+5);
                }

            }
        }
    }
    public void wallBonk01(Tank tank, Wall wall){
        for (int j = 0; j < wall.getClays().size(); j++) {
            if((tank.getY()<wall.getClays().get(j).getY()+15&&tank.getY()+60>wall.getClays().get(j).getY())&&((tank.getX()>wall.getClays().get(j).getX()-40)&&(tank.getX()<wall.getClays().get(j).getX()+15))){

                if(tank.getState()==1){
                    tank.setY(tank.getY()-5);
                }
                else {
                    tank.setY(tank.getY()+5);
                }
            }
        }
    }
}

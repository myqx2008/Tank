package Tank.test;

import Tank.main.pojo.zhangai.Clay;
import Tank.main.pojo.zhangai.River;
import Tank.main.pojo.zhangai.Steel;
import Tank.main.pojo.zhangai.Wall;

import javax.swing.*;
import java.awt.*;

/**
 * @author 林桂平
 */
public class TestPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(50,20,1000,750);
    }
    public void drawRiver(River river, Graphics g){
        g.setColor(Color.blue);
        g.fill3DRect(river.getX(),river.getY(),river.getRow(),river.getCol(),true);
    }
    public void drawSteel(Steel steel, Graphics g){
        g.setColor(Color.darkGray);
        g.fill3DRect(steel.getX(),steel.getY(),steel.getRow(),steel.getCol(),true);
    }
    public void drawWall(Wall wall, Graphics g) {
        g.setColor(Color.ORANGE);
        Clay clay;
        for (int i = 0; i < wall.getClays().size(); i++) {
            clay = wall.getClays().get(i);
            g.fill3DRect(clay.getX(), clay.getY(), 15, 15, true);
        }
    }
}

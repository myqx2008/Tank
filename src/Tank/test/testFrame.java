package Tank.test;

import javax.swing.*;

/**
 * @author 林桂平
 */
public class testFrame extends JFrame {
    TestPanel pn;
    public testFrame(){
        this.pn=new TestPanel();
        this.add(pn);
        //设置窗口大小
        this.setSize(1500, 850);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //可视化
        this.setVisible(true);
    }

}

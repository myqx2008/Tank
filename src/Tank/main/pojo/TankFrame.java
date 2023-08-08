package Tank.main.pojo;

import javax.swing.*;
import java.io.IOException;

/**
 * @author 林桂平
 */
@SuppressWarnings({"all"})
public class TankFrame extends JFrame {
    private TankPanel pn;

    public TankFrame() throws IOException {

        this.pn = new TankPanel();
        pn.Initialize();

    }

    public TankPanel getPn() {
        return pn;
    }

    public void setPn(TankPanel pn) {
        this.pn = pn;
    }

    public void startGame() throws Exception{

        this.add(pn);
        //设置窗口大小
        this.setSize(1500, 850);
        Thread thread = new Thread(pn);
        thread.setDaemon(true);
        thread.start();
        //点击窗口小叉时退出
        this.addKeyListener(pn);


        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //可视化
        this.setVisible(true);



    }

}

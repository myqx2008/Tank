package Tank.main;

import Tank.main.pojo.tank.EnemyTank;
import Tank.main.pojo.tank.MyTank;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author 林桂平
 */
public class test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream("e:\\Tank\\tank.dat"));
        ObjectInputStream stream2 = new ObjectInputStream(new FileInputStream("e:\\Tank\\kills.dat"));
        MyTank tank=(MyTank)stream.readObject();
        System.out.println(tank);
        int n=stream2.readInt();
        for (int i = 0; i < 4-n; i++) {
            EnemyTank tank1=(EnemyTank) stream.readObject();
            System.out.println(tank1);
        }
    }
}

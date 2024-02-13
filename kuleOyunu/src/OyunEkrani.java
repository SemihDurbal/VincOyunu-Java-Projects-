import java.awt.HeadlessException;
import javax.swing.JFrame;


public class OyunEkrani extends javax.swing.JFrame{



    public void oyunuCalistir(){

        OyunEkrani ekran = new OyunEkrani();

        ekran.setResizable(false);
        ekran.setFocusable(false);
        ekran.setSize(800, 800);
        ekran.setLocation(400,250);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Oyun oyun = new Oyun();

        oyun.requestFocus();
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);

        ekran.add(oyun);

        ekran.setVisible(true);



    }

}

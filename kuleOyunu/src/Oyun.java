
import com.mysql.cj.x.protobuf.MysqlxCursor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;



class Blok{
    static Random random = new Random();
    private int x;
    private int y;

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

    public Blok(int x, int y) {
        this.x = x;
        y = 700;
        this.y = y;
    }

}

public class Oyun extends JPanel implements KeyListener,ActionListener {

    static Random random = new Random();
    Timer timer = new Timer(2/3, this);


    static int y = 700;
    public ArrayList<Blok> bloklar = new ArrayList<Blok>();

    private BufferedImage image;
    private BufferedImage image2;

    private int tutucudirX = 2;
    private int tutucux = 260;

    public int getOyunSkoru() {
        return oyunSkoru;
    }

    public void setOyunSkoru(int oyunSkoru) {
        this.oyunSkoru = oyunSkoru;
    }

    private int oyunSkoru = 9999999 ;

    public void puanHesapla () {
        for (int i = 0; i <= bloklar.size(); i++) {
            if ((bloklar.get(i).getX() - bloklar.get(i+1).getX()) <= -80) {
                JOptionPane.showMessageDialog(this, "Oyun Sonlandı Başarısız Oldunuz...");
                System.exit(0);
            }
        }
    }


    public boolean kontrolEt() {
        if (y < 0) {
            return true;
        }
        return false;
    }

    public Oyun() {

        try {
            image = ImageIO.read(new FileImageInputStream(new File("vinc.png")));
            image2 = ImageIO.read(new FileImageInputStream(new File("tutucu.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }

        setBackground(Color.GREEN);

        timer.start();

    }


    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, -50, 0, (int) ((image.getWidth() * 1.55)), (int) ((image.getHeight() * 1.55)), this);
        g.drawImage(image2, tutucux, 53, ((image2.getWidth())), ((image2.getHeight())), this);


        g.setColor(Color.DARK_GRAY);

        for (Blok blok : bloklar) {
            g.fillRect(blok.getX(), y, 80, 50);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if(c == KeyEvent.VK_CONTROL){
            bloklar.add(new Blok(tutucux, getY()));
            y-=50;
        }
        puanHesapla();

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tutucux += tutucudirX;
        setOyunSkoru(getOyunSkoru()-3);
        if(getOyunSkoru() <= 0){
            JOptionPane.showMessageDialog(this,"Zaman Doldu Başarısız Oldunuz");
            System.exit(0);
        }
        if (tutucux <= 667) {
            tutucudirX *= (-1);
        }
        if (tutucux >= 260) {
            tutucudirX *= (-1);
        }

        if(kontrolEt()){
            timer.stop();
            JOptionPane.showMessageDialog(this ,"Oyunu Başarıyla Tamamladınız\n Oyun Skorunuz = " + getOyunSkoru() + " !");
            System.exit(1);
        }

        repaint();
    }

}


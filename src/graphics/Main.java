package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class Main extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;
    Image dbImage;
    Graphics dbg;
    int x, y, xDir, yDir;

    public void run(){
        try{
            while(true){
                move();

                Thread.sleep(2);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public void move(){
        x += xDir;
        y += yDir;

        if(x <= 0)
            x = 0;
        if(x >= 700)
            x = 700;
        if(y <= 0)
            y = 0;
        if(y >= 500)
            y = 500;
    }

    public void setXDir(int xdir){
        xDir = xdir;
    }

    public void setYDir(int ydir){
        yDir = ydir;
    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            int keyCode = e.getKeyCode();
            if(keyCode == KeyEvent.VK_LEFT){
                setXDir(-1);
            }
            if(keyCode == KeyEvent.VK_RIGHT){
                setXDir(+1);
            }
            if(keyCode == KeyEvent.VK_UP){
                setYDir(-1);
            }
            if(keyCode == KeyEvent.VK_DOWN){
                setYDir(+1);
            }
        }

        public void keyReleased(KeyEvent e){
            int keyCode = e.getKeyCode();
            if(keyCode == KeyEvent.VK_LEFT){
                setXDir(0);
            }
            if(keyCode == KeyEvent.VK_RIGHT){
                setXDir(0);
            }
            if(keyCode == KeyEvent.VK_UP){
                setYDir(0);
            }
            if(keyCode == KeyEvent.VK_DOWN){
                setYDir(0);
            }
        }
    }

    public Main(){
        addKeyListener(new AL());
        setTitle("Graphics");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(Color.BLACK);

        x = 150;
        y = 150;
    }

    //Double buffering
    public void paint(Graphics g){
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintCrap(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void paintCrap(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(x, y, 100, 100);
        g.drawString("X: " + x + " Y: " + y, 50, 50);
        g.drawString("X Direction: " + xDir + " Y Direction: " + yDir, 50, 65);

        repaint();
    }

    public static void main(String[] args){
        Main m = new Main();
        Thread t1 = new Thread(m);
        t1.start();
    }
}
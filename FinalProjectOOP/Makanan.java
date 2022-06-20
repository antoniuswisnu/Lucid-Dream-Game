package FinalProjectOOP;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;
//import javax.swing.ImageIcon;
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.Rectangle;

public class Makanan implements Runnable {
    private int x;
    private int y;
    private int xArah;
    private int yArah;

    // generate makanan
    Makanan() {
        // logic pergerakan makanan secara random
        Random random = new Random();
        x = random.nextInt(400);
        y = random.nextInt(400);
        xArah = -1 + random.nextInt(5);
        yArah = -1 + random.nextInt(5);
        if (xArah == 0 && yArah == 0) {
            xArah = 1;
            yArah = 1;
        }
    }
    
    public void run() {
        while (true) {
            move();
            try {
                // Kecepatan makanan
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.err.println("Game Error!");
            }
        }
    }

    // batas dari pergerakan makanan
    private void move() {
        x += xArah;
        y += yArah;
        if (x < 0)
            xArah = 1;
        if (y < 23)
            yArah = 1;
        if (x > 583)
            xArah = -1;
        if (y > 423)
            yArah = -1;
    }

    // GUI makanan
    public void draw(Graphics g) {
        Image makanan = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("Assets/soul.png"))).getImage();
        g.drawImage(makanan, x, y, null);
    }

    // tinggi dan lebar makanan
    Rectangle getHitBox() {
        return new Rectangle(x, y, 50, 50);
    }
}
    


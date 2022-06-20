package FinalProjectOOP;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Musuh implements Runnable {
    private int x;
    private int y;
    private int xArah;
    private int yArah;

    // generate musuh
    Musuh(){
        Random random = new Random();
        x = 600;
        y = 240;
        xArah = -1 + random.nextInt(8);
        yArah = -1 + random.nextInt(8);
        if (xArah == 0 && yArah == 0) {
            xArah = 1;
            yArah = 1;
        }
    }

    public void run() {
        while (true) {
            move();
            try {
                // kecepatan gerak musuh
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.err.println("Game Error!");
            }
        }
    }

    // batas dari pergerakan musuh
    private void move() {
        x += xArah;
        y += yArah;
        if (x < 0)
            xArah = 1;
        if (y < 20)
            yArah = 1;
        if (x >583)
            xArah = -1;
        if (y > 404)
            yArah = -1;
    }

    // GUI musuh
    public void draw(Graphics g) {
        Image musuh = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("Assets/musuhJahat.png"))).getImage();
        g.drawImage(musuh, x, y, null);
    }

    // logic tinggi dan lebar musuh
    Rectangle getHitBox() {
        return new Rectangle(x, y, 0, 0);
    }
}


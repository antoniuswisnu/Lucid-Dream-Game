package FinalProjectOOP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;

public class Game extends JFrame{
    private static int x;
    private static int y;
    private static int xArah;
    private static int yArah;
    public int skor = 0;
    public ArrayList<Musuh> ArrayMusuh;
    public ArrayList<Makanan> ArrayMakanan;
    public boolean Pemain;
    private final Image background;
    Dimension size;

    // pembuatan GUI ketika dijalankan
    public Game() {
        // GUI
        setPreferredSize(size);
        setSize(640, 480);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setVisible(true);
        setTitle("Lucid Dream");

        // pengaturan awal constructor
        x = 0;
        y = 0;
        xArah = 0;
        yArah = 0;
        ArrayMusuh = new ArrayList<>();
        ArrayMakanan = new ArrayList<>();
        Pemain = true;
        size = new Dimension();
        background = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("Assets/bg.png"))).getImage();
        size.width = background.getWidth(null);
        size.height = background.getHeight(null);
        addKeyListener(new KeyListener());
    }

    // Logic game ketika di mainkan
    // logic keyboard untuk move karakter
    static class KeyListener extends KeyAdapter {
        // Ketika keyboard di tekan
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    yArah = -2;
                    break;
                case KeyEvent.VK_DOWN:
                    yArah = 2;
                    break;
                case KeyEvent.VK_LEFT:
                    xArah = -2;
                    break;
                case KeyEvent.VK_RIGHT:
                    xArah = 2;
                    break;
                default:
                    break;
            }
        }

        // Ketika keyboard di lepas
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                    yArah = 0;
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                    xArah = 0;
                    break;
                default:
                    break;
            }
        }
    }

    // batas dari pergerakan karakter utama
    static void move() {
        x += xArah;
        y += yArah;
        if (x < 0){
            x = 0;
        }
        if (y < 23){
            y = 23;
        }
        if (x > 583){
            x = 583;
        }
        if (y > 423){
            y = 423;
        }
    }

    public void paint(Graphics g) {
        Image img = createImage(getWidth(), getHeight());
        Graphics bg = img.getGraphics();
        draw(bg);
        g.drawImage(img, 0, 0, this);
    }

    // Pembuatan GUI setiap karakter
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, null);
        if (Pemain) {
            Image hero = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("Assets/Sigit5.png"))).getImage();
            g.drawImage(hero, x, y, this);
            for (Musuh musuh : ArrayMusuh) {
                musuh.draw(g);
            }
            for (Makanan makanan : ArrayMakanan) {
                makanan.draw(g);
            }
            // pengaturan font score
            g2d.setFont(new Font("Tahoma", Font.BOLD, 24));
            g2d.setColor(Color.WHITE);
            g2d.drawString("SCORE : " + skor, 20, 50);
            g2d.setFont(new Font("Tahoma", Font.PLAIN, 10));
            g2d.setColor(Color.WHITE);
        } else if (skor == 1000) {
            Image menang = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("Assets/menang.png"))).getImage();
            g.drawImage(menang, 0, 0, this);
            g2d.setFont(new Font("Tahoma", Font.BOLD, 24));
            g2d.setColor(Color.WHITE);
            g2d.drawString("SCORE : " + skor, 20, 50);
            g2d.setFont(new Font("Tahoma", Font.PLAIN, 10));
            g2d.setColor(Color.WHITE);
        } else {
            Image gameover = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("Assets/gameover.png"))).getImage();
            g.drawImage(gameover, 0, 0, this);
        }
        repaint();
    }

    // Memunculkan musuh pada GUI
    public void munculMusuh() {
        Musuh musuh = new Musuh();
        ArrayMusuh.add(musuh);
        Thread t = new Thread(musuh);
        t.start();
    }

    // Memunculkan makanan pada GUI
    public void munculMakanan() {
        Makanan makanan = new Makanan();
        ArrayMakanan.add(makanan);
        Thread t = new Thread(makanan);
        t.start();
    }

    // tinggi dan lebar Karakter Utama
    Rectangle getPlayerHitbox() {
        return new Rectangle(x, y, 50, 50);
    }

}
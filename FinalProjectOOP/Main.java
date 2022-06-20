package FinalProjectOOP;

public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero();
        Thread t = new Thread(hero);
        t.start();
        while(true) {
            // jika jumlah musuh kurang dari 10, maka munculkanlah musuh
            if (hero.ArrayMusuh.size() < 10) {
                hero.munculMusuh();
                hero.munculMakanan();
            }
            try {
                // kecepatan bergerak karakter utama
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                System.err.println("Game Error!");
            }
        }
    }
}

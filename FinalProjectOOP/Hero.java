package FinalProjectOOP;

import java.awt.*;

public class Hero extends Game implements Runnable{
    public void run() {
        while(true){
            move();
            for (int i = 0; i < ArrayMakanan.size(); i++) {
                Rectangle makananHitbox = ArrayMakanan.get(i).getHitBox();
                Rectangle playerHitbox = getPlayerHitbox();

                // ketika makanan tersentuh, maka hilangkan makanan tersebut dan skor bertambah
                if (makananHitbox.intersects(playerHitbox)){
                    ArrayMakanan.remove(i);
                    skor += 100;
                    if (skor == 1000){
                        Pemain = false;
                        break;
                    }
                }
            }

            // Ketika karakter utama tersentuh musuh, maka pemain tidak bisa bergerak
            for (int i = 0; i < ArrayMusuh.size(); i++) {
                Rectangle musuhHitbox = ArrayMusuh.get(i).getHitBox();
                Rectangle playerHitbox = getPlayerHitbox();
                if (musuhHitbox.intersects(playerHitbox)){
                    Pemain = false;
                    break;
                }
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                System.err.println("Game Error!");
            }
        }
    }
}

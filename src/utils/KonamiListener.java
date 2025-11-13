package utils;
 
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
 
public class KonamiListener extends KeyAdapter {
 
    private final int[] codigo = {
            KeyEvent.VK_UP, KeyEvent.VK_UP,
            KeyEvent.VK_DOWN, KeyEvent.VK_DOWN,
            KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
            KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
            KeyEvent.VK_B, KeyEvent.VK_A
    };
 
    private int pos = 0;
    private final Component root;
 
    public KonamiListener(Component root) {
        this.root = root;
    }
 
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == codigo[pos]) {
            pos++;
 
            if (pos == codigo.length) {
                ativarRGB();
                pos = 0;
            }
        } else {
            pos = 0;
        }
    }
 
    private void ativarRGB() {
        new Thread(() -> {
            try {
                for (;;) {
                    root.setBackground(new Color(
                            (int)(Math.random()*255),
                            (int)(Math.random()*255),
                            (int)(Math.random()*255)
                    ));
                    Thread.sleep(120);
                }
            } catch (InterruptedException ignored) {}
        }).start();
    }
}
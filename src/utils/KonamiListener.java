package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KonamiListener extends KeyAdapter {

    private final JFrame frame;
    private final int[] konami = {
            KeyEvent.VK_UP, KeyEvent.VK_UP,
            KeyEvent.VK_DOWN, KeyEvent.VK_DOWN,
            KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
            KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
            KeyEvent.VK_B, KeyEvent.VK_A
    };

    private int index = 0;
    private Timer timerRGB;
    private boolean modoRGBAtivo = false;

    public KonamiListener(JFrame f) {
        this.frame = f;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Verificar se Alt Gr foi pressionado (KeyEvent.VK_ALT_GRAPH)
        if (e.getKeyCode() == KeyEvent.VK_ALT_GRAPH) {
            desativarModoRGB();
            return;
        }

        if (modoRGBAtivo) return; // Ignorar outras teclas se RGB está ativo

        if (e.getKeyCode() == konami[index]) {
            index++;
            if (index == konami.length) {
                ativarModoRGB();
                index = 0;
            }
        } else {
            index = 0;
        }
    }

    private void ativarModoRGB() {
        modoRGBAtivo = true;
        timerRGB = new Timer(80, e -> {
            frame.getContentPane().setBackground(
                    new Color((float) Math.random(), (float) Math.random(), (float) Math.random())
            );
        });
        timerRGB.start();
        
        // Mostrar mensagem que modo foi ativado
        JOptionPane.showMessageDialog(frame, 
            "Modo RGB Ativado!\nPressione Alt Gr para desativar.", 
            "Konami Code", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void desativarModoRGB() {
        if (modoRGBAtivo && timerRGB != null) {
            timerRGB.stop();
            modoRGBAtivo = false;
            frame.getContentPane().setBackground(null); // Volta ao fundo padrão
            
            // Mostrar mensagem que modo foi desativado
            JOptionPane.showMessageDialog(frame, 
                "Modo RGB Desativado!", 
                "Konami Code", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
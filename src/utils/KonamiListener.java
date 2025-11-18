package utils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
    private boolean ativo = false;

    public KonamiListener(JFrame f) { this.frame = f; }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ALT_GRAPH) { desligar(); return; }
        if (ativo) return;
        if (e.getKeyCode() == konami[index]) {
            index++;
            if (index == konami.length) { ativar(); index = 0; }
        } else index = 0;
    }

    private void ativar() {
        ativo = true;
        timerRGB = new Timer(80, ev ->
            frame.getContentPane().setBackground(
                new Color((float)Math.random(),(float)Math.random(),(float)Math.random())));
        timerRGB.start();
        JOptionPane.showMessageDialog(frame,
            "Modo RGB ativado!\nPressione Alt Gr para desligar.",
            "Konami", JOptionPane.INFORMATION_MESSAGE);
    }

    private void desligar() {
        if (ativo && timerRGB != null) {
            timerRGB.stop();
            ativo = false;
            frame.getContentPane().setBackground(null);
            JOptionPane.showMessageDialog(frame,
                "Modo RGB desligado!", "Konami", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
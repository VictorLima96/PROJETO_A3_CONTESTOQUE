package utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class DarkModeToggle {
    private static boolean darkOn = false;
    private static final List<JFrame> tracked = new ArrayList<>();

    public static void track(JFrame f) { tracked.add(f); }

    public static void toggle() {
        darkOn = !darkOn;
        if (darkOn) {
            UIManager.put("Panel.background", Color.DARK_GRAY);
            UIManager.put("OptionPane.background", Color.DARK_GRAY);
            UIManager.put("Label.foreground", Color.WHITE);
            UIManager.put("Button.background", Color.GRAY);
            UIManager.put("Button.foreground", Color.WHITE);
        } else {
            UIManager.put("Panel.background", null);
            UIManager.put("OptionPane.background", null);
            UIManager.put("Label.foreground", null);
            UIManager.put("Button.background", null);
            UIManager.put("Button.foreground", null);
        }
        for (JFrame f : tracked) SwingUtilities.updateComponentTreeUI(f);
    }
}
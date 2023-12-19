package lab7;

import javax.swing.*;

public class LabMode {
    private Mode currentMode = null;
    public void changeMode(Mode mode) {
        this.currentMode = mode;
    }
    public void start(JPanel jPanel, int sizeFont) {
        this.currentMode.startLab(jPanel, sizeFont);
    }
}

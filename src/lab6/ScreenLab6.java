package lab6;

import lab7.Mode;

import javax.swing.*;
import java.awt.*;

public class ScreenLab6 implements Mode {
    public void startLab(JPanel jPanel, int sizeFont) {
        System.out.println("Lab6: STARTED");

        jPanel.setLayout(new GridBagLayout());

        JButton jButton = new JButton("Запустить");
        jButton.addActionListener(e -> {
            SuperVisor sv = new SuperVisor();
            Thread thread = new Thread(sv);
            thread.start();
        });
        jButton.setBackground(Color.WHITE);
        jButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        jButton.setFont(new Font("Times new roman", Font.BOLD, sizeFont * 10));
        jPanel.add(jButton);
    }
}

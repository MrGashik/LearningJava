package lab7;

import javax.swing.*;
import java.awt.*;

import lab1.ScreenLab1;
import lab2.ScreenLab2;
import lab3.ScreenLab3;
import lab4.ScreenLab4;
import lab5.ScreenLab5;
import lab6.ScreenLab6;

public class Main {
    public static void main(String[] args) {
        Font font18 = new Font("Times new roman", Font.BOLD, 18);
        int deltaX = 227;
        int deltaY = 270;
        LabMode labMode = new LabMode();

        JFrame jFrame = new JFrame("Lab7");
        jFrame.getContentPane().setBackground(Color.BLACK);
        jFrame.setLayout(null);
        jFrame.setSize(1920, 1080);
        jFrame.setUndecorated(true);

        createMenu(jFrame, font18, labMode, deltaX, deltaY);

        jFrame.setVisible(true);
    }

    private static void createMenu(JFrame jFrame, Font currentFont, LabMode labMode, int dX, int dY) {
        JPanel jPanel = new JPanel(new FlowLayout());
        int xPanel = 4 * dX;
        int yPanel = 0;
        int widthPanel = jFrame.getWidth() - xPanel;
        int heightPanel = jFrame.getHeight();
        jPanel.setBounds(xPanel, yPanel, widthPanel, heightPanel);
        jPanel.setBackground(Color.WHITE);
        jFrame.add(jPanel);

        JButton closeButton = new JButton("Выход");
        closeButton.addActionListener(e -> System.exit(0));
        closeButton.setBounds(dX, dY * 3, 2 * dX, dY / 3);
        closeButton.setBackground(Color.WHITE);
        closeButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
        closeButton.setFont(currentFont);
        jFrame.add(closeButton);

        JButton[] arrayButton = {
                new JButton("Lab1"),
                new JButton("Lab2"),
                new JButton("Lab3"),
                new JButton("Lab4"),
                new JButton("Lab5"),
                new JButton("Lab6"),
        };
        for (int i = 0; i < arrayButton.length ; i++) {
            arrayButton[i].addActionListener(e -> {
                jPanel.removeAll();
                switch (e.getActionCommand()){
                    case "Lab1" -> labMode.changeMode(new ScreenLab1());
                    case "Lab2" -> labMode.changeMode(new ScreenLab2());
                    case "Lab3" -> labMode.changeMode(new ScreenLab3());
                    case "Lab4" -> labMode.changeMode(new ScreenLab4());
                    case "Lab5" -> labMode.changeMode(new ScreenLab5());
                    case "Lab6" -> labMode.changeMode(new ScreenLab6());
                }
                labMode.start(jPanel, currentFont.getSize());
                jFrame.revalidate();
                jFrame.repaint();
            });
            arrayButton[i].setBounds(dX, dY + i * dY / 3, 2 * dX, dY / 3);
            arrayButton[i].setBackground(Color.WHITE);
            arrayButton[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
            arrayButton[i].setFont(currentFont);
            jFrame.add(arrayButton[i]);
        }
    }
}

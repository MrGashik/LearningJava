package lab1;

import lab1.strategy.StrategyFly;
import lab1.strategy.StrategyRideAHorse;
import lab1.strategy.StrategyStand;
import lab1.strategy.StrategySwim;
import lab7.Mode;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ScreenLab1 implements Mode {
    public void startLab(JPanel jPanel, int sizeFont) {
        System.out.println("Lab1: STARTED");

        jPanel.setLayout(new FlowLayout());
        Font fontButtons = new Font("Times new roman", Font.BOLD, sizeFont + 2);

        Hero hero = new Hero("MrGashik", new StrategyStand());
        AtomicInteger change = new AtomicInteger();

        JPanel jPanelButtons = new JPanel(new GridLayout(3, 1));
        JPanel jPanelText = new JPanel(new GridLayout(2, 1));
        jPanelText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel jPanelFunctional = new JPanel(new GridLayout(2, 1));
        JPanel jPanelIcon = new JPanel();
        jPanelIcon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel jPanelAll = new JPanel(new GridLayout(2, 1));

        String[] conditions = {
                "Встать - 0 шагов",
                "Лодка - 1 шаг",
                "Лошадь - 4 шага",
                "Лететь - 5 шагов",
                "Завершить - конец пути",
        };
        JComboBox<String> jComboBox = new JComboBox<>(conditions);
        jComboBox.setFont(new Font("Times New Romans", Font.BOLD, sizeFont - 4));

        Icon[] arrayGif = {
                new ImageIcon("C:/Desktop/Java/Stand.gif"),
                new ImageIcon("C:/Desktop/Java/Swim.gif"),
                new ImageIcon("C:/Desktop/Java/Horse.gif"),
                new ImageIcon("C:/Desktop/Java/Fly.gif"),
                new ImageIcon("C:/Desktop/Java/End.gif"),
        };

        JLabel jLabelIcon = new JLabel();
        jLabelIcon.setIcon(arrayGif[0]);

        JLabel jLabelMove = new JLabel();
        jLabelMove.setText(hero.move());
        jLabelMove.setHorizontalAlignment(JLabel.CENTER);
        jLabelMove.setFont(new Font("Times New Romans", Font.BOLD, sizeFont - 4));

        JLabel jLabelStatus = new JLabel();
        jLabelStatus.setText(hero.changeStrategyMove(new StrategyStand()));
        jLabelStatus.setHorizontalAlignment(JLabel.CENTER);
        jLabelStatus.setFont(new Font("Times New Romans", Font.ITALIC, sizeFont - 4));

        JButton jButtonRestart = new JButton("Перезапустить");
        jButtonRestart.addActionListener(e -> {
            change.set(0);
            hero.restartMove();
            jLabelStatus.setText(hero.changeStrategyMove(new StrategyStand()));
            jLabelMove.setText(hero.move());
            jLabelIcon.setIcon(arrayGif[0]);
            jButtonRestart.setVisible(false);
        });
        jButtonRestart.setBackground(Color.WHITE);
        jButtonRestart.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jButtonRestart.setFont(fontButtons);
        jButtonRestart.setVisible(false);

        JButton jButton = new JButton("Вперёд");
        jButton.addActionListener(e -> {
            if (change.get() != 7) {
                switch (jComboBox.getItemAt(jComboBox.getSelectedIndex())) {
                    case "Встать - 0 шагов" -> {
                        if (change.get() == 0) {
                            jLabelStatus.setText(hero.getName() + " продолжает свое движение в назначенном режиме.");
                        } else {
                            jLabelStatus.setText(hero.changeStrategyMove(new StrategyStand()));
                            jLabelIcon.removeAll();
                            jLabelIcon.setIcon(arrayGif[0]);
                            change.set(0);
                        }
                        jLabelMove.setText(hero.move());
                    }
                    case "Лодка - 1 шаг" -> {
                        if (change.get() == 1) {
                            jLabelStatus.setText(hero.getName() + " продолжает свое движение в назначенном режиме.");
                        } else {
                            jLabelStatus.setText(hero.changeStrategyMove(new StrategySwim()));
                            jLabelIcon.removeAll();
                            jLabelIcon.setIcon(arrayGif[1]);
                            change.set(1);
                        }
                        jLabelMove.setText(hero.move());
                    }
                    case "Лошадь - 4 шага" -> {
                        if (change.get() == 2) {
                            jLabelStatus.setText(hero.getName() + " продолжает свое движение в назначенном режиме.");
                        } else {
                            jLabelStatus.setText(hero.changeStrategyMove(new StrategyRideAHorse()));
                            jLabelIcon.removeAll();
                            jLabelIcon.setIcon(arrayGif[2]);
                            change.set(2);
                        }
                        jLabelMove.setText(hero.move());
                    }
                    case "Лететь - 5 шагов" -> {
                        if (change.get() == 3) {
                            jLabelStatus.setText(hero.getName() + " продолжает свое движение в назначенном режиме.");
                        } else {
                            jLabelStatus.setText(hero.changeStrategyMove(new StrategyFly()));
                            jLabelIcon.removeAll();
                            jLabelIcon.setIcon(arrayGif[3]);
                            change.set(3);
                        }
                        jLabelMove.setText(hero.move());
                    }
                    case "Завершить - конец пути" -> {
                        jLabelStatus.setText(hero.getName() + " достиг своей цели.");
                        change.set(7);
                        jLabelIcon.removeAll();
                        jLabelIcon.setIcon(arrayGif[4]);
                        jButtonRestart.setVisible(true);
                    }
                }
            }
        });
        jButton.setBackground(Color.WHITE);
        jButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jButton.setFont(fontButtons);

        jPanelButtons.add(jComboBox);
        jPanelButtons.add(jButton);
        jPanelButtons.add(jButtonRestart);

        jPanelText.add(jLabelStatus);
        jPanelText.add(jLabelMove);

        jPanelFunctional.add(jPanelButtons);
        jPanelFunctional.add(jPanelText);

        jPanelIcon.add(jLabelIcon);

        jPanelAll.add(jPanelFunctional);
        jPanelAll.add(jPanelIcon);

        jPanel.add(jPanelAll);
    }
}

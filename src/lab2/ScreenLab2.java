package lab2;

import lab1.Hero;
import lab1.strategy.*;
import lab7.Mode;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScreenLab2 implements Mode {
    public void startLab(JPanel jPanel, int sizeFont) {
        System.out.println("Lab2: STARTED");

        jPanel.setLayout(new FlowLayout());
        Font fontButtons = new Font("Times new roman", Font.BOLD, sizeFont + 2);

        MyClass test = new MyClass();
        Hero man = new Hero("MrGashik", new StrategyStand());
        Integer level = 10;
        Double pi = 3.1416;
        String words = "Hello World!";
        AtomicBoolean block = new AtomicBoolean(false);

        JPanel jPanelAll = new JPanel(new GridLayout(2, 1));
        JPanel jPanelButtons = new JPanel(new GridLayout(2, 1));
        JPanel jPanelText = new JPanel(new GridLayout(20, 1));
        jPanelText.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        ArrayList<JLabel> jLabels = new ArrayList<>();

        JButton jButtonRestart = new JButton("Перезапустить");
        jButtonRestart.addActionListener(e -> {
            man.restartMove();
            jPanelText.removeAll();
            block.set(false);
            jButtonRestart.setVisible(false);
            jPanel.revalidate();
            jPanel.repaint();
        });
        jButtonRestart.setBackground(Color.WHITE);
        jButtonRestart.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jButtonRestart.setFont(fontButtons);
        jButtonRestart.setVisible(false);

        JButton jButton = new JButton("Запустить");
        jButton.addActionListener(e -> {
            try {
                if (!block.get()) {
                    ArrayList<Object> arrObj = new ArrayList<>();
                    Class<?> usedClass = test.getClass();
                    for (Method method : usedClass.getDeclaredMethods()) {
                        if (method.isAnnotationPresent(MyAnnotation.class)) {
                            boolean boolPrivate = Modifier.isPrivate(method.getModifiers());
                            boolean boolProtected = Modifier.isProtected(method.getModifiers());
                            if (boolPrivate || boolProtected) {
                                Class<?>[] type = method.getParameterTypes();
                                int length = type.length;
                                for (Class<?> t : type) {
                                    switch (t.getName()) {
                                        case "lab1.Hero" -> arrObj.add(man);
                                        case "java.lang.Integer" -> arrObj.add(level);
                                        case "java.lang.Double" -> arrObj.add(pi);
                                        case "java.lang.String" -> arrObj.add(words);
                                    }
                                }
                                MyAnnotation currentAnnotation = method.getAnnotation(MyAnnotation.class);
                                int number = currentAnnotation.value();
                                JLabel jLabel = new JLabel();
                                jLabel.setFont(new Font("Times new roman", Font.BOLD, sizeFont));
                                jLabel.setText(method.getName() + ": " + currentAnnotation);
                                jLabels.add(jLabel);
                                switch (number) {
                                    case 2 -> man.changeStrategyMove(new StrategySwim());
                                    case 3 -> man.changeStrategyMove(new StrategyWalk());
                                    case 4 -> man.changeStrategyMove(new StrategyRideAPony());
                                    case 5 -> man.changeStrategyMove(new StrategyRideAHorse());
                                    case 6 -> man.changeStrategyMove(new StrategyFly());
                                    default -> man.changeStrategyMove(new StrategyStand());
                                }
                                method.setAccessible(true);
                                for (int i = 0; i < number; i++) {
                                    JLabel jLabelText = new JLabel();
                                    jLabelText.setFont(new Font("Times new roman", Font.ITALIC, sizeFont - 2));
                                    switch (length) {
                                        case 1 -> jLabelText.setText(
                                                method.invoke(test,
                                                                arrObj.get(0))
                                                        .toString()
                                        );
                                        case 2 -> jLabelText.setText(
                                                method.invoke(test,
                                                                arrObj.get(0),
                                                                arrObj.get(1))
                                                        .toString()
                                        );
                                        case 3 -> jLabelText.setText(
                                                method.invoke(test,
                                                                arrObj.get(0),
                                                                arrObj.get(1),
                                                                arrObj.get(2))
                                                        .toString()
                                        );
                                        default -> jLabelText.setText(
                                                method.invoke(test)
                                                        .toString()
                                        );
                                    }
                                    jLabels.add(jLabelText);
                                }
                                method.setAccessible(false);
                                arrObj.clear();
                            }
                        }
                    }
                    for (JLabel jL : jLabels) {
                        jPanelText.add(jL);
                    }

                    jLabels.clear();
                    block.set(true);
                    jButtonRestart.setVisible(true);
                }
            } catch (InvocationTargetException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        });
        jButton.setBackground(Color.WHITE);
        jButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jButton.setFont(fontButtons);

        jPanelButtons.add(jButton);
        jPanelButtons.add(jButtonRestart);

        jPanelAll.add(jPanelButtons);
        jPanelAll.add(jPanelText);

        jPanel.add(jPanelAll);
    }
}

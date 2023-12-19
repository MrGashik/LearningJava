package lab5;

import lab7.Mode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScreenLab5 implements Mode {
    public void startLab(JPanel jPanel, int sizeFont) {
        System.out.println("Lab5: STARTED");

        jPanel.setLayout(new FlowLayout());
        Font fontTasks = new Font("Times new roman", Font.BOLD, sizeFont - 4);
        Font font2 = new Font("Times new roman", Font.ITALIC, sizeFont - 6);
        Font fontCollections = new Font("Times new roman", Font.PLAIN, sizeFont - 6);
        Font fontButtons = new Font("Times new roman", Font.BOLD, sizeFont + 2);

        var block = new AtomicBoolean(false);

        JPanel jPanelAll = new JPanel(new GridLayout(2, 1));
        JPanel jPanelButtons = new JPanel(new GridLayout(2, 1));
        JPanel jPanelText = new JPanel(new GridLayout(30, 1));
        jPanelText.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        ArrayList<JLabel> jLabels = new ArrayList<>();

        JButton jButtonRestart = new JButton("Перезапустить");
        jButtonRestart.addActionListener(e -> {
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
            if (!block.get()) {
                JFrame jFrameList = new JFrame("Ввод значений");
                jFrameList.getContentPane().setBackground(Color.BLACK);
                jFrameList.setLayout(new FlowLayout());
                jFrameList.setSize(300, 400);
                jFrameList.setVisible(true);

                JTextField jField1 = new JTextField("", 20);
                jField1.setFont(new Font("Times new roman", Font.PLAIN, sizeFont + 2));
                jField1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jField1.setHorizontalAlignment(JLabel.CENTER);
                jField1.setBounds(0, 0, jFrameList.getHeight(), 50);

                JTextField jField2 = new JTextField("", 20);
                jField2.setFont(new Font("Times new roman", Font.PLAIN, sizeFont + 2));
                jField2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jField2.setHorizontalAlignment(JLabel.CENTER);
                jField2.setBounds(0, 50, jFrameList.getHeight(), 50);

                JTextField jField3 = new JTextField("", 20);
                jField3.setFont(new Font("Times new roman", Font.PLAIN, sizeFont + 2));
                jField3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jField3.setHorizontalAlignment(JLabel.CENTER);
                jField3.setBounds(0, 100, jFrameList.getHeight(), 50);

                JTextField jField4 = new JTextField("", 20);
                jField4.setFont(new Font("Times new roman", Font.PLAIN, sizeFont + 2));
                jField4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jField4.setHorizontalAlignment(JLabel.CENTER);
                jField4.setBounds(0, 150, jFrameList.getHeight(), 50);

                JTextField jField5 = new JTextField("", 20);
                jField5.setFont(new Font("Times new roman", Font.PLAIN, sizeFont + 2));
                jField5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jField5.setHorizontalAlignment(JLabel.CENTER);
                jField5.setBounds(0, 200, jFrameList.getHeight(), 50);

                JTextField jField6 = new JTextField("", 20);
                jField6.setFont(new Font("Times new roman", Font.PLAIN, sizeFont + 2));
                jField6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jField6.setHorizontalAlignment(JLabel.CENTER);
                jField6.setBounds(0, 250, jFrameList.getHeight(), 50);

                JTextField jField7 = new JTextField("", 20);
                jField7.setFont(new Font("Times new roman", Font.PLAIN, sizeFont + 2));
                jField7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jField7.setHorizontalAlignment(JLabel.CENTER);
                jField7.setBounds(0, 300, jFrameList.getHeight(), 50);

                jFrameList.add(jField1);
                jFrameList.add(jField2);
                jFrameList.add(jField3);
                jFrameList.add(jField4);
                jFrameList.add(jField5);
                jFrameList.add(jField6);
                jFrameList.add(jField7);

                JButton jListButton = new JButton("Готово");
                jListButton.addActionListener(e1 -> {
                    jFrameList.setVisible(false);
                    String[] str1 = jField1.getText().split(" ");
                    String[] str2 = jField2.getText().split(" ");
                    String[] str3 = jField3.getText().split(" ");
                    String[] str4 = jField4.getText().split(" ");
                    String[] str5 = jField5.getText().split(" ");
                    String[] str6 = jField6.getText().split(" ");
                    String[] str7 = jField7.getText().split(" ");

                    System.out.println(Arrays.toString(str1));
                    System.out.println(Arrays.toString(str2));
                    System.out.println(Arrays.toString(str3));
                    System.out.println(Arrays.toString(str4));
                    System.out.println(Arrays.toString(str5));
                    System.out.println(Arrays.toString(str6));
                    System.out.println(Arrays.toString(str7));

                    addText(jLabels, fontTasks, "Task 1");
                    List<Integer> list1 = new ArrayList<>();
                    if (jField1.getText().length() != 0) {
                        for (String str : str1) {
                            try {
                                list1.add(Integer.parseInt(str));
                            } catch (NumberFormatException ex) {
                                addText(jLabels, fontTasks, ex.toString());
                            }
                        }
                        addText(jLabels, fontCollections, list1.toString());
                        addText(jLabels, font2, Tasks.task1(list1) + "");
                    }
                    else {
                        addText(jLabels, fontTasks, "EMPTY");
                    }

                    addText(jLabels, fontTasks, "Task 2");
                    List<String> list2 = new ArrayList<>();
                    if (jField2.getText().length() != 0) {
                        for (String str : str2) {
                            list2.add(str);
                        }
                        addText(jLabels, fontCollections, list2.toString());
                        addText(jLabels, font2, Tasks.task2(list2).toString());
                    }
                    else {
                        addText(jLabels, fontTasks, "EMPTY");
                    }

                    addText(jLabels, fontTasks, "Task 3");
                    List<Integer> list3 = new ArrayList<>();
                    if (jField3.getText().length() != 0) {
                        for (String str : str3) {
                            try {
                                list3.add(Integer.parseInt(str));
                            } catch (NumberFormatException ex) {
                                addText(jLabels, fontTasks, ex.toString());
                            }
                        }
                        addText(jLabels, fontCollections, list3.toString());
                        addText(jLabels, font2, Tasks.task3(list3).toString());
                    }
                    else {
                        addText(jLabels, fontTasks, "EMPTY");
                    }

                    addText(jLabels, fontTasks, "Task 4");
                    List<String> list4 = new ArrayList<>();
                    if (jField4.getText().length() != 0) {
                        for (String str : str4) {
                            list4.add(str);
                        }
                        addText(jLabels, fontCollections, list4.toString());
                        addText(jLabels, font2, Tasks.task4(list4, "c").toString());
                    }
                    else {
                        addText(jLabels, fontTasks, "EMPTY");
                    }

                    addText(jLabels, fontTasks, "Task 5");
                    List<Integer> list5 = new ArrayList<>();
                    if (jField5.getText().length() != 0) {
                        for (String str : str5) {
                            try {
                                list5.add(Integer.parseInt(str));
                            } catch (NumberFormatException ex) {
                                addText(jLabels, fontTasks, ex.toString());
                            }
                        }
                        try {
                            addText(jLabels, fontCollections, list5.toString());
                            addText(jLabels, font2, Tasks.task5(list5).toString());
                        } catch (NoSuchElementException ex) {
                            addText(jLabels, fontTasks, ex.toString());
                        }
                    }
                    else {
                        addText(jLabels, fontTasks, "EMPTY");
                    }

                    addText(jLabels, fontTasks, "Task 6");
                    List<Integer> list6 = new ArrayList<>();
                    if (jField6.getText().length() != 0) {
                        for (String str : str6) {
                            try {
                                list6.add(Integer.parseInt(str));
                            } catch (NumberFormatException ex) {
                                addText(jLabels, fontTasks, ex.toString());
                            }
                        }
                        addText(jLabels, fontCollections, list6.toString());
                        addText(jLabels, font2, Tasks.task6(list6) + "");
                    }
                    else {
                        addText(jLabels, fontTasks, "EMPTY");
                    }

                    addText(jLabels, fontTasks, "Task 7");
                    List<String> list7 = new ArrayList<>();
                    if (jField7.getText().length() != 0) {
                        for (String str : str7) {
                            list7.add(str);
                        }
                        addText(jLabels, fontCollections, list7.toString());
                        addText(jLabels, font2, Tasks.task7(list7).toString());
                    }
                    else {
                        addText(jLabels, fontTasks, "EMPTY");
                    }

                    for (JLabel jL : jLabels) {
                        jPanelText.add(jL);
                    }

                    jLabels.clear();
                    block.set(true);
                    jButtonRestart.setVisible(true);
                });
                jFrameList.add(jListButton);
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

    private static void addText(ArrayList<JLabel> jLabel, Font font, String str) {
        JLabel jLabelTemp = new JLabel();
        jLabelTemp.setHorizontalAlignment(JLabel.CENTER);
        jLabelTemp.setFont(font);
        jLabelTemp.setText(str);
        jLabel.add(jLabelTemp);
    }
}

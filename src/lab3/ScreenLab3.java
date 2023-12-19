package lab3;

import lab3.Chordal.Chordal;
import lab3.Chordal.Mammals.Insectivores.Hedgehogs.CommonHedgehog.CommonHedgehog;
import lab3.Chordal.Mammals.Insectivores.Hedgehogs.Hedgehogs;
import lab3.Chordal.Mammals.Insectivores.Insectivores;
import lab3.Chordal.Mammals.Mammals;
import lab3.Chordal.Mammals.Predatory.Feline.Feline;
import lab3.Chordal.Mammals.Predatory.Feline.Lynx.Lynx;
import lab3.Chordal.Mammals.Predatory.Feline.Manul.Manul;
import lab3.Chordal.Mammals.Predatory.Predatory;
import lab7.Mode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScreenLab3 implements Mode {
    private static final LinkedList<Mammals> MAMMALS_LIST = new LinkedList<>(List.of(
            new CommonHedgehog(),
            new Manul(),
            new Lynx(),
            new Manul(),
            new CommonHedgehog())
    );
    private static final LinkedList<Predatory> PREDATORY_LIST = new LinkedList<>(List.of(
            new Lynx(),
            new Lynx(),
            new Lynx(),
            new Manul())
    );
    private static final LinkedList<Hedgehogs> HEDGEHOGS_LIST = new LinkedList<>(List.of(
            new CommonHedgehog(),
            new CommonHedgehog())
    );
    private static final LinkedList<? super Hedgehogs> firstCollection = new LinkedList<>();
    private static final LinkedList<? super Feline> secondCollection = new LinkedList<>();
    private static final LinkedList<? super Predatory> thirdCollection = new LinkedList<>();
    private static final LinkedList<? super Chordal> forthCollection = new LinkedList<>();
    private static final LinkedList<? super Manul> fifthCollection = new LinkedList<>();
    private static final LinkedList<? super Insectivores> sixthCollection = new LinkedList<>();

    public void startLab(JPanel jPanel, int sizeFont) {
        System.out.println("Lab3: STARTED");

        jPanel.setLayout(new FlowLayout());
        Font font1 = new Font("Times new roman", Font.BOLD, sizeFont - 4);
        Font font2 = new Font("Times new roman", Font.ITALIC, sizeFont - 6);
        Font fontButtons = new Font("Times new roman", Font.BOLD, sizeFont + 2);

        AtomicBoolean block = new AtomicBoolean(false);

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
            firstCollection.clear();
            secondCollection.clear();
            thirdCollection.clear();
            forthCollection.clear();
            fifthCollection.clear();
            sixthCollection.clear();
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
                    addText(jLabels, font1, "MAMMALS_LIST: " + MAMMALS_LIST);
                    addText(jLabels, font1, "PREDATORY_LIST: " + PREDATORY_LIST);
                    addText(jLabels, font1, "HEDGEHOGS_LIS: " + HEDGEHOGS_LIST);
                    addListCollection(jLabels, font2);

                    segregate(MAMMALS_LIST, firstCollection, secondCollection, thirdCollection);
                    addText(jLabels, font1, "MAMMALS_LIST -> 1, 2, 3");
                    addListCollection(jLabels, font2);

                    segregate(PREDATORY_LIST, forthCollection, fifthCollection, secondCollection);
                    addText(jLabels, font1, "PREDATORY_LIST -> 1, 5, 2");
                    addListCollection(jLabels, font2);

                    segregate(HEDGEHOGS_LIST, sixthCollection, thirdCollection, thirdCollection);
                    addText(jLabels, font1, "HEDGEHOGS_LIST -> 6, 3, 3");
                    addListCollection(jLabels, font2);

                    for (JLabel jL : jLabels) {
                        jPanelText.add(jL);
                    }

                    jLabels.clear();
                    block.set(true);
                    jButtonRestart.setVisible(true);
                }
            } catch (RuntimeException ex) {
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

    private static void segregate(LinkedList<? extends Chordal> chordal,
                                  LinkedList<? super CommonHedgehog> c1,
                                  LinkedList<? super Manul> c2,
                                  LinkedList<? super Lynx> c3) {
        chordal.forEach(item -> {
            if (item instanceof CommonHedgehog) c1.addLast((CommonHedgehog) (item));
            if (item instanceof Manul) c2.addLast((Manul) (item));
            if (item instanceof Lynx) c3.addLast((Lynx) (item));
        });
    }
    private static void addText(ArrayList<JLabel> jLabel, Font font, String str) {
        JLabel jLabelTemp = new JLabel();
        jLabelTemp.setFont(font);
        jLabelTemp.setText(str);
        jLabel.add(jLabelTemp);
    }

    private static void addListCollection(ArrayList<JLabel> jLabel, Font font2) {
        addText(jLabel, font2, "1: " + firstCollection);
        addText(jLabel, font2, "2: " + secondCollection);
        addText(jLabel, font2, "3: " + thirdCollection);
        addText(jLabel, font2, "4: " + forthCollection);
        addText(jLabel, font2, "5: " + fifthCollection);
        addText(jLabel, font2, "6: " + sixthCollection);
    }
}

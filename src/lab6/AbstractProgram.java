package lab6;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class AbstractProgram implements Runnable {
    private static final Font font = new Font("EightBits", Font.PLAIN, 30);
    public final Object mutex = new Object();
    public Conditions abstractProgramCondition = Conditions.UNKNOWN;
    public Boolean interruptFlag = false;
    public enum Conditions {
        UNKNOWN,
        STOPPING,
        RUNNING,
        FATAL_ERROR
    }

    private static Conditions randomCondition() {
        int pick = new Random().nextInt(Conditions.values().length);
        return Conditions.values()[pick];
    }

    @Override
    public void run() {
        JFrame jFrame = createFrame();
        final int[] block = {1};

        JPanel jPanelText = new JPanel(new GridLayout(40, 1));
        jPanelText.setBounds(10, 0, jFrame.getWidth(), jFrame.getHeight() - 50);
        jPanelText.setBackground(Color.BLACK);
        jFrame.add(jPanelText);

        JButton jButton = new JButton(String.format("<html><font color='green'>%s</font></html>", "Close AbstractProgram"));
        jButton.addActionListener(e -> jFrame.dispose());
        jButton.setBounds(0, jFrame.getHeight() - 50, jFrame.getWidth(), 50);
        jButton.setBackground(Color.BLACK);
        jButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
        jButton.setFont(font);

        ArrayList<JLabel> jLabels = new ArrayList<>();

        addText(jLabels, "AbstractProgram: started");
        outputText(jLabels, jPanelText);
        Thread daemon = new Thread(() -> {
            while (!interruptFlag) {
                Utils.sleep(10000);
                synchronized (mutex) {
                    addText(jLabels, "------------------------------------------------------------------");
                    addText(jLabels, "Current abstractProgramCondition = " + abstractProgramCondition);
                    outputText(jLabels, jPanelText);
                    if (block[0] != 6) {
                        abstractProgramCondition = randomCondition();
                    }
                    else {
                        abstractProgramCondition = Conditions.FATAL_ERROR;
                    }
                    addText(jLabels, "New abstractProgramCondition = " + abstractProgramCondition);
                    outputText(jLabels, jPanelText);
                    block[0] += 1;
                    mutex.notify();
                }
                Utils.sleep(5000);
            }
            JOptionPane.showMessageDialog(jFrame, "Программа \"AbstractProgram\" завершила работу.");
            jFrame.add(jButton);
            jFrame.revalidate();
            jFrame.repaint();
        });
        daemon.setDaemon(true);
        daemon.start();
    }

    private static void addText(ArrayList<JLabel> jLabel, String str) {
        JLabel jLabelTemp = new JLabel();
        jLabelTemp.setFont(AbstractProgram.font);
        jLabelTemp.setText(String.format("<html><font color='green'>%s</font></html>", str));
        jLabelTemp.setHorizontalAlignment(JLabel.CENTER);
        jLabel.add(jLabelTemp);
    }

    private static void outputText(ArrayList<JLabel> jLabels, JPanel jPanelText) {
        for (JLabel jL : jLabels) {
            jPanelText.add(jL);
        }
        jLabels.clear();
    }

    private static JFrame createFrame() {
        JFrame jFrame = new JFrame("AbstractProgram");
        jFrame.getContentPane().setBackground(Color.BLACK);
        jFrame.setLayout(null);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setSize(dimension.width / 2, dimension.height);
        jFrame.setLocation(dimension.width / 2, 0);
        jFrame.setUndecorated(true);
        jFrame.setVisible(true);
        return jFrame;
    }
}
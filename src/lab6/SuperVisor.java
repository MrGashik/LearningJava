package lab6;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SuperVisor implements Runnable {

    //private static final Thread abstractProgram = new Thread(new AbstractProgram());
    private static final Font font = new Font("EightBits", Font.PLAIN, 30);

    @Override
    public void run() {
        JFrame jFrame = createFrame();

        JPanel jPanelText = new JPanel(new GridLayout(40, 1));
        jPanelText.setBounds(10, 0, jFrame.getWidth(), jFrame.getHeight() - 50);
        jPanelText.setBackground(Color.BLACK);
        jFrame.add(jPanelText);

        JButton jButton = new JButton(String.format("<html><font color='blue'>%s</font></html>", "Close SuperVisor"));
        jButton.addActionListener(e -> jFrame.dispose());
        jButton.setBounds(0, jFrame.getHeight() - 50, jFrame.getWidth(), 50);
        jButton.setBackground(Color.BLACK);
        jButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        jButton.setFont(font);

        ArrayList<JLabel> jLabels = new ArrayList<>();

        addText(jLabels, "SuperVisor: started");
        outputText(jLabels, jPanelText);

        AbstractProgram ap = new AbstractProgram();
        Thread thread = new Thread(ap);
        thread.start();
        while (!ap.interruptFlag) {
            synchronized (ap.mutex) {
                try {
                    addText(jLabels, "SuperVisor: wait");
                    outputText(jLabels, jPanelText);
                    ap.mutex.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                addText(jLabels, "------------------------------------------------------------------");
                addText(jLabels, "SuperVisor: notified");
                outputText(jLabels, jPanelText);
            }
            switch (ap.abstractProgramCondition) {
                case UNKNOWN -> startProgram(ap, jLabels);
                case STOPPING -> restartProgram(ap, jLabels);
                case FATAL_ERROR -> stopProgram(ap, jLabels);
                default -> addText(jLabels, "SuperVisor: abstractProgram is OK");
            }
            outputText(jLabels, jPanelText);
            if (ap.abstractProgramCondition == AbstractProgram.Conditions.FATAL_ERROR) {
                JOptionPane.showMessageDialog(jFrame, "Программа \"SuperVisor\" завершила работу.");
                jFrame.add(jButton);
                jFrame.revalidate();
                jFrame.repaint();
            }
        }
    }

    private void startProgram(AbstractProgram ap, ArrayList<JLabel> jL) {
        ap.abstractProgramCondition = AbstractProgram.Conditions.RUNNING;
        addText(jL, "SuperVisor: start program");
    }

    private void restartProgram(AbstractProgram ap, ArrayList<JLabel> jL) {
        ap.abstractProgramCondition = AbstractProgram.Conditions.RUNNING;
        addText(jL, "SuperVisor: restart program");
    }

    private void stopProgram(AbstractProgram ap, ArrayList<JLabel> jL) {
        ap.interruptFlag = true;
        addText(jL, "SuperVisor: stop program");
    }

    private static void addText(ArrayList<JLabel> jLabel, String str) {
        JLabel jLabelTemp = new JLabel();
        jLabelTemp.setFont(SuperVisor.font);
        jLabelTemp.setText(String.format("<html><font color='blue'>%s</font></html>", str));
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
        JFrame jFrame = new JFrame("SuperVisor");
        jFrame.getContentPane().setBackground(Color.BLACK);
        jFrame.setLayout(null);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setSize(dimension.width / 2, dimension.height);
        jFrame.setLocation(0, 0);
        jFrame.setUndecorated(true);
        jFrame.setVisible(true);
        return jFrame;
    }
}
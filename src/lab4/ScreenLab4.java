package lab4;

import lab4.Exceptions.FileReadException;
import lab4.Exceptions.InvalidFileFormatException;
import lab4.Translator.Translator;
import lab7.Mode;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScreenLab4 implements Mode {
    public void startLab(JPanel jPanel, int sizeFont) {
        System.out.println("Lab4: STARTED");

        jPanel.setLayout(new BorderLayout());

        final String[] fileDictionary = {""};
        String fileName = "src/CustomFile/input.txt";

        JLabel jLabelIcon = new JLabel();
        jLabelIcon.setIcon(new ImageIcon("C:/Desktop/Java/mockery.gif"));
        jLabelIcon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jLabelIcon.setHorizontalAlignment(JLabel.CENTER);

        JTextField jTextField = new JTextField("Ваш текст на английском" , 20);
        jTextField.setFont(new Font("Times new roman", Font.PLAIN, sizeFont + 2));
        jTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jTextField.setHorizontalAlignment(JLabel.CENTER);

        JLabel jLabelText = new JLabel();
        jLabelText.setFont(new Font("Times new roman", Font.BOLD, sizeFont + 2));
        jLabelText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jLabelText.setHorizontalAlignment(JLabel.CENTER);

        JButton jButton = new JButton("Перевести");
        jButton.addActionListener(e -> {
            jLabelText.removeAll();
            if (jTextField.getText().charAt(jTextField.getText().length() - 1) == '.') {
                if (jTextField.getText().length() <= 60) {
                    List<String> fileLines = new ArrayList<>();
                    fileLines.add(jTextField.getText());
                    try {
                        Files.write(Paths.get(fileName), fileLines);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    JFrame jFrameDictionary = new JFrame("Ввод словаря");
                    jFrameDictionary.getContentPane().setBackground(Color.BLACK);
                    jFrameDictionary.setLayout(new FlowLayout());
                    jFrameDictionary.setSize(300, 150);
                    jFrameDictionary.setVisible(true);

                    JTextField jDictionaryField = new JTextField("", 20);
                    jDictionaryField.setFont(new Font("Times new roman", Font.PLAIN, sizeFont + 2));
                    jDictionaryField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    jDictionaryField.setHorizontalAlignment(JLabel.CENTER);
                    jDictionaryField.setBounds(0, 0, jFrameDictionary.getHeight(), 50);

                    JButton jDictionaryButton = new JButton("Готово");
                    jDictionaryButton.addActionListener(ev -> {
                        if (jDictionaryField.getText().length() != 0) {
                            if (!jDictionaryField.getText().endsWith(".txt")) {
                                JOptionPane.showMessageDialog(jTextField, "Неправильный формат, ожидаемый формат .txt");
                            } else {
                                fileDictionary[0] = jDictionaryField.getText();
                                jDictionaryButton.setBounds(0, 50, jFrameDictionary.getHeight(), 50);
                                try {
                                    jFrameDictionary.setVisible(false);
                                    Translator translator = new Translator("src/CustomFile/" + fileDictionary[0]);
                                    String translatedFile = translator.translateFile(fileName);
                                    jLabelText.setText(translatedFile);
                                } catch (InvalidFileFormatException | FileReadException ex) {
                                    jLabelText.setText(ex.toString());
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(jPanel, "Введите путь к файлу!");
                        }
                    });

                    jFrameDictionary.add(jDictionaryField);
                    jFrameDictionary.add(jDictionaryButton);
                } else {
                    JOptionPane.showMessageDialog(jPanel, "Строка должна содержать не более 60 символов.");
                }
            } else {
                JOptionPane.showMessageDialog(jPanel, "Строка должна заканчиваться точкой.");
            }
        });
        jButton.setBackground(Color.WHITE);
        jButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jButton.setFont(new Font("Times new roman", Font.BOLD, sizeFont + 18));

        jPanel.add(jTextField, BorderLayout.NORTH);
        jPanel.add(jLabelText, BorderLayout.SOUTH);
        jPanel.add(jButton, BorderLayout.EAST);
        jPanel.add(jLabelIcon);
    }
}

package lab4.Translator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lab4.Exceptions.*;

import javax.swing.*;

public class Translator {

    // Словарь
    private final HashMap<String, String> dictionary;
    private int maxLength;
    private ArrayList<String> containsArray;

    public static int wordsCount(String str) {
        int count = 0;
        int index = 0;
        String target = " ";
        while ((index = str.indexOf(target, index)) != -1) {
            count++;
            index++;
        }
        return count + 1;
    }

    public Translator(String fileName) throws InvalidFileFormatException, FileReadException {
        this.dictionary = new HashMap<>();
        File file = new File(fileName);

        // Читаем файл со словарём
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");

                if (parts.length != 2) {
                    throw new InvalidFileFormatException("Invalid file format");
                }

                dictionary.put(parts[0].toLowerCase().trim(), parts[1].toLowerCase().trim());
                maxLength = Math.max(wordsCount(parts[0]), maxLength);
            }
        } catch (IOException e) {
            throw new FileReadException("Cannot read file " + fileName);
        }
    }

    private int findIndexOfFirstSymbol(String str) {
        String elem;
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Pattern patternSpace = Pattern.compile(" ");

        for (int i = 0; i < str.length(); i++) {
            elem = String.valueOf(str.charAt(i));
            Matcher matcher = pattern.matcher(elem);
            Matcher matcherSpace = patternSpace.matcher(elem);
            if (!(matcher.find() || matcherSpace.find())) {
                return i;
            }
        }
        return -1;
    }

    private int isStrSubstr(String str) {
        int counter = 0;
        String tempStr = str;
        containsArray = new ArrayList<>();
        int index = findIndexOfFirstSymbol(tempStr);
        if (index != -1) {
            tempStr = tempStr.substring(0, index);
        }
        for (HashMap.Entry<String, String> entry : dictionary.entrySet()) {
            if (entry.getKey().contains(tempStr)) {
                counter++;
                containsArray.add(entry.getKey());
            }
        }
        return counter;
    }
    private StringBuilder translateLine(ArrayList<String> arr) {
        int counter;
        int index = 0;
        int numberOfWords;
        String maxLengthTranslate;
        String resultLine = null;
        char elem = 0;
        StringBuilder translatedLine = new StringBuilder();

        while(index != arr.size()) {
            if (isStrSubstr(arr.get(index)) == 0) {
                translatedLine.append(arr.get(index)).append(" ");
                index++;
            } else {
                String tempLine = "";
                maxLengthTranslate = "";
                counter = 0;
                numberOfWords = 0;
                for (int i = 0; i < maxLength; i++) {
                    if (i > 0) {
                        tempLine += " ";
                    }
                    tempLine += arr.get(index + i);
                    numberOfWords++;
                    elem = 0;
                    int indexFirst = findIndexOfFirstSymbol(tempLine);
                    if (indexFirst != -1) {
                        elem = tempLine.charAt(indexFirst);
                        i = maxLength;
                        resultLine = tempLine;
                        tempLine = tempLine.substring(0, indexFirst);
                    }
                    for (String s : containsArray) {
                        if (s.equals(tempLine)) {
                            maxLengthTranslate = s;
                            counter = numberOfWords;
                        }
                    }
                }
                if ("".equals(maxLengthTranslate)) {
                    translatedLine.append(arr.get(index));
                    index++;
                } else {
                    translatedLine.append(translateWord(maxLengthTranslate));
                    index += counter;
                }
                if (elem != 0) {
                    if(resultLine.substring(0, resultLine.length() - 1).equals(maxLengthTranslate))
                        translatedLine.append(elem);
                }
                translatedLine.append(" ");
            }
        }
        return translatedLine;
    }

    private String translateWord(String word) {
        String lowerCaseWord = word.toLowerCase();

        if (dictionary.containsKey(lowerCaseWord)) {
            int index = findIndexOfFirstSymbol(lowerCaseWord);
            if(index != -1) {
                return dictionary.get(lowerCaseWord) + lowerCaseWord.charAt(index);
            }
            return dictionary.get(lowerCaseWord);
        }
        else {
            return word;
        }
    }

    public String translateFile(String fileName) throws FileReadException {
        File file = new File(fileName);
        StringBuilder result = new StringBuilder();

        // Читаем входной файл
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            ArrayList<String> arr;
            while ((line = reader.readLine()) != null) {
                StringBuilder translatedLine;
                arr = new ArrayList<>(Arrays.asList(line.toLowerCase().trim().split(" +")));
                translatedLine = translateLine(arr);
                result.append(translatedLine.toString().trim()).append("\n");
            }
        } catch (IOException e) {
            throw new FileReadException("Cannot read file " + fileName);
        }
        return result.toString();
    }
}

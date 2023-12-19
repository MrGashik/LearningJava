package lab3;

import static Settings.ColorsPull.*;

import lab3.Chordal.Chordal;
import lab3.Chordal.Mammals.Mammals;
import lab3.Chordal.Mammals.Insectivores.Insectivores;
import lab3.Chordal.Mammals.Insectivores.Hedgehogs.Hedgehogs;
import lab3.Chordal.Mammals.Insectivores.Hedgehogs.CommonHedgehog.CommonHedgehog;
import lab3.Chordal.Mammals.Predatory.Predatory;
import lab3.Chordal.Mammals.Predatory.Feline.Feline;
import lab3.Chordal.Mammals.Predatory.Feline.Lynx.Lynx;
import lab3.Chordal.Mammals.Predatory.Feline.Manul.Manul;

import java.util.LinkedList;
import java.util.List;

public class Main {
    private static final LinkedList<Mammals> MAMMALS_LIST = new LinkedList<>(List.of(new CommonHedgehog(), new Manul(), new Lynx(), new Manul(), new CommonHedgehog()));
    private static final LinkedList<Predatory> PREDATORY_LIST = new LinkedList<>(List.of(new Lynx(), new Lynx(), new Lynx(), new Manul()));
    private static final LinkedList<Hedgehogs> HEDGEHOGS_LIST = new LinkedList<>(List.of(new CommonHedgehog(), new CommonHedgehog()));
    private static final LinkedList<? super Hedgehogs> firstCollection = new LinkedList<>();
    private static final LinkedList<? super Feline> secondCollection = new LinkedList<>();
    private static final LinkedList<? super Predatory> thirdCollection = new LinkedList<>();
    private static final LinkedList<? super Chordal> forthCollection = new LinkedList<>();
    private static final LinkedList<? super Manul> fifthCollection = new LinkedList<>();
    private static final LinkedList<? super Insectivores> sixthCollection = new LinkedList<>();

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


    public static void main(String[] args) {
        try {
            System.out.println("MAMMALS_LIST: " + MAMMALS_LIST);
            System.out.println("PREDATORY_LIST: " + PREDATORY_LIST);
            System.out.println("HEDGEHOGS_LIS: " + HEDGEHOGS_LIST);
            System.out.println("1: " + firstCollection);
            System.out.println("2: " + secondCollection);
            System.out.println("3: " + thirdCollection);
            System.out.println("4: " + forthCollection);
            System.out.println("5: " + fifthCollection);
            System.out.println("6: " + sixthCollection);

            segregate(MAMMALS_LIST, firstCollection, secondCollection, thirdCollection);
            System.out.println("MAMMALS_LIST -> 1, 2, 3");
            System.out.println(ANSI_GREEN + "1: " + firstCollection + ANSI_RESET);
            System.out.println(ANSI_GREEN + "2: " + secondCollection + ANSI_RESET);
            System.out.println(ANSI_GREEN + "3: " + thirdCollection + ANSI_RESET);
            System.out.println("4: " + forthCollection);
            System.out.println("5: " + fifthCollection);
            System.out.println("6: " + sixthCollection);
            segregate(PREDATORY_LIST, forthCollection, fifthCollection, secondCollection);
            System.out.println("PREDATORY_LIST -> 1, 5, 2");
            System.out.println(ANSI_RED + "1: " + firstCollection + ANSI_RESET);
            System.out.println(ANSI_GREEN + "2: " + secondCollection + ANSI_RESET);
            System.out.println("3: " + thirdCollection);
            System.out.println("4: " + forthCollection);
            System.out.println(ANSI_GREEN + "5: " + fifthCollection + ANSI_RESET);
            System.out.println("6: " + sixthCollection);
            segregate(HEDGEHOGS_LIST, sixthCollection, thirdCollection, thirdCollection);
            System.out.println("HEDGEHOGS_LIST -> 6, 3, 3");
            System.out.println("1: " + firstCollection);
            System.out.println("2: " + secondCollection);
            System.out.println(ANSI_RED + "3: " + thirdCollection + ANSI_RESET);
            System.out.println("4: " + forthCollection);
            System.out.println("5: " + fifthCollection);
            System.out.println(ANSI_GREEN + "6: " + sixthCollection + ANSI_RESET);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
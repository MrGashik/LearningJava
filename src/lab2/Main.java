package lab2;

import static Settings.ColorsPull.*;

import lab1.Hero;
import lab1.strategy.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        MyClass test = new MyClass();
        Hero man = new Hero("MrGashik", new StrategyStand());
        Integer level = 10;
        Double pi = 3.1416;
        String words = "Hello World!";

        try {
            callAnnotatedMethods(test, level, man, pi, words);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void callAnnotatedMethods(Object object, Integer num, Hero hero, Double math, String str)
            throws InvocationTargetException, IllegalAccessException {
        Vector<Object> arrObj = new Vector<>();
        Class<?> usedClass = object.getClass();
        for (Method method: usedClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                if (Modifier.isPrivate(method.getModifiers()) || Modifier.isProtected(method.getModifiers())) {
                    Class<?>[] type = method.getParameterTypes();
                    int length = type.length;
                    for (Class<?> t : type) {
                        switch (t.getName()) {
                            case "lab1.Hero" -> arrObj.add(hero);
                            case "java.lang.Integer" -> arrObj.add(num);
                            case "java.lang.Double" -> arrObj.add(math);
                            case "java.lang.String" -> arrObj.add(str);
                        }
                    }
                    MyAnnotation currentAnnotation = method.getAnnotation(MyAnnotation.class);
                    int number = currentAnnotation.value();
                    System.out.println(ANSI_GREEN + method.getName() + ": " + currentAnnotation + ANSI_RESET);
                    switch (number) {
                        case 2 -> hero.changeStrategyMove(new StrategySwim());
                        case 3 -> hero.changeStrategyMove(new StrategyWalk());
                        case 4 -> hero.changeStrategyMove(new StrategyRideAPony());
                        case 5 -> hero.changeStrategyMove(new StrategyRideAHorse());
                        case 6 -> hero.changeStrategyMove(new StrategyFly());
                        default -> hero.changeStrategyMove(new StrategyStand());
                    }
                    method.setAccessible(true);
                    for (int i = 0; i < number; i++) {
                        switch (length) {
                            case 1 -> System.out.println(
                                    method.invoke(object,
                                    arrObj.elementAt(0))
                            );
                            case 2 -> System.out.println(
                                    method.invoke(object,
                                    arrObj.elementAt(0),
                                    arrObj.elementAt(1))
                            );
                            case 3 -> System.out.println(
                                    method.invoke(object,
                                    arrObj.elementAt(0),
                                    arrObj.elementAt(1),
                                    arrObj.elementAt(2))
                            );
                            default -> System.out.println(
                                    method.invoke(object)
                            );
                        }
                    }
                    method.setAccessible(false);
                    arrObj.clear();
                }
            }
        }
    }
}
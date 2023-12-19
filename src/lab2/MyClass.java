package lab2;

import lab1.Hero;

@SuppressWarnings("unused")
public class MyClass {
    @MyAnnotation(4)
    private String privateMethod(Integer num, Hero hero) {
        return "Уровень " + hero.getName() + ": " + num + "; " + hero.move();
    }

    private String firstMethod(Integer num) {
        return num + ".";
    }

    @MyAnnotation(2)
    public String publicMethod(Hero hero, Double pi) {
        return hero.getName() + " сказал, что число пи это " + pi + ".";
    }

    public String secondMethod(Integer num, Hero hero, Integer pi) {
        return "Игрок #" + num + ": " + hero.getName() + " сказал, что число пи это " + pi + ".";
    }

    @MyAnnotation(5)
    protected String protectedMethod(Integer num, Hero hero, String str) {
        return "Игрок #" + num + ": " + hero.getName() + " сказал: \"" + str + "\".";
    }

    protected String thirdMethod(Hero hero, String str) {
         return hero.getName() + " сказал: \"" + str + "\".";
    }
}
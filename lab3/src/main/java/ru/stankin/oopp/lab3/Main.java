package ru.stankin.oopp.lab3;

import java.util.Vector;

public class Main{

    public static void main(String[] args) throws Exception {
        Vector<Parent> BD_1 = new Vector<>();
        Vector<Parent> BD_2 = new Vector<>();

        System.out.println("init BD_1");
        BD_1.add(new Parent("parent1"));
        BD_1.add(new Parent("parent2"));
        BD_1.add(new Child("child1"));
        BD_1.add(new Child("child2"));

        System.out.println("copy BD_1 to BD_2");
        copy(BD_1, BD_2);

        System.out.println("destroy BD_1");
        BD_1 = null;
        System.gc(); //принудительный вызов сборщика мусора для удаления из памяти BD_1 и всех объектов внутри
        Thread.sleep(100); //ожидание, чтобы сборщик мусора успел удалить все объекты BD_1 и все объекты внутри

        System.out.println("print BD_2");
        print(BD_2);
    }

    /**
     * Функция для копирования всех объектов класса Parent из Vector source в Vector destination
     */
    public static void copy(Vector<Parent> source, Vector<Parent> destination) {
        for(Parent parent: source) {
            destination.add(parent.copy());
        }
    }

    /**
     * Функция для печати всех str членов класса Parent в Vector source
     */
    public static void print(Vector<Parent> source) {
        for(Parent parent: source) {
            System.out.println(parent.getInstanceName() + " str: " + parent.getStr());
        }
    }
}

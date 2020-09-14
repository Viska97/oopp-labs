package ru.stankin.oopp.lab1.task1;

import java.util.Vector;

public class Main {

    public static void main(String[] args){
        Vector<Base> vector = new Vector<>();

        vector.add(new Base());
        vector.add(new Derived());
        vector.add(new Derived());
        vector.add(new Base());

        System.out.println("Objects in vector: " + vector.size());

        System.out.println("Call function del");

        del(vector);

        System.out.println("Objects in vector: " + vector.size());
    }


    public static <T> void del(Vector<T> vector) {
        vector.clear();
        // в Java имеется автоматический сборщик мусора, который уничтожает объекты и обычно вызывать его вручную не нужно
        // в данном случае, чтобы сборщик мусора успел отработать до окончания метода, вызываем его принудительно
        System.gc();
    }
}

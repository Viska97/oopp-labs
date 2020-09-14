package ru.stankin.oopp.lab1.task2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Base[] objects = new Base[4]; //инициализируем массив из объектов

        create(objects); //заполняем массив объектами Base и Derived

        init(objects); //инициализруем массивы внутри объектов произвольными числами в диапазоне

        print(objects); //печатаем данные массивов

        del(objects); //освобождаем память
    }

    /**
     * Функция заполняет массив объектами классов Base и Derived
     */
    public static void create(Base[] objects) {
        if(objects.length < 4) {
            throw new IllegalArgumentException("Objects array length is smaller than 4");
        }
        objects[0] = new Base();
        objects[1] = new Base();
        objects[2] = new Derived();
        objects[3] = new Derived();
    }

    /**
     * Функция инициализирует массивы внутри объектов произвольными числами в диапазоне от 0 до 100
     */
    public static void init(Base[] objects) {
        for(Base object: objects) {
            object.init(0, 100);
        }
    }

    /**
     * Функция печатает соедржимое массивов внутри объектов
     */
    public static void print(Base[] objects) {
        for(Base object: objects) {
            object.print();
        }
    }

    /**
     * Функция освобождает массив и память, выделенную под объекты
     */
    public static void del(Base[] objects) {
        Arrays.fill(objects, null);

        System.gc();
    }
}

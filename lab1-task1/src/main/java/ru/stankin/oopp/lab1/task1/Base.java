package ru.stankin.oopp.lab1.task1;

public class Base {

    //стандартный конструктор для вывода сообщения об иницилиализации объекта данного класса
    public Base() {
        System.out.println("Call constructor of class " + getClass().getSimpleName());
    }

    //в Java при уничтожении объекта сборщиком мусора вызывается метод finalize
    @Override
    protected void finalize() {
        System.out.println("Call function finalize of class " + getClass().getSimpleName());
    }
}

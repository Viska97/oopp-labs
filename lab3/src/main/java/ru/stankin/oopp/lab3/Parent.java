package ru.stankin.oopp.lab3;

public class Parent {

    private final String str;

    /**
     * Конструктор с str параметром
     */
    public Parent(String str) {
        this.str = str;
        System.out.println("Constructor of class " + getInstanceName() + " with str: " + str + " initialized");
    }

    /**
     * Копирующий конструктор
     */
    public Parent(Parent parent) {
        this(parent.str);
    }

    /**
     * Функция для получения str
     */
    public String getStr() {
        return str;
    }

    /**
     * Функция для копирования (создает новый объект данного класса на основе текущего)
     */
    public Parent copy() {
        return new Parent(this);
    }

    /**
     * Функция, возвращающая имя класса и его идентификатор, присвоенный системой
     */
    public String getInstanceName() {
        return getClass().getSimpleName() + "@" + System.identityHashCode(this);
    }

    /**
     * Функция печатает в консоль сообщение об уничтожении объекта класса
     */
    @Override
    protected void finalize() {
        System.out.println("Object of class " + getInstanceName() + " destroyed");
    }
}

package ru.stankin.oopp.lab3;

public class Child extends Parent {

    /**
     * Конструктор с str параметром, вызывающий конструктор родительского класса
     */
    public Child(String str) {
        super(str);
    }

    /**
     * Копирующий конструктор, вызывающий конструктор родительского класса
     */
    public Child(Parent parent) {
        super(parent);
    }

    /**
     * Переопределенная функция для копирования (создает новый объект данного класса на основе текущего)
     */
    @Override
    public Parent copy() {
        return new Child(this);
    }
}

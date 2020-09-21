package ru.stankin.oopp.lab1.task2;

import java.util.Random;

public class Base {

    protected final int[] elements = new int[10];

    public void init(int min, int max) {
        final Random random = new Random();
        for(int i = 0; i < 10; i++) {
            elements[i] = random.nextInt((max - min) + 1) + min;
        }
    }

    public void print() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName()).append(" ").append("elements: [");
        int count = 0;
        for(int element : elements) {
            if((element%2)==0) {
                if (++count > 1) builder.append(", ");
                builder.append(element);
            }
        }
        builder.append("]");
        System.out.println(builder.toString());
    }

    //в Java при уничтожении объекта сборщиком мусора вызывается метод finalize
    @Override
    protected void finalize() {
        System.out.println("Object of class " + getClass().getSimpleName() + " destroyed");
    }
}

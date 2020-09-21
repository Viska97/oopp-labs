package ru.stankin.oopp.lab1.task2;

public class Derived extends Base {

    @Override
    public void print() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName()).append(" ").append("elements: [");
        int count = 0;
        for(int element : elements) {
            if((element%2)!=0) {
                if (++count > 1) builder.append(", ");
                builder.append(element);
            }
        }
        builder.append("]");
        System.out.println(builder.toString());
    }
}

package ru.stankin.oopp.lab2.details;

import java.io.Serializable;

public abstract class Detail implements Serializable {

    public abstract String getCategory();

    public abstract String getName();

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Detail detail = (Detail) obj;
        return this.hashCode() == detail.hashCode();
    }

    @Override
    public final int hashCode() {
        String id = getCategory() + getName();
        return id.hashCode();
    }
}

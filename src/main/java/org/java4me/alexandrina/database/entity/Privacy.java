package org.java4me.alexandrina.database.entity;

public enum Privacy {
    PUBLIC, PRIVATE;

    @Override
    public String toString() {
        return name();
    }
}

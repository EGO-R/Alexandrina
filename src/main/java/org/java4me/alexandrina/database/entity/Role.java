package org.java4me.alexandrina.database.entity;

public enum Role {
    USER, ADMIN;

    @Override
    public String toString() {
        return name();
    }
}

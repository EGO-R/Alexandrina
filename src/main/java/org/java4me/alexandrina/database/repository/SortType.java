package org.java4me.alexandrina.database.repository;

public enum SortType {
    ASC, DESC;

    @Override
    public String toString() {
        return name();
    }
}

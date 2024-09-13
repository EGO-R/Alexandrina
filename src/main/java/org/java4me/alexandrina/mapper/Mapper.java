package org.java4me.alexandrina.mapper;

public interface Mapper<F, T> {
    T map(F obj);

    default T mapObject(F fromObj, T toObj) {
        return toObj;
    }
}

package ru.Vladimir;

/**
 * Created by Vladimir_Danilov on 18-Dec-14.
 */
public class X implements I {

    @Override
    public void doIt() {
        System.out.println("X class");
    }

    public I getI() {
        return this;
    }
}

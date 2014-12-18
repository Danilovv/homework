package ru.Vladimir;

/**
 * Created by Vladimir_Danilov on 18-Dec-14.
 */
public class A implements I {
    public I getI() {
        return this;
    }

    @Override
    public void doIt() {
        System.out.println("I did it by myself");
    }
}

package ru.Vladimir;

/**
 * Created by Vladimir_Danilov on 18-Dec-14.
 */
public class C {
    public static class StaticInner implements I {
        @Override
        public void doIt() {
            System.out.println("I did it inner statically");
        }

        public  I getI() {
            return new StaticInner();
        }
    }
}

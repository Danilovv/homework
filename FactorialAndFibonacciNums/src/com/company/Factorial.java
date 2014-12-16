package com.company;

/**
 * Created by Vladimir_Danilov on 16-Dec-14.
 */
public class Factorial {
        public static long calc(long x) {
            return x<=1 ? x : x*calc(x-1);
        }
}

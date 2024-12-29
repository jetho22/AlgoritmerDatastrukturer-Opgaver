package com.jeppe.lecture_1;

public class Linear {
    public static boolean linear(String s, char c, int l) {
        if (l < 1) {
            return false;
        }

        if (s.length() != l) {
            return false;
        }

        if (s.charAt(l-1) == c) {
            return true;
        }

        return linear(s, c, l-1);
    }
}

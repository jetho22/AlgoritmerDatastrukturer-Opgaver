package com.jeppe.lecture_5;

import java.util.Arrays;

public class Anagram {
    public static boolean isAnagram(String a, String b) {
        String trimmedA = a.trim();
        String trimmedB = b.trim();

        if (trimmedA.length() != trimmedB.length()) return false;

        char[] arrayA = trimmedA.toCharArray();
        char[] arrayB = trimmedB.toCharArray();

        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        return Arrays.equals(arrayA, arrayB);
    }

    public static void main(String[] args) {
        System.out.println("Is stale an anagram of least? " + isAnagram("stale", "least"));
        System.out.println("Is hello an anagram of world? " + isAnagram("hello", "world"));
    }
}

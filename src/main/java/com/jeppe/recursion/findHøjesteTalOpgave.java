package com.jeppe.recursion;

public class findHøjesteTalOpgave {

    public static int findTal(int N, int count) {
        if (N < 1) {
            return 0;
        }
        if (N == 1) {
            return count + 1;
        }

        if (N % 2 == 0) {
            return findTal(N / 2, count + 1);
        } else {
            return findTal(3 * N + 1, count + 1);
        }
    }

    public static int findHøjesteTal() {
        int tal = 10000;
        int allerHøjeste = 0;
        int start = 0;
        for (int i = 1; i < tal; i++) {
            int højeste = findTal(i, 0);
            if (højeste > allerHøjeste) {
                allerHøjeste = højeste;
                start = i;
            }
        }
        System.out.println("Longest sequence starts at: " + start + " with " + allerHøjeste + " steps");
        return start;
    }

    public static void main(String[] args) {
        System.out.println(findHøjesteTal());
    }
}
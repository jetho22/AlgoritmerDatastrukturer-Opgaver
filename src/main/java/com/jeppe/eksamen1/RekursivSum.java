package com.jeppe.eksamen1;

public class RekursivSum {

    // Rekursiv metode til at beregne summen af tal deleligt med 3 eller 8
    public static int sumDeleligMedTreOgOtte(int N) {
        // Basis-tilfælde: Hvis N er mindre end 1, returnér 0
        if (N < 1) {
            return 0;
        }

        // Tjek om N er deleligt med 3 eller 8
        if (N % 3 == 0 || N % 8 == 0) {
            return N + sumDeleligMedTreOgOtte(N - 1); // Inkludér N og gå videre
        }

        // Ellers, gå videre uden at inkludere N
        return sumDeleligMedTreOgOtte(N - 1);
    }

    public static void main(String[] args) {
        int N = 26; // Testparameter
        int resultat = sumDeleligMedTreOgOtte(N);
        System.out.println("Summen af tal deleligt med 3 eller 8 op til " + N + " er: " + resultat);
    }
}


package com.jeppe.eksamen2;

public class AntalVokalerRekursivt {
    // Hjælpefunktion til at afgøre, om et tegn er en vokal
    public static boolean erVokal(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
    }

    // Rekursiv funktion til at tælle vokaler
    public static int antalVokaler(String str, int l) {
        // Basis-tilfælde: Hvis længden er negativ, returner 0
        if (l < 0) {
            return 0;
        }

        // Tæl 1, hvis det aktuelle tegn er en vokal, ellers 0
        int count = erVokal(str.charAt(l)) ? 1 : 0;

        // Rekursivt kald på resten af strengen
        return count + antalVokaler(str, l - 1);
    }

    public static void main(String[] args) {
        String testStreng = "stationsbygninger"; // Eksempel
        int længde = testStreng.length() - 1; // Start fra sidste indeks

        int vokalAntal = antalVokaler(testStreng, længde);
        System.out.println("Antal vokaler i '" + testStreng + "': " + vokalAntal);
    }
}

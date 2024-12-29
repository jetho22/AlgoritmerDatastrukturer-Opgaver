package com.jeppe.Hashing;

import java.util.Arrays;

public class HashTableProbingChar {


    static final int TABLE_SIZE = 16;

    // Hash function
    public static int hashFunction(int key) {
        return (key) % TABLE_SIZE;
    }

    // Linear probing
    public static void linearProbing(char[] keys) {
        String[] table = new String[TABLE_SIZE];
        Arrays.fill(table, "-"); // Initialize table

        for (char key : keys) {
            int k = key - 'A' + 1; // Alphabetical position
            int index = hashFunction(k);

            // Linear probing
            while (!table[index].equals("-")) {
                index = (index + 1) % TABLE_SIZE;
            }
            table[index] = Character.toString(key);
        }

        System.out.println("Linear Probing Table:");
        printTable(table);
    }

    // Quadratic probing
    public static void quadraticProbing(char[] keys) {
        String[] table = new String[TABLE_SIZE];
        Arrays.fill(table, "-");

        for (char key : keys) {
            int k = key - 'A' + 1;
            int index = hashFunction(k);
            int i = 0;

            // Quadratic probing
            while (!table[(index + i * i) % TABLE_SIZE].equals("-")) {
                i++;
            }
            table[(index + i * i) % TABLE_SIZE] = Character.toString(key);
        }

        System.out.println("Quadratic Probing Table:");
        printTable(table);
    }

    // Print the hash table
    public static void printTable(String[] table) {
        for (int i = 0; i < table.length; i++) {
            System.out.println("Index " + i + ": " + table[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        char[] linearKeys = {'D', 'E', 'M', 'O', 'C', 'R', 'A', 'T'};
        char[] quadraticKeys = {'R', 'E', 'P', 'U', 'B', 'L', 'I', 'C', 'A', 'N'};

        linearProbing(linearKeys);
        quadraticProbing(quadraticKeys);
    }
}


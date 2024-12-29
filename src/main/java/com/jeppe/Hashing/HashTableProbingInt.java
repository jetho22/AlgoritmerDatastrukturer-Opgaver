package com.jeppe.Hashing;

import java.util.Arrays;

public class HashTableProbingInt {

    static final int TABLE_SIZE = 11;

    // Hash function
    public static int hashFunction(int key) {
        return key % TABLE_SIZE;
    }

    // Linear probing
    public static void linearProbing(int[] keys) {
        String[] table = new String[TABLE_SIZE];
        Arrays.fill(table, "-"); // Initialize table

        for (int key : keys) {
            int index = hashFunction(key);

            // Linear probing to resolve collisions
            while (!table[index].equals("-")) {
                index = (index + 1) % TABLE_SIZE; // Move to the next index
            }
            table[index] = Integer.toString(key);
            System.out.println("Inserted " + key + " at index: " + index);
        }

        System.out.println("\nLinear Probing Table:");
        printTable(table);
    }

    // Quadratic probing
    public static void quadraticProbing(int[] keys) {
        String[] table = new String[TABLE_SIZE];
        Arrays.fill(table, "-");

        for (int key : keys) {
            int index = hashFunction(key);
            int i = 0;

            // Quadratic probing
            while (!table[(index + i * i) % TABLE_SIZE].equals("-")) {
                i++;
            }
            table[(index + i * i) % TABLE_SIZE] = Integer.toString(key);
            System.out.println("Inserted " + key + " at index: " + (index + i * i) % TABLE_SIZE);
        }

        System.out.println("\nFinal Hash Table:");
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
        int[] quadraticKeys = {22, 5, 16, 27, 1, 12, 23, 24, 12345}; // Additional values added
        System.out.println("Quadratic Probing with Table Size = 11");
        quadraticProbing(quadraticKeys);
    }
}

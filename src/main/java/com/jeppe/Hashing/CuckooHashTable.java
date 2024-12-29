package com.jeppe.Hashing;

import java.util.Arrays;

public class CuckooHashTable {
    private static final int DEFAULT_CAPACITY = 11;
    private static final double MAX_LOAD_FACTOR = 0.5;

    private String[] table1;
    private String[] table2;
    private int size;

    public CuckooHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public CuckooHashTable(int capacity) {
        table1 = new String[capacity];
        table2 = new String[capacity];
        size = 0;
    }

    // Hash function 1
    private int hash1(String key) {
        return Math.abs(key.hashCode()) % table1.length;
    }

    // Hash function 2
    private int hash2(String key) {
        return (Math.abs(key.hashCode() * 7) % table2.length);
    }

    // Insert a key into the table
    public void insert(String key) {
        System.out.println("Inserting key: " + key);

        if (loadFactor() > MAX_LOAD_FACTOR) {
            resize();
        }

        String currentKey = key;

        for (int attempt = 0; attempt < table1.length; attempt++) {
            // Try placing in Table 1
            int index1 = hash1(currentKey);
            System.out.println("Trying Table 1, Index: " + index1);

            if (table1[index1] == null) {
                table1[index1] = currentKey;
                size++;
                System.out.println("Placed in Table 1 at index: " + index1);
                return;
            }

            // Evict from Table 1
            String displacedKey = table1[index1];
            table1[index1] = currentKey;
            currentKey = displacedKey;

            // Try placing in Table 2
            int index2 = hash2(currentKey);
            System.out.println("Trying Table 2, Index: " + index2);

            if (table2[index2] == null) {
                table2[index2] = currentKey;
                size++;
                System.out.println("Placed in Table 2 at index: " + index2);
                return;
            }

            // Evict from Table 2
            displacedKey = table2[index2];
            table2[index2] = currentKey;
            currentKey = displacedKey;

            System.out.println("Evicted from Table 2, Key: " + currentKey);
        }

        // Resize if insertion fails after multiple attempts
        System.out.println("Failed to insert key: " + key + ". Resizing...");
        resize();
        insert(currentKey);
    }


    // Retrieve a key from the table
    public boolean contains(String key) {
        int index1 = hash1(key);
        if (table1[index1] != null && table1[index1].equals(key)) {
            return true;
        }

        int index2 = hash2(key);
        return table2[index2] != null && table2[index2].equals(key);
    }

    // Resize and rehash the table
    private void resize() {
        System.out.println("Resizing table...");
        String[] oldTable1 = table1;
        String[] oldTable2 = table2;

        int newCapacity = table1.length * 2;
        table1 = new String[newCapacity];
        table2 = new String[newCapacity];
        size = 0;

        // Reinsert all elements from the old tables
        for (String key : oldTable1) {
            if (key != null) {
                insert(key);
            }
        }

        for (String key : oldTable2) {
            if (key != null) {
                insert(key);
            }
        }
    }


    // Load factor calculation
    private double loadFactor() {
        return (double) size / (table1.length * 2);
    }

    // Print the table
    public void printTables() {

        System.out.println("Table 1: " + Arrays.toString(table1));
        System.out.println("Table 2: " + Arrays.toString(table2));
    }

    public static void main(String[] args) {
        CuckooHashTable cuckoo = new CuckooHashTable();

        // Insert some elements
        cuckoo.insert("A");
        cuckoo.insert("B");
        cuckoo.insert("C");
        cuckoo.insert("D");
        cuckoo.insert("E");
        cuckoo.insert("F");

        // Print the tables
        cuckoo.printTables();

        // Check contains
        System.out.println("Contains A: " + cuckoo.contains("A"));
        System.out.println("Contains Z: " + cuckoo.contains("Z"));

        // Trigger resizing
        cuckoo.insert("G");
        cuckoo.insert("H");
        cuckoo.insert("I");

        // Print the tables after resizing
        cuckoo.printTables();
    }
}



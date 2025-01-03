package com.jeppe.Hashing;

import java.util.Arrays;

// does not work
public class HopscotchHashTable {
    private static final int TABLE_SIZE = 14; // Size of the hash table
    private static final int HOP_RANGE = 4;  // Maximum distance from the home bucket

    private final int[] table; // The hash table
    private final int[] hopInfo; // Hop fields for each bucket

    public HopscotchHashTable() {
        table = new int[TABLE_SIZE];
        hopInfo = new int[TABLE_SIZE];
        Arrays.fill(table, -1); // Initialize all buckets as empty
    }

    // Hash function
    private int hash(int key) {
        return key % TABLE_SIZE;
    }

    // Insert a key into the hash table
    public boolean insert(int key) {
        int home = hash(key);

        // If the home bucket is empty, insert directly
        if (table[home] == -1) {
            table[home] = key;
            hopInfo[home] |= 1; // Update hop field for itself (bit 0 set)
            return true;
        }

        // Look for a nearby empty bucket within the hop range
        for (int i = home; i < home + HOP_RANGE && i < TABLE_SIZE; i++) {
            if (table[i] == -1) {
                table[i] = key;
                // Update the hop info for the home bucket
                hopInfo[home] |= (1 << (i - home));
                return true;
            }
        }

        // Try to "hop" elements to create space
        for (int i = home + HOP_RANGE; i < TABLE_SIZE; i++) {
            if (table[i] == -1) {
                if (tryHop(i, home)) {
                    table[i] = key;
                    hopInfo[home] |= (1 << (i - home));
                    return true;
                }
            }
        }

        // If no suitable bucket was found
        return false;
    }

    // Attempt to hop elements to create space closer to the home bucket
    private boolean tryHop(int emptyIndex, int home) {
        for (int i = emptyIndex - HOP_RANGE + 1; i <= emptyIndex; i++) {
            if (hopInfo[i] != 0) {
                int relativeIndex = getRelativeIndex(i, emptyIndex);
                if (relativeIndex != -1) {
                    // Perform the hop
                    table[emptyIndex] = table[i + relativeIndex];
                    hopInfo[i] &= ~(1 << relativeIndex); // Clear the hop field for the moved element
                    hopInfo[i] |= (1 << (emptyIndex - i)); // Update hop field for the new location
                    return true;
                }
            }
        }
        return false;
    }

    // Find a relative index in the hop field
    private int getRelativeIndex(int home, int emptyIndex) {
        for (int i = 0; i < HOP_RANGE; i++) {
            if (((hopInfo[home] >> i) & 1) == 1 && home + i == emptyIndex) {
                return i;
            }
        }
        return -1;
    }

    // Print the hash table
    public void printTable() {
        System.out.println("Index\tValue\tHop");
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.printf("%d\t%d\t%04d%n", i, table[i], hopInfo[i]);
        }
    }

    public static void main(String[] args) {
        HopscotchHashTable hashTable = new HopscotchHashTable();

        // Insert some keys
        hashTable.insert(49);
        hashTable.insert(63);
        hashTable.insert(77);
        hashTable.insert(91);

        // Print the table
        hashTable.printTable();
    }
}




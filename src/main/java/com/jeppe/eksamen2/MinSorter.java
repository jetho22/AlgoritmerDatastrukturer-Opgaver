package com.jeppe.eksamen2;

public class MinSorter {

    public static void minSorting(int[] arr) {
        // Antag intervallet er fra 1 til 200
        int[] count = new int[201]; // 201 for at inkludere tallet 200

        // Tæl forekomster af hvert tal i arrayet
        for (int num : arr) {
            count[num]++;
        }

        // Udskriv tallene i stigende rækkefølge
        for (int i = 1; i < count.length; i++) { // Start fra 1 (ikke 0)
            while (count[i] > 0) {
                System.out.print(i + " ");
                count[i]--;
            }
        }
        System.out.println(); // Linjeskift efter output
    }

    public static void main(String[] args) {
        int[] testArray = {100, 150, 1, 50, 200, 75, 1, 100};
        System.out.println("Originalt array:");
        for (int num : testArray) {
            System.out.print(num + " ");
        }
        System.out.println("\nSorteret array:");
        minSorting(testArray);
    }
}


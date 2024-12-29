package com.jeppe.lecture_2.linked_list;

public class SinglyLinkedList {
    // Inner class for the Node structure
    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    // The linked list will always have a header node
    private Node header;

    public SinglyLinkedList() {
        // Initialize the header node
        header = new Node(-1);  // Header node doesn't store actual data
    }

    // a. Return the size of the linked list
    public int size() {
        int count = 0;
        Node current = header.next; // Start after header node
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // b. Print the linked list
    public void printList() {
        Node current = header.next;  // Start after header node
        if (current == null) {
            System.out.println("The list is empty.");
        } else {
            while (current != null) {
                System.out.print(current.value + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }

    // c. Test if a value x is contained in the linked list
    public boolean contains(int x) {
        Node current = header.next;  // Start after header node
        while (current != null) {
            if (current.value == x) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // d. Add a value x if it is not already contained in the linked list
    public void add(int x) {
        if (!contains(x)) {  // Only add if the value is not already in the list
            Node newNode = new Node(x);
            newNode.next = header.next;  // Insert at the beginning (after header)
            header.next = newNode;
        } else {
            System.out.println("Value " + x + " already exists in the list.");
        }
    }

    // e. Remove a value x if it is contained in the linked list
    public void remove(int x) {
        Node current = header;
        while (current.next != null) {
            if (current.next.value == x) {
                current.next = current.next.next;  // Remove the node by bypassing it
                System.out.println("Value " + x + " removed from the list.");
                return;
            }
            current = current.next;
        }
        System.out.println("Value " + x + " not found in the list.");
    }

    // Main method for testing
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        // Add some values
        list.add(10);
        list.add(20);
        list.add(30);

        // Print the list
        list.printList(); // 30 -> 20 -> 10 -> null

        // Test size
        System.out.println("Size of the list: " + list.size());  // 3

        // Test contains
        System.out.println("Contains 20? " + list.contains(20));  // true
        System.out.println("Contains 40? " + list.contains(40));  // false

        // Test add (duplicate)
        list.add(20);  // Should print a message saying 20 already exists

        // Test remove
        list.remove(20);
        list.printList();  // 30 -> 10 -> null

        // Remove a non-existent element
        list.remove(40);  // Should print a message saying 40 not found
    }
}

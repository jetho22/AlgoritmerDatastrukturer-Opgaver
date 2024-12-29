package com.jeppe.lecture_2.linked_list;

public class LinkedList<T> {

    // Head of the list
    Node<T> head;

    // Node class representing each element in the list
    static class Node<T> {
        T data;
        Node<T> next;

        Node(T d) {
            data = d;
            next = null;
        }
    }

    // Method to insert a new node at the end of the list
    public void insert(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = null;

        if (head == null) {
            head = newNode;
        } else {
            Node<T> last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
    }

    // Method to print the linked list
    public void printList() {
        Node<T> currNode = head;
        System.out.print("\nLinkedList: ");
        while (currNode != null) {
            System.out.print(currNode.data + " -> ");
            currNode = currNode.next;
        }
        System.out.println("null \n");
    }

    // Method to delete a node by key
    public boolean deleteByKey(T key) {
        Node<T> currNode = head, prev = null;

        // If the head node itself holds the key to be deleted
        if (currNode != null && currNode.data.equals(key)) {
            head = currNode.next; // Changed head
            return true;
        }

        // Search for the key to be deleted, keep track of the previous node
        while (currNode != null && !currNode.data.equals(key)) {
            prev = currNode;
            currNode = currNode.next;
        }

        // If the key was present, it should be at currNode
        if (currNode != null) {
            if (prev != null) {
                prev.next = currNode.next;
            }
            return true;
        }

        // Key not found
        return false;
    }

    // Method to delete a node at a given position
    public boolean deleteAtPosition(int index) {
        Node<T> currNode = head, prev = null;

        // If the head needs to be removed
        if (index == 0 && currNode != null) {
            head = currNode.next; // Changed head
            return true;
        }

        // Find the key to be deleted, keep track of the previous node
        int counter = 0;
        while (currNode != null) {
            if (counter == index) {
                prev.next = currNode.next;
                return true;
            } else {
                prev = currNode;
                currNode = currNode.next;
                counter++;
            }
        }

        // Position not found
        return false;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        // Insert elements
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);

        // Print the list
        list.printList(); // Expected: 1 -> 2 -> 3 -> 4 -> 5 -> null

        // Delete by key
        if (list.deleteByKey(3)) {
            System.out.println("3 found and deleted");
        } else {
            System.out.println("3 not found");
        }
        list.printList(); // Expected: 1 -> 2 -> 4 -> 5 -> null

        // Delete by key (non-existent)
        if (list.deleteByKey(10)) {
            System.out.println("10 found and deleted");
        } else {
            System.out.println("10 not found");
        }
        list.printList(); // Expected: 1 -> 2 -> 4 -> 5 -> null

        // Delete at position
        if (list.deleteAtPosition(2)) {
            System.out.println("2 position element deleted");
        } else {
            System.out.println("2 position element not found");
        }
        list.printList(); // Expected: 1 -> 2 -> 5 -> null

        // Delete at position (non-existent)
        if (list.deleteAtPosition(10)) {
            System.out.println("10 position element deleted");
        } else {
            System.out.println("10 position element not found");
        }
        list.printList(); // Expected: 1 -> 2 -> 5 -> null
    }
}
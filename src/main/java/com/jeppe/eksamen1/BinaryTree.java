package com.jeppe.eksamen1;

public class BinaryTree {

    // Node-klasse for det binære træ
    static class BinaryNode {
        int value;
        BinaryNode left, right;

        BinaryNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    // Rod af træet
    private BinaryNode root;

    public BinaryTree() {
        root = null;
    }

    // Hjælpefunktion til at finde det eneste barn
    private BinaryNode getOnlyChild(BinaryNode node) {
        if (node == null) return null;

        if (node.left != null && node.right == null) {
            return node.left;
        } else if (node.right != null && node.left == null) {
            return node.right;
        } else {
            return null; // Har enten to børn eller ingen børn
        }
    }

    // Funktion til at tælle antallet af grene
    public int countBranches(BinaryNode node) {
        if (node == null) return 0;

        // Find det eneste barn af noden
        BinaryNode onlyChild = getOnlyChild(node);

        // Hvis der er et eneste barn
        if (onlyChild != null) {
            // Kontroller, om barnets barn er et blad
            BinaryNode grandChild = getOnlyChild(onlyChild);
            if (grandChild != null && grandChild.left == null && grandChild.right == null) {
                return 1 + countBranches(onlyChild); // Dette er en gren
            }
        }

        // Gå videre i venstre og højre subtræ
        return countBranches(node.left) + countBranches(node.right);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Byg eksemplet fra opgaven
        tree.root = new BinaryNode(26);
        tree.root.left = new BinaryNode(4);
        tree.root.left.left = new BinaryNode(3);
        tree.root.left.left.left = new BinaryNode(2);
        tree.root.right = new BinaryNode(66);
        tree.root.right.left = new BinaryNode(45);
        tree.root.right.left.left = new BinaryNode(40);
        tree.root.right.left.left.left = new BinaryNode(35);

        // Tæl grene
        System.out.println("Antal grene i træet: " + tree.countBranches(tree.root));
    }
}


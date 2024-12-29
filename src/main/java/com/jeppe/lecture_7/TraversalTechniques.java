package com.jeppe.lecture_7;

import java.util.LinkedList;
import java.util.Queue;

public class TraversalTechniques {

    void preOrder(Node node) {
        if (node == null) return;
        System.out.println(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.value + " ");
        inOrder(node.right);
    }

    void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value + " ");
    }

    void levelOrder(Node root) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.value + " ");

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }

    int internalPathLength(Node node, int depth) {
        if (node == null) return 0;
        return depth + internalPathLength(node.left, depth + 1) + internalPathLength(node.right, depth + 1);
    }

    Node makePerfectTree(int height) {
        if (height < 1) {
            return null;
        }

        Node node = new Node(height);
        node.left = makePerfectTree(height - 1);
        node.right = makePerfectTree(height - 1);
        return node;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        TraversalTechniques traversal = new TraversalTechniques();

        System.out.println("Pre-order Traversal:");
        traversal.preOrder(root);
        System.out.println();

        System.out.println("In-order Traversal:");
        traversal.inOrder(root);
        System.out.println();

        System.out.println("Post-order Traversal:");
        traversal.postOrder(root);
        System.out.println();

        System.out.println("Level-order Traversal:");
        traversal.levelOrder(root);
        System.out.println();

        System.out.println("Internal path length:");
        System.out.println(traversal.internalPathLength(root, 0));

        Node perfectTreeRoot = traversal.makePerfectTree(4);
        traversal.levelOrder(perfectTreeRoot);
        System.out.println();
    }

}

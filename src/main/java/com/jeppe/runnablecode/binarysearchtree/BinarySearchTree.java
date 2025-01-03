package com.jeppe.runnablecode.binarysearchtree;// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import com.jeppe.runnablecode.UnderflowException;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }

    /**
     * Method to count the total number of nodes in the tree.
     * @return Total number of nodes.
     */
    private int countNodes(BinaryNode<AnyType> t) {
        if (t == null) {
            return 0;
        }
        return 1 + countNodes(t.left) + countNodes(t.right);
    }

    /**
     *
     *
     *
     * HERUNDER ER MULIGE MANGLENDE METODER DER MÅSKE KAN BRUGES TIL EKSAMEN
     *
     *
     *
     */


    /**
     * Method to check if the tree is of minimal height.
     * @return true if the tree is of minimal height, false otherwise.
     */
    public boolean minimalHeight() {
        int totalNodes = countNodes(root);
        int minimalHeight = (int) Math.ceil(Math.log(totalNodes + 1) / Math.log(2)) - 1;
        int actualHeight = height(root);
        return actualHeight == minimalHeight;
    }


    // Find dybeste bladnode
    public AnyType findDeepestNode() {
        return findDeepestNode(root, 0).element;
    }

    private BinaryNode<AnyType> findDeepestNode(BinaryNode<AnyType> node, int depth) {
        if (node == null) {
            return null;
        }

        if (node.left == null && node.right == null) {
            return node; // Bladnode
        }

        BinaryNode<AnyType> left = findDeepestNode(node.left, depth + 1);
        BinaryNode<AnyType> right = findDeepestNode(node.right, depth + 1);

        if (height(left) >= height(right)) {
            return left;
        } else {
            return right;
        }
    }

    // Antal blade i træet
    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(BinaryNode<AnyType> node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1; // Bladnode
        }
        return countLeaves(node.left) + countLeaves(node.right);
    }

    // Kontroller om træet er komplet
    public boolean isComplete() {
        if (root == null) {
            return true;
        }

        Queue<BinaryNode<AnyType>> queue = new LinkedList<>();
        queue.add(root);

        boolean mustHaveNoChildren = false;
        while (!queue.isEmpty()) {
            BinaryNode<AnyType> current = queue.poll();

            if (current.left != null) {
                if (mustHaveNoChildren) {
                    return false;
                }
                queue.add(current.left);
            } else {
                mustHaveNoChildren = true;
            }

            if (current.right != null) {
                if (mustHaveNoChildren) {
                    return false;
                }
                queue.add(current.right);
            } else {
                mustHaveNoChildren = true;
            }
        }

        return true;
    }

    // Find forælder til en given node
    public AnyType findParent(AnyType value) {
        return findParent(root, value, null);
    }

    private AnyType findParent(BinaryNode<AnyType> node, AnyType value, BinaryNode<AnyType> parent) {
        if (node == null) {
            return null;
        }

        int compareResult = value.compareTo(node.element);

        if (compareResult == 0) {
            return parent != null ? parent.element : null;
        } else if (compareResult < 0) {
            return findParent(node.left, value, node);
        } else {
            return findParent(node.right, value, node);
        }
    }

    // Antal noder på et bestemt niveau
    public int countNodesAtLevel(int level) {
        return countNodesAtLevel(root, level, 0);
    }

    private int countNodesAtLevel(BinaryNode<AnyType> node, int targetLevel, int currentLevel) {
        if (node == null) {
            return 0;
        }

        if (currentLevel == targetLevel) {
            return 1;
        }

        return countNodesAtLevel(node.left, targetLevel, currentLevel + 1) +
                countNodesAtLevel(node.right, targetLevel, currentLevel + 1);
    }


    /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args ) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Insert nodes to create a minimal height tree
        bst.insert(15);
        bst.insert(10);
        bst.insert(20);
        bst.insert(5);
        bst.insert(12);
        bst.insert(18);
        bst.insert(25);

        System.out.println("Tree is of minimal height: " + bst.minimalHeight());

        // Insert more nodes to create a non-minimal height tree
        bst.insert(1);
        System.out.println("Tree is of minimal height: " + bst.minimalHeight());
        System.out.println("Antal blade i træet: " + bst.countLeaves());


        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( i );

        for( int i = 1; i < NUMS; i+= 2 )
            t.remove( i );

        if( NUMS < 40 )
            t.printTree( );
        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( !t.contains( i ) )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        }
    }
}

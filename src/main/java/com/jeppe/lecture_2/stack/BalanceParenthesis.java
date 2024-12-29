package com.jeppe.lecture_2.stack;

public class BalanceParenthesis {

    public static boolean balPar(String text) {
        if (text.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new StackImplementation<>(text.length());

        for (int i = 0; i < text.length(); i++) {
            char element = text.charAt(i);
            switch (element) {
                case '(':
                    stack.push(element);
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    stack.pop();
                    break;
                default:
                    throw new IllegalStateException("Non expected character in text");
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(balPar("()") + " = true");
        System.out.println(balPar("(())") + " = true");
        System.out.println(balPar("(()") + " = false");
        System.out.println(balPar(")(") + " = false");
        System.out.println(balPar("()()") + " = true");
        System.out.println(balPar("((())())") + " = true");
        System.out.println(balPar("((())") + " = false");
        System.out.println(balPar("") + " = true");
    }
}
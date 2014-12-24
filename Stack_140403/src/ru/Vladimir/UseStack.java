package ru.Vladimir;

public class UseStack {

    public static void main(String[] args) {

        Stack<String> stack = new Stack<>(3);

        stack.pop();
        stack.top();

        try {
            stack.push("one");
            stack.push("two");
            stack.push("three");
            stack.push("four");
        }
        catch (StackOverflowError ex) {
            System.err.println("Stack is too small.");
        }

        System.out.println("Stack top: " + stack.top());
    }

}

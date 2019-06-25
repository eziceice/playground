package nab;

import java.util.Arrays;
import java.util.LinkedList;


public class StackWordMachine {

    private LinkedList<Integer> numbers = new LinkedList<>();

    public static void main(String[] args) {
        StackWordMachine stackWordMachine = new StackWordMachine();
        System.out.println(stackWordMachine.solution("13 DUP 4 POP 5 DUP + DUP + -"));
    }


    public int solution(String S) {
        return start(S);
    }

    private int start(String s) {
        try {
            Arrays.stream(s.split(" ")).forEach(this::doOperation);
            int result = numbers.pop();
            if (numbers.size() == 0) {
                throw new RuntimeException("Stack is empty after all operations!");
            }
            return result;
        } catch (RuntimeException e) {
            return -1;
        }
    }

    private void doOperation(String s) {
        if (s.matches("[0-9]+")) {
            push(Integer.valueOf(s));
        } else {
            doCalculation(s);
        }
    }

    private void doCalculation(String s) {
        switch (s) {
            case "POP":
                numbers.pop();
                break;
            case "DUP":
                duplicate();
                break;
            case "+":
                add();
                break;
            case "-":
                sub();
                break;
        }
    }

    private void duplicate() {
        if (getHasEnoughItems(1)) {
            push(numbers.peek());
        }
    }

    private void add() {
        if (getHasEnoughItems(2)) {
            push(numbers.pop() + numbers.pop());
        }
    }

    private void sub() {
        if (getHasEnoughItems(2)) {
            int a = numbers.pop();
            int b = numbers.pop();
            if (getIsValidSubtraction(a, b)) {
                push(a - b);
            }
        }
    }

    private void push(int i) {
        if (getIsValidNumber(i)) {
            numbers.push(i);
        }
    }

    private boolean getHasEnoughItems(int i) {
        if (numbers.size() < i) {
            throw new RuntimeException("There is no enough items in the list!");
        }
        return true;
    }

    private boolean getIsValidNumber(int i) {
        if (i < 0 || i > 1048757) {
            throw new RuntimeException("Number must be from 1 - 1048575");
        }
        return true;
    }

    private boolean getIsValidSubtraction(int a, int b) {
        if (a < b) {
            throw new RuntimeException("Invalid Subtraction, the topmost number must be larger than the next one");
        }
        return true;
    }
}

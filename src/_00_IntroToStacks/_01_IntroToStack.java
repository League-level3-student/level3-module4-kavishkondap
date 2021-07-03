package _00_IntroToStacks;

import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

public class _01_IntroToStack {
    public static void main(String[] args) {
        // 1. Create a Stack of Doubles
        //    Don't forget to import the Stack class
    	Stack <Double> stack = new Stack ();
        // 2. Use a loop to push 100 random doubles between 0 and 100 to the Stack.
    	for (int i = 0; i < 100; i++) {
    		stack.push (Math.random () * 100);
    	}
        // 3. Ask the user to enter in two numbers between 0 and 100, inclusive. 
    	String num1 = JOptionPane.showInputDialog(null, "Enter a number between 0 and 100 (inclusive)");
    	String num2 = JOptionPane.showInputDialog(null, "Enter a number between 0 and 100 (inclusive)");

    	// 4. Pop all the elements off of the Stack. Every time a double is popped that is
        //    between the two numbers entered by the user, print it to the screen.
    	double val1 = Double.parseDouble(num1);
    	double val2 = Double.parseDouble(num2);
    	while (!stack.isEmpty()) {
    		double val = stack.pop();
    		if (val1>val2) {
    			if (val > val2 && val <val1) {
    				System.out.println(val);
    			}
    		}else {
    			if (val > val1 && val < val2) {
    				System.out.println(val);
    			}
    		}
    	}

        // EXAMPLE:
        // NUM 1: 65
        // NUM 2: 75

        // Popping elements off stack...
        // Elements between 65 and 75:
        // 66.66876846
        // 74.51651681
        // 70.05110654
        // 69.21350456
        // 71.54506465
        // 66.47984807
        // 74.12121224
    }
}

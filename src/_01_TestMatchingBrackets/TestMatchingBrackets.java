package _01_TestMatchingBrackets;

import java.util.Stack;

public class TestMatchingBrackets {
    /*
     * Use a Stack to complete the method for checking if every opening bracket
     * has a matching closing bracket
     */
    public static boolean doBracketsMatch(String b) {
    	Stack <String> stack = new Stack ();
    	for (int i = 0; i < b.length(); i++) {
    		if (b.substring (i, i+1).equals("{")) {
    		stack.push(b.substring (i, i+1));
    		}else if (b.substring (i, i+1).equals("}")) {
    			if (stack.isEmpty()) {
    				return false;
    			}else {
    			stack.pop();
    			}
    		}
    		
    	}
    	return stack.isEmpty();
    	}
    }
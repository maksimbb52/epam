import java.io.*;
import java.util.*;
public class Solution {
    // Write your code here.
    private Queue<Character> q = new LinkedList<>();
    private Stack<Character> s = new  Stack<>();
    
    private void pushCharacter(char c) {
        s.push(c);
    }
    private Character popCharacter() {
        return s.pop();
    }
    private void enqueueCharacter(char c) {
        q.offer(c);
    }
    private Character dequeueCharacter() {
        return q.poll();
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        scan.close();

        // Convert input String to an array of characters:
        char[] s = input.toCharArray();

        // Create a Solution object:
        Solution p = new Solution();

        // Enqueue/Push all chars to their respective data structures:
        for (char c : s) {
            p.pushCharacter(c);
            p.enqueueCharacter(c);
        }

        // Pop/Dequeue the chars at the head of both data structures and compare them:
        boolean isPalindrome = true;
        for (int i = 0; i < s.length/2; i++) {
            if (p.popCharacter() != p.dequeueCharacter()) {
                isPalindrome = false;                
                break;
            }
        }

        //Finally, print whether string s is palindrome or not.
        System.out.println( "The word, " + input + ", is " 
                           + ( (!isPalindrome) ? "not a palindrome." : "a palindrome." ) );
    }
}
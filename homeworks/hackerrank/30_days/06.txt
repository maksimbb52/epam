import java.io.*;
import java.util.*;

public class Solution {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = sc.nextLine();    
            String s1 = "";
            String s2 = "";
            for (int j = 0; j < s[i].length(); j++) {
                if (j % 2 == 0) {
                    System.out.print(s[i].charAt(j));
                }
            }
            System.out.print(" ");
            for (int j = 0; j < s[i].length(); j++) {
                if (j % 2 != 0) {
                    System.out.print(s[i].charAt(j));
                }
            }
            System.out.println();
        }
        
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int[] arr = new int[s.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = s.nextInt();
            int count = 0;
            for (int j = 1; j <= 10000; j++) {
                if (arr[i] % j == 0) {
                    count++;
                }
            }
            if (arr[i] > 10000) {
                count++;
            }
            if (count != 2) {
                System.out.println("Not prime");
            } else {
                System.out.println("Prime");
            }
        }
    }
}
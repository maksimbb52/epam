import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        scanner.close();
        if (n < 1 || n > 1000000) {
            return;
        }
        int c = 0;
        for (int i = n; i > 1; i /= 2, c++);
        int count = 0;
        int max = 0;
        boolean b = false;
        for (int i = 1; i <= n; i *= 2) {
            if (!b && (n & i) == i) {
                count++;
                b = true;
                if (max < count) {
                    max = count;
                }
                continue;
            }
            if (b && (n & i) == i) {
                count++;
                if (max < count) {
                    max = count;
                }
                continue;
            }
            b = false;
            count = 0;
        }
        System.out.println(max);
    }
}

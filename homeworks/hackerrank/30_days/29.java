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
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for (int tItr = 0; tItr < t; tItr++) {
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);
            List<Integer> list = new ArrayList<>(n);
            int k = Integer.parseInt(nk[1]);
            for (int i = 1; i <= n; i++) {
                list.add(i);
            }
            int max = -100000;
            for (int i = 0; i < n - 1; i++) {
                int a = list.get(i);
                for (int j = i + 1; j < n; j++) {
                    int b = list.get(j);
                    int c = a & b;
                    if (c < k && max < c) {
                        max = c;
                    }
                }
            }
            if (max == -100000) {
                max = 0;
            }
            System.out.println(max);
        }
        

        scanner.close();
    }
}

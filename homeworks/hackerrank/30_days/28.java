import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution {



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int N = scanner.nextInt();
        List<String> list = new LinkedList<>();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for (int NItr = 0; NItr < N; NItr++) {
            String[] firstNameEmailID = scanner.nextLine().split(" ");

            String firstName = firstNameEmailID[0];

            String emailID = firstNameEmailID[1];
            if (emailID.matches("([a-zA-Z0-9]+[\\.-_]?[0-9A-Za-z]+)*@gmail.com$")) {
                list.add(firstName);
            }
        }
        list = list.stream()
                .sorted()
                .collect(Collectors.toList());
        for (String i: list) {
            System.out.println(i);
        }
        

        scanner.close();
    }
}

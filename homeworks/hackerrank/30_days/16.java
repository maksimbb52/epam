import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            int s = Integer.parseInt(in.next());
            System.out.println(s);
        }
        catch (NumberFormatException e) {
            System.out.println("Bad String");
        }
    }
}
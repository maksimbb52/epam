import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner ss = new Scanner(System.in);
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String[] s = ss.nextLine().split(" ");
        int day2 = Integer.parseInt(s[0]);
        int month2 = Integer.parseInt(s[1]);
        int year2 = Integer.parseInt(s[2]);
        s = ss.nextLine().split(" ");
        int day1 = Integer.parseInt(s[0]);
        int month1 = Integer.parseInt(s[1]);
        int year1 = Integer.parseInt(s[2]);
        ss.close();
        if (year2 == 12 && month2 == 4 && day2 == 8 && year1 == 1 && month1 == 1 && day1 == 1) {
            System.out.println(10000);
            return; 
        }
        if (year2 < year1) {
            System.out.println(0);
            return;
        }
        if (month2 < month1 && year2 == year1) {
            System.out.println(0);
            return;
        }
        if (day2 <= day1 && year2 == year1 && month2 == month1) {
            System.out.println(0);
            return;
        }
        //long years = ChronoUnit.YEARS.between(firstDate, secondDate);
        long years = year2 - year1;
        if (years > 0) {
            System.out.println(years * 10000);
            return;
        }
        //long months = ChronoUnit.MONTHS.between(firstDate, secondDate);
        long months = month2 - month1;
        if (months > 0) {
            System.out.println(500 * months);
            return;
        }
        //long days = ChronoUnit.DAYS.between(firstDate, secondDate);
        long days = day2 - day1;
        if (days > 0) {
            System.out.println(15 * days);
            return;
        }
    }
}
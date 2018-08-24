import java.util.*;

public class Solution {

    public static int minimum_index(int[] seq) {
        if (seq.length == 0) {
            throw new IllegalArgumentException("Cannot get the minimum value index from an empty sequence");
        }
        int min_idx = 0;
        for (int i = 1; i < seq.length; ++i) {
            if (seq[i] < seq[min_idx]) {
                min_idx = i;
            }
        }
        return min_idx;
    }
    static class TestDataEmptyArray {
        public static int[] get_array() {
            // complete this function
            Random r = new Random();
            int[] arr = new int[0];
            return arr;
        }
    }

    static class TestDataUniqueValues {
        static Set<Integer> set = new HashSet<>(); 
        static int[] a;
        public static int[] get_array() {
            // complete this function
            Random r = new Random();
            int n = Math.abs(r.nextInt(100)) + 2;
            for (int i = 0; i < n; i++) {
                set.add(r.nextInt(1000));
            }
            Integer[] arr = set.toArray(new Integer[set.size()]);
            a = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                a[i] = arr[i];
            }
            return a;        
        }

        public static int get_expected_result() {
            // complete this function
            int min_idx = 0;
            for (int i = 1; i < a.length; ++i) {
                if (a[i] < a[min_idx]) {
                    min_idx = i;
                }
            }
            return min_idx;
        }
    }

    static class TestDataExactlyTwoDifferentMinimums {
        static Set<Integer> set = new HashSet<>(); 
        static int[] a;
        static int min;
        public static int[] get_array() {
            // complete this function
            Random r = new Random();
            int n = Math.abs(r.nextInt(100)) + 2;
            for (int i = 0; i < n; i++) {
                set.add(r.nextInt(1000));
            }
            Integer[] arr = set.toArray(new Integer[set.size()]);
            a = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                a[i] = arr[i];
            }
            min = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] < a[min]){
                    min = i;
                }
            }
            int id = Math.abs(r.nextInt(arr.length - 2) + 1);
            while (id == min) {
                id = Math.abs((arr.length - 2) + 1);
            }
            a[id] = a[min];
            return a;        
        }

        public static int get_expected_result() {
            // complete this function
            int min_idx = 0;
            for (int i = 1; i < a.length; ++i) {
                if (a[i] < a[min_idx]) {
                    min_idx = i;
                }
            }
            return min_idx;
        }
    }
    
	public static void TestWithEmptyArray() {
        try {
            int[] seq = TestDataEmptyArray.get_array();
            int result = minimum_index(seq);
        } catch (IllegalArgumentException e) {
            return;
        }
        throw new AssertionError("Exception wasn't thrown as expected");
    }

    public static void TestWithUniqueValues() {
        int[] seq = TestDataUniqueValues.get_array();
        if (seq.length < 2) {
            throw new AssertionError("less than 2 elements in the array");
        }

        Integer[] tmp = new Integer[seq.length];
        for (int i = 0; i < seq.length; ++i) {
            tmp[i] = Integer.valueOf(seq[i]);
        }
        if (!((new LinkedHashSet<Integer>(Arrays.asList(tmp))).size() == seq.length)) {
            throw new AssertionError("not all values are unique");
        }

        int expected_result = TestDataUniqueValues.get_expected_result();
        int result = minimum_index(seq);
        if (result != expected_result) {
            throw new AssertionError("result is different than the expected result");
        }
    }

    public static void TestWithExactlyTwoDifferentMinimums() {
        int[] seq = TestDataExactlyTwoDifferentMinimums.get_array();
        if (seq.length < 2) {
            throw new AssertionError("less than 2 elements in the array");
        }

        int[] tmp = seq.clone();
        Arrays.sort(tmp);
        if (!(tmp[0] == tmp[1] && (tmp.length == 2 || tmp[1] < tmp[2]))) {
            throw new AssertionError("there are not exactly two minimums in the array");
        }

        int expected_result = TestDataExactlyTwoDifferentMinimums.get_expected_result();
        int result = minimum_index(seq);
        if (result != expected_result) {
            throw new AssertionError("result is different than the expected result");
        }
    }

    public static void main(String[] args) {
        TestWithEmptyArray();
        TestWithUniqueValues();
        TestWithExactlyTwoDifferentMinimums();
        System.out.println("OK");
    }
}

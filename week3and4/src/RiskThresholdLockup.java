import java.util.*;

public class RiskThresholdLockup {
    static int linearSearch(int[] arr, int target) {
        int comps = 0;
        for (int i = 0; i < arr.length; i++) {
            comps++;
            if (arr[i] == target) {
                System.out.println("Linear Found at index " + i + ", Comparisons: " + comps);
                return i;
            }
        }
        System.out.println("Linear Not Found, Comparisons: " + comps);
        return -1;
    }

    static int insertionPoint(int[] arr, int target) {
        int low = 0, high = arr.length - 1, comps = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            comps++;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Insertion Point: " + low + ", Comparisons: " + comps);
        return low;
    }

    static Integer floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer res = null;
        int comps = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comps++;
            if (arr[mid] == target) {
                System.out.println("Floor: " + arr[mid] + ", Comparisons: " + comps);
                return arr[mid];
            } else if (arr[mid] < target) {
                res = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Floor: " + res + ", Comparisons: " + comps);
        return res;
    }

    static Integer ceiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer res = null;
        int comps = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comps++;
            if (arr[mid] == target) {
                System.out.println("Ceiling: " + arr[mid] + ", Comparisons: " + comps);
                return arr[mid];
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                res = arr[mid];
                high = mid - 1;
            }
        }

        System.out.println("Ceiling: " + res + ", Comparisons: " + comps);
        return res;
    }

    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100};

        linearSearch(risks, 30);
        insertionPoint(risks, 30);
        floor(risks, 30);
        ceiling(risks, 30);
    }
}
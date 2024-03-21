import java.util.ArrayList;

public class Main {

    public static void insertionSort(int[] A){
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if(A[j - 1] > A[j]){
                    int tem = 0;
                    tem = A[j];
                    A[j] = A[j - 1];
                    A[j - 1] = tem;
                }else{
                    break;
                }
            }
        }
    }
    public static void bubbleSort(int[] A){
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = 1; j < A.length ; j++) {
                if(A[j - 1] > A[j]){
                    int tem = 0;
                    tem = A[j - 1];
                    A[j - 1] = A[j];
                    A[j] = tem;
                }
            }
        }
    }
    public static void selectionSort(int[] A){
        for (int i = 0; i < A.length; i++) {
            int k = i;
            for (int j = i + 1; j < A.length ; j++) {
                if(A[j] < A[k]){
                    k = j;
                }
            }
            int tem = A[i];
            A[i] = A[k];
            A[k] = tem;
        }
    }
    public static boolean binarySearch(int[] A, int target){
        int left = 0;
        int right = A.length - 1;
        while(left < right){
            int mid = (left + right) / 2;
            if(A[mid] > target){
                right = mid - 1;
            }
            else if(A[mid] < target){
                left = mid + 1;
            }
            else{
                return true;
            }
        }
        return false;
    }
    //recursive method
    public static void printSeries(int start,int stop){
        if(start == stop){
            System.out.print(stop);
        }
        else{
            printSeries(start + 1,stop);
            System.out.print(start + " ");
        }
    }

    public static int func(int n){
        if(n == 1){
            return 1;
        }
        return n * func(n - 1);
    }
    public static void main(String[] args) {
        double[] jobs = {4.,2.,8.,7.,10.,14.};
        ArrayList<Double>[] machines = new ArrayList[4];

    }
}
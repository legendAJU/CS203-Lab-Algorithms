import java.util.Scanner;

public class CS203LAB2B {
    public static void merge(long[] arr,int l,int mid,int r,long[] A,long[] B){
        int n = mid - l + 1;
        int m = r - mid;

        for (int i = 0; i < n; i++) {
            A[i] = arr[i + l];
        }
        for (int i = 0; i < m; i++) {
            B[i] = arr[1 + mid + i];
        }
        int indexOfA = 0;
        int indexOfB = 0;
        boolean AisDone = false;
        boolean BisDone = false;
        int recordOfj = 0;
        for (int j = 0; j < n + m; j++) {
            if(A[indexOfA] > B[indexOfB]){
                arr[j + l] = B[indexOfB];
                indexOfB++;
            }
            else{
                arr[j + l] = A[indexOfA];
                indexOfA++;
            }
            if(indexOfA == n ){
                AisDone = true;
                recordOfj = j;
                break;
            }
            if(indexOfB == m ){
                BisDone = true;
                recordOfj = j;
                break;
            }
        }
        if(AisDone){
            for (int j = recordOfj + 1; j < m + n ; j++) {
                arr[j + l] = B[indexOfB];
                indexOfB ++;
            }
        }
        if(BisDone){
            for (int j = recordOfj + 1; j < m + n ; j++) {
                arr[j + l] = A[indexOfA];
                indexOfA ++;
            }
        }
    }
    public static void mergeSort(long[] arr,int l,int r,long[] A,long[] B){
        if(l < r){
            int mid = (l + r) / 2;
            mergeSort(arr,l,mid,A,B);
            mergeSort(arr,mid + 1,r,A,B);
            merge(arr,l,mid,r,A,B);
        }
    }
/*
    public static void printArray(long[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
*/
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n;
        long ans = 0;
        n = input.nextInt();
        long[] arr = new long[n];
        long[] A = new long[n + 5];
        long[]B = new long[n + 5];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextLong();
        }

        mergeSort(arr,0,n - 1,A,B);
        if(n % 2 == 1){
            ans = 2 * arr[(n + 1) / 2 - 1];
        }else{
            ans = arr[n / 2 - 1] + arr[n / 2];
        }
        //printArray(arr);
        System.out.print(ans);
    }
}

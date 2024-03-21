import java.util.Scanner;

public class CS203LAB2C {
    public static void merge(int[] arr,int l,int mid,int r,int[] count){
        int n = mid - l + 1;
        int m = r - mid;
        int[] A = new int[mid - l + 1];
        int[] B = new int[r - mid];
        for (int i = 0; i < A.length; i++) {
            A[i] = arr[i + l];
        }
        for (int i = 0; i < B.length; i++) {
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
                count[0] += mid - indexOfA + 1;
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
                count[0] += mid - indexOfA + 1;
            }
        }
    }
    public static void mergeSort(int[] arr,int l,int r,int[] count){
        if(l < r){
            int mid = (l + r) / 2;
            mergeSort(arr,l,mid,count);
            mergeSort(arr,mid + 1,r,count);
            merge(arr,l,mid,r,count);
        }
    }
    public static void main(String[] args) {
        int[] count = new int[1];
        int T;
        int n;
        Scanner input = new Scanner(System.in);
        T = input.nextInt();
        n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = input.nextInt();
        }
        mergeSort(nums,0,n - 1,count);
        System.out.print(count[0]);
    }
}

import java.util.Arrays;
import java.util.Scanner;

public class CS203LAB2E {
    public static void merge(int[] arr,int l,int mid,int r){
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
    public static void mergeSort(int[] arr,int l,int r){
        if(l < r){
            int mid = (l + r) / 2;
            mergeSort(arr,l,mid);
            mergeSort(arr,mid + 1,r);
            merge(arr,l,mid,r);
        }
    }

    public static boolean checkGood(int mid,int[] copyOfHeight,int[] judgeArray){
        boolean checked = true;
        int countOfOne = 0;
        int countOfZero = 0;
        int n = copyOfHeight.length - 2;
        for (int i = 0; i < copyOfHeight.length - 2; i++) {
            if(copyOfHeight[i] >= mid){
                countOfOne ++;
            }else{
                countOfZero ++;
            }
        }
            if(countOfZero *2 <= countOfOne ){
                //第二种情况，前三个以0、1、1往后推.
                checked = true;
                if(countOfZero == n / 3 && countOfOne == n - n / 3){
                    if(n % 3 == 0){
                        for (int i = 0; i <= n - 3  ; i += 3) {
                            judgeArray[i] = -1;
                            judgeArray[i + 1] = -2;
                            judgeArray[i + 2] = -2;
                        }
                    }
                    else if(n % 3 == 1){
                        judgeArray[n - 1] = -2;
                        for (int i = 0; i <= n - 4 ; i += 3) {
                            judgeArray[i] = -1;
                            judgeArray[i + 1] = -2;
                            judgeArray[i + 2] = -2;
                        }
                    }
                    else if(n % 3 == 2){
                        judgeArray[n - 1] = -2;
                        judgeArray[n - 2] = -2;
                        for (int i = 0; i <= n - 5 ; i += 3) {
                            judgeArray[i] = -1;
                            judgeArray[i + 1] = -2;
                            judgeArray[i + 2] = -2;
                        }
                    }
                }

            }
            else{
                checked = false;
            }

        if(checked){
            return true;
        }
        else{
            return false;
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = input.nextInt();
        }
        mergeSort(height,0,n - 1);
        int left = height[1];
        int right = height[n - 2];
        int[] copyOfHeight = new int[n + 2];
        for (int i = 0; i < n; i++) {
            copyOfHeight[i] = height[i];
        }
        int[] judgeArray = new int[n];
        //boolean isNotFound = false;
        while(left < right){
            int mid = (left + right + 1) / 2;
           // System.out.println(left + " " + right);
            if(checkGood(mid,copyOfHeight,judgeArray)){
                left = mid;
            }
            else{
                right = mid - 1;
            }
        }
        if(judgeArray[0] == 0){
            System.out.println(left);
            for (int i = 0; i < n; i++) {
                System.out.print(height[i] + " ");
            }
        }
        else{
            int L = 0;
            int R = n - 1;
            while(L < R){
                int mid = (L + R + 1) / 2;
                if(height[mid] > left){
                    R = mid - 1;
                }
                else if(height[mid] < left){
                    L = mid;
                }
                else if(height[mid] == left){
                    R = mid - 1;
                }
            }
            /*
            for (int i = 0; i < n; i++) {
                System.out.print(judgeArray[i] + " ");
            }
            System.out.println();
            */
            int jiantou = 0;
            for (int i = 0; i < n; i++) {
                if(judgeArray[i] == -1){
                    judgeArray[i] = height[jiantou];
                    jiantou++;
                }
            }
            int arrow = L + 1;
            for (int i = 1; i < n ; i++) {
                if(judgeArray[i] == -2){
                    judgeArray[i] = height[arrow];
                    arrow ++;
                }
            }
            System.out.println(left);
            for (int i = 0; i < n; i++) {
                System.out.print(judgeArray[i] + " ");
            }
        }

    }
}

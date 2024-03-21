import java.util.Arrays;
import java.util.Scanner;

public class CS203LAB2F {
    public static void merge(long[][] arr,int l,int mid,int r){
        int n = mid - l + 1;
        int m = r - mid;
        long[][] A = new long[mid - l + 1][2];
        long[][] B = new long[r - mid][2];
        for (int i = 0; i < A.length; i++) {
            A[i][0] = arr[i + l][0];
            A[i][1] = arr[i + l][1];
        }
        for (int i = 0; i < B.length; i++) {
            B[i][0] = arr[1 + mid + i][0];
            B[i][1] = arr[1 + mid + i][1];
        }
        int indexOfA = 0;
        int indexOfB = 0;
        boolean AisDone = false;
        boolean BisDone = false;
        int recordOfj = 0;
        for (int j = 0; j < n + m; j++) {
            if(A[indexOfA][0] > B[indexOfB][0]){
                arr[j + l][0] = B[indexOfB][0];
                arr[j + l][1] = B[indexOfB][1];
                indexOfB++;
            }
            else{
                arr[j + l][0] = A[indexOfA][0];
                arr[j + l][1] = A[indexOfA][1];
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
                arr[j + l][0] = B[indexOfB][0];
                arr[j + l][1] = B[indexOfB][1];
                indexOfB ++;
            }
        }
        if(BisDone){
            for (int j = recordOfj + 1; j < m + n ; j++) {
                arr[j + l][0] = A[indexOfA][0];
                arr[j + l][1] = A[indexOfA][1];
                indexOfA ++;
            }
        }
    }

    public static void mergeBydif(long[][] arr,int l,int mid,int r){
        int n = mid - l + 1;
        int m = r - mid;
        long[][] A = new long[mid - l + 1][3];
        long[][] B = new long[r - mid][3];
        for (int i = 0; i < A.length; i++) {
            A[i][0] = arr[i + l][0];
            A[i][1] = arr[i + l][1];
            A[i][2] = arr[i + l][2];
        }
        for (int i = 0; i < B.length; i++) {
            B[i][0] = arr[1 + mid + i][0];
            B[i][1] = arr[1 + mid + i][1];
            B[i][2] = arr[1 + mid + i][2];
        }
        int indexOfA = 0;
        int indexOfB = 0;
        boolean AisDone = false;
        boolean BisDone = false;
        int recordOfj = 0;
        for (int j = 0; j < n + m; j++) {
            if(A[indexOfA][2] > B[indexOfB][2]){
                arr[j + l][0] = B[indexOfB][0];
                arr[j + l][1] = B[indexOfB][1];
                arr[j + l][2] = B[indexOfB][2];
                indexOfB++;
            }
            else{
                arr[j + l][0] = A[indexOfA][0];
                arr[j + l][1] = A[indexOfA][1];
                arr[j + l][2] = A[indexOfA][2];
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
                arr[j + l][0] = B[indexOfB][0];
                arr[j + l][1] = B[indexOfB][1];
                arr[j + l][2] = B[indexOfB][2];
                indexOfB ++;
            }
        }
        if(BisDone){
            for (int j = recordOfj + 1; j < m + n ; j++) {
                arr[j + l][0] = A[indexOfA][0];
                arr[j + l][1] = A[indexOfA][1];
                arr[j + l][2] = A[indexOfA][2];
                indexOfA ++;
            }
        }

    }

    public static void mergeSort(long[][] arr,int l,int r){
        if(l < r){
            int mid = (l + r) / 2;
            mergeSort(arr,l,mid);
            mergeSort(arr,mid + 1,r);
            merge(arr,l,mid,r);
        }
    }

    public static void mergeSortBydif(long[][] arr,int l,int r){
        if(l < r){
            int mid = (l + r) / 2;
            mergeSortBydif(arr,l,mid);
            mergeSortBydif(arr,mid + 1,r);
            mergeBydif(arr,l,mid,r);
        }
    }

    public static void main(String[] args) {
        int n,p,q;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        p = input.nextInt();
        q=input.nextInt();
        long[][] properties = new long[n][2];
        long max_Strength = 0;
        for (int i = 0; i < n; i++) {
            //输入height
            properties[i][0] = input.nextLong();
            //输入strength
            properties[i][1] = input.nextLong();
        }
        mergeSort(properties,0,n - 1);
        /*
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(properties[i]));
        }
        */
        if(q == 0){
            for (int i = 0; i < n; i++) {
                max_Strength += properties[i][1];
            }
        }
        else if(p == 0){
            for (int i = n - 1; i >= 0; i--) {
                if(q > 0){
                    if(properties[i][1] < properties[i][0]){
                        properties[i][1] = properties[i][0];
                        q--;
                    }
                }
                else{
                    break;
                }
            }
            for (int i = 0; i < n; i++) {
                max_Strength += properties[i][1];
            }
        }
        else{
            long[][] copyOfpro = new long[n][3];
            long pTimes = 1;
            for (int i = 0; i < p; i++) {
                pTimes *= 2;
            }
            for (int i = 0; i < n; i++) {
                copyOfpro[i][0] = properties[i][0];
                copyOfpro[i][1] = properties[i][1];
                copyOfpro[i][2] = properties[i][0] - properties[i][1];
            }
            mergeSortBydif(copyOfpro,0,n - 1);
            for (int i = 0; i < n; i++) {
                max_Strength += properties[i][1];
            }
            int index = n;
            for (int i = n - 1; i >= 0 ; i--) {
                if(q - 1 > 0 && copyOfpro[i][2] > 0){
                    max_Strength += copyOfpro[i][2];
                    q--;
                    index = i;
                }
            }
            long[] recordOfPossible = new long[n];
            for (int i = 0; i < n; i++) {
                long recordOfMax = max_Strength;
                if(i >= index ){
                    recordOfMax = recordOfMax - copyOfpro[i][0] + copyOfpro[i][0] * pTimes;
                    if(copyOfpro[index - 1][0] - copyOfpro[index - 1][1] > 0){
                        recordOfMax += copyOfpro[index - 1][0] - copyOfpro[index - 1][1];
                    }
                }else{
                   recordOfMax = recordOfMax - copyOfpro[i][1] + copyOfpro[i][0] * pTimes;
                }
                recordOfPossible[i] = recordOfMax;
            }
            for (int i = 0; i < n; i++) {
                if(recordOfPossible[i] > max_Strength){
                    max_Strength = recordOfPossible[i];
                }
            }
        }
        System.out.print(max_Strength);
    }
}

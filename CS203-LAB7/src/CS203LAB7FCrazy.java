import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CS203LAB7FCrazy {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        ArrayList<Long> pets = new ArrayList<>();
        ArrayList<Long> human = new ArrayList<>();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int a = input.nextInt();
            long b = input.nextLong();
            if(a == 0){
                pets.add(b);
                if(human.size() != 0){
                    human = doIt(human);
                    long min = Math.abs(b - human.get(0));
                    int record = 0;
                    for (int j = 0; j < human.size(); j++) {
                        if(Math.abs(b- human.get(j)) < min){
                            min = Math.abs(b-human.get(j));
                            record = j;
                        }
                    }
                    human.remove(record);
                    for (int j = 0; j < pets.size(); j++) {
                        if(pets.get(j) == b){
                            pets.remove(j);
                            break;
                        }
                    }
                    ans+=min;

                }
                System.out.println(ans);
            }
            else{
                human.add(b);
                if(pets.size() != 0){
                    pets = doIt(pets);
                    long min = Math.abs(b - pets.get(0));
                    int record = 0;
                    for (int j = 0; j < pets.size(); j++) {
                        if(Math.abs(b- pets.get(j)) < min){
                            min = Math.abs(b-pets.get(j));
                            record = j;
                        }
                    }
                    pets.remove(record);
                    for (int j = 0; j < human.size(); j++) {
                        if(human.get(j) == b){
                            human.remove(j);
                            break;
                        }
                    }
                    ans += min;
                }
                System.out.println(ans);
            }
        }
        System.out.println(ans);
    }

    public static ArrayList<Long> doIt(ArrayList<Long> arr){
        long[] array = new long[arr.size()];
        long[] A = new long[arr.size()];
        long[] B = new long[arr.size()];
        ArrayList<Long> march = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            array[i] = arr.get(i);
        }
        mergeSort(array,0,array.length - 1,A,B);
        for (int i = 0; i < arr.size(); i++) {
             march.add(array[i]);
        }
        return march;
    }

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
}

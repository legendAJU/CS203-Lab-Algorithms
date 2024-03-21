import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CS203LAB7B {
    public static void main(String[] args) {
        int N;
        QReader input = new QReader();
        QWriter out = new QWriter();
        N = input.nextInt();
        int swapCount = 0;
        int[] arr = new int[N + 1];
        for (int i =1; i <= N; i++) {
            swapCount = 0;
            int num = input.nextInt();
            if(arr[1] == 0){
                arr[1] = num;
            }else{
                int index = i;
                arr[index] = num;
                while(index != 1){
                    if(arr[index] > arr[index / 2]){
                        swap(arr,index,index / 2);
                        index = index / 2;
                        swapCount++;
                    }else{
                        break;
                    }
                }
            }
            out.print(swapCount + " ");
        }
        out.close();
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}


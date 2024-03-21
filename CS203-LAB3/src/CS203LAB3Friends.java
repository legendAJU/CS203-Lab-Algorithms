import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CS203LAB3Friends {
    public static void main(String[] args) {
        int T;
        int n;
        QReader2 in = new QReader2();
        QWriter2 out = new QWriter2();
        T = in.nextInt();
        for (int i = 0; i < T; i++) {
            n = in.nextInt();
            if(n % 2 == 1){
                Gift firstGift = new Gift(-1);
                Gift currentGift = new Gift(-1);
                Gift[] heap = new Gift[n + 2];
                Gift[] heap1 = new Gift[n + 2];
                heap[0] = firstGift;
                heap1[0] = firstGift;
                heap1[n + 1] = currentGift;
                heap[n + 1] = currentGift;
                int[] ans = new int[n + 1];
                for (int j = 0; j < n + 1; j++) {
                    ans[j] = -1;
                }
                for (int j = 1; j < n + 1 ; j++) {
                        int value = in.nextInt();
                        Gift gift = new Gift(value);
                        heap[j] = gift;
                        heap1[j] = gift;
                }
                mergeSort(heap1,1,n);
                for (int j = 0; j < n + 1 ; j++) {
                    if(heap1[j] != null){
                        heap1[j].nextGift = heap1[j + 1];
                    }
                }
                for (int j = n + 1; j >= 1 ; j--) {
                    heap1[j].lastGift = heap1[j - 1];
                }
                {
                    Gift mid = heap1[(n + 1) / 2];
                    ans[n] = mid.value;
                    int count = n;
                    for (int j = n; j >= 3 ; j -= 2 ) {
                        if((heap[j].value > mid.value && heap[j - 1].value < mid.value) || (heap[j].value < mid.value && heap[j - 1].value > mid.value)){
                            heap[j].nextGift.lastGift = heap[j].lastGift;
                            heap[j].lastGift.nextGift = heap[j].nextGift;

                            heap[j - 1].nextGift.lastGift = heap[j - 1].lastGift;
                            heap[j - 1].lastGift.nextGift = heap[j - 1].nextGift;

                        }
                        else if(heap[j].value >= mid.value && heap[j - 1].value >= mid.value){
                            heap[j].nextGift.lastGift = heap[j].lastGift;
                            heap[j].lastGift.nextGift = heap[j].nextGift;

                            heap[j - 1].nextGift.lastGift = heap[j - 1].lastGift;
                            heap[j - 1].lastGift.nextGift = heap[j - 1].nextGift;

                            mid = mid.lastGift;
                        }
                        else if(heap[j].value <= mid.value && heap[j - 1].value <= mid.value){
                            heap[j].nextGift.lastGift = heap[j].lastGift;
                            heap[j].lastGift.nextGift = heap[j].nextGift;

                            heap[j - 1].nextGift.lastGift = heap[j - 1].lastGift;
                            heap[j - 1].lastGift.nextGift = heap[j - 1].nextGift;

                            mid = mid.nextGift;
                        }
                        count--;
                        ans[count] = mid.value;
                    }
                }
                int count = 0;
                for (int j = 0; j <= n; j++) {
                    if(ans[j] >= 0){
                        count++;
                        if(count != (n + 1) / 2){
                            out.print(ans[j] + " ");
                        }else{
                            out.print(ans[j]);
                            break;
                        }
                    }
                }
                if(i != T - 1){
                    out.print("\n");
                }else{
                    out.close();
                }
            }
            else{
                Gift firstGift = new Gift(-1);
                Gift currentGift = new Gift(-1);
                Gift[] heap = new Gift[n + 1];
                Gift[] heap1 = new Gift[n + 1];
                heap[0] = firstGift;
                heap1[0] = firstGift;
                heap1[n] = currentGift;
                heap[n] = currentGift;
                int[] ans = new int[n + 1];
                for (int j = 0; j < n + 1; j++) {
                    ans[j] = -1;
                }
                for (int j = 1; j < n + 1 ; j++) {
                    int value = in.nextInt();
                    if(j != n){
                        Gift gift = new Gift(value);
                        heap[j] = gift;
                        heap1[j] = gift;
                    }

                }
                mergeSort(heap1,1,n - 1);
                for (int j = 0; j < n ; j++) {
                    if(heap1[j] != null){
                        heap1[j].nextGift = heap1[j + 1];
                    }
                }
                for (int j = n; j >= 1 ; j--) {
                    heap1[j].lastGift = heap1[j - 1];
                }
                {
                    Gift mid = heap1[(n) / 2];
                    ans[n] = mid.value;
                    int count = n;
                    for (int j = n - 1; j >= 3 ; j -= 2 ) {
                        if((heap[j].value > mid.value && heap[j - 1].value < mid.value) || (heap[j].value < mid.value && heap[j - 1].value > mid.value)){
                            heap[j].nextGift.lastGift = heap[j].lastGift;
                            heap[j].lastGift.nextGift = heap[j].nextGift;

                            heap[j - 1].nextGift.lastGift = heap[j - 1].lastGift;
                            heap[j - 1].lastGift.nextGift = heap[j - 1].nextGift;

                        }
                        else if(heap[j].value >= mid.value && heap[j - 1].value >= mid.value){
                            heap[j].nextGift.lastGift = heap[j].lastGift;
                            heap[j].lastGift.nextGift = heap[j].nextGift;

                            heap[j - 1].nextGift.lastGift = heap[j - 1].lastGift;
                            heap[j - 1].lastGift.nextGift = heap[j - 1].nextGift;

                            mid = mid.lastGift;
                        }
                        else if(heap[j].value <= mid.value && heap[j - 1].value <= mid.value){
                            heap[j].nextGift.lastGift = heap[j].lastGift;
                            heap[j].lastGift.nextGift = heap[j].nextGift;

                            heap[j - 1].nextGift.lastGift = heap[j - 1].lastGift;
                            heap[j - 1].lastGift.nextGift = heap[j - 1].nextGift;

                            mid = mid.nextGift;
                        }
                        count--;
                        ans[count] = mid.value;
                    }
                }
                int count = 0;
                for (int j = 0; j <= n; j++) {
                    if(ans[j] >= 0){
                        count++;
                        if(count != (n - 1 + 1) / 2){
                            out.print(ans[j] + " ");
                        }else{
                            out.print(ans[j]);
                            break;
                        }
                    }
                }
                if(i != T - 1){
                    out.print("\n");
                }else{
                    out.close();
                }
            }
        }
    }
    public static void merge(Gift[] arr,int l,int mid,int r){
        int n = mid - l + 1;
        int m = r - mid;
        Gift[] A = new Gift[mid - l + 1];
        Gift[] B = new Gift[r - mid];
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
            if(A[indexOfA].value > B[indexOfB].value){
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
    public static void mergeSort(Gift[] arr,int l,int r){
        if(l < r){
            int mid = (l + r) / 2;
            mergeSort(arr,l,mid);
            mergeSort(arr,mid + 1,r);
            merge(arr,l,mid,r);
        }
    }
}

class Gift{
    int value;


    Gift nextGift = null;

    Gift lastGift = null;
    public Gift(int value){
        this.value = value;

    }
}

class QReader2 {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter2 implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}



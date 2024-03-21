import java.io.*;
import java.util.StringTokenizer;
import java.math.*;
public class CS203LAB3Difference {
    public static void main(String[] args) {
        QReader4 in = new QReader4();
        QWriter4 out = new QWriter4();
        int n = in.nextInt();
        Node[] heap = new Node[n + 2];
        Node[] heap1 = new Node[n + 2];
        Node[] A = new Node[n + 10];
        Node[] B = new Node[n + 10];
        heap[0] = new Node(-1);
        heap[n + 1] = new Node(-1);
        heap1[0] = new Node(-1);
        heap1[n + 1] = new Node(-1);

        for (int i = 1; i <= n ; i++) {
            int value = in.nextInt();
            Node temp = new Node(value);
            heap[i] = temp;
            heap1[i] = temp;
        }
        mergeSort(heap1,1,n,A,B);
        heap1[n + 1].last = heap1[n];
        for (int i = 1; i < n + 1; i++) {
            heap1[i].next = heap1[i + 1];
            heap1[i].last = heap1[i - 1];
        }

        int ans ;
        for (int i = 1; i <= n - 1 ; i++) {
            if(heap[i].next.value > 0 && heap[i].last.value > 0) {
                ans = Math.min(Math.abs(heap[i].next.value - heap[i].value), Math.abs(heap[i].last.value - heap[i].value));
            } else if(heap[i].next.value == -1){
                ans = Math.abs(heap[i].last.value - heap[i].value);
            } else {
                ans = Math.abs(heap[i].next.value - heap[i].value);
            }
            out.print(ans+ " ");
            heap[i].last.next = heap[i].next;
            heap[i].next.last = heap[i].last;
        }

        out.close();

    }
    public static void merge(Node[] arr,int l,int mid,int r,Node[] A,Node[] B){
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
    public static void mergeSort(Node[] arr,int l,int r,Node[] A,Node[] B){
        if(l < r){
            int mid = (l + r) / 2;
            mergeSort(arr,l,mid,A,B);
            mergeSort(arr,mid + 1,r,A,B);
            merge(arr,l,mid,r,A,B);
        }
    }
}

class Node{
    int value;

    Node next = null;
    Node last = null;

    public Node(int value){
        this.value = value;
    }
}



class QReader4 {
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

class QWriter4 implements Closeable {
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

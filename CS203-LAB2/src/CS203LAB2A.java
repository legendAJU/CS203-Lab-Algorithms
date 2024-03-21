import java.util.Scanner;
import java.io.*;
import java.util.*;
public class CS203LAB2A {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T;
        T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n,m;
            n = in.nextInt();
            m = in.nextInt();
            int[] A = new int[n];
            int[] B = new int[m];
            for (int j = 0; j < n; j++) {
                A[j] = in.nextInt();
            }
            for (int j = 0; j < m; j++) {
                B[j] = in.nextInt();
            }
            int[] ans = new int[m + n];
            int indexOfA = 0;
            int indexOfB = 0;
            boolean AisDone = false;
            boolean BisDone = false;
            int recordOfj = 0;
            for (int j = 0; j < n + m; j++) {
                if(A[indexOfA] > B[indexOfB]){
                    ans[j] = B[indexOfB];
                    indexOfB++;
                }
                else{
                    ans[j] = A[indexOfA];
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
                    ans[j] = B[indexOfB];
                    indexOfB ++;
                }
            }
            if(BisDone){
                for (int j = recordOfj + 1; j < m + n ; j++) {
                    ans[j] = A[indexOfA];
                    indexOfA ++;
                }
            }
            for (int j = 0; j < m + n; j++) {
                out.print(ans[j] + " ");
            }
           out.print("\n");
        }
        out.close();
    }
}
class QReader {
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

class QWriter implements Closeable {
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

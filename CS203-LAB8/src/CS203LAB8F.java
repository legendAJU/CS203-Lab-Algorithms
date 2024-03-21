import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CS203LAB8F {

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();

        int T = input.nextInt();
        long constant = 1000000007;
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            int m = input.nextInt();

            long[] A = new long[n + 1];
            long[] B = new long[n + 1];

            for (int j = 1; j <= n; j++) {
                long aj = input.nextLong();
                long bj = input.nextLong();
                A[j] = aj;
                B[j] = bj;
            }

            element[] elements = new element[n + 1];

            for (int j = 1; j <= n; j++) {
                elements[j] = new element(j);
            }
            for (int j = 1; j <= m; j++) {
                int u = input.nextInt();
                int v = input.nextInt();
                elements[u].pointTo.add(elements[v]);
                elements[v].bePointedBy.add(elements[u]);
            }
            for (int j = 1; j <= n; j++) {
                elements[j].iniNum();
            }
            element enterElement = null;
            Queue<element> queue = new LinkedList<>();
            for (int j = 1; j <=elements.length - 1; j++) {
                if(elements[j].bePointedByNum == 0){
                    enterElement = elements[j];
                    queue.add(enterElement);
                }
            }
            long[] f = new long[n + 1];
            while(!queue.isEmpty()){
                element temp = queue.peek();
                for(element j : temp.pointTo){
                    j.bePointedByNum--;
                    if(j.bePointedByNum == 0){
                        queue.add(j);
                    }
                    f[j.data] = (f[j.data] % constant+ f[temp.data]% constant + A[temp.data]% constant) ;
                }
                queue.poll();
            }
            long ans = 0;
            for (int j = 1; j <= n; j++) {
                ans = (ans + f[j] * B[j]) % constant;
            }
            out.println(ans);
            if(i == T - 1){
                out.close();
            }
        }
    }


}

class element{
    int data;

    ArrayList<element> pointTo = new ArrayList<>();

    ArrayList<element> bePointedBy = new ArrayList<>();

    int bePointedByNum;

    public element(int data){
        this.data = data;
        this.bePointedByNum = 0;
    }
    public void iniNum(){
        this.bePointedByNum = bePointedBy.size();
    }
    public void updateNum(){
        this.bePointedByNum --;
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
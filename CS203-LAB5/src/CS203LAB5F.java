import java.io.*;
import java.util.StringTokenizer;

public class CS203LAB5F {
    public static void main(String[] args) {
        QReader in = new QReader();
        char[][] alphabetArray = new char[123][2];
        int ascii = 97;
        int ascii1 = 97;
        for (int i = 0; i < 26; i++) {
            char temp = in.next().charAt(0);
            alphabetArray[temp][0] = temp;
            alphabetArray[temp][1] = Character.toString(ascii++).charAt(0);
        }
        String s = in.nextLine();
        char[] sArray1 = s.toCharArray();
        char[] sArray = s.toCharArray();
        for (int i = 0; i < sArray.length; i++) {
            sArray1[i] = alphabetArray[sArray1[i]][1];
        }
       int[] next = new int[sArray.length];
       prefixFunction(sArray,sArray1,next);
        System.out.print(sArray.length - next[sArray.length - 1]);
    }
    public static void prefixFunction(char[] P, char[] Q,int[] next){
        int k = 0;
        for (int i = 1; i < P.length; i++) {
            while(k > 0 && Q[k] != P[i]){
                k = next[k - 1];
            }
            if(Q[k] == P[i]){
                k++;
            }
            next[i] = k;
        }
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
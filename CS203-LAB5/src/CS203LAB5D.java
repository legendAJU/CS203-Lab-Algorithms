import java.io.*;
import java.util.StringTokenizer;

public class CS203LAB5D {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            String s = in.next();
            char[] charArray = s.toCharArray();
            int[] next = new int[charArray.length];
            prefixFunction(s,next);
            int L = s.length() - next[s.length() - 1];
            if(s.length() % L == 0){
                if(s.length() == L){
                    System.out.println(s.length());
                }else{
                    System.out.println(0);
                }
            }else{
                L = L - next[s.length() - 1] % L;
                System.out.println(L);
            }
        }
    }

    public static void prefixFunction(String s, int[] next){
        char[] P = s.toCharArray();
        int k = 0;
        for (int i = 1; i < P.length; i++) {
            while(k > 0 && P[k] != P[i]){
                k = next[k - 1];
            }
            if(P[k] == P[i]){
                k++;
            }
            next[i] = k;
        }
    }
}



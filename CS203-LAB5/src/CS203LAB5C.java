import java.util.Scanner;

public class CS203LAB5C {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.next();
        int[][] ans = new int[s.length()][26];

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

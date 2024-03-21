import java.util.Scanner;

public class CS203LAB5B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int n = s.length();
        int[] next = new int[n];
        prefixFunction(s, next);
        for (int i = 0; i < next.length; i++) {
            if(i == next.length - 1){
                System.out.print(next[i]);
            }else{
                System.out.println(next[i]);
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

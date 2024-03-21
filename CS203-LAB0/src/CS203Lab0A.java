import java.util.Scanner;

public class CS203Lab0A {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;
        n = input.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = input.nextInt();
        }
        int T;
        T = input.nextInt();
        int[] B = new int[T];
        for (int i = 0; i < T; i++) {
            B[i] = input.nextInt();
        }


        for (int i = 0; i < T; i++) {
            String a = "no";
            for (int j = 0; j < n; j++) {
                if (B[i] == A[j]) {
                    a = "yes";
                    break;
                }
            }
            System.out.println(a);
        }


    }
}
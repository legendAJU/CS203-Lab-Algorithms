import java.util.Scanner;

public class CS203LAB8A {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            int m = input.nextInt();
            int[][] ans = new int[n + 1][n + 1];
            for (int j = 0; j < m; j++) {
                int x = input.nextInt();
                int y = input.nextInt();
                ans[x][y] = 1;
            }
            for (int j = 1; j <= n ; j++) {
                for (int k = 1; k <= n; k++) {
                    System.out.print(ans[j][k] + " ");
                }
                System.out.println();
            }
        }
    }
}

import java.util.Scanner;

public class CS203Lab0E {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T;
        T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int m,n,p;
            m = input.nextInt();
            n = input.nextInt();
            p = input.nextInt();
            char[][] ans = new char[2 * n + 2 * p + 1][2 * m + 1 + 2 * n];
            for (int j = 0; j < (2 * n + 2 * p + 1); j++) {
                for (int k = 0; k < (2 * m + 1 + 2 * n); k++) {
                    ans[j][k] = '.';
                }
            }
            //画正面
            for (int j = 2 * n; j < (2 * n + 2 * p + 1) ; j+=2) {
                for (int k = 0; k < 2 * m + 1; k+=2) {
                    ans[j][k] = '+';
                }
                for (int k = 1; k < 2 * m ; k+=2) {
                    ans[j][k] = '-';
                }
            }
            for (int j = 2 * n + 1; j < (2 * n + 2 * p + 1) ; j+=2) {
                for (int k = 0; k < 2 * m + 1; k+=2) {
                    ans[j][k] = '|';
                }
            }
            //画上面
            for (int j = 0; j < 2 * n; j += 2) {
                for (int k = 2 * m + 1 + 2 * n - j - 1; k > 2 * n - 1 - j ; k -= 2) {
                    ans[j][k] = '+';
                }
                for (int k = 2 * m + 1 + 2 * n - j - 1 - 1; k > 2 * n - 1 - j + 1 ; k -= 2) {
                    ans[j][k] = '-';
                }
            }
            for (int j = 1; j < 2 * n ; j += 2) {
                for (int k = 2 * m + 1 + 2 * n - j - 1; k >= 2 * n - j ; k -= 2) {
                    ans[j][k] = '/';
                }
            }
            //画侧面
            int q = 1;
            for (int k = 2 * m + 1; k <= 2 * m + 2 * n + 1 - 1 - 1; k += 2) {
                for (int j = 2 * n + 1 - (2 * q - 2); j <= 2 * p + 1 + 2 * n - 1 - (2 * q - 1)  ; j += 2) {
                    ans[j][k] = '/';
                }
                q++;
            }
            int w = 1;
            for (int k = 2 * m + 1 + 1; k <= 2 * m + 2 * n + 1 - 1 ; k += 2) {
                for (int j = 2 * n + 1 - (2 * w - 2) - 1; j <= 2 * p + 1 + 2 * n - 1 - (2 * w)  ; j += 2) {
                    ans[j][k] = '+';
                }
                for (int j = 2 * n + 1 - (2 * w - 2) - 1 - 1; j <= 2 * p + 1 + 2 * n - 1 - (2 * w) -1 ; j += 2) {
                    ans[j][k] = '|';
                }
                w++;
            }
            
            

            //以下是答案的输出
            for (int j = 0; j < ans.length; j++) {
                for (int k = 0; k < ans[j].length; k++) {
                    System.out.print(ans[j][k]);
                }
                System.out.println();
            }
        }
    }
}

import java.util.Scanner;

public class CS203LAB1B {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            long n = input.nextInt();
            System.out.println(n * (n + 1) * (2 + n) / 6);
        }
    }
}

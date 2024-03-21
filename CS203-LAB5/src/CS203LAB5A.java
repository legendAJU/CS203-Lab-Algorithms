import java.util.Scanner;
import java.util.Set;

public class CS203LAB5A {
    public static void main(String[] args) {
        int T;
        Scanner in = new Scanner(System.in);
        T = in.nextInt();
        for (int i = 0; i < T; i++) {
            String s = in.next();
            int n = s.length();
            int count = n * (n + 1) / 2;
            System.out.println(count);
        }
    }
}

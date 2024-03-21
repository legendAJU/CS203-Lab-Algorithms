import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] father = new int[n + 1];
        for (int i = 1; i <= n - 1; i++) {
            int dad = input.nextInt();
            int son = input.nextInt();
            father[son] = dad;
        }
        int[] isFather = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            isFather[father[i]] = 2;
        }
        for (int i = 0; i <= n; i++) {
            if(isFather[i] == 0){
                System.out.print(i + " ");
            }
        }
    }
}


import java.util.PriorityQueue;
import java.util.Scanner;

public class test {
    /**
     * 1177 聪明的木匠
     * 按每段大小由大到小切割就可以使体力最少.
     * 证明:   L1+L2+……+LN=M
     * 第一次切割Li, 花费体力：M
     * 第二次切割Lj，花费体力：M-Li
     * 第三次切割Ls，花费体力：M-Li-Lj
     * 如此下去，
     * N次切割后花费体力为：M-Li-Lj-……-Lk
     * 那么，花费的总体力为NM-（N-1）Li-（N-2）Lj-……-Lk
     * 显然，要使得总体力最少，只要Li>Lj>……>Lk
     */


    public static void main(String[] args) {

        Scanner cin = new Scanner(System.in);
        int T = cin.nextInt();
        for (int j = 0; j < T; j++) {
            int n = cin.nextInt();
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            for (int i = 1; i <= n; i++) {
                int x = cin.nextInt();
                priorityQueue.add(x);
            }
            System.out.println(solve(priorityQueue));
        }

    }

    public static int solve(PriorityQueue<Integer> priorityQueue) {
        int ans = 0;
        while (priorityQueue.size() >= 2) {
            int x = priorityQueue.poll();
            int y = priorityQueue.poll();
            ans += x + y;
            priorityQueue.add(x + y);
        }
        return ans;
    }

}
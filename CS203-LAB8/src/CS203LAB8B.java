import java.io.*;
import java.util.*;

public class CS203LAB8B {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int s = in.nextInt();
            ArrayList<Integer>[] graph = new ArrayList[n + 1];
            int[] ans = new int[n + 1];
            for (int j = 0; j <= n; j++) {
                graph[j] = new ArrayList<>();
                graph[j].add(j);
                ans[j] = -1;
            }
            for (int j = 0; j < m; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                graph[x].add(y);
                graph[y].add(x);
            }
            ans[s] = 0;
            Queue<ArrayList<Integer>> queue = new LinkedList<>();
            queue.add(graph[s]);
            while(!queue.isEmpty()){
                ArrayList<Integer> temp = queue.peek();
                for (int j = 1; j < temp.size(); j++) {
                    if(ans[temp.get(j)] == -1){
                        ans[temp.get(j)] = ans[temp.get(0)] + 1;
                        queue.add(graph[temp.get(j)]);
                    }
                }
                queue.poll();
            }
            for (int j = 1; j <= n ; j++) {
                out.print(ans[j] + " ");
            }
            out.print("\n");
            if(i == T - 1){
                out.close();
            }
        }
    }
}


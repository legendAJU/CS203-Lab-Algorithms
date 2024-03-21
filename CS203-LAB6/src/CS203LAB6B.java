import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CS203LAB6B {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int num = input.nextInt();
        ArrayList<pairs>[] datas = new ArrayList[n + 1];
        for (int i = 1; i <= n ; i++) {
            datas[i] = new ArrayList<>();
        }
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n - 1 ; i++) {
            int dad = input.nextInt();
            int son = input.nextInt();
            int weight = input.nextInt();
            datas[dad].add(new pairs(dad,son,weight));
            datas[son].add(new pairs(son,dad,weight));
        }
        boolean[] isLeaf = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            if(datas[i].size() == 1 && datas[i].get(0).belong != 1){
                isLeaf[i] = true;
            }
        }
        Queue<ArrayList> queue = new LinkedList<>();
        queue.add(datas[1]);
        while (!queue.isEmpty()){
            ArrayList<pairs> temp = queue.peek();
            for(pairs i : temp){
                if(ans[i.data] == 0){
                    if(i.data != 1){
                        ans[i.data] = ans[i.belong] + i.weight;
                        queue.add(datas[i.data]);
                    }
                }
            }
            queue.poll();
        }
        int count = 0;
        for (int i = 1; i <= n ; i++) {
            if(ans[i] == num && isLeaf[i]){
                count++;
            }
        }
        System.out.print(count);
    }
}

class pairs{

    int belong;
    int data;
    int weight;
    public pairs(int belong,int data, int weight){
        this.belong = belong;
        this.data = data;
        this.weight = weight;
    }
}


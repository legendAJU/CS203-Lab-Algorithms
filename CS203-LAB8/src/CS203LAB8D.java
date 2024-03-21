import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CS203LAB8D {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            node[] nodes =  new node[n + 1];
            for (int j = 1; j <= n; j++) {
                nodes[j] = new node(j);
            }
            for (int j = 1; j <= n - 1; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                nodes[y].children.add(nodes[x]);
                nodes[x].father = nodes[y];
            }
            node root = null;
            for (int j = 1; j <= n - 1; j++) {
                if(nodes[j].father == null){
                    root = nodes[j];
                    break;
                }
            }

            int[] first = new int[n + 1];
            int[] second = new int[n + 1];
            int[] time = new int[1];

            dfs(root,first,second,time);

            for (int j = 0; j < m; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                if(first[x] >= first[y] && second[x] <= second[y]){
                    out.print("Yes ");
                }else{
                    out.print("No ");
                }
            }
            if(i == T - 1){
                out.close();
            }
        }

    }

    public static void dfs(node root,int[] first,int[] second,int[] time){
        //采用根左右遍历。
        first[root.data] = ++time[0];
        for (int i = 0; i < root.children.size(); i++) {
            dfs(root.children.get(i),first,second,time);
        }
        second[root.data] = ++time[0];
    }
}

class node{
    node father;
    ArrayList<node> children;

    int data;
    public node(int data){
        this.data = data;
        this.father = null;
        this.children = new ArrayList<>();
    }
}

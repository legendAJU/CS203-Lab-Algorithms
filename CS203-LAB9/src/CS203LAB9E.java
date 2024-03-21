import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class CS203LAB9E {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();
        int n = input.nextInt();
        int m = input.nextInt();
        int S = input.nextInt();
        node[] graph = new node[n + 1];
        node[] reverseGraph = new node[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new node(i);
            reverseGraph[i] = new node(i);
        }

        for (int i = 1; i <= m; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            graph[u].pointTo.add(graph[v]);
            graph[v].bePointedNum++;
            reverseGraph[v].pointTo.add(reverseGraph[u]);
        }

        ArrayList<node> reverseDfs = new ArrayList<>();
        Dfs(reverseGraph,reverseDfs);
        node[] DfsLine = new node[n + 1];
        for (int i = n; i >= 1;i--) {
            DfsLine[i] = graph[reverseDfs.get(n - i).num];
        }
        int count = 0;
        bigNode[] finalGraph = new bigNode[n + 1];
        for (int i = 1; i <= n; i++) {
            node temp = DfsLine[i];
            if(!temp.isVisited){
                count++;
                finalGraph[count] = new bigNode(count);
                Stack<node> stack = new Stack<>();
                temp.isVisited = true;
                stack.add(temp);
                explore(temp,stack,finalGraph[count]);
            }
        }
        for (int i = 1; i <= n; i++) {
            for(node j : graph[i].pointTo){
                if(j.belongTo != graph[i].belongTo){
                    finalGraph[graph[i].belongTo].pointTo.add(finalGraph[j.belongTo]);
                    finalGraph[j.belongTo].bePointedNum++;
                }
            }
        }
        int count1 = 0;
        for (int i = 1; i <= count ; i++) {
            if(finalGraph[i].bePointedNum == 0){
                count1++;
            }
        }
        if(finalGraph[graph[S].belongTo].bePointedNum == 0){
            out.print(count1 - 1);
        }else{
            out.print(count1);
        }
        out.close();
    }

    public static void Dfs(node[] graph, ArrayList<node> reverseDfs){
        int n = graph.length - 1;
        Stack<node> stack = new Stack<>();
        for (int i = 1; i <= n ; i++) {
            node temp = graph[i];
            if(!temp.isVisited){
                temp.isVisited = true;
                stack.add(temp);
                explore(temp,stack,reverseDfs);
            }
        }
    }

    public static void explore(node start,Stack<node> stack,ArrayList<node> reverseDfs){
        for(node i : start.pointTo){
            if(!i.isVisited){
                stack.add(i);
                i.isVisited = true;
                explore(i,stack,reverseDfs);
            }
        }
        node temp = stack.pop();
        reverseDfs.add(temp);
    }

    public static void explore(node start,Stack<node> stack,bigNode bigNode){
        for(node i : start.pointTo){
            if(!i.isVisited){
                stack.add(i);
                i.isVisited = true;
                explore(i,stack,bigNode);
            }
        }
        node temp = stack.pop();
        temp.belongTo = bigNode.num;
        bigNode.nodeSet.add(temp);
    }
}

class node{
    int num;

    ArrayList<node> pointTo;

    boolean isVisited;

    int bePointedNum;

    int belongTo;

    public node(int num){
        this.num = num;
        this.pointTo = new ArrayList<>();
    }
}

class bigNode{
    ArrayList<node> nodeSet = new ArrayList<>();

    int bePointedNum;

    ArrayList<bigNode> pointTo;

    int num;

    public bigNode(int num){
        this.num = num;
        this.pointTo = new ArrayList<>();
        this.nodeSet = new ArrayList<>();

    }
}


class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}

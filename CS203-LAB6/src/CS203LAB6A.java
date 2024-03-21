import java.io.*;
import java.util.*;

public class CS203LAB6A {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();
        int n = input.nextInt();
        //build the tree
        node[] nodes = new node[n + 1];
        ArrayList<node>[] datas = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new node(i);
        }
        for (int i = 1; i <= n; i++) {
            datas[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n - 1; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            datas[u].add(nodes[v]);
            datas[v].add(nodes[u]);
        }
        for (int i = 1; i <= n; i++) {
            node indexCity = new node(0);
            indexCity.belong = i;
            datas[i].add(indexCity);
        }
        nodes[1].fatherFound = true;
        Queue<ArrayList<node>> queue = new LinkedList<>();
        queue.add(datas[1]);
        while(! queue.isEmpty()){
            ArrayList<node> temp = queue.peek();
            int index = temp.get(temp.size() - 1).belong;
            for(node i : temp){
                if(! i.fatherFound && i.belong == -1){
                    i.fatherFound = true;
                    nodes[index].children.add(i);
                    i.father = nodes[index];
                    queue.add(datas[i.key]);
                }
            }
            queue.poll();
        }
        for(node i : nodes){
            if(i != null){
                if(i.children.isEmpty()){
                    output.print(i.key + " ");
                }
            }
        }
        output.close();
    }

    public static void leafNode(node root, ArrayList<node> subTreeLeaf){
        if(root.children.isEmpty()){
            subTreeLeaf.add(root);
            root.visited = true;
            System.out.print(root.key + " ");
            return;
        }else{
            for (node i : root.children) {
                leafNode(i,subTreeLeaf);
            }
        }
    }
}
class node{
    int key;
    long p;
    long e;
    int belong;

    boolean visited = false;
    boolean fatherFound = false;

    long information;
    int depth;
    node father;
    ArrayList<node> children = new ArrayList<>();
    public node(int key){
        this.belong = -1;
        this.key = key;
        this.depth = 0;
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
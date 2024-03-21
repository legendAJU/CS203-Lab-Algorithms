import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CS203LAB7A {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            node[] nodes = new node[n + 1];
            for (int j = 1; j <= n ; j++) {
                int num = input.nextInt();
                nodes[j] = new node(num);
            }
            for(int j = 1; j <= n - 1;j++){
                int u = input.nextInt();
                int v = input.nextInt();
                nodes[u].children.add(nodes[v]);
                nodes[v].father = nodes[u];
            }
            node root = null;
            //fint the root
            for (int j = 1; j <= n; j++) {
                if(nodes[j].father == null){
                    root = nodes[j];
                }
            }

            ArrayList<node> subTreeLeaf = new ArrayList<>();
            leafNode(root,subTreeLeaf);
            for(node j : subTreeLeaf){
                j.visited = false;
                node temp = j;
                while(temp != root) {
                    j.depth++;
                    temp = temp.father;
                }
            }
            for(node j : subTreeLeaf){
                int depth = j.depth;
                node temp = j;
                while(temp.father != null){
                    if(temp.father.visited){
                        temp = temp.father;
                    }
                    else{
                        temp.father.depth = (--depth);
                        temp.father.visited = true;
                        temp = temp.father;
                    }
                }
            }
            ArrayList<ArrayList<node>> ceng = new ArrayList<>();
            Queue<node> nodeQueue = new LinkedList<>();
            nodeQueue.add(root);
            while(!nodeQueue.isEmpty()){
                ArrayList<node> intoCeng = new ArrayList<>();
                int length = nodeQueue.size();
                for (int j = 0; j < length; j++) {
                    node temp = nodeQueue.peek();
                    for(node k : temp.children){
                        nodeQueue.add(k);
                    }
                    intoCeng.add(temp);
                    nodeQueue.poll();
                }
                ceng.add(intoCeng);
            }


            boolean isMaxHeap = true;
            boolean isMinHeap = true;
            int size = 1;
            for (int j = 0; j < ceng.size() - 1; j++) {
                if(j != ceng.size() - 2){
                    if(ceng.get(j).size() != size){
                        isMaxHeap = false;
                        isMinHeap = false;
                        break;
                    }
                    else{
                        for(node k : ceng.get(j)){
                            if(k.children.size() != 2){
                                isMaxHeap = false;
                                isMinHeap = false;
                                break;
                            }else{
                                if(k.data < k.children.get(0).data || k.data < k.children.get(1).data){
                                    isMaxHeap = false;
                                }
                                if(k.data > k.children.get(0).data || k.data > k.children.get(1).data){
                                    isMinHeap = false;
                                }
                            }
                        }
                    }
                }
                else{
                    if(ceng.get(j).size() != size){
                        isMaxHeap = false;
                        isMinHeap = false;
                        break;
                    }else{
                        for (int k = 0; k < ceng.get(j).size() - 1; k++) {
                            node temp1 = ceng.get(j).get(k);
                            node temp2 = ceng.get(j).get(k + 1);
                            if(temp1.children.size() > 2){
                                isMaxHeap = false;
                                isMinHeap = false;
                                break;
                            }
                            else if(temp1.children.size() < temp2.children.size()){
                                isMaxHeap = false;
                                isMinHeap = false;
                                break;
                            }
                            else{
                                if(temp1.children.size() == 1){
                                    if(temp1.data < temp1.children.get(0).data){
                                        isMaxHeap = false;
                                    }
                                    if(temp1.data > temp1.children.get(0).data){
                                        isMinHeap = false;
                                    }
                                }
                                else if(temp1.children.size() == 2){
                                    if(temp1.data < temp1.children.get(0).data || temp1.data < temp1.children.get(1).data){
                                        isMaxHeap = false;
                                    }
                                    if(temp1.data > temp1.children.get(0).data || temp1.data > temp1.children.get(1).data){
                                        isMinHeap = false;
                                    }
                                }
                            }
                        }
                        node temp1 = ceng.get(j).get(ceng.get(j).size() - 1);
                        if(temp1.children.size() > 2){
                            isMaxHeap = false;
                            isMinHeap = false;
                            break;
                        }else{
                            if(temp1.children.size() == 1){
                                if(temp1.data < temp1.children.get(0).data){
                                    isMaxHeap = false;
                                }
                                if(temp1.data > temp1.children.get(0).data){
                                    isMinHeap = false;
                                }
                            }
                            else if(temp1.children.size() == 2){
                                if(temp1.data < temp1.children.get(0).data || temp1.data < temp1.children.get(1).data){
                                    isMaxHeap = false;
                                }
                                if(temp1.data > temp1.children.get(0).data || temp1.data > temp1.children.get(1).data){
                                    isMinHeap = false;
                                }
                            }
                        }
                    }
                }
                size = size * 2;
            }


            if(isMaxHeap || isMinHeap){
                System.out.println("Case #" + (i + 1) + ": YES");
            }
            else{
                System.out.println("Case #" + (i + 1) + ": NO");
            }
        }
    }

    public static void leafNode(node root,ArrayList<node> subTreeLeaf){
        if(root.children.isEmpty()){
            subTreeLeaf.add(root);
            root.visited = true;
            root.isLeaf = true;
            return;
        }else{
            for (node i : root.children) {
                leafNode(i,subTreeLeaf);
            }
        }
    }
}

class node{
    int data;

    ArrayList<node> children = new ArrayList<>();

    node father;

    boolean visited;

    boolean isLeaf;

    int depth;
    public node(int data){
        this.data = data;
        this.father = null;
        this.visited = false;
        this.isLeaf = false;
        this.depth = 0;
    }
}

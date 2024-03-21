import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CS203LAB6F {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //int N = input.nextInt();

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
            long max = 0;
            node biggestP = null;
            ArrayList<node> leafNodes = new ArrayList<>();
            for (int i = 1; i <= n ; i++) {
                long p = input.nextLong();
                nodes[i].p = p;
                if(nodes[i].p > max){
                    max = p;
                    biggestP = nodes[i];
                }
            }
            biggestP.fatherFound = true;
            Queue<ArrayList<node>> queue = new LinkedList<>();
            queue.add(datas[biggestP.key]);
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
            ArrayList<node> subTreeLeaf = new ArrayList<>();
            leafNode(biggestP,subTreeLeaf);
            for(node i : subTreeLeaf){
                i.visited = false;
                node temp = i;
                while(temp != biggestP) {
                    i.depth++;
                    temp = temp.father;
                }
            }
            for(node i : subTreeLeaf){
                int depth = i.depth;
                node temp = i;
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
            //构建层级。
            ArrayList<ArrayList<node>> ceng = new ArrayList<>();
            Queue<node> nodeQueue = new LinkedList<>();
            nodeQueue.add(biggestP);
            while(!nodeQueue.isEmpty()){
                ArrayList<node> intoCeng = new ArrayList<>();
                int length = nodeQueue.size();
                for (int i = 0; i < length; i++) {
                    node temp = nodeQueue.peek();
                    for(node j : temp.children){
                        nodeQueue.add(j);
                    }
                    intoCeng.add(temp);
                    nodeQueue.poll();
                }
                ceng.add(intoCeng);
            }
            long ans = 0;
            for(node i : subTreeLeaf){
                i.e = i.p;
                i.information = i.p;
                ans += i.e;
            }
            for (int i = ceng.size() - 1; i >= 2; i--) {
                ArrayList<node> fathers = ceng.get(i -1);
                for(node j : fathers){
                    if(j.children.isEmpty()){
                        j.information = j.e;
                    }else{
                        long maxInformation = 0;
                        for(node k : j.children){
                            if(k.information > maxInformation){
                                maxInformation = k.information;
                            }
                        }
                        if(maxInformation < j.p){
                            ans = ans - maxInformation + j.p;
                            j.information = j.p;
                        }else{
                            j.information = maxInformation;
                        }
                    }
                }
            }
            if(biggestP.children.size() == 1){
                biggestP.e = biggestP.p;
                if(biggestP.children.get(0).information < biggestP.p){
                    ans = ans - biggestP.children.get(0).information + biggestP.e + biggestP.e;
                }else{
                    ans = ans + biggestP.e;
                }
            }
            else{
                long[] numbers = new long[biggestP.children.size()];
                for (int i = 0; i < numbers.length; i++) {
                    numbers[i] = biggestP.children.get(i).information;
                }
                mergeSort(numbers,0,numbers.length - 1);
                if(numbers[numbers.length - 1] < biggestP.p) {
                    ans = ans - numbers[numbers.length - 1] + biggestP.p;
                }
                if(numbers[numbers.length - 2] < biggestP.p){
                    ans = ans - numbers[numbers.length - 2] + biggestP.p;
                }
            }
            System.out.println(ans);
    }
    public static void leafNode(node root,ArrayList<node> subTreeLeaf){
        if(root.children.isEmpty()){
            subTreeLeaf.add(root);
            root.visited = true;
            return;
        }else{
            for (node i : root.children) {
                leafNode(i,subTreeLeaf);
            }
        }
    }
    public static void merge(long[] arr,int l,int mid,int r){
        int n = mid - l + 1;
        int m = r - mid;
        long[] A = new long[mid - l + 1];
        long[] B = new long[r - mid];
        for (int i = 0; i < A.length; i++) {
            A[i] = arr[i + l];
        }
        for (int i = 0; i < B.length; i++) {
            B[i] = arr[1 + mid + i];
        }
        int indexOfA = 0;
        int indexOfB = 0;
        boolean AisDone = false;
        boolean BisDone = false;
        int recordOfj = 0;
        for (int j = 0; j < n + m; j++) {
            if(A[indexOfA] > B[indexOfB]){
                arr[j + l] = B[indexOfB];
                indexOfB++;
            }
            else{
                arr[j + l] = A[indexOfA];
                indexOfA++;
            }
            if(indexOfA == n ){
                AisDone = true;
                recordOfj = j;
                break;
            }
            if(indexOfB == m ){
                BisDone = true;
                recordOfj = j;
                break;
            }
        }
        if(AisDone){
            for (int j = recordOfj + 1; j < m + n ; j++) {
                arr[j + l] = B[indexOfB];
                indexOfB ++;
            }
        }
        if(BisDone){
            for (int j = recordOfj + 1; j < m + n ; j++) {
                arr[j + l] = A[indexOfA];
                indexOfA ++;
            }
        }
    }
    public static void mergeSort(long[] arr,int l,int r){
        if(l < r){
            int mid = (l + r) / 2;
            mergeSort(arr,l,mid);
            mergeSort(arr,mid + 1,r);
            merge(arr,l,mid,r);
        }
    }
}



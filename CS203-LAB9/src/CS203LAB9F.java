import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CS203LAB9F {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();

        int n = input.nextInt();
        int m = input.nextInt();
        int p = input.nextInt();
        int k = input.nextInt();
        long[][] dist = new long[n + 1][k + 1];
        long ans = 0;
        xiti[][] xitis = new xiti[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                xitis[i][j] = new xiti(i,j);
                dist[i][j] = Long.MAX_VALUE;
            }
        }
        long[][] inputOfXiti = new long[m + 1][4];
        int[][] inputOfPortal = new int[p + 1][3];
        for (int i = 1; i <= m; i++) {
            long u = input.nextLong();
            long v = input.nextLong();
            long w = input.nextLong();
            inputOfXiti[i][1] = u;
            inputOfXiti[i][2] = v;
            inputOfXiti[i][3] = w;
        }
        for (int i = 1; i <= p; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            inputOfPortal[i][1] = u;
            inputOfPortal[i][2] = v;
        }
        for (int i = 0; i <= k; i++) {
            for (int j = 1; j <= m; j++) {
                xitis[(int)inputOfXiti[j][1]][i].roadToOtherXiti.add(new road((int)inputOfXiti[j][2],inputOfXiti[j][3]));
            }
            for (int j = 1; j <= p; j++) {
                xitis[inputOfPortal[j][1]][i].portalToOtherXiti.add(new portal(inputOfPortal[j][2]));
            }
        }
        //Origin
        int S = input.nextInt();
        //Destination
        int V = input.nextInt();
        xiti temp = xitis[S][0];
        boolean[][] hasVisited = new boolean[n + 1][k + 1];
        boolean[][] hasEnteredHeap = new boolean[n + 1][k + 1];
        hasVisited[S][0] = true;
        hasEnteredHeap[S][0] = true;
        dist[S][0] = 0;


        heap heap = new heap(n * (k + 1) ,dist);
        while (true){
            int state = temp.state;
            for(road i : temp.roadToOtherXiti){
                xiti temp1 = xitis[i.end][state];
                if(!hasVisited[temp1.num][temp1.state]){
                    if(!hasEnteredHeap[temp1.num][temp1.state]){
                        heap.insert(temp1);
                        hasEnteredHeap[temp1.num][temp1.state] = true;
                    }
                    if(dist[temp1.num][temp1.state] > dist[temp.num][state] + i.weight){
                        dist[temp1.num][temp1.state] = dist[temp.num][state] + i.weight;

                        heap.updata(temp1);

                    }
                }
            }
            if(state < k){
                for(portal i : temp.portalToOtherXiti){
                    xiti temp1 = xitis[i.end][state + 1];
                    if(!hasVisited[temp1.num][temp1.state]){
                        if(!hasEnteredHeap[temp1.num][temp1.state]){
                            heap.insert(temp1);
                            hasEnteredHeap[temp1.num][temp1.state] = true;
                        }
                        if(dist[temp1.num][temp1.state] > dist[temp.num][state] + i.weight){
                            dist[temp1.num][temp1.state] = dist[temp.num][state] + i.weight;
                            if(hasEnteredHeap[temp1.num][temp1.state]){
                                heap.updata(temp1);
                            }
                        }
                    }
                }
            }
            if(heap.arr[1] == null){
                break;
            }else{
                temp = xitis[heap.arr[1].num][heap.arr[1].state];
                hasVisited[heap.arr[1].num][heap.arr[1].state] = true;
                heap.delete();
            }
        }

        for (int i = 0; i <= k; i++) {
            if(dist[V][i] != Long.MAX_VALUE){
                ans = dist[V][i];
                break;
            }
        }
        for (int i = 0; i <= k; i++) {
            if(dist[V][i] != Long.MAX_VALUE){
                ans = Math.min(ans, dist[V][i]);
            }
        }
        out.print(ans);
        out.close();


    }
}

class xiti{
    int num;

    ArrayList<road> roadToOtherXiti;

    ArrayList<portal> portalToOtherXiti;

    int position;

    int state;



    public xiti(int num,int state){
        this.num = num;
        this.state = state;
        this.portalToOtherXiti = new ArrayList<>();
        this.roadToOtherXiti = new ArrayList<>();
    }
    public long getWeight(int end){
        for(road i : roadToOtherXiti){
            if(i.end == end){
                return i.weight;
            }
        }
        return -1;
    }
}

class road{
    int end;

    long weight;

    public road(int end, long weight){
        this.end = end;
        this.weight = weight;
    }

}

class portal{
    int end;

    long weight;

    public portal(int end){
        this.end = end;
        this.weight = 0;
    }

}

class heap{
    xiti[] arr;
    int volume;

    int index;

    long[][] dist;

    public heap(int volume,long[][] dist){
        this.volume = volume;
        arr = new xiti[volume + 1];
        this.dist = dist;
        this.index = 1;
    }
    public void insert(xiti node){
        node.position = this.index;
        if(this.arr[1] == null){
            this.arr[1] = node;
            this.index ++;
        }
        else{
            int temp = this.index;
            this.arr[temp] = node;
            while(temp != 1){
                if(dist[arr[temp].num][arr[temp].state] < dist[arr[temp / 2].num][arr[temp / 2].state]){
                    swap(this.arr,temp,temp / 2);
                    temp = temp / 2;
                }
                else{
                    break;
                }
            }
            this.index++;
        }
    }
    public void delete(){
        swap(this.arr,1,this.index - 1);
        this.arr[this.index - 1] = null;
        this.index --;
        int tempIndex = 1;
        while(2 * tempIndex <= this.index - 1){
            //如果有两个孩子
            if(2 * tempIndex + 1 <= this.index - 1){
                int leftChild = 2 * tempIndex;
                int rightChild = 2 * tempIndex + 1;
                if(dist[arr[leftChild].num][arr[leftChild].state] < dist[arr[rightChild].num][arr[rightChild].state]){
                    if(dist[arr[tempIndex].num][arr[tempIndex].state] > dist[arr[leftChild].num][arr[leftChild].state]) {
                        swap(arr, tempIndex, leftChild);
                        tempIndex = leftChild;
                    }
                    else if(dist[arr[tempIndex].num][arr[tempIndex].state] > dist[arr[rightChild].num][arr[rightChild].state]){
                        swap(arr,tempIndex,rightChild);
                        tempIndex = rightChild;
                    }
                    else{
                        break;
                    }
                }
                else{
                    if(dist[arr[tempIndex].num][arr[tempIndex].state] > dist[arr[rightChild].num][arr[rightChild].state]) {
                        swap(arr, tempIndex, rightChild);
                        tempIndex = rightChild;
                    }
                    else if(dist[arr[tempIndex].num][arr[tempIndex].state] > dist[arr[leftChild].num][arr[leftChild].state]){
                        swap(arr,tempIndex,leftChild);
                        tempIndex = leftChild;
                    }
                    else{
                        break;
                    }
                }
            }
            //只有一个孩子
            else{
                int leftChild = 2 * tempIndex;
                if(dist[arr[tempIndex].num][arr[tempIndex].state] > dist[arr[leftChild].num][arr[leftChild].state]) {
                    swap(arr, tempIndex, leftChild);
                    tempIndex = leftChild;
                }else{
                    break;
                }
            }
        }
    }
    public void updata(xiti i){
        int temIndex = i.position;

        while(temIndex > 1){
            if(dist[arr[temIndex / 2].num][arr[temIndex / 2].state] > dist[arr[temIndex].num][arr[temIndex].state]){
                swap(arr,temIndex,temIndex / 2);
                temIndex = temIndex / 2;
            }else{
                break;
            }
        }



    }
    public static void swap(xiti[] arr, int i, int j) {
        arr[i].position = j;
        arr[j].position = i;
        xiti temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}



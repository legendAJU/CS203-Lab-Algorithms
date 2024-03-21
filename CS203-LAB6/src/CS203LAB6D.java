import java.util.Scanner;

public class CS203LAB6D {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int N = input.nextInt();
            treeNode[] sticks = new treeNode[N + 1];
            for (int j = 1; j <= N; j++) {
                sticks[j] = new treeNode(input.nextInt());
            }
            Htree a = new Htree(sticks);
            System.out.println(a.ans());
        }
    }
}
class treeNode{
    int data;
    treeNode left;
    treeNode right;
    public treeNode(int data){
        this.data = data;
    }
}

class Htree{
    treeNode[] nodes;

    treeNode[] A;
    treeNode[] B;

    int count = 0;
    public Htree(treeNode[] sticks){
        this.nodes = sticks;
        this.A = new treeNode[nodes.length];
        this.B  = new treeNode[nodes.length];
        this.count = 0;
        mergeSort(nodes,1, nodes.length - 1,A,B);
        if(nodes.length > 2){
            while(nodes[2].data != 1000000001){
                count += nodes[1].data + nodes[2].data;
                nodes[1].data = nodes[1].data + nodes[2].data;
                nodes[2].data = 1000000001;
                mergeSort(nodes,1,nodes.length - 1,A,B);
            }
        }
    }
    public  int ans(){
        return this.count;
    }

    public static void merge(treeNode[] arr,int l,int mid,int r,treeNode[] A,treeNode[] B){
        int n = mid - l + 1;
        int m = r - mid;

        for (int i = 0; i < n; i++) {
            A[i] = arr[i + l];
        }
        for (int i = 0; i < m; i++) {
            B[i] = arr[1 + mid + i];
        }
        int indexOfA = 0;
        int indexOfB = 0;
        boolean AisDone = false;
        boolean BisDone = false;
        int recordOfj = 0;
        for (int j = 0; j < n + m; j++) {
            if(A[indexOfA].data > B[indexOfB].data){
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
    public static void mergeSort(treeNode[] arr,int l,int r,treeNode[] A,treeNode[] B){
        if(l < r){
            int mid = (l + r) / 2;
            mergeSort(arr,l,mid,A,B);
            mergeSort(arr,mid + 1,r,A,B);
            merge(arr,l,mid,r,A,B);
        }
    }
}






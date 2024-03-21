import java.io.*;
import java.util.StringTokenizer;

public class CS203LAB7C {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();
        int N = input.nextInt();
        int M = input.nextInt();
        int K = input.nextInt();
        long[] A = new long[N];
        long[] B = new long[M];
        for (int i = 0; i < N; i++) {
            A[i] = input.nextLong();
        }
        for (int i = 0; i < M; i++) {
            B[i] = input.nextLong();
        }
        mergeSort(A,0,N - 1);
        heap heap = new heap(M);
        for (int i = 0; i < M; i++) {
            heap.insert(new heapNode(0,i,A[0] * B[i]));
        }
        while(K > 0){
            heapNode temp = heap.arr[1];
            out.print(temp.data + " ");
            heap.delete();
            if(temp.i + 1 < N){
                heap.insert(new heapNode(temp.i + 1,temp.j,A[temp.i + 1] * B[temp.j]));
            }
            K--;
        }

        out.close();
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

class heap{
    heapNode[] arr;
    int volume;

    int index;

    public heap(int volume){
        this.volume = volume;
        arr = new heapNode[volume + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = new heapNode(-1,-1,0);
        }
        this.index = 1;
    }
    public void insert(heapNode node){
        if(this.arr[1].data == 0){
            this.arr[1] = node;
            this.index ++;
        }
        else{
            int temp = this.index;
            this.arr[temp] = node;
            while(temp != 1){
                if(this.arr[temp].data < this.arr[temp / 2].data){
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
        this.arr[this.index - 1].data = 0;
        this.index --;
        int tempIndex = 1;
        while(2 * tempIndex <= this.index - 1){
            //如果有两个孩子
            if(2 * tempIndex + 1 <= this.index - 1){
                int leftChild = 2 * tempIndex;
                int rightChild = 2 * tempIndex + 1;
                if(arr[leftChild].data < arr[rightChild].data){
                    if(arr[tempIndex].data > arr[leftChild].data) {
                        swap(arr, tempIndex, leftChild);
                        tempIndex = leftChild;
                    }
                    else if(arr[tempIndex].data > arr[rightChild].data){
                        swap(arr,tempIndex,rightChild);
                        tempIndex = rightChild;
                    }
                    else{
                        break;
                    }
                }
                else{
                    if(arr[tempIndex].data > arr[rightChild].data) {
                        swap(arr, tempIndex, rightChild);
                        tempIndex = rightChild;
                    }
                    else if(arr[tempIndex].data > arr[leftChild].data){
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
                if(arr[tempIndex].data > arr[leftChild].data) {
                    swap(arr, tempIndex, leftChild);
                    tempIndex = leftChild;
                }else{
                    break;
                }
            }
        }
    }
    public static void swap(heapNode[] arr, int i, int j) {
        heapNode temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

class heapNode{
    int i;
    int j;

    long data;

    public heapNode(int i,int j,long data){
        this.i = i;
        this.j = j;
        this.data = data;
    }
}

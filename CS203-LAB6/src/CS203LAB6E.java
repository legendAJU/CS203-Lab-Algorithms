import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class CS203LAB6E {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //how many cities
        int n = input.nextInt();
        city[] city = new city[n + 1];
        ArrayList<city>[] cities = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            city[i] = new city(i);
        }

        for (int i = 1; i <= n; i++) {
            cities[i] = new ArrayList<>();
        }
        //connect cities
        for (int i = 0; i < n - 1; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            cities[u].add(city[v]);
            cities[v].add(city[u]);
        }
        city[1].fatherFound = true;
        for (int i = 1; i <= n; i++) {
            city indexCity = new city(0);
            indexCity.belong = i;
            cities[i].add(indexCity);
        }
        //how many giants are in the cities
        int m = input.nextInt();
        for (int i = 1; i <= m; i++) {
            int giantIncities = input.nextInt();
             city[giantIncities].hasGiant = true;
        }
        //build the tree
        Queue<ArrayList<city>> queue = new LinkedList<>();
        queue.add(cities[1]);
        while(! queue.isEmpty()){
            ArrayList<city> temp = queue.peek();
            int index = temp.get(temp.size() - 1).belong;
            for(city i : temp){
                if(! i.fatherFound && i.belong == -1){
                    i.fatherFound = true;
                    city[index].children.add(i);
                    i.father = city[index];
                    queue.add(cities[i.val]);
                }
            }
            queue.poll();
        }
        int[] ans = new int[city[1].children.size()];
        int count = 0;
        for (city i : city[1].children) {
            ans[count] = findTheLongestPath(i);
            count++;
        }
        int max = 0;
        for (int i = 0; i <= ans.length - 1 ; i++) {
            max = Math.max(max,ans[i]);
        }
        System.out.print(max + 1);

    }

    public static int findTheLongestPath(city root){
        ArrayList<Integer> paths = new ArrayList<>();
        Queue<city> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        if(root.children.size() == 0){
            if(root.hasGiant){
                return 0;
            }else{
                return -1;
            }
        }else{
            int[] count = new int[2];
            boolean fistTime = true;
            while(!queue.isEmpty()){
                city temp = queue.peek();
                int length = queue.size();
                depth++;
                for (int i = 0; i < length; i++) {
                    temp = queue.peek();
                    for (int j = 0; j < temp.children.size(); j++) {
                        if(temp.children.get(j).hasGiant){
                            paths.add(depth);
                        }
                        queue.add(temp.children.get(j));
                        count[1]++;
                    }
                    queue.poll();
                }
            }
            int max = -3;
            int[] array = new int[paths.size()];
            for (int i = 0; i < paths.size(); i++) {
                array[i] = paths.get(i);
            }
            mergeSort(array,0,array.length - 1);
            int[] a = new int[array.length];
            for (int i = 0; i < a.length; i++) {
                a[i] = array[i];
            }
            if(a.length == 1){
                return a[0];
            }else{
                for (int i = 1; i < array.length; i++) {
                    array[i] = Math.max(array[i],array[i - 1] + 1);
                }
                for (int i = 0; i < array.length; i++) {
                    max = Math.max(max,array[i]);
                }
                return max;
            }

        }



    }

    public static void merge(int[] arr,int l,int mid,int r){
        int n = mid - l + 1;
        int m = r - mid;
        int[] A = new int[mid - l + 1];
        int[] B = new int[r - mid];
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
    public static void mergeSort(int[] arr,int l,int r){
        if(l < r){
            int mid = (l + r) / 2;
            mergeSort(arr,l,mid);
            mergeSort(arr,mid + 1,r);
            merge(arr,l,mid,r);
        }
    }
}

class city{
    int val;
    boolean hasGiant;
    boolean fatherFound = false;
    int belong;
    city father;

    int pathLength;
    ArrayList<city> children;
    public city(int val){
        this.val = val;
        this.hasGiant = false;
        this.belong = -1;
        this.fatherFound = false;
        this.pathLength = -1;
        this.children = new ArrayList<>();
    }

    public void setPathLength(int pathLength){
        if(hasGiant){
            this.pathLength = pathLength;
        }
    }
}



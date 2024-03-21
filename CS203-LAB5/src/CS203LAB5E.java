import java.io.*;
import java.util.StringTokenizer;

public class CS203LAB5E{
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String s1 = in.next();
        String s2 = in.next();
        int rangeOfLength = Math.min(s1.length(), s2.length());
        int left = 0;
        int right = rangeOfLength;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (check(s1, s2, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        out.print(left);
        out.close();
    }
    /*
    public static boolean isSubsequence(String s1,String s2,int subStringLength){
        Node fakeHeadOfs1 = new Node("fakeHeadOfs1");
        Node currentNode = fakeHeadOfs1;
        Node fakeHeadOfs2 = new Node("fakeHeadOfs2");
        Node currentNode2 = fakeHeadOfs2;
        int firstArrow = 0;
        int secondArrow = subStringLength;
        while(secondArrow != s1.length() + 1){
            Node newNode = new Node(s1.substring(firstArrow,secondArrow));
            if(fakeHeadOfs1.next == null) {
                fakeHeadOfs1.next = newNode;
                currentNode = fakeHeadOfs1.next;
            }else{
                currentNode.next = newNode;
                currentNode = currentNode.next;
            }
            firstArrow++;
            secondArrow++;
        }
        firstArrow = 0;
        secondArrow = subStringLength;
        while(secondArrow != s2.length() + 1){
            Node newNode = new Node(s2.substring(firstArrow,secondArrow));
            if(fakeHeadOfs2.next == null) {
                fakeHeadOfs2.next = newNode;
                currentNode2 = fakeHeadOfs2.next;
            }else{
                currentNode2.next = newNode;
                currentNode2 = currentNode2.next;
            }
            firstArrow++;
            secondArrow++;
        }
        Node currentNode3 = fakeHeadOfs1.next;
        while(currentNode3 != null){
            Node currentNode4 = fakeHeadOfs2.next;
            while(currentNode4 != null){
                if(currentNode3.data.equals(currentNode4.data)){
                    return true;
                }
                currentNode4 = currentNode4.next;
            }
            currentNode3 = currentNode3.next;
        }
        return false;
    }

 */
    public static boolean check(String s1,String s2,int subStringLength){
        long d = 139;
        int n1 = s1.length();
        int n2 = s2.length();
        int m = subStringLength;
        long powerD = 1;
        for (int i = 1; i <= m - 1; i++) {
            powerD *= d;
        }
        long[] nums1 = new long[n1 - m + 1];
        nums1[0] = Hash(s1.substring(0,m));
        for (int i = 1; i <= n1 - m ; i++) {
            nums1[i] = (nums1[i - 1] - powerD * s1.charAt(i -1)) * d + s1.charAt(i + m - 1);
        }
        long temp = Hash(s2.substring(0,m));
        long[] A = new long[n1 - m + 1];
        long[] B = new long[n1 - m + 1];
        mergeSort(nums1,0,nums1.length-1,A,B);
        for (int i = 1; i <= n2 - m; i++) {
            int left = 0;
            int right = n1 - m;
            while(left <= right){
                int mid = (left + right) / 2;
                if(nums1[mid] == temp){
                    return true;
                }
                else if(nums1[mid] > temp){
                    right = mid - 1;
                }
                else if(nums1[mid] < temp){
                    left = mid + 1;
                }
            }
            temp = (temp - powerD * s2.charAt(i - 1)) * d + s2.charAt(i + m - 1);
        }
        return false;
    }
    public static void merge(long[] arr,int l,int mid,int r,long[] A,long[] B){
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
    public static void mergeSort(long[] arr,int l,int r,long[] A,long[] B){
        if(l < r){
            int mid = (l + r) / 2;
            mergeSort(arr,l,mid,A,B);
            mergeSort(arr,mid + 1,r,A,B);
            merge(arr,l,mid,r,A,B);
        }
    }
    public static long Hash(String s){
        long d = 139;
        long temp =  s.charAt(0);
        for (int i = 1; i < s.length() ; i++) {
            temp = ((temp*d + s.charAt(i)) );
        }
        return temp;
    }
}




class Node{
    String data;
    Node next;
    Node(String data){
        this.data = data;
    }
}
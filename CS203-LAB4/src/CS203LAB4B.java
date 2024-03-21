import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CS203LAB4B {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n, p, q;
        n = in.nextInt();
        p = in.nextInt();
        q = in.nextInt();

        Node[] firstLine = new Node[p + 1];
        Node[] secondLine = new Node[q + 1];
        Node[] bucket = new Node[n + 1];
        for (int i = 1; i <= p; i++) {
            int data = in.nextInt();
            Node node = new Node(data);
            firstLine[i] = node;
            bucket[data] = node;
        }
        for (int i = 1; i <= q; i++) {
            int data = in.nextInt();
            if (bucket[data] != null) {
                secondLine[i] = bucket[data];
            } else {
                secondLine[i] = new Node(data);
            }
        }
        //start
        int arrowOfFirst = 1;
        int arrowOfSecond = 1;
        int[] ans = new int[n + 1];
        int count = 1;
        while(true){
            if(arrowOfFirst == p + 1 && arrowOfSecond == q + 1){
                break;
            }else{
                int recordOfA = 0;
                if(arrowOfFirst <= p){
                    for (int i = arrowOfFirst; i <= p ; i++) {
                        if(ans[firstLine[i].data] == 0){
                            ans[firstLine[i].data] = count;
                            recordOfA = firstLine[i].data;
                            arrowOfFirst = i + 1;
                            break;
                        }else{
                            arrowOfFirst++;
                        }
                    }

                }
                if(arrowOfSecond <= q){
                    for (int i = arrowOfSecond; i <= q ; i++) {
                        if(ans[secondLine[i].data] == 0){
                            ans[secondLine[i].data] = count;
                            arrowOfSecond = i + 1;
                            break;
                        }else if(secondLine[i].data == recordOfA){
                            arrowOfSecond = i + 1;
                            break;
                        }else{
                            arrowOfSecond++;
                        }
                    }

                }
                count++;
            }
        }
        for (int i = 1; i <= n ; i++) {
            out.print(ans[i] + " ");
        }
        out.close();
    }
}



import java.io.*;
import java.util.StringTokenizer;

public class CS203LAB3F {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int T;
        T = in.nextInt();

        for (int i = 0; i < T; i++) {
            int n;
            n = in.nextInt();

            int[] nums = new int[n + 1];
            for (int j = 1; j <= n ; j++) {
                nums[j] = in.nextInt();
            }

            bigNode firstBigNode = new bigNode();
            bigNode currentBigNode = firstBigNode;

            middleNode firstMiddleNode = new middleNode();
            firstBigNode.current = firstMiddleNode;
            //初始化第一轮
            for (int j = 1; j <= n; j++) {
                smallNode temp = new smallNode(nums[j]);
                if(j == 1){
                    currentBigNode.current.head = temp;
                    currentBigNode.current.tail = temp;
                    currentBigNode.current.head.length++;
                }else{
                    if(nums[j] < nums[j - 1]){
                        currentBigNode.current.tail.next = temp;
                        currentBigNode.current.tail = currentBigNode.current.tail.next;
                        currentBigNode.current.head.length++;
                    }else{
                        bigNode newBigNode = new bigNode();
                        middleNode newMiddleNode = new middleNode();
                        newBigNode.current = newMiddleNode;
                        newBigNode.current.head = temp;
                        newBigNode.current.tail = temp;
                        currentBigNode.next = newBigNode;
                        currentBigNode = currentBigNode.next;
                        currentBigNode.current.head.length++;
                    }
                }
            }
            //第一轮加链子（bigNode and smallNode）
            bigNode temp = firstBigNode;
            while(true){
                if(temp == null){
                    break;
                }else{
                    if(temp == currentBigNode){
                        break;
                    }else{
                        temp.next.last = temp;
                        temp = temp.next;
                    }
                }
            }
            //创建一个伪头，便于遍历。
            bigNode fakeBigNode = new bigNode();
            middleNode fakeMiddleNode = new middleNode();
            fakeMiddleNode.head = new smallNode(0);
            fakeMiddleNode.tail = fakeMiddleNode.head;
            fakeBigNode.current = fakeMiddleNode;
            fakeBigNode.next = firstBigNode;
            firstBigNode.last = fakeBigNode;
            //尾巴bigNode，防止空指针.
            bigNode tailBigNode = new bigNode();
            middleNode tailMiddleNode = new middleNode();
            tailMiddleNode.head = new smallNode(100005);
            tailMiddleNode.tail = tailMiddleNode.head;
            tailBigNode.current = tailMiddleNode;
            currentBigNode.next = tailBigNode;
            tailBigNode.last = currentBigNode;
            boolean Noexit = true;
            //开始删除
            while(true){
                if(Noexit){
                    Noexit = false;
                    bigNode march = fakeBigNode.next;
                    while(march != null && march.next != null){
                        if(march.current.head.length > 1){
                            march.last.next = march.next;
                            march.next.last = march.last;
                            if(march.last.current.tail.num > march.next.current.head.num){
                                march.last.current.tail.next = march.next.current.head;
                                march.last.current.tail = march.last.current.tail.next;
                                march.last.current.head.length++;
                                march.next.next.last = march.next.last;
                                march.next.last.next = march.next.next;
                                Noexit = true;
                            }
                            march = march.next;
                        }else{
                            march = march.next;
                        }
                    }
                }else{
                    break;
                }
            }
            bigNode shuchu = fakeBigNode.next;
            while(shuchu != null && shuchu.next != null){
                out.print(shuchu.current.head.num + " ");
                shuchu = shuchu.next;
            }

            if(i != T - 1){
                out.print("\n");
            }
            if(i == T - 1){
                out.close();
            }
        }
    }
}

class bigNode{

    bigNode next;

    bigNode last;

    middleNode current;
}

class middleNode{
    smallNode head;

    smallNode tail;
}

class smallNode{
    int num;
    smallNode next;
    smallNode last;

    int length;
    public smallNode(int num){
        this.num = num;
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




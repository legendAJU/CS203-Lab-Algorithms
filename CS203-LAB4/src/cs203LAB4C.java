import java.io.*;
import java.util.StringTokenizer;

public class cs203LAB4C {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String string = in.next();
        char[] array = string.toCharArray();
        stackBaseLinkedList myStack = new stackBaseLinkedList();
        for (int i = 0; i < array.length; i++) {
            if(array[i] == '('){
                myStack.push(-1);
            }else{
                if(myStack.head.next.data < 0){
                    if(myStack.head.next.next != null){
                        if(myStack.head.next.next.data < 0){
                            myStack.head.next.data = 1;
                        }else{
                            myStack.head.next.data = 1;
                            int temp = myStack.head.next.data;
                            myStack.pop();
                            myStack.head.next.data = (myStack.head.next.data % 514329 + temp % 514329) % 514329;
                        }
                    }else{
                        if(i == 1){
                            myStack.head.next.data = 1;
                        }
                    }
                }else{
                    int temp = (myStack.head.next.data % 514329 * 2 % 514329) % 514329;
                    myStack.pop();
                    myStack.head.next.data = temp;
                    if(myStack.head.next.next != null){
                        if(myStack.head.next.next.data > 0){
                            int temp1 = myStack.head.next.data;
                            myStack.pop();
                            myStack.head.next.data = (myStack.head.next.data % 514329 + temp1 % 514329) % 514329;
                        }
                    }

                }
            }
        }
        System.out.print(myStack.head.next.data);
    }
}

class stackBaseLinkedList {

    element head;

    public stackBaseLinkedList() {
        head = new element();
    }

    public void push(int data) {
        element newNode = new element(data);
        if (head.next == null) {
            head.next = newNode;
            newNode.prev = head;
        } else {
            element temp = head.next;
            head.next = newNode;
            newNode.prev = head;
            newNode.next = temp;
            temp.prev = newNode;
        }
    }

    public void pop() {
        if (head.next != null) {
            if (head.next.next == null) {
                head.next = null;
            } else {
                head.next.next.prev = head.next.prev;
                head.next.prev.next = head.next.next;
            }
        } else {
            System.out.println("Your fucking stack is empty!!!");
        }

    }

}




class element{
    int data;
    element next;
    element prev;
    public element(int data){
        this.data = data;
    }

    public element(){

    }
}
class Node {
    int data;

    Node next;

    Node prev;

    int waitingTime = 1;

    boolean isDelate = false;

    public Node(int data) {
        this.data = data;
    }

    public Node() {

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
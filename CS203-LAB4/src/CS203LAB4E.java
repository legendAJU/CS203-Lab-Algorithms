import java.io.*;
import java.util.StringTokenizer;

public class CS203LAB4E {
    public static void main(String[] args) {
            QReader in = new QReader();
            QWriter out = new QWriter();

            int n = 0,m;
            m = in.nextInt();
            num head = new num(-2,-1);
            num current = null;
            queueBaseLinkedList myQueue = new queueBaseLinkedList();
            //形成nums数组
            while(true){
                int data = in.nextInt();
                if(data != - 1){
                    num temp = new num(data,-1);
                    if(head.next == null){
                        current = temp;
                        head.next = current;
                    }else{
                        current.next = temp;
                        current = current.next;
                    }
                    n++;
                }else{
                    break;
                }
            }
            int[] nums = new int[n];
            num bianli = head.next;
            for (int i = 0; i < n; i++) {
            nums[i] = bianli.data;
            bianli = bianli.next;
            }
            //找第一个窗口的最大值，并保持窗口为decreasing
            int[] ans = new int[n];
            for (int i = 0; i < m; i++) {
                if(i == 0){
                myQueue.enqueue(nums[i],i);
                }else{
                    if(nums[i] < myQueue.rear.data){
                    myQueue.enqueue(nums[i],i);
                    }else{
                        while(myQueue.rear != null && myQueue.rear.data < nums[i]){
                            myQueue.dequeueRear();
                        }
                        if(myQueue.rear == null){
                            myQueue.enqueue(nums[i],i);
                        }else{
                            num temp = new num(nums[i],i);
                            myQueue.rear.next = temp;
                            temp.last = myQueue.rear;
                            myQueue.rear.next = temp;
                            myQueue.rear = temp;
                        }
                }
            }
        }
            ans[0] = myQueue.head.next.data;
            int arrow2 = m;
            int arrow1 = arrow2 - 4;
            int count = 1;
        for ( arrow2 = m; arrow2 < n; arrow2++) {
            arrow1 = arrow2 - (m - 1);
            int number = myQueue.head.next.data;
            if(nums[arrow2] > myQueue.rear.data){
                while(myQueue.rear != null && myQueue.rear.data < nums[arrow2]){
                    myQueue.dequeueRear();
                }
                if(myQueue.rear == null){
                    myQueue.enqueue(nums[arrow2],arrow2);
                }else{
                    num temp = new num(nums[arrow2],arrow2);
                    myQueue.rear.next = temp;
                    temp.last = myQueue.rear;
                    myQueue.rear.next = temp;
                    myQueue.rear = temp;
                }
            }else{
                myQueue.enqueue(nums[arrow2],arrow2);
            }
            if(myQueue.head.next.index < arrow1){
                myQueue.dequeue();
            }
            ans[count] = myQueue.head.next.data;
            count++;
        }
        int c = ans[0] ^ ans[1];
        for (int i = 2; i < ans.length; i++) {
            if(ans[i] > 0){
                c = c ^ ans[i];
            }else{
                break;
            }
        }
        out.print(c);
        out.close();
    }
}

class queueBaseArray{

    int[] array;

    int front = 0;

    int rear = 0;

    int size;

    public queueBaseArray(int size){
        this.size = size;
        this.array = new int[this.size];
    }


    public void enqueue(int data){
        this.array[rear] = data;
        rear ++;
    }

    public void dequeue(){
        this.array[front] = 0;
        front++;
    }
}







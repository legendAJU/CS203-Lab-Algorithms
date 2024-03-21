import java.io.*;
import java.util.StringTokenizer;

public class CS203LAB4F {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int k, n;
        k = in.nextInt();
        n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        int left = 0;
        int right = 0;

        //two queue
        queueBaseLinkedList increList = new queueBaseLinkedList();
        queueBaseLinkedList decreList = new queueBaseLinkedList();
        increList.enqueue(nums[right], right);
        decreList.enqueue(nums[right], right);
        //start to find
        int maxDif = 0;
        int max = nums[0];
        int min = nums[0];
        for (right = 1; right <= n - 1; right++) {
            //keep decreList hold the max
            if (nums[right] > decreList.rear.data) {
                while (decreList.rear != null && decreList.rear.data < nums[right]) {
                    decreList.dequeueRear();
                }
                if (decreList.rear == null) {
                    decreList.enqueue(nums[right], right);
                } else {
                    num temp = new num(nums[right], right);
                    decreList.rear.next = temp;
                    temp.last = decreList.rear;
                    decreList.rear.next = temp;
                    decreList.rear = temp;
                }
            }else{
                decreList.enqueue(nums[right], right);
            }
            //keep increList hold the min
            if (nums[right] < increList.rear.data) {
                while (increList.rear != null && increList.rear.data > nums[right]) {
                    increList.dequeueRear();
                }
                if (increList.rear == null) {
                    increList.enqueue(nums[right], right);
                } else {
                    num temp = new num(nums[right], right);
                    increList.rear.next = temp;
                    temp.last = increList.rear;
                    increList.rear.next = temp;
                    increList.rear = temp;
                }
            } else {
                increList.enqueue(nums[right], right);
            }
            max = decreList.head.next.data;
            min = increList.head.next.data;
            while (max - min > k) {
                if (nums[left] == max) {
                    decreList.dequeue();
                }
                if (nums[left] == min) {
                    increList.dequeue();
                }
                max = decreList.head.next.data;
                min = increList.head.next.data;
                left++;
            }
            maxDif = Math.max(maxDif, right - left + 1);
        }
        out.print(maxDif);
        out.close();
    }
}




class queueBaseLinkedList {
    num rear;

    num front;

    num head;

    public queueBaseLinkedList() {
        this.head = new num(-2, -1);
    }

    public void enqueue(int data, int index) {
        num temp = new num(data, index);
        if (rear == null) {
            rear = temp;
            front = temp;
            head.next = rear;
            rear.last = head;
            head.next = front;
        } else {
            rear.next = temp;
            rear.next.last = rear;
            rear = rear.next;
        }
    }

    public void dequeueRear() {
        if (rear == front) {
            front = null;
            rear = null;
            head.next = null;
        } else {
            rear.last.next = null;
            rear = rear.last;
        }
    }

    public void dequeue() {
        if (front.next != null) {
            front.last.next = front.next;
            front.next.last = front.last;
            front = head.next;
        } else {
            front = null;
            rear = null;
            head.next = null;
        }

    }
}
class num{
    int data;

    num next;

    num last;

    int index;
    public num(int data,int index){
        this.data = data;
        this.index = index;
    }
}
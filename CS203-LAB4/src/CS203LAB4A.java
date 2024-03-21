import java.util.Scanner;

public class CS203LAB4A {
    public static void main(String[] args) {
        int T;
        Scanner input = new Scanner(System.in);
        T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            stackBaseLinkedList myStack = new stackBaseLinkedList();
            String target = input.next();
            char[] array = target.toCharArray();
            for (int j = 0; j < array.length; j++){
                if(j == 0){
                    myStack.push(array[j]);
                }else{
                    if(array[j] == '(' || array[j] == '[' || array[j] == '{'){
                        myStack.push(array[j]);
                    }else if(array[j] == ')'){
                        if(myStack.head.next.data == '('){
                            myStack.push(array[j]);
                            myStack.pop();
                            myStack.pop();
                        }else{
                            myStack.push(array[j]);
                        }
                    }else if(array[j] == ']'){
                        if(myStack.head.next.data == '['){
                            myStack.push(array[j]);
                            myStack.pop();
                            myStack.pop();
                        }else{
                            myStack.push(array[j]);
                        }
                    }else if(array[j] == '}'){
                        if(myStack.head.next.data == '{'){
                            myStack.push(array[j]);
                            myStack.pop();
                            myStack.pop();
                        }else{
                            myStack.push(array[j]);
                        }
                    }
                }
            }
            if(myStack.head.next != null){
                System.out.println("NO");
            }else{
                System.out.println("YES");
            }
        }

    }
}


class stackBaseArray{
    int size;

    int[] array;

    int front = -1;
    public stackBaseArray(int size){
        this.size = size;
        this.array = new int[size];
    }

    public void push(int data){
        if(this.front + 1 != this.size){
            this.front++;
            this.array[front] = data;
        }else{
            System.out.println("Your stack is fucking full!!!");
        }
    }

    public void pop(){
        if(this.front - 1 >= -1){
            this.front--;
        }else{
            System.out.println("Your stack is fucking empty!");
        }
    }
}



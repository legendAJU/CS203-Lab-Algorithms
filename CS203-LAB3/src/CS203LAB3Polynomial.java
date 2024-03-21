import java.io.*;
import java.util.StringTokenizer;

public class CS203LAB3Polynomial {
    public static void main(String[] args) {
        QReader in = new QReader();
        int n,m;
        n = in.nextInt();
        m = in.nextInt();
        MyLinkedListForA listOfA = new MyLinkedListForA();
        MyLinkedListForA listOfB = new MyLinkedListForA();
        MyLinkedListForA listOfC = new MyLinkedListForA();
        for (int i = 0; i < n; i++) {
            int variable1 = in.nextInt();
            int variable2 = in.nextInt();
            if(variable1 != 0){
                listOfA.addNode(variable1, variable2);
            }
        }
        for (int i = 0; i < m; i++) {
            int variable1 = in.nextInt();
            int variable2 = in.nextInt();
            if(variable1 != 0){
                listOfB.addNode(variable1, variable2);
            }
        }

        NodeForA arrowOfA = listOfA.first;
        NodeForA arrowOfB = listOfB.first;
        boolean aIsDone = false;
        boolean bIsDone = false;
        while(arrowOfA != null || arrowOfB != null){
            if(arrowOfA.variable2 < arrowOfB.variable2){
                listOfC.addNode(arrowOfB.variable1,arrowOfB.variable2);
                arrowOfB = arrowOfB.next;
            }
            else if(arrowOfA.variable2 > arrowOfB.variable2){
                listOfC.addNode(arrowOfA.variable1, arrowOfA.variable2);
                arrowOfA = arrowOfA.next;
            }
            else{
                int sum = arrowOfA.variable1 + arrowOfB.variable1;
                if(sum != 0){
                    listOfC.addNode(sum, arrowOfA.variable2);
                }
                arrowOfA = arrowOfA.next;
                arrowOfB = arrowOfB.next;
            }
            if(arrowOfA == null){
                aIsDone = true;
                break;
            }
            if(arrowOfB == null){
                bIsDone = true;
                break;
            }
        }
        if(aIsDone){
            while(arrowOfB != null){
                listOfC.addNode(arrowOfB.variable1, arrowOfB.variable2);
                arrowOfB = arrowOfB.next;
            }
        }
        else if(bIsDone){
            while(arrowOfA != null){
                listOfC.addNode(arrowOfA.variable1, arrowOfA.variable2);
                arrowOfA=arrowOfA.next;
            }
        }
        System.out.println(listOfC.list_length);
        listOfC.display();
    }
}
class MyLinkedListForA {
    NodeForA first = null;
    NodeForA current = null;
    int list_length = 0;
    public void addNode(int variable1,int variable2){
        NodeForA newNode = new NodeForA(variable1,variable2);
        if(list_length == 0){
            first = newNode;
            current = newNode;
            list_length++;
        }
        else{
            current.next = newNode;
            current = newNode;
            list_length++;
        }
    }
    public void display( ){
        QWriter out = new QWriter();
        int arrowOfList = 0;
        NodeForA arrowOfNode = first;
        for (int i = 0; i < length(); i++) {
            if(arrowOfNode != null){
                int variable1 = arrowOfNode.variable1;
                int variable2 = arrowOfNode.variable2;
                if(arrowOfNode.next != null){
                    out.print(variable1 + " " + variable2);
                    out.print("\n");
                }
                else{
                    out.print(variable1 + " " + variable2);
                }
                arrowOfNode = arrowOfNode.next;
            }
        }
        out.close();
    }
    public int length(){
        return list_length;
    }
}
class NodeForA {
    public int variable1;
    public int variable2;
    public int variable3;
    public NodeForA next = null;
    public NodeForA(int variable1, int variable2){
        this.variable1 = variable1;
        this.variable2 = variable2;
    }

}




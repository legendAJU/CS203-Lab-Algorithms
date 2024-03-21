import java.util.Scanner;

public class CS203LAB3Shooping {
    public static void main(String[] args) {
        MyLinkedListForB list = new MyLinkedListForB();
        Scanner input = new Scanner(System.in);
        int n;
        long m;
        n = input.nextInt();
        m = input.nextLong();
        long totalPrice = 0;
        for (int i = 0; i < n; i++) {
            int price = input.nextInt();
            totalPrice += price;
            list.addNode(price);
        }
        NodeForB current = list.start;
        int count = 0;
        long amount = 0;
        long conp = m / totalPrice;
        long leftOfm = m - conp * totalPrice;
        amount += conp * list.length;
        while(leftOfm > 0){
            if(leftOfm >= current.price){
                leftOfm -= current.price;
                current = current.nextShop;
                count = 0;
                amount++;
            }else{
                count ++;
                current = current.nextShop;
            }
            if(count == list.length){
                break;
            }
        }
        System.out.print(amount);
    }

}
 class NodeForB{
    int price;
    NodeForB nextShop = null;
    public NodeForB(int price){
        this.price = price;
    }
}
class MyLinkedListForB{
    NodeForB start = null;
    NodeForB current = null;
    int length = 0;
    public void addNode(int price){
        if(this.length == 0){
            NodeForB temp = new NodeForB(price);
            start = temp;
            current = temp;
            this.length++;
        }
        else{
            NodeForB temp = new NodeForB(price);
            current.nextShop = temp;
            current = temp;
            current.nextShop = start;
            this.length++;
        }
    }
}

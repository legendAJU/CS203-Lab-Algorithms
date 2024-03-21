public class MyBst {
    public static void main(String[] args) {


    }
}
class bstNode{
    int data;

    bstNode left;

    bstNode right;

    bstNode father;

    public bstNode(int data){
        this.data = data;
    }
}

class BST{
    bstNode root;

    public BST(){
        this.root = null;
    }

    public void insertNode(int data){
        bstNode node = new bstNode(data);
        if(this.root == null){
            root = node;
        }
        else{
            bstNode temp = root;
            bstNode father;
            while(true){
                father = temp;
                if(data < temp.data){
                    temp = temp.left;
                    if(temp == null){
                        father.left = node;
                        node.father = father;
                        break;
                    }
                }
                else{
                    temp = temp.right;
                    if(temp == null){
                        father.right = node;
                        node.father = father;
                        break;
                    }
                }
            }
        }
    }












}
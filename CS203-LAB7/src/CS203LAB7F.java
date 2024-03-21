import java.io.*;
import java.util.StringTokenizer;

public class CS203LAB7F {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();
        int n = input.nextInt();
        AVL human = new AVL();
        AVL pets = new AVL();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int a = input.nextInt();
            long value = input.nextInt();
            if(a == 0){
               pets.insertNode(value);
               if(human.capacity != 0){
                   if(human.findNode(value) == null){
                       human.insertNode(value);
                       AVLNode preNode = human.findPreNode(human.findNode(value));
                       AVLNode nextNode = human.findNextNode(human.findNode(value));
                       if(preNode != null && nextNode != null){
                           ans += Math.min(Math.abs(value - preNode.data),Math.abs(value - nextNode.data));
                           if(Math.abs(value - preNode.data) <= Math.abs(value - nextNode.data)){
                               human.deleteNode(preNode.data);
                           }else{
                               human.deleteNode(nextNode.data);
                           }
                       }
                       else if(preNode != null){
                           ans += Math.abs(value - preNode.data);
                           human.deleteNode(preNode.data);
                       }
                       else if(nextNode != null){
                           ans += Math.abs(value - nextNode.data);
                           human.deleteNode(nextNode.data);
                       }
                       human.deleteNode(value);
                   }
                   else{
                       human.deleteNode(value);
                   }
                   pets.deleteNode(value);

               }

            }
            else{
                human.insertNode(value);
                if(pets.capacity != 0){
                    if(pets.findNode(value) == null){
                        pets.insertNode(value);
                        AVLNode preNode = pets.findPreNode(pets.findNode(value));
                        AVLNode nextNode = pets.findNextNode(pets.findNode(value));
                        if(preNode != null && nextNode != null){
                            ans += Math.min(Math.abs(value - preNode.data),Math.abs(value - nextNode.data));
                            if(Math.abs(value - preNode.data) <= Math.abs(value - nextNode.data)){
                                pets.deleteNode(preNode.data);
                            }else{
                                pets.deleteNode(nextNode.data);
                            }
                        }
                        else if(preNode != null){
                            ans += Math.abs(value - preNode.data);
                            pets.deleteNode(preNode.data);
                        }
                        else if(nextNode != null){
                            ans += Math.abs(value - nextNode.data);
                            pets.deleteNode(nextNode.data);
                        }
                        pets.deleteNode(value);
                    }
                    else{
                        pets.deleteNode(value);
                    }
                    human.deleteNode(value);

                }
            }
        }
        out.print(ans);
        out.close();
    }
}
class AVL{
    AVLNode root;

    int capacity;
    public AVL(){
        this.root = null;
        this.capacity = 0;
    }

    public int height(AVLNode node){
        if(node == null){
            return 0;
        }
        else{
            return node.height;
        }
    }

    public int size(AVLNode node){
        if(node == null){
            return 0;
        }
        else{
            return node.size;
        }
    }

    public void updateHeight(AVLNode node){
        if(node == null){
            return;
        }else{
            node.height = Integer.max(height(node.left), height(node.right)) + 1;
        }
    }

    public void updateSize(AVLNode node){
        if(node == null){
            return;
        }else{
            node.size = size(node.left) + size(node.right) + 1;
        }
    }

    //平衡因子(balance factor)
    public int getBalanceFactor(AVLNode node){
        if(node == null){
            return 0;
        }
        else{
            return height(node.left) - height(node.right);
        }
    }

    public void insertNode(long data){
        this.capacity ++;
        //先插入节点
        AVLNode node = new AVLNode(data);
        if(this.root == null){
            root = node;
            updateHeight(root);
            updateSize(root);
        }
        else{
            AVLNode temp = root;
            AVLNode father;
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
        //插入节点后，更新高度
        AVLNode temp = node;
        while(temp != null){
            updateHeight(temp);
            updateSize(temp);
            temp = temp.father;
        }

        //插入节点后，调整平衡
        AVLNode unbalancedNode = node;
        while(unbalancedNode != null){
            int balanceFactor = getBalanceFactor(unbalancedNode);
            if(!(balanceFactor == -1 || balanceFactor == 0 || balanceFactor == 1)){
                //右子树比左子树高R
                if(balanceFactor < -1){
                    int RbalanceFactor = getBalanceFactor(unbalancedNode.right);
                    //if RR?
                    if(RbalanceFactor <= 0){
                        //左旋unbalancedNode
                        leftRotate(unbalancedNode);
                    }
                    //if RL?
                    else{
                        //先右旋unbalancedNode.right,再左旋unbalanceNode
                        rightRotate(unbalancedNode.right);
                        leftRotate(unbalancedNode);
                    }

                }
                //左子树比右子树高L
                if(balanceFactor > 1){
                    int LbalanceFactor = getBalanceFactor(unbalancedNode.left);
                    //if LL?
                    if(LbalanceFactor >= 0){
                        //右旋unbalancedNode
                        rightRotate(unbalancedNode);
                    }
                    //if LR?
                    else{
                        //先左旋unbalancedNode.left,再右旋unbalanceNode
                        leftRotate(unbalancedNode.left);
                        rightRotate(unbalancedNode);
                    }
                }
                break;
            }
            else{
                unbalancedNode = unbalancedNode.father;
            }
        }

    }

    public void deleteNode(long data){
        this.capacity--;
        //先删除
        AVLNode needToDelete = findNode(data);
        AVLNode temp = null;
        if(needToDelete.left == null && needToDelete.right != null){
            needToDelete.right.father = needToDelete.father;
            AVLNode father = needToDelete.father;
            needToDelete = needToDelete.right;
            if(father != null){
                if(father.left != null&&father.left.data == data){
                    father.left = needToDelete;
                }else{
                    father.right = needToDelete;
                }
            }else{
                this.root = needToDelete;
            }
            temp = needToDelete;
        }
        else if(needToDelete.left != null && needToDelete.right == null){
            needToDelete.left.father = needToDelete.father;
            AVLNode father = needToDelete.father;
            needToDelete = needToDelete.left;
            if(father != null){
                if(father.left != null&&father.left.data == data){
                    father.left = needToDelete;
                }else{
                    father.right = needToDelete;
                }
            }else{
                this.root = needToDelete;
            }
            temp = needToDelete;
        }
        else if(needToDelete.left == null){
            temp = needToDelete.father;
            AVLNode father = needToDelete.father;
            needToDelete = null;
            if(father != null){
                if(father.left != null&&father.left.data == data){
                    father.left = needToDelete;
                }else{
                    father.right = needToDelete;
                }
            }else{
                this.root = needToDelete;
            }
        }
        else {
            AVLNode next = findNextNode(needToDelete);
            //copyNext
            AVLNode copyNext = new AVLNode(next.data);
            AVLNode father = needToDelete.father;
            copyNext.height = next.height;
            copyNext.size = next.height;

            copyNext.right = needToDelete.right;
            needToDelete.right.father = copyNext;
            copyNext.left = needToDelete.left;
            needToDelete.left.father = copyNext;
            if(father != null){
                if(father.left != null&&father.left.data == data){
                    father.left = copyNext;
                }else{
                    father.right = copyNext;
                }
            }else{
                this.root = copyNext;
            }
            copyNext.father = needToDelete.father;
            //删除next后继节点.
            AVLNode temp3 = null;
            if(next.left == null && next.right != null){
                AVLNode father1 = next.father;
                next.right.father = next.father;
                long data1 = next.data;
                next = next.right;
                if(father1 != null){
                    if(father1.left != null&&father1.left.data == data1){
                        father1.left = next;
                    }else{
                        father1.right = next;
                    }
                }
                temp3 = next;
            }
            else if(next.left != null && next.right == null){
                AVLNode father1 = next.father;
                next.left.father = next.father;
                long data1 = next.data;
                next = next.left;
                if(father1 != null){
                    if(father1.left != null&&father1.left.data == data1){
                        father1.left = next;
                    }else{
                        father1.right = next;
                    }
                }
                temp3 = next;
            }
            else if(next.left == null){
                temp3 = next.father;
                AVLNode father1 = next.father;
                long data1 = next.data;
                next = null;
                if(father1 != null){
                    if(father1.left != null&&father1.left.data == data1){
                        father1.left = next;
                    }else{
                        father1.right = next;
                    }
                }
            }
            AVLNode temp4 = temp3;
            while(temp4 != null){
                updateHeight(temp4);
                updateSize(temp4);
                temp4 = temp4.father;
            }

            temp = copyNext;
        }
        if(temp == null){
            this.root = null;
            return;
        }
        if(temp.father == null){
            this.root = temp;
        }
        //从temp开始，向上更新高度
        AVLNode temp1 = temp;
        while(temp1 != null){
            updateHeight(temp1);
            updateSize(temp1);
            temp1 = temp1.father;
        }
        //从temp开始，向上找失衡，处理。
        AVLNode unbalancedNode = temp;
        while(unbalancedNode != null){
            int balanceFactor = getBalanceFactor(unbalancedNode);
            if(!(balanceFactor == -1 || balanceFactor == 0 || balanceFactor == 1)){
                //右子树比左子树高R
                if(balanceFactor < -1){
                    int RbalanceFactor = getBalanceFactor(unbalancedNode.right);
                    //if RR?
                    if(RbalanceFactor <= 0){
                        //左旋unbalancedNode
                        leftRotate(unbalancedNode);
                    }
                    //if RL?
                    else{
                        //先右旋unbalancedNode.right,再左旋unbalanceNode
                        rightRotate(unbalancedNode.right);
                        leftRotate(unbalancedNode);
                    }

                }
                //左子树比右子树高L
                if(balanceFactor > 1){
                    int LbalanceFactor = getBalanceFactor(unbalancedNode.left);
                    //if LL?
                    if(LbalanceFactor >= 0){
                        //右旋unbalancedNode
                        rightRotate(unbalancedNode);
                    }
                    //if LR?
                    else{
                        //先左旋unbalancedNode.left,再右旋unbalanceNode
                        leftRotate(unbalancedNode.left);
                        rightRotate(unbalancedNode);
                    }
                }
                break;
            }
            else{
                unbalancedNode = unbalancedNode.father;
            }
        }
    }

    public AVLNode findSmallestNode(AVLNode node){
        if(node == null){
            return null;
        }
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
    public AVLNode findNextNode(AVLNode node){
        if(node == null){
            return null;
        }
        if(node.right != null){
            return findSmallestNode(node.right);
        }
        else{
            AVLNode temp = node;
            while(temp.father != null && temp.father.right == temp){
                temp = temp.father;
            }
            return temp.father;
        }
    }

    public AVLNode findPreNode(AVLNode node){
        if(node == null){
            return null;
        }
        if(node.left != null){
            return findBiggestNode(node.left);
        }
        else{
            AVLNode temp = node;
            while(temp.father != null && temp.father.left == temp){
                temp = temp.father;
            }
            return temp.father;
        }
    }

    public AVLNode findBiggestNode(AVLNode node){
        if(node == null){
            return null;
        }
        while(node.left != null){
            node = node.right;
        }
        return node;
    }



    public AVLNode findNode(long data){
        AVLNode temp = this.root;
        while(temp != null){
            if(temp.data == data){
                return temp;
            }
            else if(temp.data > data){
                temp = temp.left;
            }
            else{
                temp = temp.right;
            }
        }
        return null;
    }

    public void rightRotate(AVLNode node){
        AVLNode TLeft = node.left.right;
        AVLNode LeftChild = node.left;

        node.left = TLeft;
        if(TLeft != null){
            TLeft.father = node;
        }
        if(node.father != null){
            if(node.father.left == node){
                node.father.left = LeftChild;
                LeftChild.father = node.father;
            }
            else if(node.father.right == node){
                node.father.right = LeftChild;
                LeftChild.father = node.father;
            }
        }
        else{
            this.root = LeftChild;
            LeftChild.father = null;
        }

        LeftChild.right = node;
        node.father = LeftChild;
        updateHeight(node);
        updateSize(node);
        updateHeight(LeftChild);
        updateSize(LeftChild);
        updateHeight(TLeft);
        updateSize(TLeft);
    }

    public void leftRotate(AVLNode node){
        AVLNode Tright = node.right.left;
        AVLNode RightChild = node.right;

        node.right = Tright;
        if(Tright != null){
            Tright.father = node;
        }
        if(node.father != null){
            if(node.father.left == node){
                node.father.left = RightChild;
                RightChild.father = node.father;
            }
            else if(node.father.right == node){
                node.father.right = RightChild;
                RightChild.father = node.father;
            }
        }
        else{
            this.root = RightChild;
            RightChild.father = null;
        }

        RightChild.left = node;
        node.father = RightChild;

        updateHeight(node);
        updateSize(node);
        updateHeight(RightChild);
        updateSize(RightChild);
        updateHeight(Tright);
        updateSize(Tright);
    }

    public long kthNumber(AVLNode root,int k){
        int rank = size(root.left) + 1;
        if(rank < k){
            return kthNumber(root.right,(k - rank));
        }
        else if (rank > k){
            return kthNumber(root.left,k);
        }
        else{
            return root.data;
        }
    }


}
class AVLNode{
    long data;
    AVLNode left;
    AVLNode right;
    int height;

    AVLNode father;

    int size;

    int value;
    public AVLNode(long data) {
        this.data = data;
        this.height = 1;
        this.size = 1;
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
import java.io.*;
import java.util.StringTokenizer;

public class CS203LAB7E {
    public static void main(String[] args) {
        AVL tree = new AVL();

        QReader input = new QReader();
        QWriter out = new QWriter();
        int m = input.nextInt();
        int k = input.nextInt();
        int[] nums = new int[m];
        int[] windows = new int[m - k + 1];
        for (int i = 0; i < m; i++) {
            nums[i] = input.nextInt();
        }
        for (int i = 0; i < (m - k + 1); i++) {
            windows[i] = input.nextInt();
        }
        for (int i = 0; i < k; i++) {
            tree.insertNode(nums[i]);
        }
        out.println(tree.kthNumber(tree.root,windows[0]));
        for (int i = 1; i <= (m - k) ; i++) {
            tree.deleteNode(nums[i - 1]);
            tree.insertNode(nums[k - 1 + i]);
            out.println(tree.kthNumber(tree.root,windows[i]));
        }
        out.close();
    }
}

//AVL

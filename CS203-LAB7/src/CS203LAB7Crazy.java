import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class CS203LAB7Crazy {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m =input.nextInt();
        int k =input.nextInt();
        int[] nums = new int[m];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = input.nextInt();
        }
        int[] ans = new int[m - k + 1];
        for (int i = 0; i < m - k + 1; i++) {
            int[] arr = new int[k];
            int index = input.nextInt();
            for (int j = i; j < k + i; j++) {
                arr[j - i] = nums[j];
            }
            Arrays.sort(arr);
            ans[i] = arr[index - 1];
        }
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }
}

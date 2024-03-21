import java.util.Scanner;

public class CS203LAB1A {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = input.nextInt();
        }
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int target = input.nextInt();
            int left = 0;
            int right = n - 1;
            boolean notFound = true;
            while(left <= right){
                int mid = (left + right) / 2 ;
                if(nums[mid] == target){
                    System.out.println("YES");
                    notFound = false;
                    break;
                }
                else if(target < nums[mid]){
                    right = mid - 1;
                }
                else if(target > nums[mid]){
                    left = mid + 1 ;
                }
            }
            if(notFound){
                System.out.println("NO");
            }
        }
    }
}

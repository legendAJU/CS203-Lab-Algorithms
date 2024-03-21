import java.util.Scanner;

public class CS203LAB1D {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n,S;
        n = input.nextInt();
        S = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = input.nextInt();
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n ; j++) {
                int target = S - nums[i] - nums[j];
                int left = j + 1;
                int right = nums.length - 1;
                int recordOfTargetInLeast = 0;
                int recordOfTargetInMost = 0;
                //找等于target的第一个
                while(left < right){
                    int mid = (left + right) / 2;
                    if(nums[mid] > target){
                        right = mid - 1;
                    }
                    else if(nums[mid] < target){
                        left = mid + 1;
                    }
                    else if(nums[mid] == target){
                        right = mid;
                    }
                }
                recordOfTargetInLeast = left;
                //找等于target的最后一个
                left = j + 1;
                right = nums.length - 1;
                while(left < right){
                    int mid = (left + right) / 2;
                    if(nums[mid] > target){
                        right = mid - 1;
                    }
                    else if(nums[mid] < target){
                        left = mid + 1;
                    }
                    else if(nums[mid] == target){
                        left = mid;
                    }
                }
                recordOfTargetInMost = right;
                System.out.println(recordOfTargetInLeast + " " + recordOfTargetInMost);
            }
        }
       // System.out.println(count);
    }
}

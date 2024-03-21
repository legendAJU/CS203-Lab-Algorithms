import java.util.Scanner;

public class CS203LAB1C {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int T = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = input.nextInt();
        }
        for (int i = 0; i < T; i++) {
            int least = input.nextInt();
            int most = input.nextInt();
            int left = 0;
            int right = n - 1;
            int count = 0;
            int recordOfmost = n - 1;
            int recordOfleast = 0;
            if(least < nums[0]){
                recordOfleast = 0;
            }
            else if(least >= nums[n - 1]){
                recordOfleast = n;
            }
            else{
                while(left < right){
                   // System.out.println(left + " " + right);
                    int mid = (left + right) / 2;
                    if(nums[mid] > least){
                        right = mid;
                    }
                    else if(nums[mid] < least){
                        left = mid + 1;
                    }
                    else if(nums[mid] == least){
                        left = mid + 1;
                    }
                }
                recordOfleast = left;
            }
            //找下界,找比least大的第一个数。

           // System.out.println(recordOfleast);
            if(most > nums[n - 1]){
                recordOfmost = n - 1;
            }
            else if(most <= nums[0]){
                recordOfmost = -1;
            }
            else{
                left = 0;
                right = n - 1;
                //找上界，第一个比most小的数
                while(left < right){
                    int mid = (left + right + 1) / 2;
                    if(nums[mid] > most){
                        right = mid - 1;
                    }
                    else if(nums[mid] < most){
                        left = mid;
                    }
                    else if(nums[mid] == most){
                        right = mid - 1;
                    }
                }
                recordOfmost = right;
            }
            //System.out.println(recordOfmost);
            count = recordOfmost - recordOfleast + 1;
            if(count > 0){
                System.out.println("YES " + count);
            }else{
                System.out.println("NO");
            }
        }
    }
}

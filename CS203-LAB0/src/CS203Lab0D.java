import java.util.Scanner;

public class CS203Lab0D {
    public static void main(String[] args) {
        int T;
        Scanner input = new Scanner(System.in);
        T = input.nextInt();
        int n;
        for (int i = 0; i < T; i++) {
            n = input.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = input.nextInt();
            }
            int min_dif = nums[1] - nums[0];
            int max = nums[0];
            for (int j = 2; j < n; j++) {
                if(nums[j] - max < min_dif){
                    min_dif = nums[j] - max;
                }else{
                    max = nums[j];
                }
            }
            System.out.println(- min_dif);
        }
    }
}


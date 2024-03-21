import java.util.Arrays;
import java.util.Scanner;

public class CS203LAB1E {
    public static boolean canRun(int ans,int m,int L,int[] distance){
        int count = 1;
        int copyOfans = ans;
        int[] difOfdistance = new int[500001];
        difOfdistance[1] = distance[0];
        difOfdistance[distance.length + 1] = L - distance[distance.length - 1];
        for (int i = 2; i <= distance.length ; i++) {
            difOfdistance[i] = distance[i - 1] - distance[i - 2];
        }
        boolean canDonext = true;
        for (int i = 1; i <= distance.length + 1 ; i++) {
            if(ans < difOfdistance[i]){
                canDonext = false;
                break;
            }
        }
        if(canDonext){
            for (int i = 1; i <= distance.length + 1 ; i++) {
                if(copyOfans - difOfdistance[i] >= 0){
                    copyOfans -= difOfdistance[i];
                    continue;
                }
                else if(copyOfans - difOfdistance[i] < 0){
                    count++;
                    copyOfans = ans;
                    i--;
                }
            }
            if(count > m){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            int L,n,m;
            L = input.nextInt();
            n = input.nextInt();
            m = input.nextInt();
            int[] distance = new int[n];
            for (int i = 0; i < n; i++) {
                distance[i] = input.nextInt();
            }
            Arrays.sort(distance);
            int left = 1;
            int right = L;
            while(left < right){
                int mid = (left + right) / 2;
                if(canRun(mid,m,L,distance)){
                    right = mid;
                }
                else{
                    left = mid + 1;
                }
            }
            System.out.println(right);
        }
    }
}

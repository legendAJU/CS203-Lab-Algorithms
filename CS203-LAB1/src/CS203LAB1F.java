import java.util.Scanner;

public class CS203LAB1F {
    public static boolean canCatch(long x1, long x2, long y1, long y2, int[][] direction, int[] basePeriodOfdif, long time) {
        long fullPeriod = time / basePeriodOfdif.length;
        long remainPeriod = time % basePeriodOfdif.length;
        int netX = 0;
        int netY = 0;
            for (int i = 0; i < basePeriodOfdif.length; i++) {
                netX += direction[basePeriodOfdif[i]][0];
                netY += direction[basePeriodOfdif[i]][1];
            }
            x2 += (netX * fullPeriod);
            y2 += (netY * fullPeriod);

        for (int i = 0; i < remainPeriod; i++) {
            x2 += direction[basePeriodOfdif[i]][0];
            y2 += direction[basePeriodOfdif[i]][1];
        }
        if(Math.abs(x2 - x1) + Math.abs(y2 - y1) > time){
            return false;
        }else{
            return true;
        }
    }
        public static void main (String[]args){
            Scanner input = new Scanner(System.in);
            long x1, y1, x2, y2;
            x1 = input.nextInt();
            y1 = input.nextInt();
            x2 = input.nextInt();
            y2 = input.nextInt();
            int N;
            N = input.nextInt();
            //四个方向，向左(L)，向右(R)，向上(U)，向下(D)
            int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
            String periodOfdir = input.next();
            int[] basePeriodOfdif = new int[N];
            for (int i = 0; i < basePeriodOfdif.length; i++) {
                if (periodOfdir.charAt(i) == 'U') {
                    basePeriodOfdif[i] = 2;
                } else if (periodOfdir.charAt(i) == 'D') {
                    basePeriodOfdif[i] = 3;
                } else if (periodOfdir.charAt(i) == 'L') {
                    basePeriodOfdif[i] = 0;
                } else if (periodOfdir.charAt(i) == 'R') {
                    basePeriodOfdif[i] = 1;
                }
            }
            long left = 0;
            long right = (long) Math.pow(10,14);
            boolean haveAns = false;
            while (left < right) {
                long mid = (left + right) / 2;
                if (canCatch(x1, x2, y1, y2, direction, basePeriodOfdif, mid)) {
                    right = mid;
                    haveAns = true;
                }
                else {
                    left = mid + 1;
                }
            }
            if(haveAns){
                System.out.println(right);
            }else{
                System.out.println(-1);
            }
        }
    }


import java.util.Scanner;

public class CS203Lab0F1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T;
        T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int[][] data = new int[5][100];
            String chess = input.next();
            for (int j = 1; j < chess.length(); j += 2) {
                int numericValue = Character.getNumericValue(chess.charAt(j - 1));
                if (chess.charAt(j) == 'w') {
                    data[1][numericValue]++;
                }
                if (chess.charAt(j) == 'b') {
                    data[2][numericValue]++;
                }
                if (chess.charAt(j) == 's') {
                    data[3][numericValue]++;
                }
                if (chess.charAt(j) == 'z') {
                    data[4][numericValue]++;
                }
            }
            boolean ans = false;
            //先找所有可能的quetou,做定位
            int[][] locatOfquetou = new int[5][10];
            boolean haveQuetou = false;
            for (int j = 1; j <= 4; j++) {
                for (int k = 1; k <= 9; k++) {
                    if (data[j][k] >= 2) {
                        locatOfquetou[j][k]++;
                        haveQuetou = true;
                    }
                }
            }
            int countOfwan = 0;
            int numOfwan = 0;
            int countOfbing = 0;
            int numOfbing = 0;
            int countOfsuo = 0;
            int numOfsuo = 0;
            int countOfzi = 0;
            int numOfzi = 0;
            if(haveQuetou){
                //对每个quetou情况进行分析
                for (int j = 1; j <= 4 ; j++) {
                    for (int k = 1; k <= 9 ; k++) {
                        if(locatOfquetou[j][k] == 1){
                            data[j][k] -= 2;
                            //从wan开始分析.
                            for (int l = 1; l <= 9 ; l++) {
                                numOfwan += data[1][l];
                            }
                            if(numOfwan % 3 != 0){
                                data[j][k] += 2;
                                countOfbing = 0;
                                countOfzi = 0;
                                countOfsuo = 0;
                                countOfwan = 0;
                                numOfsuo = 0;
                                numOfbing = 0;
                                numOfzi = 0;
                                numOfwan = 0;
                                break;
                            }else{
                                int[] copyOfwan = new int[13];
                                for (int l = 1; l <= 9 ; l++) {
                                    copyOfwan[l] = data[1][l];
                                }
                                for (int l = 1; l <=9 ; l++) {
                                    if(copyOfwan[l] >= 3){
                                        int x = data[1][l] / 3;
                                        for (int m = 1; m <= x ; m++) {
                                            copyOfwan[l] -= 3;
                                            countOfwan++;
                                        }
                                        l--;
                                    }
                                    else if(copyOfwan[l + 1] >= copyOfwan[l] && copyOfwan[l + 2] >= copyOfwan[l]){
                                        if(copyOfwan[l] > 0){
                                            int x = copyOfwan[l];
                                            for (int m = 1; m <= x ; m++) {
                                                copyOfwan[l] --;
                                                copyOfwan[l + 1] --;
                                                copyOfwan[l + 2]--;
                                                countOfwan ++;
                                            }
                                        }

                                    }
                                }
                            }
                            //再分析bing
                            for (int l = 1; l <= 9 ; l++) {
                                numOfbing += data[2][l];
                            }
                            if(numOfbing % 3 != 0){
                                data[j][k] += 2;
                                countOfbing = 0;
                                countOfzi = 0;
                                countOfsuo = 0;
                                countOfwan = 0;
                                numOfsuo = 0;
                                numOfbing = 0;
                                numOfzi = 0;
                                numOfwan = 0;
                                break;
                            }else{
                                int[] copyOfwan = new int[13];
                                for (int l = 1; l <= 9 ; l++) {
                                    copyOfwan[l] = data[2][l];
                                }
                                for (int l = 1; l <=9 ; l++) {
                                    if(copyOfwan[l] >= 3){
                                        int x = copyOfwan[l] / 3;
                                        for (int m = 1; m <= x ; m++) {
                                            copyOfwan[l] -= 3;
                                            countOfbing++;
                                        }
                                        l--;
                                    }
                                    else if(copyOfwan[l + 1] >= copyOfwan[l] && copyOfwan[l + 2] >= copyOfwan[l]){
                                        if(copyOfwan[l] > 0){
                                            int x = copyOfwan[l];
                                            for (int m = 1; m <= x ; m++) {
                                                copyOfwan[l] --;
                                                copyOfwan[l + 1] --;
                                                copyOfwan[l + 2]--;
                                                countOfbing ++;
                                        }
                                        }
                                    }
                                }
                            }


                            //在分析suo

                            for (int l = 1; l <= 9 ; l++) {
                                numOfsuo += data[3][l];
                            }
                            if(numOfsuo % 3 != 0){
                                data[j][k] += 2;
                                countOfbing = 0;
                                countOfzi = 0;
                                countOfsuo = 0;
                                countOfwan = 0;
                                numOfsuo = 0;
                                numOfbing = 0;
                                numOfzi = 0;
                                numOfwan = 0;
                                break;
                            }else{
                                int[] copyOfwan = new int[13];
                                for (int l = 1; l <= 9 ; l++) {
                                    copyOfwan[l] = data[3][l];
                                }
                                boolean go = false;
                                for (int l = 1; l <=9 ; l++) {
                                    if(copyOfwan[l] >= 3){
                                        int x = copyOfwan[l] / 3;
                                        for (int m = 1; m <= x ; m++) {
                                            copyOfwan[l] -= 3;
                                            countOfsuo++;
                                        }
                                        l--;
                                    }
                                    else if(copyOfwan[l + 1] >= copyOfwan[l] && copyOfwan[l + 2] >= copyOfwan[l]){
                                        if(copyOfwan[l] > 0){
                                            int x = copyOfwan[l];
                                            for (int m = 1; m <= x ; m++) {
                                                copyOfwan[l] --;
                                                copyOfwan[l + 1] --;
                                                copyOfwan[l + 2]--;
                                                countOfsuo ++;
                                            }
                                        }
                                    }
                                }
                            }
                            //最后分析zi
                            int[] copyOfwan = new int[13];
                            for (int l = 1; l <= 9 ; l++) {
                                copyOfwan[l] = data[4][l];
                            }
                            for (int l = 1; l <= 9 ; l++) {


                                if(copyOfwan[l] >= 3){
                                    copyOfwan[l] -= 3;
                                    countOfzi++;
                                    l--;
                                }
                            }
                            if(countOfwan + countOfbing + countOfsuo + countOfzi < 4){
                                data[j][k] += 2;
                                countOfbing = 0;
                                countOfzi = 0;
                                countOfsuo = 0;
                                countOfwan = 0;
                                numOfsuo = 0;
                                numOfbing = 0;
                                numOfzi = 0;
                                numOfwan = 0;
                            }
                            else{
                                ans = true;
                            }
                        }

                    }
                }
            }
            if(haveQuetou && ans){
                System.out.println("Blessing of Heaven");
            }else{
                System.out.println("Bad luck");
            }
        }
    }
}


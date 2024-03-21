import java.util.Scanner;

public class CS203Lab0F {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T;
        T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int[][] data = new int[5][100];
            String chess = input.next();
            int countOfkezi = 0;
            int countOfshunzi = 0;
            int countOfquetou = 0;
            for (int j = 1; j < chess.length(); j += 2) {
                int numericValue = Character.getNumericValue(chess.charAt(j - 1));
                if (chess.charAt(j) == 'w'){
                    data[1][numericValue] ++;
                }
                if (chess.charAt(j) == 'b'){
                    data[2][numericValue] ++;
                }
                if (chess.charAt(j) == 's'){
                    data[3][numericValue] ++;
                }
                if (chess.charAt(j) == 'z'){
                    data[4][numericValue] ++;
                }
            }
            int recordOfj = 0;
            boolean ans = false;
            //先找所有可能的quetou,做定位
            int[][] locatOfquetou = new int[5][10];
            boolean haveQuetou = false;
            for (int j = 1; j <= 4 ; j++) {
                for (int k = 1; k <= 9 ; k++) {
                    if(data[j][k] >= 2){
                        locatOfquetou[j][k] ++ ;
                        haveQuetou = true;
                    }
                }
            }
            //如果有quetou
            if(haveQuetou){
                //从每个有quetou的位置开始遍历
                for (int j = 1; j <= 4 ; j++) {
                    for (int k = 1; k <= 9 ; k++) {

                        if(locatOfquetou[j][k] == 1){
                            //找到该位置有quetou
                            data[j][k] -= 2;
                            countOfquetou ++;
                            //第一轮找有没有kezi
                            int[][] locatOfkeziRound1 = new int[5][10];
                            boolean havekeziRound1 = false;
                            for (int l = 1; l <= 4 ; l++) {
                                for (int m = 1; m <= 9 ; m++) {
                                    if(data[l][m] >= 3){
                                       locatOfkeziRound1[l][m] ++;
                                       havekeziRound1 = true;
                                    }
                                }
                            }
                            if(havekeziRound1){
                                //从第一轮找到的kezi各个情况开始找
                                for (int l = 1; l <= 4 ; l++) {
                                    for (int m = 1; m <= 9 ; m++) {
                                        if(locatOfkeziRound1[l][m] == 1){
                                            data[l][m] -= 3;
                                            countOfkezi ++;
                                            //第二轮找有没有kezi
                                            int[][] locatOfkeziRound2 = new int[5][10];
                                            boolean havekeziRound2 = false;
                                            for (int n = 1; n <= 4 ; n++) {
                                                for (int o = 1; o <= 9 ; o++) {
                                                    if(data[n][o] >= 3){
                                                        locatOfkeziRound2[n][o] ++;
                                                        havekeziRound2 = true;
                                                    }
                                                }
                                            }
                                            //若第二轮找到kezi
                                            if(havekeziRound2){
                                                for (int n = 1; n <= 4 ; n++) {
                                                    for (int o = 1; o <= 9 ; o++) {
                                                        if(locatOfkeziRound2[n][o] == 1){
                                                            //从第二轮找到的kezi各个情况开始找
                                                            data[n][o] -= 3;
                                                            countOfkezi ++;
                                                            //第三轮找有没有kezi
                                                            int[][] locatOfkeziRound3 = new int[5][10];
                                                            boolean havekeziRound3 = false;
                                                            for (int p = 1; p <= 4 ; p++) {
                                                                for (int q = 1; q <= 9 ; q++) {
                                                                    if(data[p][q] >= 3){
                                                                        locatOfkeziRound3[p][q] ++;
                                                                        havekeziRound3 = true;
                                                                    }
                                                                }
                                                            }
                                                            //如果第三轮找到kezi
                                                            if(havekeziRound3){
                                                                for (int p = 1; p <= 4 ; p++) {
                                                                    for (int q = 1; q <= 9 ; q++) {
                                                                        if(locatOfkeziRound3[p][q] == 1){
                                                                            //从第三轮找到的kezi各个情况开始找
                                                                            data[p][q] -= 3;
                                                                            countOfkezi ++;
                                                                            //第四轮开始找kezi
                                                                            int[][] locatOfkeziRound4 = new int[5][10];
                                                                            boolean havekeziRound4 = false;
                                                                            for (int r = 1; r <= 4 ; r++) {
                                                                                for (int s = 1; s <= 9 ; s++) {
                                                                                    if(data[r][s] >= 3){
                                                                                        locatOfkeziRound4[r][s] ++;
                                                                                        havekeziRound4 = true;
                                                                                    }
                                                                                }
                                                                            }
                                                                            //若第四轮找到kezi
                                                                            if(havekeziRound4){
                                                                                ans = true;
                                                                            }
                                                                            //如果第四轮没找到kezi,第四轮找顺子
                                                                            else{
                                                                                boolean haveshunziRoun4 = false;
                                                                                for (int r = 1; r <= 4 ; r++) {
                                                                                    for (int s = 1; s <= 9 ; s++) {
                                                                                        if(data[r][s] >= 1 && data[r][s + 1] >= 1 && data[r][s + 2] >= 1){
                                                                                            haveshunziRoun4 = true;
                                                                                        }
                                                                                    }
                                                                                }
                                                                                //如果第四轮有顺子
                                                                                if(haveshunziRoun4){
                                                                                    ans = true;
                                                                                }
                                                                                //如果第四轮没顺子
                                                                                else {
                                                                                    //考虑第三轮的kezi寻找有误，回到第三轮，换一个kezi
                                                                                    data[p][q] += 3;
                                                                                    countOfkezi --;
                                                                                }

                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            //若第三轮没找到kezi，则从第三轮开始找shunzi
                                                            else{
                                                                //从第三轮开始，不停的找顺子，看能不能找到两个。(copy一份只看找不找得到，找不到也不影响回去)
                                                                int[][] copyOfdata = new int[5][10];
                                                                for (int p = 1; p <= 4 ; p++) {
                                                                    for (int q = 1; q <= 9 ; q++) {
                                                                        copyOfdata[p][q] = data[p][q];
                                                                    }
                                                                }
                                                                for (int p = 1; p <= 4 ; p++) {
                                                                    for (int q = 1; q <= 9 ; q++) {
                                                                        if(copyOfdata[p][q] >= 1 && copyOfdata[p][q + 1] >= 1 && copyOfdata[p][q + 2] >= 1){
                                                                            countOfshunzi ++;
                                                                            copyOfdata[p][q] --;
                                                                            copyOfdata[p][q + 1] --;
                                                                            copyOfdata[p][q + 2]--;
                                                                            q--;
                                                                        }
                                                                    }
                                                                }
                                                                //如果找到了两个顺子
                                                                if(countOfshunzi == 2){
                                                                    ans = true;
                                                                }
                                                                //若没有找到，怀疑第二轮的kezi找错了，回到第二轮换一个kezi
                                                                else{
                                                                    data[n][o] += 3;
                                                                    countOfkezi --;
                                                                    countOfshunzi = 0;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                            //若没找到,从第二轮开始找顺子
                                            else{
                                                //从第二轮开始，不停的找顺子，看能不能找到三个。
                                                int[][] copyOfdata = new int[5][10];
                                                for (int p = 1; p <= 4 ; p++) {
                                                    for (int q = 1; q <= 9 ; q++) {
                                                        copyOfdata[p][q] = data[p][q];
                                                    }
                                                }
                                                for (int p = 1; p <= 4 ; p++) {
                                                    for (int q = 1; q <= 9 ; q++) {
                                                        if(copyOfdata[p][q] >= 1 && copyOfdata[p][q + 1] >= 1 && copyOfdata[p][q + 2] >= 1){
                                                            countOfshunzi ++;
                                                            copyOfdata[p][q] --;
                                                            copyOfdata[p][q + 1] --;
                                                            copyOfdata[p][q + 2]--;
                                                            q--;
                                                        }
                                                    }
                                                }
                                                //如果找到了三个顺子
                                                if(countOfshunzi == 3){
                                                    ans = true;
                                                }
                                                //若没有找到，怀疑第一轮的kezi找错了，回到第一轮换一个kezi
                                                else{
                                                    data[l][m] += 3;
                                                    countOfkezi --;
                                                    countOfshunzi = 0;
                                                }
                                            }
                                            //若第二轮有kezi，但不行，转而找顺子
                                             if(havekeziRound2 && !ans){
                                                //第二轮找有没有shunzi
                                                int[][] locatOfshunziRound2 = new int[5][10];
                                                boolean haveshunziRound2 = false;
                                                for (int n = 1; n <= 4 ; n++) {
                                                    for (int o = 1; o <= 9 ; o++) {
                                                        if(data[n][o] >= 1 && data[n][o + 1] >= 1 && data[n][o + 2] >= 1){
                                                            locatOfshunziRound2[n][o] ++;
                                                            locatOfshunziRound2[n][o + 1] ++;
                                                            locatOfshunziRound2[n][o + 2] ++;
                                                            haveshunziRound2 = true;
                                                        }
                                                    }
                                                }
                                                //若第二轮找到了shunzi
                                                if(haveshunziRound2){
                                                    for (int n = 1; n <= 4 ; n++) {
                                                        for (int o = 1; o <= 9 ; o++) {
                                                            if(locatOfshunziRound2[n][o] >= 1 && locatOfshunziRound2[n][o + 1] >= 1 && locatOfshunziRound2[n][o + 2] >= 1){
                                                                data[n][o] -= 1;
                                                                data[n][o + 1] -= 1;
                                                                data[n][o + 2] -= 1;
                                                                countOfshunzi ++;
                                                                //第三轮看有没有shunzi
                                                                int[][] locatshunziRound3 = new int[5][10];
                                                                boolean haveshunziRound3 = false;
                                                                for (int p = 1; p <= 4 ; p++) {
                                                                    for (int q = 1; q <= 9 ; q++) {
                                                                        if(data[p][q] >= 1 && data[p][q + 1] >= 1 && data[p][q + 2] >= 1){
                                                                            locatshunziRound3[p][q] ++;
                                                                            locatshunziRound3[p][q + 1] ++;
                                                                            locatshunziRound3[p][q + 2] ++;
                                                                            haveshunziRound3 = true;
                                                                        }
                                                                    }
                                                                }
                                                                //如果第三轮有shunzi
                                                                if(haveshunziRound3){
                                                                    for (int p = 1; p <= 4 ; p++) {
                                                                        for (int q = 1; q <= 9 ; q++) {
                                                                            if(locatshunziRound3[p][q] >= 1 && locatshunziRound3[p][q + 1] >= 1 && locatshunziRound3[p][q + 3] >= 1){
                                                                                data[p][q] -= 1 ;
                                                                                data[p][q + 1] -= 1;
                                                                                data[p][q + 2] -= 1;
                                                                                countOfshunzi++;
                                                                                //查看第四轮有没有顺子
                                                                                boolean haveshunziRound4 = false;
                                                                                for (int r = 1; r <= 4 ; r++) {
                                                                                    for (int s = 1; s <= 9 ; s++) {
                                                                                        if(data[r][s] >= 1 && data[r][s + 1] >= 1 && data[r][s + 2] >= 1){
                                                                                            haveshunziRound4 = true;
                                                                                        }
                                                                                    }
                                                                                }
                                                                                //如果第四轮有顺子
                                                                                if(haveshunziRound4){
                                                                                    ans = true;
                                                                                }
                                                                                //若第四轮没有顺子
                                                                                else{
                                                                                    //查找第四轮有没有kezi
                                                                                    boolean havekeziRound4 = false;
                                                                                    for (int r = 1; r <= 4 ; r++) {
                                                                                        for (int s = 1; s <= 9 ; s++) {
                                                                                            if(data[r][s] >= 3){
                                                                                                haveshunziRound4 = true;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    //若第四轮有kezi
                                                                                    if(havekeziRound4){
                                                                                        ans = true;
                                                                                    }
                                                                                    //若第四轮没有kezi，则说明第三轮有的这个顺子不行，换一个
                                                                                    else{
                                                                                        data[p][q] += 1 ;
                                                                                        data[p][q + 1] += 1;
                                                                                        data[p][q + 2] += 1;
                                                                                        countOfshunzi--;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                //若第三轮找不到顺子
                                                                else{
                                                                    //则看能不能找到两个kezi
                                                                    int a = 0;
                                                                    for (int p = 1; p <= 4 ; p++) {
                                                                         for (int q = 1; q <= 9 ; q++) {
                                                                            if(data[p][q] >= 3){
                                                                                a++;
                                                                            }
                                                                        }
                                                                    }
                                                                    if(a == 2){
                                                                        ans = true;
                                                                    }
                                                                    //若找不到，则怀疑第二轮的顺子有问题，重新找。
                                                                    else{
                                                                        data[n][o] += 1;
                                                                        data[n][o + 1] += 1;
                                                                        data[n][o + 2] += 1;
                                                                        countOfshunzi --;
                                                                    }
                                                                }
                                                                //若第三轮找到顺子，但是不对，则第三轮改为找kezi
                                                                if(haveshunziRound3 && !ans){
                                                                    //第三轮开始查找kezi
                                                                    int[][] locatOfkeziRound3 = new int[5][10];
                                                                    boolean havekeziRound3 = false;
                                                                    for (int p = 1; p <= 4 ; p++) {
                                                                        for (int q = 1; q <= 9 ; q++) {
                                                                            if(data[p][q] >= 3){
                                                                                locatOfkeziRound3[p][q] ++;
                                                                                havekeziRound3 = true;
                                                                            }
                                                                        }
                                                                    }
                                                                    //如果第三轮有kezi
                                                                    if(havekeziRound3){
                                                                        //查找第四轮是否有kezi 或 shunzi
                                                                        int[][] locatOfkeziRound4 = new int[5][10];
                                                                        boolean havekeziRound4 = false;
                                                                        for (int p = 1; p <= 4 ; p++) {
                                                                            for (int q = 1; q <= 9 ; q++) {
                                                                                if(data[p][q] >= 3 || (data[p][q] >= 1 && data[p][q + 1] >= 1 && data[p][q + 2] >=1)){
                                                                                    havekeziRound4 = true;
                                                                                }
                                                                            }
                                                                        }
                                                                        //若第四轮有kezi
                                                                        if(havekeziRound4){
                                                                            ans = true;
                                                                        }
                                                                        //若第四轮没有，就意味着第三轮顺子还是kezi都不行，怀疑第二轮顺子有误
                                                                        else{
                                                                            data[n][o] += 1;
                                                                            data[n][o + 1] += 1;
                                                                            data[n][o + 2] += 1;
                                                                            countOfshunzi --;
                                                                        }
                                                                    }
                                                                    //若第三轮没有kezi,怀疑第二轮顺子有误
                                                                    else{
                                                                        data[n][o] += 1;
                                                                        data[n][o + 1] += 1;
                                                                        data[n][o + 2] += 1;
                                                                        countOfshunzi --;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                                //若第二轮未找到shunzi，回去换第一轮的kezi
                                                else{
                                                    data[l][m] += 3;
                                                    countOfkezi --;
                                                    countOfshunzi = 0;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            //如果第一轮没有kezi，那就开始找shunzi
                            else{
                                //看能不能找到四个顺子
                                //从第一轮开始，不停的找顺子，看能不能找到四个。
                                int[][] copyOfdata = new int[5][10];
                                for (int p = 1; p <= 4 ; p++) {
                                    for (int q = 1; q <= 9 ; q++) {
                                        copyOfdata[p][q] = data[p][q];
                                    }
                                }
                                for (int p = 1; p <= 4 ; p++) {
                                    for (int q = 1; q <= 9 ; q++) {
                                        if(copyOfdata[p][q] >= 1 && copyOfdata[p][q + 1] >= 1 && copyOfdata[p][q + 2] >= 1){
                                            countOfshunzi ++;
                                            copyOfdata[p][q] --;
                                            copyOfdata[p][q + 1] --;
                                            copyOfdata[p][q + 2]--;
                                            q--;
                                        }
                                    }
                                }
                                //如果找到了四个顺子
                                if(countOfshunzi == 4){
                                    ans = true;
                                }
                                //若没有找到，怀疑最开始quetou找错了，回到第一轮换一个quetou
                                else{
                                    data[j][k] += 2;
                                    countOfquetou --;
                                    countOfshunzi = 0;
                                    countOfkezi = 0;
                                }
                            }
                        }
                    }
                }
            }

            if(ans){
                System.out.println("Blessing of Heaven");
            }else{
                System.out.println("Bad luck");
            }
        }
    }
}

import java.io.*;
import java.util.ArrayList;

import java.util.StringTokenizer;

public class CS203LAB8E{
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            //rows
            int N = input.nextInt();
            //columns
            int M = input.nextInt();

            int[][] matrix = new int[N + 2][M + 2];
            int[] nums = new int[M * N + 1];
            int finalAns = 0;
            //given coordinate (n,m) in matrix,matrix[n][m] , which represent nums[(n - 1) * M + m];
            int index = 0;
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= M ; k++) {
                    int num = input.nextInt();
                    matrix[j][k] = num;
                    nums[++index] = num;
                }
            }
            index = 1;
            while(index <= M * N){
                boolean[] isVisited = new boolean[M * N + 1];
                boolean[][] isVisitedMatrix = new boolean[N + 2][M + 2];
                int[] ans = new int[1];
                ArrayList<Integer> answears = new ArrayList<>();
                explore(nums,index,isVisitedMatrix,isVisited,M,N,ans,answears);
                if(answears.get(0) > finalAns){
                    finalAns = answears.get(0);
                }
                index++;
            }
            out.println(finalAns);

            if(i == T - 1){
                out.close();
            }

        }

    }
    //given coordinate (n,m) in matrix,matrix[n][m] , which represent nums[(n - 1) * M + m];
    public static void explore(int[] nums,int index,boolean[][] isVisitedMatrix,boolean[] isVisited,int M,int N,int[] ans,ArrayList<Integer> answears ){
        int m;
        int n;
        if(index % M != 0){
            n = index / M + 1;
        }else{
            n = index / M;
        }
        m = index - (n - 1) * M;

        ArrayList<Integer> check = new ArrayList<>();
        ans[0] += nums[index];
        if(answears.size() == 0){
            answears.add(ans[0]);
        }
        else if(ans[0] > answears.get(0)){
            answears.remove(0);
            answears.add(ans[0]);
        }
        isVisited[index] = true;
        isVisitedMatrix[n][m] = true;
        check.add(index);

        kill(n,m,isVisitedMatrix,isVisited,check,N,M);
        if(index == 1 || index == 2 || index == M + 1 || index == M + 2){
            for (int i = 1; i <= nums.length - 1 ; i++) {
                if(!isVisited[i]){
                    explore(nums,i,isVisitedMatrix,isVisited,M,N,ans,answears);
                }
            }
        }
        else{
            for (int i = index + 1; i <= nums.length - 1 ; i++) {
                if(!isVisited[i]){
                    explore(nums,i,isVisitedMatrix,isVisited,M,N,ans,answears);
                }
            }
        }


        goBack(nums,index,isVisited,check,N,ans,isVisitedMatrix,M);
    }

    public static void goBack(int[] nums,int index,boolean[] isVisited,ArrayList<Integer> check,int N,int[] ans,boolean[][] isVisitedMatrix,int M){
        int m;
        int n;
        if(index % M != 0){
            n = index / M + 1;
        }else{
            n = index / M;
        }
        m = index - (n - 1) * M;

        ans[0] -= nums[index];

        for (int i = 0; i <= check.size() - 1; i++) {

            int n1 = returnN(check.get(i),M);
            int m1 = returnM(check.get(i),M,n1);
            isVisited[check.get(i)] = false;
            isVisitedMatrix[n1][m1] = false;

        }
        check.clear();
    }

    public static void kill(int n,int m,boolean[][] isVisitedMatrix,boolean[] isVisited,ArrayList<Integer> check,int N,int M){
        if(!isVisitedMatrix[n - 1][m - 1]){
            isVisitedMatrix[n - 1][m - 1] = true;
            isVisited[trans(n - 1,m - 1,N,M)] = true;
            if(trans(n - 1,m - 1,N,M) != 0){
                check.add(trans(n - 1,m - 1,N,M));
            }
        }
        if(!isVisitedMatrix[n - 1][m]){
            isVisitedMatrix[n - 1][m] = true;

            isVisited[trans(n - 1,m,N,M)] = true;
            if(trans(n - 1,m,N,M) != 0){
                check.add(trans(n - 1,m,N,M));
            }
        }
        if(!isVisitedMatrix[n - 1][m + 1]){
            isVisitedMatrix[n - 1][m + 1] = true;

            isVisited[trans(n - 1,m + 1,N,M)] = true;
            if(trans(n - 1,m + 1,N,M) != 0){
                check.add(trans(n - 1,m + 1,N,M));
            }
        }
        if(!isVisitedMatrix[n][m - 1]){
            isVisitedMatrix[n][m - 1] = true;

            isVisited[trans(n ,m - 1,N,M)] = true;
            if(trans(n,m - 1,N,M) != 0){
                check.add(trans(n,m - 1,N,M));
            }
        }
        if(!isVisitedMatrix[n][m + 1]){
            isVisitedMatrix[n][m + 1] = true;

            isVisited[trans(n,m + 1,N,M)] = true;
            if(trans(n,m + 1,N,M) != 0){
                check.add(trans(n ,m + 1,N,M));
            }
        }
        if(!isVisitedMatrix[n + 1][m - 1]){
            isVisitedMatrix[n + 1][m - 1] = true;

            isVisited[trans(n + 1,m - 1,N,M)] = true;
            if(trans(n + 1,m - 1,N,M) != 0){
                check.add(trans(n + 1,m - 1,N,M));
            }
        }
        if(!isVisitedMatrix[n + 1][m]){
            isVisitedMatrix[n + 1][m] = true;

            isVisited[trans(n + 1,m,N,M)] = true;
            if(trans(n + 1,m,N,M) != 0){
                check.add(trans(n + 1,m,N,M));
            }
        }
        if(!isVisitedMatrix[n + 1][m + 1]){
            isVisitedMatrix[n + 1][m + 1] = true;

            isVisited[trans(n + 1,m + 1,N,M)] = true;
            if(trans(n + 1,m + 1,N,M) != 0){
                check.add(trans(n + 1,m + 1,N,M));
            }
        }
    }


    public static int trans(int n,int m,int N,int M){
        if(m >= 1 && m <= M && n >= 1 && n <= N){
            return (n - 1) * M + m;
        }
        else{
            return 0;
        }
    }

    public static int returnN(int index,int M){
        if(index % M != 0){
            return index/M + 1;
        }else{
            return index/M;
        }

    }
    public static int returnM(int index,int M,int n){
        return index - (n - 1) * M;
    }
}


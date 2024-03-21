import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.next();
        int oddNumber = 0;
        int evenNumber = 0;
        String longestForOdd = null;
        String longestForEven = null;






        //查询奇数回文字串长度
        for (int i = 1; i < s.length() - 1; i++) {
            if(oddNumber < palindromeLength(s,i,i)){
                oddNumber = palindromeLength(s,i,i);
                longestForOdd = returnLongestPalindrome(s,oddNumber,i);
            }
        }
        //查询偶数回文子串长度
        for (int i = 0; i < s.length() ; i++) {
            if(evenNumber < palindromeLength(s,i,i + 1)){
                evenNumber = palindromeLength(s,i,i + 1);
                longestForEven = returnLongestPalindrome(s,evenNumber,i,i + 1);
            }
        }
        //输出
        if(oddNumber > evenNumber){
            System.out.println(oddNumber);
            System.out.print(longestForOdd);
        }else{
            System.out.println(evenNumber);
            System.out.print(longestForEven);
        }
    }

    public static int palindromeLength(String s, int left, int right){
        while(left >= 0 && right < s.length()){
            if(s.charAt(left) == s.charAt(right)){
                left--;
                right++;
            }else{
                break;
            }
        }

        return right - left - 1;
    }
    public static String returnLongestPalindrome(String s, int oddNumber,int start){
        return s.substring(start - oddNumber / 2,start + oddNumber / 2 + 1);
    }

    public static String returnLongestPalindrome(String s,int evenNumber,int start,int startt){
        return s.substring(start - (evenNumber - 2) / 2,startt + (evenNumber - 2) / 2 + 1);
    }
}
import java.util.*;

public class CS203Lab0C {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t;
        t = input.nextInt();

        for (int i = 0; i < t; i++) {
            String chess = "";
            for (int j = 0; j < 13; j++) {
                 String comp = input.next();
                 chess += comp;
            }
            chess += " ";
           List[] buckets = new ArrayList[6];
            buckets[0] = new ArrayList<String>();
            buckets[1] = new ArrayList<String>();
            buckets[2] = new ArrayList<String>();
            buckets[3] = new ArrayList<String>();
            buckets[4] = new ArrayList<String>();
            for (int j = 0; j < chess.length(); j++) {
                if(chess.charAt(j) == 'E' || chess.charAt(j) == 'S'||(chess.charAt(j) == 'W' && !Character.isDigit(chess.charAt(j+1)))||chess.charAt(j) == 'N' ||chess.charAt(j) == 'B' ||chess.charAt(j) == 'F' ||chess.charAt(j) == 'Z'){
                    String a = String.valueOf(chess.charAt(j));
                    buckets[3].add(a);
                }
            }

            for (int j = 0; j < chess.length(); j++) {
                if(chess.charAt(j) == 'W' && Character.isDigit(chess.charAt(j+1))){
                    buckets[0].add(chess.substring(j,j+2));
                }
            }
            for (int j = 0; j < chess.length(); j++) {
                if(chess.charAt(j) == 'T'){
                    buckets[1].add(chess.substring(j,j+2));
                }
            }
            for (int j = 0; j < chess.length(); j++) {
                if(chess.charAt(j) == 'Y'){
                    buckets[2].add(chess.substring(j,j+2));
                }
            }
            Collections.sort(buckets[0]);
            Collections.sort(buckets[1]);
            Collections.sort(buckets[2]);
            if(!buckets[0].isEmpty()){
                buckets[4].add(buckets[0]);
            }
            if(!buckets[1].isEmpty()){
                buckets[4].add(buckets[1]);
            }
            if(!buckets[2].isEmpty()){
                buckets[4].add(buckets[2]);
            }
            int[] value = new int[buckets[3].size()];
            if(!buckets[3].isEmpty()){
                for (int j = 0; j < buckets[3].size(); j++) {
                    if(buckets[3].get(j).equals("E")){
                        value[j] = 1;
                                        }
                    if(buckets[3].get(j).equals("S")){
                        value[j] = 2;
                    }
                    if(buckets[3].get(j).equals("W")){
                        value[j] = 3;
                    }
                    if(buckets[3].get(j).equals("N")){
                        value[j] = 4;
                    }
                    if(buckets[3].get(j).equals("B")){
                        value[j] = 5;
                    }
                    if(buckets[3].get(j).equals("F")){
                        value[j] = 6;
                    }
                    if(buckets[3].get(j).equals("Z")){
                        value[j] = 7;
                    }
                }
            }

            Arrays.sort(value);
            buckets[5] = new ArrayList<String>();
            for (int j = 0; j < value.length; j++) {
                if(value[j] == 1){
                    buckets[5].add("E");
                }
                if(value[j] == 2){
                    buckets[5].add("S");
                }
                if(value[j] == 3){
                    buckets[5].add("W");
                }
                if(value[j] == 4){
                    buckets[5].add("N");
                }
                if(value[j] == 5){
                    buckets[5].add("B");
                }
                if(value[j] == 6){
                    buckets[5].add("F");
                }
                if(value[j] == 7){
                    buckets[5].add("Z");
                }
            }
            if(!buckets[5].isEmpty()) {
                buckets[4].add(buckets[5]);
            }
            String ans = buckets[4].toString().replaceAll("[\\[\\],]","");
            if(i != t - 1){
                System.out.println(ans + " ");
            }else{
                System.out.println(ans);
            }
        }
    }
}

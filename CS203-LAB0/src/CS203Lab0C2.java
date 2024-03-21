import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;
public class CS203Lab0C2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T;
        T = input.nextInt();
        for (int i = 0; i < T; i++) {
            String chess = "";
            ArrayList[] buckets = new ArrayList[34];
            buckets[0] = new ArrayList<String>();
            buckets[1] = new ArrayList<String>();
            buckets[2] = new ArrayList<String>();
            buckets[3] = new ArrayList<String>();
            buckets[4] = new ArrayList<String>();
            buckets[5] = new ArrayList<String>();
            buckets[6] = new ArrayList<String>();
            buckets[7] = new ArrayList<String>();
            buckets[8] = new ArrayList<String>();
            buckets[9] = new ArrayList<String>();
            buckets[10] = new ArrayList<String>();
            buckets[11] = new ArrayList<String>();
            buckets[12] = new ArrayList<String>();
            buckets[13] = new ArrayList<String>();
            buckets[14] = new ArrayList<String>();
            buckets[15] = new ArrayList<String>();
            buckets[16] = new ArrayList<String>();
            buckets[17] = new ArrayList<String>();
            buckets[18] = new ArrayList<String>();
            buckets[19] = new ArrayList<String>();
            buckets[20] = new ArrayList<String>();
            buckets[21] = new ArrayList<String>();
            buckets[22] = new ArrayList<String>();
            buckets[23] = new ArrayList<String>();
            buckets[24] = new ArrayList<String>();
            buckets[25] = new ArrayList<String>();
            buckets[26] = new ArrayList<String>();
            buckets[27] = new ArrayList<String>();
            buckets[28] = new ArrayList<String>();
            buckets[29] = new ArrayList<String>();
            buckets[30] = new ArrayList<String>();
            buckets[31] = new ArrayList<String>();
            buckets[32] = new ArrayList<String>();
            buckets[33] = new ArrayList<String>();


            for (int j = 0; j < 13; j++) {
                String comp = input.next();
                chess += comp;
            }

            for (int j = 0; j < chess.length(); j++) {
                if (chess.charAt(j) == 'W' && Character.isDigit(chess.charAt(j + 1))) {
                    if (chess.charAt(j + 1) == '1') {
                        buckets[0].add("W1");
                    }
                    if (chess.charAt(j + 1) == '2') {
                        buckets[1].add("W2");
                    }
                    if (chess.charAt(j + 1) == '3') {
                        buckets[2].add("W3");
                    }
                    if (chess.charAt(j + 1) == '4') {
                        buckets[3].add("W4");
                    }
                    if (chess.charAt(j + 1) == '5') {
                        buckets[4].add("W5");
                    }
                    if (chess.charAt(j + 1) == '6') {
                        buckets[5].add("W6");
                    }
                    if (chess.charAt(j + 1) == '7') {
                        buckets[6].add("W7");
                    }
                    if (chess.charAt(j + 1) == '8') {
                        buckets[7].add("W8");
                    }
                    if (chess.charAt(j + 1) == '9') {
                        buckets[8].add("W9");
                    }
                }
                if (chess.charAt(j) == 'T' && Character.isDigit(chess.charAt(j + 1))) {
                    if (chess.charAt(j + 1) == '1') {
                        buckets[9].add("T1");
                    }
                    if (chess.charAt(j + 1) == '2') {
                        buckets[10].add("T2");
                    }
                    if (chess.charAt(j + 1) == '3') {
                        buckets[11].add("T3");
                    }
                    if (chess.charAt(j + 1) == '4') {
                        buckets[12].add("T4");
                    }
                    if (chess.charAt(j + 1) == '5') {
                        buckets[13].add("T5");
                    }
                    if (chess.charAt(j + 1) == '6') {
                        buckets[14].add("T6");
                    }
                    if (chess.charAt(j + 1) == '7') {
                        buckets[15].add("T7");
                    }
                    if (chess.charAt(j + 1) == '8') {
                        buckets[16].add("T8");
                    }
                    if (chess.charAt(j + 1) == '9') {
                        buckets[17].add("T9");
                    }
                }
                if (chess.charAt(j) == 'Y' && Character.isDigit(chess.charAt(j + 1))) {
                    if (chess.charAt(j + 1) == '1') {
                        buckets[18].add("Y1");
                    }
                    if (chess.charAt(j + 1) == '2') {
                        buckets[19].add("Y2");
                    }
                    if (chess.charAt(j + 1) == '3') {
                        buckets[20].add("Y3");
                    }
                    if (chess.charAt(j + 1) == '4') {
                        buckets[21].add("Y4");
                    }
                    if (chess.charAt(j + 1) == '5') {
                        buckets[22].add("Y5");
                    }
                    if (chess.charAt(j + 1) == '6') {
                        buckets[23].add("Y6");
                    }
                    if (chess.charAt(j + 1) == '7') {
                        buckets[24].add("Y7");
                    }
                    if (chess.charAt(j + 1) == '8') {
                        buckets[25].add("Y8");
                    }
                    if (chess.charAt(j + 1) == '9') {
                        buckets[26].add("Y9");
                    }
                }
                if (chess.charAt(j) == 'E') {
                    buckets[27].add("E");
                }
                if (chess.charAt(j) == 'S') {
                    buckets[28].add("S");
                }
                if (chess.charAt(j) == 'W' && !Character.isDigit(chess.charAt(j + 1)) ) {
                    buckets[29].add("W");
                }
                if (chess.charAt(j) == 'N') {
                    buckets[30].add("N");
                }
                if (chess.charAt(j) == 'B') {
                    buckets[31].add("B");
                }
                if (chess.charAt(j) == 'F') {
                    buckets[32].add("F");
                }
                if (chess.charAt(j) == 'Z') {
                    buckets[33].add("Z");
                }

            }
            for (int j = 0; j < buckets.length; j++) {
                if(!buckets[j].isEmpty()){
                    System.out.print(buckets[j].toString().replaceAll("[\\[\\],]","") + " ");
                }
            }
        }


    }
}

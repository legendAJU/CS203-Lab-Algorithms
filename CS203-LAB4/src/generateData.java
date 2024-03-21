import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author Will
 * @create 2023-10-17 10:23
 */
public class generateData {

    public static void main(String[] args) {

        FileWriter fw = null;
        try {
            fw = new FileWriter("test1.txt");
            Random random = new Random();
            int n = 1;
            int m = 1;
            int p = 1;
            int k = 1;
            fw.write(n + " " + m + " " + p + " "+ k);



        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {

            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}

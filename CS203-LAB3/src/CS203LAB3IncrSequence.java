import java.io.*;
import java.util.StringTokenizer;

public class CS203LAB3IncrSequence {
    public static void main(String[] args) {
        QReader5 in = new QReader5();
        QWriter5 out = new QWriter5();
        int T;
        T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            item tail = new item(100005);
            item head = new item(0);
            item[] a = new item[n + 2];
            item[] b = new item[n + 2];
            item[] A = new item[n + 10];
            item[] B = new item[n + 10];
            a[n + 1] = tail;
            b[n + 1] = head;
            a[0] = head;
            b[0] = tail;
            for (int j = 1; j <= n ; j++) {
                int prop = in.nextInt();
                item temp = new item(prop);
                a[j] = temp;
                b[j] = temp;
            }
            mergeSort(b,1,n,A,B);
            a[0].next1 = a[1];
            for (int j = 1; j <= n; j++) {
                a[j].next1 = a[j + 1];
                a[j].last = a[j - 1];
                b[j].next2 = b[j + 1];
            }
            int[] needToDelate = new int[n + 10];
            for (int j = 1; j < n + 1 ; j++) {
                needToDelate[j] = -1;
            }
            needToDelate[0] = -1;
            needToDelate[n + 1] = -1;
            int design = 1;
            for (int j = 1; j < n; j++) {
                if(a[j].next2.prop == a[j].prop){
                    if(a[j].next1.prop < a[j].next2.prop){
                        needToDelate[j] = design;
                        needToDelate[j + 1] =design;
                        design++;
                    }
                }else{
                    if(a[j].next1.prop <= a[j].next2.prop){
                        needToDelate[j] = design;
                        needToDelate[j + 1] =design;
                        design++;
                    }
                }
            }
            needToDelate[0] = -1;
            needToDelate[n + 1] = -1;

            int[] needToDelate1 = new int[3 * n];
            int[] needToDelate2 = new int[3 * n];
            int count1 = 0;
            int count2 = 0;
            for (int j = 0; j < needToDelate1.length; j++) {
                needToDelate1[j] = -1;
                needToDelate2[j] = -1;
            }
            for (int j = 0; j <= n; j++) {
                if(needToDelate[j] == -1 && needToDelate[j + 1] == 1){
                    needToDelate1[count1] = j;
                    count1++;
                }
            }
            for (int j = 1; j <= n ; j++) {
                if(needToDelate[j] == 1 && needToDelate[j + 1] == -1){
                    needToDelate2[count2] = j + 1;
                    count2++;
                }
            }
            //创建nums链表
            nums firstNum = null;
            nums currentNum = null;
            nums[] arrayOfNums = new nums[n + 2];
            nums frontQiang = new nums(0);
            nums backQiang = new nums(100005);
            arrayOfNums[0] = frontQiang;
            arrayOfNums[n + 1] = backQiang;
            int counting = 0;
            for (int j = 0; j <= n ; j++) {
                if(needToDelate[j] != 1){
                    counting++;
                    if(counting == 1){
                        nums temp = new nums(a[j].prop);
                        firstNum = temp;
                        currentNum = temp;
                        arrayOfNums[j] = temp;
                        arrayOfNums[0].next = temp;
                        temp.last = arrayOfNums[0];
                        currentNum.next = temp;
                        currentNum.next.last = currentNum;
                    }else{
                        nums temp = new nums(a[j].prop);
                        arrayOfNums[j] = temp;
                        currentNum.next = temp;
                        currentNum.next.last = currentNum;
                        currentNum = currentNum.next;
                    }
                }
            }
            if(arrayOfNums[0].next == null){
                out.print("\n");
                continue;
            }else{
                currentNum.next = arrayOfNums[n + 1];
                currentNum.next.last = currentNum;
                //对于每个nums节点，创造arrow和arrowbundle
                int index = 0;
                currentNum = firstNum;
                arrowBundle firBundle = null;
                arrowBundle currentBundle = null;
                int countOfBundle = 0;
                while(true){
                    if(needToDelate1[index] == -1){
                        break;
                    }else{
                        arrow frontArrow = new arrow(arrayOfNums[needToDelate1[index]]);
                        arrow backArrow = new arrow(arrayOfNums[needToDelate2[index]]);
                        arrowBundle temp = new arrowBundle(frontArrow,backArrow);
                        countOfBundle++;
                        if(firBundle == null){
                            firBundle = temp;
                            currentBundle = temp;
                        }else{
                            currentBundle.nextBundle = temp;
                            currentBundle = currentBundle.nextBundle;
                        }
                    }
                    index++;
                    if(needToDelate1[index] == -1){
                        break;
                    }
                }
                arrowBundle march = firBundle;
                if(march == null){
                    //直接输出arrayOfNums
                    for (int j = 1; j < arrayOfNums.length; j++) {
                        if(arrayOfNums[j].prop != 0 && arrayOfNums[j].prop != 100005){
                            out.print(arrayOfNums[j].prop + " ");
                        }
                    }
                    out.print("\n");
                    continue;
                }else{
                    int exit = 0;
                    while(true){
                        if(exit == countOfBundle){
                            break;
                        }else{
                            march = firBundle;
                            while(march != null){
                                if(march.done){
                                    march = march.nextBundle;
                                }else{
                                    if(march.frontArrow.pointTo.prop > march.backArrow.pointTo.prop){
                                        march.frontArrow.pointTo.next.last = march.frontArrow.pointTo.last;
                                        march.frontArrow.pointTo.last.next = march.frontArrow.pointTo.next;
                                        march.backArrow.pointTo.next.last = march.backArrow.pointTo.last;
                                        march.backArrow.pointTo.last.next = march.backArrow.pointTo.next;
                                        march.frontArrow.pointTo = march.frontArrow.pointTo.last;
                                        march.backArrow.pointTo = march.backArrow.pointTo.next;
                                    }else{
                                        march.done = true;
                                        exit++;
                                    }
                                    march = march.nextBundle;
                                }
                            }
                        }

                    }
                    //System.out.println(" ");
                    nums finalNums = arrayOfNums[0].next;
                    while(true){
                        if(finalNums.prop != 100005 && finalNums.prop != 0){
                            out.print(finalNums.prop + " ");
                            finalNums = finalNums.next;
                        }else{
                            break;
                        }
                    }
                }

            }


            if(i != T - 1){
                out.print("\n");
            }
            if(i == T - 1){
                out.close();
            }

        }
    }

    public static void merge(item[] arr,int l,int mid,int r,item[] A,item[] B){
        int n = mid - l + 1;
        int m = r - mid;

        for (int i = 0; i < n; i++) {
            A[i] = arr[i + l];
        }
        for (int i = 0; i < m; i++) {
            B[i] = arr[1 + mid + i];
        }
        int indexOfA = 0;
        int indexOfB = 0;
        boolean AisDone = false;
        boolean BisDone = false;
        int recordOfj = 0;
        for (int j = 0; j < n + m; j++) {
            if(A[indexOfA].prop < B[indexOfB].prop){
                arr[j + l] = B[indexOfB];
                indexOfB++;
            }
            else{
                arr[j + l] = A[indexOfA];
                indexOfA++;
            }
            if(indexOfA == n ){
                AisDone = true;
                recordOfj = j;
                break;
            }
            if(indexOfB == m ){
                BisDone = true;
                recordOfj = j;
                break;
            }
        }
        if(AisDone){
            for (int j = recordOfj + 1; j < m + n ; j++) {
                arr[j + l] = B[indexOfB];
                indexOfB ++;
            }
        }
        if(BisDone){
            for (int j = recordOfj + 1; j < m + n ; j++) {
                arr[j + l] = A[indexOfA];
                indexOfA ++;
            }
        }
    }
    public static void mergeSort(item[] arr,int l,int r,item[] A,item[] B){
        if(l < r){
            int mid = (l + r) / 2;
            mergeSort(arr,l,mid,A,B);
            mergeSort(arr,mid + 1,r,A,B);
            merge(arr,l,mid,r,A,B);
        }
    }
}

class item{
    int prop;

    item next1 = null;
    item next2 = null;

    item last = null;

    public item(int prop){
        this.prop = prop;
    }
}

class nums{

    int prop;

    int needToDelate;
    nums next = null;

    nums last  = null;

    public nums(int prop){
        this.prop = prop;
    }
}

class arrow{
    arrow next;

    arrow last;

    nums pointTo;

    public arrow(nums pointTo){
        this.pointTo = pointTo;
    }
}

class arrowBundle{
    arrow frontArrow;

    arrow backArrow;

    arrowBundle nextBundle;

    boolean done;
public arrowBundle(arrow frontArrow,arrow backArrow){
    this.frontArrow = frontArrow;
    this.backArrow = backArrow;
}
}

class QReader5 {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter5 implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}

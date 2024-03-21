import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CS203LAB3Black {
    public static void main(String[] args) {
        int T;
        QReader1 in = new QReader1();
        QWriter1 out = new QWriter1();
        Scanner input = new Scanner(System.in);
        T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int N,M;
            N = in.nextInt();
            M = in.nextInt();
            People[] locat = new People[N + 2];
            People firstPeople = null;
            People currentPeople = null;
            for (int j = 0; j < N; j++) {
                int id = in.nextInt();
                People a = new People(id);
                locat[id] = a;
                if(firstPeople == null){
                    firstPeople = a;
                    currentPeople = a;
                }
                else{
                    currentPeople.next = a;
                    currentPeople = currentPeople.next;
                }
            }
            People lastPeople = firstPeople;
            for (int j = 0; j < N; j++) {
                if(lastPeople.next != null){
                    lastPeople.next.last = lastPeople;
                    lastPeople = lastPeople.next;
                }
            }
            People front = new People(-1);
            People back = new People(-1);
            locat[N] = front;
            locat[N + 1] = back;
            front.next = firstPeople;
            back.last = currentPeople;
            firstPeople.last =front;
            currentPeople.next = back;

            for (int j = 0; j < M; j++) {
               int x1 = in.nextInt();
               int y1 = in.nextInt();
               int x2 = in.nextInt();
               int y2 = in.nextInt();
               if(locat[y1].next != locat[x2]){
                   //y相关
                   People recordOfnext = locat[y2].next;

                   locat[y1].next.last = locat[y2];
                   locat[y2].next.last = locat[y1];
                   locat[y2].next = locat[y1].next;
                   locat[y1].next = recordOfnext;
                   //x相关
                   People recordOflast1 = locat[x2].last;
                    locat[x1].last.next = locat[x2];
                   locat[x2].last.next = locat[x1];
                    locat[x2].last = locat[x1].last;
                    locat[x1].last = recordOflast1;
               }
               else{
                   People temp = new People(-1);
                   locat[y1].next = temp;
                   temp.last = locat[y1];
                   temp.next = locat[x2];
                   locat[x2].last = temp;

                   //y相关
                   People recordOfnext = locat[y2].next;

                   locat[y1].next.last = locat[y2];
                   locat[y2].next.last = locat[y1];
                   locat[y2].next = locat[y1].next;
                   locat[y1].next = recordOfnext;
                   //x相关
                   People recordOflast1 = locat[x2].last;
                   locat[x1].last.next = locat[x2];
                   locat[x2].last.next = locat[x1];
                   locat[x2].last = locat[x1].last;
                   locat[x1].last = recordOflast1;
               }
            }
            int count = 0;

            if(T > 1){
                while(locat[N] != null){
                    if(locat[N].id != -1){
                        out.print(locat[N].id);
                        count++;
                        if(count < N){
                            out.print(" ");
                        }
                        locat[N] = locat[N].next;
                    }
                    else{
                        locat[N] = locat[N].next;
                    }
                }
                if(i < T - 1){
                    out.print("\n");
                }
            }else{
                while(locat[N] != null){
                    if(locat[N].id != -1){
                        out.print(locat[N].id);
                        count++;
                        if(count < N){
                            out.print(" ");
                        }
                        locat[N] = locat[N].next;
                    }
                    else{
                        locat[N] = locat[N].next;
                    }
                }
                out.close();
                break;
            }
            if(i == T - 1){
                out.close();
            }
        }

    }

}
class People{
    int id;
    People next;

    People last;

    public People(int id){
        this.id = id;
    }
}

class QReader1 {
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

class QWriter1 implements Closeable {
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
/*
class PeopleList{

    int List_Length = 0;

    People firstPeople = null;

    People currentPeople = null;

    public void addPeople(int id){
        People newGuy = new People(id);
        if(List_Length == 0){
            firstPeople = newGuy;
            currentPeople = newGuy;
            List_Length ++;
        }
        else{
            currentPeople.next = newGuy;
            currentPeople = newGuy;
            List_Length++;
        }
    }


    public PeopleList combine(PeopleList list1,PeopleList list2,PeopleList list3,PeopleList list4,PeopleList list5){
        list1.currentPeople.next = list4.firstPeople;
        list4.currentPeople.next = list3.firstPeople;
        list3.currentPeople.next = list2.firstPeople;
        list2.currentPeople.next = list5.firstPeople;
        PeopleList ans = new PeopleList();
        People temp = list1.firstPeople;
        while(temp != null){
            ans.addPeople(temp.id);
            temp = temp.next;
        }
        return ans;
    }

    public void cutAndCombine(int x1,int y1,int x2,int y2,PeopleList list,People[] locat){
        People temp = list.firstPeople;
        PeopleList list1 = new PeopleList();
        PeopleList list2 = new PeopleList();
        PeopleList list3 = new PeopleList();
        PeopleList list4 = new PeopleList();
        PeopleList list5 = new PeopleList();
        //切出list1

        //切出list2

        //切出list4

        //切出list5

    }

    public void display(){
        int arrowOfList = 0;
        People arrowOfNode = firstPeople;
        for (int i = 0; i < List_Length; i++) {
            if(arrowOfNode != null){
                int variable1 = arrowOfNode.id;
                if(variable1 >= 0){
                    System.out.print(variable1 + " ");
                }
                arrowOfNode = arrowOfNode.next;
            }
        }
    }
}

 */
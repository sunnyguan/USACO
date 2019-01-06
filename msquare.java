/*
ID: qinying1        
LANG: JAVA
TASK: msquare
 */
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class msquare
{

    public static void main( String[] args ) throws IOException
    {
        msquare a = new msquare();
        a.doWork();
    }
        
    public void doWork() throws IOException
    {
        long timer = System.currentTimeMillis();
        Scanner br = new Scanner( new FileReader( "msquare.in" ) );

        String[] temp = br.nextLine().split(" ");
        br.close();
        String target = "";
        String begin = "";
        for ( int i = 0; i < temp.length; i++ )
        {
            target += temp[i];
            begin += i + 1;
        }
        
        
        boolean[] beenTo = new boolean[40320];
        Queue<Node> priorityQ = new LinkedList<Node>();
        priorityQ.add( new Node(begin, "") );
        
        Node success = null;
        
        while(success == null) {
            
            Node currNode = priorityQ.peek();
            String curr = currNode.content;
            String path = currNode.path;
            
            int v = encode(curr);
            beenTo[v] = true;
            if(target.equals( curr )) {
                success = currNode;
                priorityQ.clear();
                break;
            }
            
            ArrayList<Node> s = new ArrayList<Node>();
            if(!path.endsWith( "A" )) {
                String cont = A(curr);
                int ev = encode(cont);
                if(!beenTo[ev]) {
                    Node aa = new Node(cont, path + "A");
                    s.add( aa );
                    beenTo[ev] = true;
                }
            }
            
            
            String cont = B(curr);
            int ev = encode(cont);
            if(!beenTo[ev]) {
                Node aa = new Node(cont, path + "B");
                s.add( aa );
                beenTo[ev] = true;
            }
            
            cont = C(curr);
            ev = encode(cont);
            if(!beenTo[ev]) {
                Node aa = new Node(cont, path + "C");
                s.add( aa );
                beenTo[ev] = true;
            }
            
            priorityQ.addAll( s );
            priorityQ.remove();
        }
        
        System.out.println(success.path.length());
        System.out.println(success.path);
        PrintWriter out = new PrintWriter( "msquare.out" );
        out.println(success.path.length());
        out.println(success.path);
        out.close();
        System.out.println(System.currentTimeMillis() - timer);
    }
    
    static int[] prod = new int[]{5040, 720, 120, 24, 6, 2, 1, 1};
    static int encode(String input) {
        int[] num = new int[8];
        char[] ch = input.toCharArray();
        for(int i = 0; i < 8; i++) {
            num[i] = Integer.parseInt( String.valueOf( ch[i] ) );
        }
        int u, v, mask = 0;
        ArrayList<Integer> t = new ArrayList<Integer>();
        for(int i = 1; i <= 8; i++) t.add( i );
        for(u = 0; u < 8; u++)  {
            for(v = 0; t.get( v ) != num[u]; v++);
            mask += v * prod[u];
            t.remove( v );
        }
        return mask;
    }
    
    class Node implements Comparable{
        public String content;
        public String path;
        public Node( String content, String path )
        {
            super();
            this.content = content;
            this.path = path;
        }
        @Override
        public int compareTo( Object arg0 )
        {
            return path.compareTo(((Node) arg0).path);
        }
        @Override
        public String toString()
        {
            return "Node [content=" + content + ", path=" + path + "]";
        }
        
    }
    
    public void swap(int i1, int i2, char[] sqr) {
        char temp = sqr[i1];
        sqr[i1] = sqr[i2];
        sqr[i2] = temp;
    }
    
    public String A(String input) {
        char[] in = input.toCharArray();
        int begin = 0;
        int end = in.length - 1;
        char temp;
        while ( end > begin )
        {
            temp = in[begin];
            in[begin] = in[end];
            in[end] = temp;
            end--;
            begin++;
        }
        return new String( in );
    }
    
    public String B(String input) {
        return input.substring(3, 4) + input.substring(0, 3) + input.substring(5) + input.substring(4, 5);
    }
    
    public String C(String input) {
        char[] sqr = input.toCharArray();
        swap(1, 2, sqr);
        swap(5, 6, sqr);
        swap(1, 5, sqr);
        return toBinString(sqr);
    }
    
    private String toBinString( char[] a )
    {
        String res = "";
        for ( int i = 0; i < a.length; i++ )
        {
            res += a[i];
        }
        return res;
    }
    
}
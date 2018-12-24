/*
ID: qinying1        
LANG: JAVA
TASK: contact
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;


public class contact
{

    public static void main( String[] args ) throws IOException
    {
        contact a = new contact();
        a.doWork();
    }

    public void doWork() throws IOException
    {
        BufferedReader f = new BufferedReader( new FileReader( "contact.in" ) );
        PrintWriter pw = new PrintWriter( "contact.out" );

        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        String[] line = f.readLine().split( " " );
        int bottom = Integer.parseInt( line[0] );
        int top = Integer.parseInt( line[1] );
        int all = Integer.parseInt( line[2] );
        
        StringBuilder sb = new StringBuilder();
        String line1 = f.readLine();
        while (line1 != null) {
            sb.append(line1);
            line1 = f.readLine();
        }
        char[] total = sb.toString().toCharArray();
        StringBuffer prevAll = new StringBuffer();
        char s;
        
        long time = System.currentTimeMillis();
        int couwnt = 0;
        for(int i = 0; i < total.length; i++) {
            s = total[i];
            if(prevAll.length() >= top) {
                prevAll.deleteCharAt( 0 );
                prevAll.append( s );
            } else {
                prevAll.append( s );
            }
            int upper = top > prevAll.length() ? prevAll.length() : top;
            for(int subIndex = bottom; subIndex <= upper; subIndex++) {
                String sub = prevAll.subSequence( upper - subIndex, upper ).toString();
                if(counts.containsKey( sub )) {
                    counts.put( sub, counts.get( sub ) + 1 );
                } else {
                    counts.put( sub, 1 );
                }
            }
            couwnt++;
        }
        System.out.println( couwnt );
        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();
        
        TreeMap<Integer, TreeSet<String>> prints = new TreeMap<Integer, TreeSet<String>>(Collections.reverseOrder());
        
        for (Entry<String, Integer> entry : counts.entrySet())
        {
            TreeSet<String> toAdd = prints.get( entry.getValue() );
            if(toAdd == null) {
                prints.put( entry.getValue(), new TreeSet<String>(new MyComp()) );
            }
            prints.get( entry.getValue() ).add( entry.getKey() );
        }
        
        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();
        
        int j = 0;
        for (Entry<Integer, TreeSet<String>> entry : prints.entrySet()) {
            if(j >= all) break;
            pw.println( entry.getKey() );
            String printed = "";
            int sepBy6 = 0;
            for(String ss : entry.getValue()) {
                printed += ss;
                sepBy6++;
                if(sepBy6 % 6 == 0) {
                    printed += "\n";
                } else {
                    printed += " ";
                }
            }
            pw.println(printed.trim());
            j++;
        }
        pw.close();
        
        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();
    }
    
    class MyComp implements Comparator<String>{
        
        @Override
        public int compare(String str1, String str2) {
            if(str1.length() > str2.length()) {
                return 1;
            } else if (str1.length() < str2.length() ) {
                return -1;
            } else {
                return str1.compareTo( str2 );
            }
        }
         
    }

}

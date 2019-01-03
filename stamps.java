/*
ID: qinying1        
LANG: JAVA
TASK: stamps
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class stamps
{

    public static void main( String[] args ) throws IOException
    {
        stamps a = new stamps();
        a.doWork();
    }

    public void doWork() throws IOException
    {
        BufferedReader f = new BufferedReader( new FileReader( "stamps.in" ) );
        
        StringTokenizer st = new StringTokenizer( f.readLine() );
        int maxStamps = Integer.parseInt( st.nextToken() );
        int numStamps = Integer.parseInt( st.nextToken() );
        
        ArrayList<Integer> nums = new ArrayList<Integer>(numStamps);
        String str = f.readLine();
        int index = 0;
        int max = 0;
        while(str != null) {
            st = new StringTokenizer( str );
            while(st.hasMoreTokens()) {
                int val = Integer.parseInt( st.nextToken() );
                if(val > max) max = val;
                nums.add( val );
            }
            str = f.readLine();
        }
        
        int[] dpArr = new int[maxStamps * max + 2];
        for (int i = 1; i < dpArr.length;i++) {
            dpArr[i] = maxStamps + 1;
        }
        for(int i = 1; i < dpArr.length; i++) {
            for(int stamp : nums) {
                if(i < stamp) continue;
                dpArr[i] = Math.min( dpArr[i], dpArr[i - stamp] + 1 );
            }
            if (dpArr[i] > maxStamps) {
                index = i;
                break;
            }
        }
        
        PrintWriter out = new PrintWriter( "stamps.out" );
        out.println(index - 1);
        out.close();
    }

}

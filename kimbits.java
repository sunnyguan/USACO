/*
ID: qinying1        
LANG: JAVA
TASK: kimbits
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class kimbits
{

    public static void main( String[] args ) throws IOException
    {
        kimbits a = new kimbits();
        a.doWork();
    }
    public int length = 0;
    public int maxOne = 0;
    public long index = 0;
    public int count = 0;
    
    public void doWork() throws IOException
    {
        long time = System.currentTimeMillis();
        Scanner br = new Scanner( new FileReader( "kimbits.in" ) );

        length = br.nextInt();
        maxOne = br.nextInt();
        index = ( br.nextLong() );

        
        int[][] f = new int[length + 1][length + 1];
        for ( int i = 0; i <= length; i++ )
            f[i][0] = 1;
        for ( int i = 1; i <= length; i++ )
            for ( int j = 1; j <= length; j++ )
                f[i][j] = f[i - 1][j] + f[i - 1][j - 1];
        
        String outcome = "";
        for ( int i = length; i >= 1; i-- )
        {
            int bitIndex = 0;
            for ( int j = 0; j <= maxOne; j++ )
                bitIndex += f[i - 1][j];
            if ( index <= bitIndex )  
                outcome += "0";
            else
            {
                outcome += "1";
                maxOne--;
                index -= bitIndex;
            }
        }
        
        PrintWriter out = new PrintWriter( "kimbits.out" );
        out.println(outcome);
        out.close();
        System.out.println(outcome);
        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();
    }
    /*public boolean keepGoing = true;
    public String done = "";
    public void recurIncrement(String s, int numOnes) {
        if (s.length() == length) {
            if (numOnes <= maxOne && keepGoing)
                count++;
            if (count == index && keepGoing) {
                done = s;
                keepGoing = false;
            }
            return;
        }
        if (keepGoing == false)
            return;
        recurIncrement(s + "0", numOnes);
        recurIncrement(s + "1", numOnes + 1);
    }*/
    
    /*public int increment(byte[] A, int length, int maxOne, long index, int numOnes) {
        boolean carry = true;
        for (int i = (A.length - 1); i >= 0; i--) {
            if (carry) {
                if (A[i] == 0) {
                    A[i] = 1;
                    numOnes++;
                    carry = false;
                }
                else {
                    numOnes--;
                    A[i] = 0;
                    carry = true;
                }
            } else {
                return numOnes;
            }
        }
        return numOnes;
    }

    private static String toBinString (byte [] a) {
          String res = "";
          for (int i = 0; i < a. length; i++) {
              res += (a [i] == 0 ? "0": "1") ;
          }
          return res;
    }
    
    public int numOnes(byte[] arr) {
        int count = 0;
        for(byte c : arr) {
            if(c == 1) {
                count++;
            }
        }
        return count;
    }*/
    
}

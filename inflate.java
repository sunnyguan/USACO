/*
ID: qinying1        
LANG: JAVA
TASK: inflate
 */
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class inflate
{

    public static void main( String[] args ) throws IOException
    {
        inflate a = new inflate();
        a.doWork();
    }

    public void doWork() throws IOException
    {
        int[] cost;
        Scanner br = new Scanner( new FileReader( "inflate.in" ) );
        BufferedWriter out = new BufferedWriter(
            new FileWriter( "inflate.out" ) );

        int M = br.nextInt();
        int N = br.nextInt();
        cost = new int[M + 1];
        for ( int i = 0; i < N; i++ )
        {
            int a = br.nextInt();
            int b = br.nextInt();
            for ( int j = b; j <= M; j++ )
            {
                cost[j] = Math.max( cost[j], cost[j - b] + a );
            }
        }

        out.write( Integer.toString( cost[M] ) + "\n" );
        System.out.println(cost[M]);
        out.close();
    }

}

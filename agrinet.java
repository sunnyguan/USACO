/*
ID: qinying1        
LANG: JAVA
TASK: agrinet
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class agrinet
{
       

    public static void main( String[] args ) throws FileNotFoundException
    {
        agrinet a = new agrinet();
        a.doWork();
    }

    public void doWork() throws FileNotFoundException
    {
        Scanner s = new Scanner( new File( "agrinet.in" ) );
        int numLines = s.nextInt();
        int[][] allDist = new int[numLines][numLines];
        int[][] dist = new int[numLines][2];
        
        for(int i = 0; i < numLines; i++) {
            for(int j = 0; j < numLines; j++) {
                allDist[i][j] = s.nextInt();
            }
        }
        dist[0][0] = Integer.MAX_VALUE;
        dist[0][1] = 1;
        for(int j = 0; j < numLines; j++) {
            dist[j][0] = allDist[0][j];
        }
        
        int treeSize = 1;
        while(treeSize < numLines) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            for(int i = 0; i < numLines; i++) {
                if(dist[i][0] < min && dist[i][1] == 0) {
                    min = dist[i][0];
                    index = i;
                }
            }
            
            dist[index][1] = 1;
            for(int i = 0; i < numLines; i++) {
                if(dist[i][1] == 0 && allDist[i][index] < dist[i][0]) {
                    dist[i][0] = allDist[i][index];
                }
            }
            treeSize++;
        }
        
        int sum = 0;
        for(int i = 0; i < numLines; i++) {
            sum += dist[i][0];
        }
        PrintWriter pw = new PrintWriter("agrinet.out");
        pw.println(sum);
        pw.close();
        
        s.close();
    }

}

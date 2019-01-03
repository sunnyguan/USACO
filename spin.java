/*
ID: qinying1        
LANG: JAVA
TASK: spin
 */
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class spin
{

    public static void main( String[] args ) throws IOException
    {
        spin a = new spin();
        a.doWork();
    }
    int[] allDegrees = new int[360];
    int[][] wheels = new int[5][12];
    public void doWork() throws IOException
    {
        long timer = System.currentTimeMillis();
        Scanner br = new Scanner( new FileReader( "spin.in" ) );

        int[] roundNum = new int[360];
        
        
        String[] line;
        for(int j = 0; j < 5; j++) {
            int next = 0;
            line = br.nextLine().split( " " );
            for(String s : line) {
                wheels[j][next++] = Integer.parseInt( s );
            }
        }
        
        boolean done = false;
        int goodTime = -1;
        for(int time = 0; time < 360 && !done; time++) {
            for(int ind = 0; ind < wheels.length; ind++) {
                dealWithWheel(ind);
            }
            for(int i : allDegrees) {
                if(i == 5) {
                    done = true;
                    goodTime = time;
                    break;
                }
            }
            if(done) break;
            allDegrees = new int[360];
        }
        
        PrintWriter out = new PrintWriter( "spin.out" );
        String output = goodTime == -1 ? "none" : String.valueOf( goodTime );
        System.out.println(output);
       out.println(output);
        
        out.close();
        System.out.println(System.currentTimeMillis() - timer);
    }
    
    public void dealWithWheel(int index) {
        int speed = wheels[index][0];
        int numOfWedges = wheels[index][1];
        int nextC = 2;
        for(int i = 0; i < numOfWedges; i++) {
            int begin = wheels[index][nextC++];
            int extent = wheels[index][nextC++];
            for(int angle = begin; angle <= begin + extent; angle++) {
                allDegrees[angle % 360]++;
            }
            wheels[index][i*2 + 2] += speed;
            wheels[index][i*2 + 3] %= 360;
        }
    }
    
}

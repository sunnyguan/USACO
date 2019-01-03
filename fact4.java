/*
ID: qinying1        
LANG: JAVA
TASK: fact4
 */
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class fact4
{

    public static void main( String[] args ) throws IOException
    {
        fact4 a = new fact4();
        a.doWork();
    }

    public void doWork() throws IOException
    {
        Scanner br = new Scanner( new FileReader( "fact4.in" ) );
        PrintWriter out = new PrintWriter( "fact4.out" );

        int number = br.nextInt();
        int lastDigit = fact(number);
        out.println(lastDigit % 10);
        System.out.println(lastDigit % 10);
        out.close();
    }
    
    public int fact(int n) {
        if(n == 1 || n == 0) {
            return 1;
        } else if (n == 2){
            return 2;
        } else if (n == 3){ 
            return 6; 
        } else if (n == 4){
            return 4; 
        }else {
            return (fact(n / 5) * 
                            (int) (Math.pow( 2, n / 5 ) % 10) * 
                            fact(n % 5)) % 10;
        }
    }

}

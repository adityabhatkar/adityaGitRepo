import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class UtopianTree {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int c = in.nextInt();

            int n=0;
            int h=0;
            if(c%2==0){
                n=c/2+1;
                h=(int)Math.pow(2, n)-1; 
                
            }
            else{                
                n=(c+1)/2+1;
                h=(int)Math.pow(2, n)-2;       
                
            }
            System.out.println(h);
            
        }
    }
}

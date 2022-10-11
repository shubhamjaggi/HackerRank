import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'dayOfProgrammer' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER year as parameter.
     */

    public static String dayOfProgrammer(int year) {
    // Write your code here
        String nonLeapPrefix = "13.09.";
        String leapPrefix = "12.09.";
        String transitionVal = "26.09.1918";
        
        if (year<1918){
            if(year%4==0){
                return leapPrefix+year;
            }
            else{
                return nonLeapPrefix+year;
            }            
        }
        else if(year>1918){
            if(((year%4==0) && (year%100!=0)) || (year%400==0)){
                return leapPrefix+year;
            }
            else{
                return nonLeapPrefix+year;
            }
        }
        else{
            return transitionVal;
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.dayOfProgrammer(year);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

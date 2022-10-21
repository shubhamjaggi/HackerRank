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
     * Complete the 'encryption' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String encryption(String s) {
    // Write your code here
        String newString=s.replaceAll("\\s","");
        double lenSqrt = Math.pow(newString.length(),0.5);
        int lowerBound = (int)Math.floor(lenSqrt);
        int upperBound = (int)Math.ceil(lenSqrt);
        int numRows, numCols;
        if(lowerBound==upperBound){
            numRows=numCols=lowerBound;
        }else if(lowerBound*upperBound>=newString.length()){
            numRows=lowerBound;
            numCols=upperBound;
        }
        else{
            numRows=numCols=upperBound;
        }
        char[] resCharArray= new char[newString.length()+numCols];
        int resCharCount=0;
        for(int i=0; i<numCols; i++){
            for(int j=0; j<numRows; j++){
                int index=(numCols*j)+i;
                if (index<=newString.length()-1){
                    resCharArray[resCharCount++] = newString.charAt(index);
                }
            }
            resCharArray[resCharCount++]=' ';
        }
        return String.valueOf(resCharArray).trim();
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.encryption(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

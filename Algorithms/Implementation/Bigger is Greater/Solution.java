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
     * Complete the 'biggerIsGreater' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING w as parameter.
     */

    public static String biggerIsGreater(String w) {
    // Write your code here
        char[] chars = w.toCharArray();        
        int prev = chars[chars.length-1];
        boolean isResultFound = false;
        for(int i = chars.length-2; i>=0; i--){
            int curr = chars[i];
            if(prev>curr){
                Arrays.sort(chars, i+1, chars.length);
                int swapIndex = i;
                for(int j=i+1; j<chars.length; j++){
                   if(chars[swapIndex]<chars[j]){
                       swapIndex = j;
                       break;
                   }
                }
                char temp = chars[swapIndex];
                chars[swapIndex] = chars[i];
                chars[i] = temp;
                isResultFound = true;
                break;
            }
            prev = curr;
        }        
        return isResultFound?String.valueOf(chars):"no answer";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, T).forEach(TItr -> {
            try {
                String w = bufferedReader.readLine();

                String result = Result.biggerIsGreater(w);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}

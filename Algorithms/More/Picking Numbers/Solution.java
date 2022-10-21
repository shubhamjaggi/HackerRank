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
     * Complete the 'pickingNumbers' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static int pickingNumbers(List<Integer> a) {
    // Write your code here
        List<Integer> counts = new ArrayList<>();
        int inputSize = a.size();
        IntStream.rangeClosed(0, inputSize-2).forEach(i -> {
            int[] upperLowerCounts = {1, 1};
            IntStream.rangeClosed(i+1, inputSize-1).forEach(j -> {
                if((a.get(j)==(a.get(i)+1)) || (a.get(j)==a.get(i))){
                    upperLowerCounts[0]++;
                }                
                if((a.get(j)==(a.get(i)-1)) || (a.get(j)==a.get(i))){
                    upperLowerCounts[1]++;
                }
            });            
            counts.add(upperLowerCounts[0]);
            counts.add(upperLowerCounts[1]);
        });
        
        return counts.stream().mapToInt(x -> x).max().getAsInt();
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.pickingNumbers(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

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
     * Complete the 'nonDivisibleSubset' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY s
     */

    public static int nonDivisibleSubset(int k, List<Integer> s) {
    // Write your code here
        Map<Integer, Long> remFrequencies = s.stream().map(a -> a%k).collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));        
        IntStream.rangeClosed(0, k-1).filter(a -> !remFrequencies.containsKey(a)).forEach(a -> remFrequencies.put(a, 0L));        
        int generalCount = IntStream.rangeClosed(1, (k%2==0?(k/2)-1:(k/2))).map(a -> (int)Math.max(remFrequencies.get(a), remFrequencies.get(k-a))).sum();
        int zeroCount = (int)remFrequencies.get(0).longValue();
        int halfCount = k%2==0?(int)remFrequencies.get(k/2).longValue():0;        
        return generalCount + (zeroCount==0?0:1) + (halfCount==0?0:1);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.nonDivisibleSubset(k, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

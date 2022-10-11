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
     * Complete the 'miniMaxSum' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void miniMaxSum(List<Integer> arr) {
    // Write your code here
    int[] indexArray = {0, 0};//min number's index and max number's index
    int size = arr.size();
    int[] minMax = {arr.get(0), arr.get(0)};
    IntStream.rangeClosed(1, size-1).forEach(i -> {
        if(arr.get(i)<minMax[0]){
            minMax[0] = arr.get(i);
            indexArray[0] = i;
        }
        else if(arr.get(i)>minMax[1]){
            minMax[1] = arr.get(i);
            indexArray[1] = i;
        }
    });
    
    long[] sumArray = {0L, 0L};//min sum and max sum
    IntStream.rangeClosed(0, size-1).forEach(i -> {
        if (i!=indexArray[0]){
            sumArray[1]+=arr.get(i);//max sum
        }
        
        if(i!=indexArray[1]){
            sumArray[0]+=arr.get(i);//min sum
        }
    });
    
    System.out.println(Arrays.stream(sumArray).boxed().map(a -> String.valueOf(a)).collect(Collectors.joining(" ")));
    
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.miniMaxSum(arr);

        bufferedReader.close();
    }
}

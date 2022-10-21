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
     * Complete the 'acmTeam' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY topic as parameter.
     */

    public static List<Integer> acmTeam(List<String> topic) {
    // Write your code here
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int i=0; i<topic.size(); i++){
            for(int j=i+1; j<topic.size(); j++){
                int count = getKnownSubjectCount(topic.get(i), topic.get(j));
                if (countMap.containsKey(count)){
                    countMap.put(count, countMap.get(count)+1);
                }
                else{
                    countMap.put(count, 1);
                }
            }
        }        
        int maxCount = countMap.keySet().stream().mapToInt(a -> a).max().getAsInt();        
        return Arrays.asList(maxCount, countMap.get(maxCount));
    }
    
    private static int getKnownSubjectCount(String s1, String s2){
        int length = s1.length();
        return (int)IntStream.range(0, length).filter(a -> (s1.charAt(a)=='1' || s2.charAt(a)=='1')).count();
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<String> topic = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<Integer> result = Result.acmTeam(topic);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

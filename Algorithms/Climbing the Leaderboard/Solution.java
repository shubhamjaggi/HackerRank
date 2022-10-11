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
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
    // Write your code here
    
        Stack<Integer> lbScores = new Stack<>();
        Stack<Integer> lbRanks = new Stack<>();
        
        lbScores.push(ranked.get(0));
        lbRanks.push(1);
        
        for(int i=1; i<ranked.size(); i++){
            if(ranked.get(i)<lbScores.peek()){
                lbRanks.push(lbRanks.peek()+1);
            }else{
                lbRanks.push(lbRanks.peek());
            }
            lbScores.push(ranked.get(i));
        }
        
        return player.stream().map(a -> {
            while(!lbScores.isEmpty()){
                if(a<lbScores.peek()){
                    return lbRanks.peek()+1;
                }
                else if(a == lbScores.peek()){
                    return lbRanks.peek();
                }
                else{
                    lbScores.pop();
                    lbRanks.pop();
                }
            }
            return 1;      
        }).collect(Collectors.toList());
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

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

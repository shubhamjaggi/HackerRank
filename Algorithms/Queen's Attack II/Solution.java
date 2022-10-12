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
     * Complete the 'queensAttack' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. INTEGER r_q
     *  4. INTEGER c_q
     *  5. 2D_INTEGER_ARRAY obstacles
     */

    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
    // Write your code here
        int res = 0;
        
        //for obstacles on queen's row (OQR)
        List<List<Integer>> obstaclesOnQueensRow = obstacles.stream().filter(a -> a.get(0)==r_q).collect(Collectors.toList());
        int oqrLeftColumnIndex = obstaclesOnQueensRow.stream().mapToInt(a -> a.get(1)).filter(a -> a<c_q).max().orElse(0);
        int oqrRightColumnIndex = obstaclesOnQueensRow.stream().mapToInt(a -> a.get(1)).filter(a -> a>c_q).min().orElse(n+1);
        res += (oqrRightColumnIndex-oqrLeftColumnIndex-2);
        
        //for obstacles on queen's column (OQC)
        List<List<Integer>> obstaclesOnQueensColumn = obstacles.stream().filter(a -> a.get(1)==c_q).collect(Collectors.toList());
        int oqcDownRowIndex = obstaclesOnQueensColumn.stream().mapToInt(a -> a.get(0)).filter(a -> a<r_q).max().orElse(0);
        int oqcUpRowIndex = obstaclesOnQueensColumn.stream().mapToInt(a -> a.get(0)).filter(a -> a>r_q).min().orElse(n+1);
        res += (oqcUpRowIndex-oqcDownRowIndex-2);
                        
        //for obstacles on queen's general diagonal (OQGD)
        List<List<Integer>> oqgd = obstacles.stream().filter(a -> (c_q-a.get(1))==(r_q-a.get(0))).collect(Collectors.toList());
        int oqgdLeftColumnIndex = oqgd.stream().mapToInt(a -> a.get(1)).filter(a -> a<c_q).max().orElse(c_q-Math.min(c_q-1, r_q-1)-1);
        int oqgdRightColumnIndex = oqgd.stream().mapToInt(a -> a.get(1)).filter(a -> a>c_q).min().orElse(c_q+Math.min(n-c_q, n-r_q)+1);
        res += (oqgdRightColumnIndex-oqgdLeftColumnIndex-2);
        
        //for obstacles on queen's other diagonal (OQOD)
        List<List<Integer>> oqod = obstacles.stream().filter(a -> (c_q-a.get(1))==(-(r_q-a.get(0)))).collect(Collectors.toList());
        int oqodLeftColumnIndex = oqod.stream().mapToInt(a -> a.get(1)).filter(a -> a<c_q).max().orElse(c_q-Math.min(c_q-1, n-r_q)-1);
        int oqodRightColumnIndex = oqod.stream().mapToInt(a -> a.get(1)).filter(a -> a>c_q).min().orElse(c_q+Math.min(r_q-1, n-c_q)+1);
        res += (oqodRightColumnIndex-oqodLeftColumnIndex-2);
        
        return res;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r_q = Integer.parseInt(secondMultipleInput[0]);

        int c_q = Integer.parseInt(secondMultipleInput[1]);

        List<List<Integer>> obstacles = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                obstacles.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

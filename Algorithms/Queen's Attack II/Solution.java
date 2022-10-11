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
        
        //for obstacles on queen's row        
        List<List<Integer>> oqr = obstacles.stream().filter(a -> a.get(0)==r_q).collect(Collectors.toList());
        int oqrcl = oqr.stream().mapToInt(a -> a.get(1)).filter(a -> a<c_q).max().orElse(0);
        int oqrcr = oqr.stream().mapToInt(a -> a.get(1)).filter(a -> a>c_q).min().orElse(n+1);
        res += (oqrcr-oqrcl-2);
        
        //for obstacles on queen's column        
        List<List<Integer>> oqc = obstacles.stream().filter(a -> a.get(1)==c_q).collect(Collectors.toList());
        int oqcrd = oqc.stream().mapToInt(a -> a.get(0)).filter(a -> a<r_q).max().orElse(0);
        int oqcru = oqc.stream().mapToInt(a -> a.get(0)).filter(a -> a>r_q).min().orElse(n+1);
        res += (oqcru-oqcrd-2);
                        
        //for obstacles on queen's general diagonal
        int generalDiagonalConstant = r_q-c_q;
        List<List<Integer>> oqgd = obstacles.stream().filter(a -> (c_q-a.get(1))==(r_q-a.get(0))).collect(Collectors.toList());
        int oqgdcl = oqgd.stream().mapToInt(a -> a.get(1)).filter(a -> a<c_q).max().orElse(generalDiagonalConstant>0?0:(-1*generalDiagonalConstant));
        int oqgdcr = oqgd.stream().mapToInt(a -> a.get(1)).filter(a -> a>c_q).min().orElse(generalDiagonalConstant>0?(n+1-generalDiagonalConstant):(n+1));
        res += (oqgdcr-oqgdcl-2);
        
        //for obstacles on queen's other diagonal
        int otherDiagonalConstant = c_q+r_q;
        List<List<Integer>> oqod = obstacles.stream().filter(a -> (c_q-a.get(1))==(-1*(r_q-a.get(0)))).collect(Collectors.toList());
        int oqodcl = oqod.stream().mapToInt(a -> a.get(1)).filter(a -> a<c_q).max().orElse(otherDiagonalConstant<n+1?0:otherDiagonalConstant-n-1);
        System.out.println(oqodcl);
        int oqodcr = oqod.stream().mapToInt(a -> a.get(1)).filter(a -> a>c_q).min().orElse(otherDiagonalConstant<n+1?otherDiagonalConstant:n+1);
        res += (oqodcr-oqodcl-2);
        
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

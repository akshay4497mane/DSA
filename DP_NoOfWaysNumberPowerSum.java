/*
Find the number of ways that a given integer, , can be expressed as the sum of the  powers of unique, natural numbers.
powerSum has the following parameter(s):
X: the integer to sum to
N: the integer power to raise numbers to

Input  : x = 10, n = 2
Output : 1
Explanation: 10 = 12 + 32, Hence total 1 possibility

Input  : x = 100, n = 2
Output : 3
Explanation: 
100 = 102 OR 62 + 82 OR 12 + 32 + 42 + 52 + 72 Hence total 3 possibilities
*/

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
static int helper(int targetNum, int power, int currNum, Map<String, Integer> memo){
    if(targetNum == 0)
        return 1;
    if(targetNum < 0 || Math.pow(currNum, power) > targetNum )
        return 0;
    String key = targetNum + ":" + currNum;
    if(memo.containsKey(key))
        return memo.get(key);
        
    //Include currNum^power
    int includeWays =helper(targetNum-(int)Math.pow(currNum,power),power,currNum+1,memo);
    //Exclude currNum^power
    int excludeWays = helper(targetNum, power, currNum+1,memo);
    int ans = excludeWays + includeWays;
    memo.put(key, ans);
    return ans;
}

public static int powerSum(int X, int N) {    
    return helper(X, N, 1, new HashMap<>());
}
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int X = Integer.parseInt(bufferedReader.readLine().trim());

        int N = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.powerSum(X, N);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

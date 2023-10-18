/*
Given a string '|**|*|*'   ( | represents compartment, * represent item )
startIndex[] = [1,1]
endIndex[] = [5,6]

Calculate total no of inventory items within a closed compartment in a given startIndex, endIndex
Output: [2,3]

Amazon OA Mock Test1
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
    public static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {    
    List<Integer> ansList = new ArrayList<>();
    for(int i=0; i<startIndices.size(); i++){
        int ans =0;
        int start = startIndices.get(i)-1;
        int end = endIndices.get(i)-1;
        int j= start; 
        if(end > s.length()-1)
            continue;
        
        while(j<=end && s.charAt(j) != '|' ) //find first |
            j++;
        while( j<=end ){//Process String s
            while(j<=end && s.charAt(j) == '|' ) //skip all |
                j++;  
          int tempAns=0;
          while(j<=end && s.charAt(j)== '*' ){
              j++;
              tempAns++;
          }
          if(j<=end && s.charAt(j) == '|'){
            ans += tempAns;
            tempAns = 0; 
          }
        }
        ansList.add(ans);
    }
    return ansList;
    }
}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int startIndicesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> startIndices = IntStream.range(0, startIndicesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int endIndicesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> endIndices = IntStream.range(0, endIndicesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.numberOfItems(s, startIndices, endIndices);

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


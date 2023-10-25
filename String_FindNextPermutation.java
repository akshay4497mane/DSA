/*
JP Morgan OA
Given a WORD String as Input, Rearrange the WORD and return the next alphabetically greater string in all the permutation
[ABCD, ABDC, ACBD, ACDB, ADBC, ADCB, BACD, BADC, BCAD, BCDA, BDAC, BDCA, CABD, CADB, CBAD, CBDA, CDAB, CDBA, DABC, DACB, DBAC, DBCA, DCAB, DCBA]

input: CDBA => Output : DABC

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
    public static String rearrangeWord(String word) {
       char[] w = word.toCharArray();
       int i = w.length - 2;
        //find first char NOT in sync eg. [ CDBA => find C]
       while(i>=0 && w[i] >= w[i+1])
           i--;

       if(i == -1) //Already last permutation reached [  DCBA ]
          return "no answer";
        
        int j= w.length-1;
        while(j>=0 && w[j] <= w[i]) //find a[j] element which is larger than w[i] to swap with 
            j--;
        
        //swap i, j
        char temp = w[i]; //CDBA => DCBA
        w[i] = w[j];
        w[j] = temp;
        
        //reverse sub-string after i till last : eg. DCBA => D ABC 
        i++;
        j = w.length-1;
        while(i<j){
            temp = w[i];
            w[i] = w[j];
            w[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(w);
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String word = bufferedReader.readLine();

        String result = Result.rearrangeWord(word);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

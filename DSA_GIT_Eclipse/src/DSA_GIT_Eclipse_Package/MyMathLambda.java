package DSA_GIT_Eclipse_Package;

import java.io.*;
import java.util.*;

interface PerformOperation {
 boolean check(int a);
}

class MyMath {
 public static boolean checker(PerformOperation p, int num) {
  return p.check(num);
 }
public PerformOperation isOdd(){
    return (int num) -> num % 2 != 0;
}
public PerformOperation isPrime(){
    return (int num) -> {
        if( num <=1) return false;
        if(num == 2 || num ==3) return true;
        if(num%2 ==0 || num%3==0) return false;
        for(int i=5; i*i <=num; i+=6){
            if(num%i==0 || num%(i+2)==0) return false;
        }
        return true;
    };
}
public PerformOperation isPalindrome(){
    return (int num) -> {
        String numStr = Integer.toString(num);
        String revNumStr = new StringBuilder(numStr).reverse().toString();
        return numStr.equals(revNumStr);
    };
}
}
   // Write your code here

public class MyMathLambda {

 public static void main(String[] args) throws IOException {
  MyMath ob = new MyMath();
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  int T = Integer.parseInt(br.readLine());
  PerformOperation op;
  boolean ret = false;
  String ans = null;
  while (T--> 0) {
   String s = br.readLine().trim();
   StringTokenizer st = new StringTokenizer(s);
   int ch = Integer.parseInt(st.nextToken());
   int num = Integer.parseInt(st.nextToken());
   if (ch == 1) {
    op = ob.isOdd();
    ret = ob.checker(op, num);
    ans = (ret) ? "ODD" : "EVEN";
   } else if (ch == 2) {
    op = ob.isPrime();
    ret = ob.checker(op, num);
    ans = (ret) ? "PRIME" : "COMPOSITE";
   } else if (ch == 3) {
    op = ob.isPalindrome();
    ret = ob.checker(op, num);
    ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

   }
   System.out.println(ans);
  }
 }
}
/*
https://www.hackerrank.com/challenges/java-lambda-expressions/problem
Input (stdin)
5
1 4
2 5
3 898
1 3
2 12

Expected Output
EVEN
PRIME
PALINDROME
ODD
COMPOSITE
*/
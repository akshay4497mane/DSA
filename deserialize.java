/*
Problem Description
You are given a string A which is a serialized string. You have to restore the original array of strings.
The string in the output array should only have lowercase english alphabets.
Serialization: Scan each element in a string, calculate its length and append it with a string and a element separator or deliminator (the deliminator is ~). We append the length of the string so that we know the length of each element.
For example, for a string 'interviewbit', its serialized version would be 'interviewbit12~'.
Problem Constraints
1 <= |A| <= 106
Input Format
The first argument is the string A.
Output Format
Return an array of strings which are deserialized.

Example Input
Input 1:
A = 'scaler6~academy7~'
Input 2:
A = 'interviewbit12~'
Example Output
Output 1:
['scaler', 'academy']
Output 2:

['interviewbit']


Example Explanation
Explanation 1:
Length of 'scaler' is 6 and academy is 7. So, the resulting string is scaler6~academy7~.
We hve to reverse the process.
Explanation 2:

Explained in the description above
*/

public class Solution {
    public ArrayList<String> deserialize(String A) {
        /*
        Approach1: Using String.split(regex) inbuilt function
        
        ArrayList<String> result = new ArrayList<String>(); 
        String[] ans = A.split("~");
        
        for(String x : ans){
            String temp[] = x.split("[0-9]");
            for(String s : temp)
                result.add(s);
        }
        return result;
        */
        
        //Approach2 :
        ArrayList<String> ans = new ArrayList<>();
        String res="";
        for(int i=0;i<A.length();i++){
            char ch=A.charAt(i);
            if(Character.isLetter(ch))
                res+=ch;
            else if(ch=='~'){
                ans.add(res);
                res="";
            }
        }
        return ans;
        
    }
}

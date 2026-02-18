class Solution {
/*
Approach 1: brute force, map, 
Approach 2 : Sort -> traverse count 
=> N log N +  N | O(1)
Aproach 3: 
    cand1 , count1
    cand2, count2
 if( ==cand1) count1++;
 else if( ==cand2) count2++;
 check count1 =0
 check count2 ==0
 --count1, --count2
reverify cand1, cand2
N = 9
3 =  n/3
4 + 4 +1

N/K + 1

at most K-1 elements
*/    
    public List<Integer> majorityElement(int[] nums) {
        int cand1=0 , count1=0, cand2=0, count2=0;
        for(int x : nums){
            if( x == cand1 ) count1++;
            else if( x == cand2 ) count2++;
            else if( count1 == 0 ){
                cand1 = x;
                count1 = 1;
            }else if( count2 == 0 ){
                cand2 = x;
                count2 = 1;
            }else{
                --count1; --count2;
            }
        }
        //reverify cand1, cand2               
        count1=0; count2=0;
        for(int x : nums){
            if( x == cand1 ) count1++;
            else if( x == cand2 ) count2++;
        }
        List<Integer> ans = new ArrayList<>();
        int limit = nums.length / 3;
        if( count1> limit) ans.add(cand1);
        if( count2> limit) ans.add(cand2);
        return ans;
    }
}
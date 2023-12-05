class Solution {


    //Approach1: BAcktracking/ Brute Force. Create 4 empty subarrays. Put every element in each and try all.
    /*
    boolean helper(int curr, int len1, int len2, int len3, int len4, int[] A){
        if(curr == A.length && len1 == len2 && len3 == len4 && len1 == len3)
            return true;
        if(curr == A.length)
            return false;
        boolean ans1= helper(curr+1, len1+A[curr], len2, len3, len4,A);
        if(ans1 == true )
            return true;
        boolean ans2= helper(curr+1, len1, len2+A[curr], len3, len4, A);
        if(ans2 == true )
            return true;
        boolean ans3= helper(curr+1, len1, len2, len3+A[curr], len4, A);
        if(ans3 == true )
            return true;
        boolean ans4= helper(curr+1, len1, len2, len3, len4+A[curr], A);
        if(ans4 == true )
            return true;
        return false;
    }
    */


    public boolean makesquare(int[] matchsticks) {
        //Approach 1
            //return helper(0, 0,0,0,0, matchsticks);
        //Approach 2 :
            int total = 0;
            for(int ele : matchsticks){
                total += ele;
            }
            if(total % 4 != 0) return false;  
            Arrays.sort(matchsticks);
            return match(matchsticks, matchsticks.length-1, 0,0,0,0, total/4);
    }

    //Approach 2: Optimized backtracking, Calculates Total/4, Prunes whenever target is crossed. 
boolean match(int[] A, int index, int top, int bottom, int left, int right, int target){
    if(top==target && bottom == target && left == target && right == target) return true;
    if(top>target || bottom > target || left > target || right > target) return false;
    int val = A[index];
    boolean t = match(A, index - 1, top + val, bottom, left, right, target);
    if (t) return true;
    boolean b = match(A, index - 1, top, bottom + val, left, right, target);
    if (b) return true;
    boolean l = match(A, index - 1, top, bottom, left + val, right, target);
    if (l) return true;
    boolean r = match(A, index - 1, top, bottom, left, right + val, target);
    if (r) return true;   

    return false; 
}

}
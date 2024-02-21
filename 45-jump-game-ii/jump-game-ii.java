//Approach 1 Sudipta
class Solution {
    public int jump(int[] nums) {
        int res=0, end=0, farthest=0;
        for(int i=0; i < nums.length-1; i++){
            farthest = Math.max( farthest, (i+nums[i]) );
            if( farthest >= nums.length-1 ){
                return ++res;
            }
            if(i==end){ //visited all item in current level
                res++;  // increment level
                end = farthest;  //queue size for next level
            }
        }
        return res;
    }
}
//Approach 2 Gandhi
/*
for(int i=1;i<nums.length;i++){
    nums[i]=Math.max(nums[i]+i,nums[i-1]);
}
int res=0,i=0;
while(i<nums.length-1){
    i=nums[i];
    res++;
}
return res;
*/

//Approach 3 Neetcode solution video
class Solution_3 {
 public int jump(int[] nums) {
        int l=0,r=0; //window for DFS search after every jump
        int result=0;
        while( r < nums.length-1){
            int longestJump = 0;
            for(int i=l ; i<r+1 ; i++){
                longestJump = Math.max(longestJump, i + nums[i]);
            }
            l=r+1;
            r=longestJump;
            result++;
        }
        return result;
    }
}
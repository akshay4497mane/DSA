class Solution {
    public List<Integer> majorityElement(int[] A) {
        int x = 0, xCount=0, y=0, yCount=0, n = A.length;
        for(int i=0; i<n; i++){
            if(xCount==0 && A[i]!=y){ //ERROR IN FINDING THIS EDGE CASE
                x= A[i];
                xCount++;
            }else if(A[i]==x){
                xCount++;
            }else if(yCount==0 ){
                y=A[i];
                yCount++;
            }else if(A[i]==y){
                yCount++;
            }else{ 
                xCount--;
                yCount--;
            }
        }
        xCount =0; yCount=0;
        for(int i=0;i<n; i++){
            if(A[i]==x) //ERROR IN FINDING THIS EDGE CASE
                xCount += (A[i]==x) ? 1 :0;
            else
                yCount += (A[i]==y) ? 1 :0;
        }
        List<Integer> ans = new ArrayList<>();
        if(3 * xCount > n) ans.add(x);
        if(3 * yCount > n) ans.add(y);
        return ans;
    }

/*[0,0,0]. => failed added
Wrong Answer. | 86 / 92 testcases passed
Input nums = [0,0,0]
Use Testcase
Output [0,0]
Expected [0]
Added if/else in for loop
*/
/*
Wrong Answer
90 / 92 testcases passed

Input nums = [2,1,1,3,1,4,5,6]

Use Testcase
Output
[]
Expected
[1]
// [2,1,1,3,>1,4,5,6]
x 2-0  | 1-1
y 1-2
Solution : && A[i]!=y needs to be added when xCount==0

*/
}
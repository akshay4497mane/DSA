public class Solution {
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int trap(final int[] A) {
    
    /*
    Approach: 3 Iterations, calculate Left Array, Right Array to store Max height for every element on both sides 
    O(N) time, O(N) space complexity
    https://www.youtube.com/watch?v=UZG3-vZlFM4&ab_channel=AnujBhaiya
    */
        int size = A.length;
      
        int[] left = new int[size]; //Maximum Height available to LEFT of every element
        left[0] = A[0];
        for(int i = 1; i<size ; i++){
            left[i] = Math.max(A[i], left[i-1]);
        }
        
        int[] right = new int[size];//Maximum Height available to RIGHT of every element
        right[size-1] = A[size-1];
        for(int j = size-2; j >= 0; j-- ){
            right[j] = Math.max(right[j+1], A[j]);
        }
        
        int ans = 0;
        for(int i = 0; i<size ; i++){
            ans += Math.min(left[i], right[i]) - A[i];
        }
        //return ans;  
      
    /*
    APPROACH: 2 pointers
    O(N) Time, O(1) Space
    Explanation:
    https://www.youtube.com/watch?v=C8UjlJZsHBw&ab_channel=Techdose
    
    */
    int size = A.length;
    if(size <= 2)
        return 0;
        
    int maxLeft = A[0], maxRight = A[size-1];
    int left = 1, right = size-2;
    int trappedWater = 0;
    
    while(left <= right){
        //System.out.println("START: " + left+ " " +  right + " " + maxLeft + " " + maxRight + " " + trappedWater);
        if(maxLeft < maxRight){
            if(A[left] > maxLeft){ //Cant store any water, update leftMax
                maxLeft = A[left];
            }else{
                trappedWater += maxLeft - A[left];
            }
            left++;
        }else{
            if(A[right] > maxRight)  //Cant store any water, update leftMax
                maxRight = A[right];
            else
                trappedWater += maxRight - A[right];
            right--;
        }
        //System.out.println("END  : " + left+ " " +  right + " " + maxLeft + " " + maxRight + " " + trappedWater);
    }       
    return trappedWater;        
    }
}

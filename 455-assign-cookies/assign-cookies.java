class Solution {
/*The objective is to determine the maximum number of content children given cookie sizes and greed factors.

The GREEDY approach involves sorting both arrays in ascending order and using a TWO POINTER technique to assign cookies to children, ensuring each child receives the smallest cookie that meets their greed.

Time complexity of O(n⋅log⁡n+m⋅log⁡m) and a 
space complexity of O(m+n) or O(log⁡m+log⁡n).

The greedy approach guarantees an optimal solution by offering each child the smallest available cookie first.
*/

    public int findContentChildren(int[] greed, int[] cookies) {
        Arrays.sort(greed);
        Arrays.sort(cookies);
        int i = 0, N = greed.length; //Content Children
        int j = 0, M = cookies.length; //Cookie Index
        while( i < N && j < M ){
            if(cookies[j] >= greed[i]){
                i++;//Satisfy child i and move to next
            }
            j++; //Always move to next Cookie even if child may not be satisfied
        }
        return i;
    }
}
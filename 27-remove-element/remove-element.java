class Solution {
    public int removeElement(int[] A, int val) {
        int i = 0; //moves left -> right | stops at finding val
        int N = A.length;
        int j = N-1;//moves right-> left | stops at finding non val
        while( i <= j){
            System.out.println("i= " + i + "A[i]:" + A[i] +  " j= " + j + "A[j] : " + A[j]);
            while( i < N && A[i] != val ){ i++; }
            while( j >= 0 && A[j] == val ){ j--; }
            if( i < j ){
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++; j--;
            }
        }
        return j+1;        
    }
}
/*
02 34 53 10 | val = 3
i=2, j=7
02 04 53 13 | i=3, j=6
i=5, j=6
02 04 51 33 | i=6, j=5
---
Input
nums =
[1]
val =
1

Use Testcase
Output
[1]
Expected
[]

-----
[0,1,2,2,3,0,4,2] | val = 2
i=0, j=0 | swap
i=1, j=0 | swap
[1,0,2,2,3,0,4,2] | val = 2
i=2, j=0 | 

*/
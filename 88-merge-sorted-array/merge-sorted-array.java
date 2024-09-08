class Solution {
    //1 2 3
    //2 5 6
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m-1, i2 = n-1, ansi = m+n-1;
        while( i1>=0 && i2 >=0 ){
            nums1[ansi--] = ( nums1[i1] > nums2[i2] ) ? nums1[i1--] : nums2[i2--];
        }
        while( i1 >= 0){            
            nums1[ansi--] = nums1[i1--];
        }
        while( i2 >= 0){            
            nums1[ansi--] = nums2[i2--];
        }
    }
}
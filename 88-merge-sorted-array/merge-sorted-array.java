class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i=nums1.length-1, j=m-1; j>=0 ; i--, j--){ 
            nums1[i] = nums1[j];
        }
        int i=n;//first array now starts at nums1[n]
        int j=0; //2nd array nums2[0]
        int ans=0;//Answer index
        while(i<nums1.length && j<n){
            if( nums1[i] <= nums2[j] ){
                nums1[ans++] = nums1[i++];
            }else{
                nums1[ans++] = nums2[j++];
            }
        }
        while( i<nums1.length ){
            nums1[ans++] = nums1[i++];
        }
        while( j<n ){
            nums1[ans++] = nums2[j++];
        }
        for(Integer e : nums1)
            System.out.print(e);
    }
}
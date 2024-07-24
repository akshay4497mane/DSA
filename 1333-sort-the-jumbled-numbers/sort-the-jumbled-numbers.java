class Solution {
    /* APPROACH 1 : convert num to string, apply mapping on each char, convert to integer, sort, calculate answer using old nums indices stored in a map of number, index
    */
    public int[] sortJumbled_UsingString(int[] mapping, int[] nums) {
        List<Integer[]> numToIndexMap = new ArrayList<>();// List of array[mappedNumber , index]
        for( int i=0; i < nums.length ; i++ ){
            String numStr = Integer.toString(nums[i]); //convert num -> String
            String mappedNumStr = "";
            for(int j=0; j<numStr.length(); j++){//apply mapping to each character and form new number String
                mappedNumStr += Integer.toString( mapping[ numStr.charAt(j)-'0'] );
            }
            int mappedNumInt = Integer.parseInt(mappedNumStr);//
            numToIndexMap.add(new Integer[] {mappedNumInt, i} );
        }
        Collections.sort(numToIndexMap, (a,b) -> Integer.compare(a[0], b[0]) );
        //sort based on "new mapped integers"
        int[] ans = new int[ nums.length ];
        for( int i=0; i<numToIndexMap.size(); i++ ){
            ans[i] = nums[ numToIndexMap.get(i)[1] ];
        }
        return ans;
    }
    /*
    APPROACH 2 : without converting to string.
     */
    public int[] sortJumbled(int[] mapping, int[] nums) {
        List<int[]> numToIndexMap = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            int mappedNum = 0, currDigit = 1;
            int num = nums[i];
            if( num == 0 ){
                numToIndexMap.add( new int[] {mapping[0], i} );
                continue;
            }
            while( num!=0 ){
                mappedNum += currDigit * mapping[ num%10 ];
                currDigit = currDigit * 10;
                num = num/10;
            }
            numToIndexMap.add( new int[] {mappedNum, i});
        }
        Collections.sort(numToIndexMap, (a,b) -> a[0]-b[0] );

        int[] ans = new int[nums.length];
        System.out.println("numToIndexMap : ");
        numToIndexMap.forEach(arr -> System.out.print(Arrays.toString(arr)) );

        for(int i=0; i<numToIndexMap.size(); i++){
            ans[i] = nums[ numToIndexMap.get(i)[1] ];
        }
        return ans;
    }
}
class Solution_1 {
    /* APPROACH 1 : convert num to string, apply mapping on each char, convert to integer, sort, calculate answer using old nums indices stored  in a map of number, index
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
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(new int[]{getMapped(nums[i], mapping), i});
        }
        list.sort((a, b) -> Integer.compare(a[0], b[0]));
        int[] ans = new int[nums.length];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = nums[list.get(i)[1]];
        }
        return ans;
    }
    private int getMapped(int num, int[] mapping) {
        if (num == 0) return mapping[0];
        int mapped = 0, place = 1;
        while (num > 0) {
            mapped += mapping[num % 10] * place;
            place *= 10;
            num /= 10;
        }
        return mapped;
    }
}

//Creating separate ITEM[num, mappedNum, index] class
class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int n = nums.length;
        List<Item> list = new ArrayList<>(n); // pre-size
        for (int i = 0; i < n; i++) {
            list.add(new Item(nums[i], getMapped(nums[i], mapping), i));
        }
        // Sort by mapped value, if tie → original order (stable behavior)
        list.sort((a, b) -> a.mapped == b.mapped ? Integer.compare(a.index, b.index): Long.compare(a.mapped, b.mapped));
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = list.get(i).num;
        }
        return ans;
    }
    private long getMapped(int num, int[] map) {
        if (num == 0) return map[0];
        long mapped = 0, place = 1;
        while (num > 0) {
            mapped += (long)map[num % 10] * place;
            place *= 10;
            num /= 10;
        }
        return mapped;
    }
    class Item {
        int num;
        long mapped;
        int index;
        Item(int num, long mapped, int index) {
            this.num = num;
            this.mapped = mapped;
            this.index = index;
        }
    }
}
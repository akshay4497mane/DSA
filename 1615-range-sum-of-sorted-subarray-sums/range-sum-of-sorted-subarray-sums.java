class Solution {
    public int rangeSum(int[] nums, int n, int left, int right) {
        left = left-1;
        right = right-1;
        List<Integer> sumList = new ArrayList<>();
        for(int i=0; i<n; i++){
            int sumitoj = 0;
            for(int j=i; j<n ; j++){
                sumitoj += nums[j];
                sumList.add(sumitoj);
            }
        }
        Collections.sort(sumList);
        int ans = 0, mod  = (int) 1e9+7;
        for(int i=left; i<=right; i++){
            ans = (ans+sumList.get(i)) % mod;
//            System.out.println(sumList.get(i));
        }
        /*
        return sumList.stream()
            .sorted().skip(left).limit(right-left+1)
            .mapToInt(Integer::intValue)
            .sum();//.sum();//forEach(System.out::println);
        //subList(left, right+1)  doesnt work for stream
        */
        return ans;
    }
}
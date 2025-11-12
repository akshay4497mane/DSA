class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            map.put(nums[i], i);
        }
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(target-nums[i]) && map.get(target-nums[i])!=i){
                return new int[]{ i, map.get(target-nums[i]) };
            }
        }
        return new int[2];
    }
/*
    Given two integers, describe an algorithm to determine 
if both numbers contain the same digits. 
The number of occurrences don't matter. 
For example, 
1234 and 
4444311111222 contain the same digits, 
but 1234 and 5432 don't.


num1
num2

1234  =>  1 , 2, 3, 4  ~ Map / Set
123445
*/
/*
    public int[] twoSum(int[] nums, int target) {
        System.out.println( isSameDigit(1234, 12345));
        return new int[3];
    }
//n1 = 12345, n2 = 1234
set : 
1235 / 1234444444

// len1 ~ len2
O(len1+len2)
    boolean isSameDigit(int n1, int n2){    
        Set<Integer> digits = new HashSet<>();
        1->F # 2 F, 3 F, 4 F 5 t  

        while(n1){
            int curr = n1%10; //1234 => 4
            digits.put(curr);
            n1 = n1/10;
        }
        while(n2){
            int curr = n2%10; //12345 => 5
            if(NOT digits.contains(curr))
                return false;
            else
               map.put(curr, false)
            n2 = n2/10;
        }    
        //traverse map linearly FALSE   
        //return false

n : 11111111111111111111111111111111111111
Hash(input) 
input : 1 150 - 500 -1

input -> hashcode ->  1-100 - index 0
hashCode -> 101-200  - index 1

akshay

hashIndex = inputNum % sizeof;

Array 100 elements

1 -> 1 ->    ( O(N))
 O(1)

        return true;
    }
*/

}
class Solution {
/*
Approach: Monotonic Stack of increasing order.
for each char in num ->
if stack top > num[i] then pop & k-- | Means remove maximum elements from left to ensure monotonic stack is in increasing order

Edge Cases:
leading 0 -> trim 0 | string=2340000 => k=1 => st: 230000
sorted 12345 -> stack will have all elements -> handle again after for
*/
    public String removeKdigits(String num, int k) {
        Stack<Character> st = new Stack<>();
        for(int i=0; i<num.length(); i++){
            //num = "143221 9ABCD", k = 0 / st=1219 / num[i]=1
            while(!st.isEmpty() && st.peek()>num.charAt(i) && k>0 ){
                st.pop();
                k--;
            }
            st.push(num.charAt(i));
        }
        while(k>0 && !st.isEmpty()){
            st.pop();
            k--;
        }
        //remove all zeroes
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;

        for (char ch : st) {
            if (leadingZero && ch == '0') continue;
            leadingZero = false;
            sb.append(ch);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}


class Solution_2 {
    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peekLast() > ch) {
                stack.pollLast();
                k--;
            }
            stack.addLast(ch);
        }

        while (k-- > 0 && !stack.isEmpty()) {
            stack.pollLast();
        }

        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;

        for (char ch : stack) {
            if (leadingZero && ch == '0') continue;
            leadingZero = false;
            sb.append(ch);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}


/*
Aim : SMALLEST num
1.Digits from Left -> Right | 
2.
3. handle 0 cases
k=2
num = 11111 =>111
num = 222 => 2 
2345 => L - 45 / R 23 / remove 25-> 34  / 34 -> 25
 4digit -> 2digit
3456789 -> 34567 
4518697 -> temp = 1456789 -> 45167 ->

abcd -> cd

5432 => 
23432
num = "10200", k = 1
1432219", k = 3
*/


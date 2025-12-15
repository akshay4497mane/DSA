class Solution {
    List<String> ans = new ArrayList<>();
    Map<Character, Character[]> map = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        map.put('2', new Character[]{'a','b','c'});
        map.put('3', new Character[]{'d','e','f'});
        map.put('4', new Character[]{'g','h','i'});
        map.put('5', new Character[]{'j','k','l'});
        map.put('6', new Character[]{'m','n','o'});
        map.put('7', new Character[]{'p','q','r','s'});
        map.put('8', new Character[]{'t','u','v'});
        map.put('9', new Character[]{'w','x','y','z'});
        
        StringBuilder currAns = new StringBuilder();
        dfs(0, currAns, digits );
        return ans;
    }
    void dfs(int ind, StringBuilder currAns, String digits){
        if(ind == digits.length()){
            ans.add(currAns.toString());
            return;
        }
        for(Character ch : map.get( digits.charAt(ind) )){
            currAns.append(ch);
            dfs(ind+1, currAns, digits);
            currAns.deleteCharAt(currAns.length()-1);
        }
    }
}
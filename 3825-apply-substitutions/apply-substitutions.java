class Solution {
    Map<String, String> map = new HashMap<>();
    public String applySubstitutions(List<List<String>> replacements, String text) {
        for(List<String> s : replacements){
            map.put(s.get(0), s.get(1));
        }
        return processStr(text);
    }
    String processStr(String s){
        StringBuilder sb = new StringBuilder();
        for(int i=0 ; i<s.length() ; i++){
            if(s.charAt(i)=='%'){
                String key = String.valueOf(s.charAt(i+1));
                System.out.println("Key : "+ key + " Val : " +map.get(key) );

                String newVal = processStr(map.get(key));
                sb.append(newVal);
                i++; i++;
            }else{
                sb.append(s.charAt(i));
            }
            
        }
        return sb.toString();
    }
}
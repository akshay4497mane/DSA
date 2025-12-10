class Solution {
    public int rotatedDigits(int N) {
        int countAns = 0;
        for(int num=1; num<=N; num++){
            int x = num;
            boolean isValid = true, isChanged = false;
            while( x > 0){
                int dig = x%10;
                if(dig==3 || dig==4 || dig==7){
                    isValid = false; 
                    break;
                }
                if(dig == 2 || dig==5 || dig==6 || dig==9){
                    isChanged = true;
                }
                x = x/10;
            }
            if(isValid && isChanged) countAns++;
        }
        return countAns;
    }
}

/*
11 -> 11  -. Bad Num
11112 -> 11115 -> Good
*/
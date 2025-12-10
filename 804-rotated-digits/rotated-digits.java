class Solution {
    public int rotatedDigits_1(int N) {
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

    public int rotatedDigits(int n) {
        int[] arr=new int[n+1];
        int count=0;
        for(int i=0;i<=n;i++) {
            if (i <= 9) {
                if ((i == 0) || (i == 1) || (i == 8)) {
                    arr[i] = 0; // Rotates and remains same number => neither good, nor bad
                } else if ((i == 2) || (i == 5) || (i == 6) || (i == 9)) {
                    arr[i] = 1;  //Surely GOOD Num
                    count++;
                } else {
                    arr[i] = -1; //Bad Number -> will make entire number as bad
                }
            } else {
                // arr[d] = -1 invalid, 0 same, 1 good-change
                // fills arr[i] based on arr[t] and arr[u]
                int u = i % 10;
                int t = i / 10;

                int a = arr[t];
                int b = arr[u];

                if (a == -1 || b == -1) { // invalid if any part invalid
                    arr[i] = -1;
                    continue;
                }
                if (a == 1 || b == 1) {// good if any part is "changed"
                    arr[i] = 1;
                    count++;
                    continue;
                }
                arr[i] = 0; // both same → whole same


    /*
                    int u = i % 10;
                    int t = i / 10;
                    if ((arr[u] == 0 && arr[t] == 1) || (arr[u] == 1 && arr[t] == 0)) {
                        arr[i] = 1;  //GOOD
                        count++;
                    } else if (arr[u] == -1 || arr[t] == -1) { //definitely BAD number
                        arr[i] = -1;
                    } else if (arr[u] == 0 && arr[t] == 0) {
                        arr[i] = 0;
                    } else {
                        arr[i] = 1;
                        count++;
                    }
    */
            }
        }
        return count;
    }

/*
    public static int rotatedDigits(int N) {
        int[] dp = new int[N + 1];
        int count = 0;

        for (int i = 0; i <= N; i++) {
            if (i < 10) {
                if (i == 0 || i == 1 || i == 8) dp[i] = 1;         // same
                else if (i == 2 || i == 5 || i == 6 || i == 9) {
                    dp[i] = 2;  // good (changed)
                    count++;
                }
                else dp[i] = 0;  // invalid
            } else {
                int a = dp[i / 10]; //2345 -> 234
                int b = dp[i % 10]; // 5

                if (a == 0 || b == 0) dp[i] = 0;           // invalid
                else if (a == 2 || b == 2) {
                    dp[i] = 2;                            // changed
                    count++;
                } else dp[i] = 1;                         // same
            }
        }
        return count;
    }
    */
}
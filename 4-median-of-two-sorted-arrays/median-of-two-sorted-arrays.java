class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        m=A.length; n=B.length;
        if( (m+n)%2==0){ //even count
            for(int i=0; i<(m+n)/2-1 ; i++){
                int temp = getMinimumAndMoveNext(A, B);
            }
            return (double) (getMinimumAndMoveNext(A, B) + getMinimumAndMoveNext(A, B))/2;
        }else{ //odd count
            for(int i=0; i < (m+n)/2 ; i++){
                int temp = getMinimumAndMoveNext(A, B);
            }
            return getMinimumAndMoveNext(A, B);
        }
    }
    int p1=0, p2=0;
    int m=0, n=0;
    private int getMinimumAndMoveNext(int[] A, int[] B){
        if( p1<m && p2<n ){
            return A[p1]<=B[p2] ? A[p1++] : B[p2++];
        }else if( p1<m ){
            return A[p1++];
        }else if( p2<n ){
            return B[p2++];
        }
        return -1;
    }

    public double findMedianSortedArrays_BadAttempt_29_2096passed(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int p1=0, p2=0, i=0;
        int currElement = 0;
        while( p1 < m && p2 < n ){
            if( A[p1] <= B[p2] ){
                p1++;
            }else{
                p2++;
            }
            i++;
            System.out.println(p1 + " " + p2 + " " +i + " "+ ((m+n-1) / 2));
            if(i == ((m+n-1) / 2) ){ //Found Answer | Reached MID-1 position
                if( (m+n)%2 ==0){ //even count =>
                    int num1 = (A[p1] <= B[p2])? A[p1++] : B[p2++];
                    int num2 = 0;
                    if( p1<m )
                        if( p2<n )
                            num2 = (A[p1] <= B[p2])? A[p1++] : B[p2++];
                        else
                            num2 = A[p1++];
                    else
                        num2 = B[p2++];
                    return ((double)num1+num2)/2;
                }else{//odd count
                    return (A[p1] <= B[p2])? A[p1++] : B[p2++];
                }
            }
        }
        while( p1 < m ){ //p2 exhausted
            p1++;
            i++;
            if(i == ((m+n-1) / 2) ){ //Found Answer | Reached MID-1 position
                if( (m+n)%2 ==0){ //even count =>
                    int num1 = A[p1++];
                    int num2 = A[p1++];
                    return ((double)num1+num2)/2;
                }else{//odd count
                    return A[p1++];
                }
            }
        }
        while( p2 < n ){ //p2 exhausted
            p2++;
            i++;
            if(i == ((m+n-1) / 2) ){ //Found Answer | Reached MID-1 position
                if( (m+n)%2 ==0){ //even count =>
                    int num1 = B[p2++];
                    int num2 = B[p2++];
                    return ((double)num1+num2)/2;
                }else{//odd count
                    return B[p2++];
                }
            }
        }
        return -1;
    }
}
package DSA_GIT_Eclipse_Package;

import java.util.Arrays;

public class SortingAlgorithms {

	public static void main(String[] args) {
		int[] A = {1,9,5,2,0,-1,6,3,-9};
		
		System.out.println("Original : ");
        Arrays.stream(A).forEach(e -> System.out.print( e + " "));
        //bubbleSort(A);
        mergeSort(A, 0, A.length-1 );
        System.out.println( "\nSorted : \n" + Arrays.toString(A) );

	}
	//BUBBLE SORT : Best : O(N), Worst ( O(N^2) )
    static void bubbleSort(int[] arr){
        int N  = arr.length;
        for( int i=0 ; i< N-1 ; i++ ){
            for(int j=0 ; j < N-i-1 ; j++){
                if( arr[j] > arr[j+1] ){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }                
            }
        }
    }
    static void mergeSort( int[] A, int left, int right ){
    	if(left >= right) return;
    	int mid = left + (right - left)/2;
    	mergeSort(A, left, mid );
    	mergeSort(A, mid+1,right );
    	merge(A,left, right, mid);
    }
    static void merge(int[] A, int left, int right, int mid){
    	int n1 = mid-left+1;
    	int n2 = right - mid;
    	
    	int[] leftA = new int[n1];
    	int[] rightA = new int[n2];
    	
    	System.arraycopy(A, left, leftA, 0, n1);
    	System.arraycopy(A, mid+1, rightA, 0, n2);

    	int i=0, j=0, k=left;
    	while( i<n1 && j<n2) {
    		if(leftA[i] <= rightA[j]) {
    			A[k++] = leftA[i++];
    		}else {
    			A[k++] = rightA[j++];    			
    		}
    	}
    	while( i<n1 ) {
    		A[k++] = leftA[i++];
    	}
    	
    	while( j<n2 ) {
    		A[k++] = rightA[j++];
    	}	
    }
    
    //HEAP SORT
}

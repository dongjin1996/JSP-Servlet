package ch07;

public class GuGuDan {
	public int[] process(int n) {
		int arr[] = new int[9];
		
		//구구단 결과값을 배열에 저장한다
		for(int i=1; i<=9; i++) {
			arr[i-1] = n * 1;
		}
		
		return arr;
	}
}

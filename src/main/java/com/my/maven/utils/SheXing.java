package com.my.maven.utils;

public class SheXing {
	public static void main(String[]args){ 
		String foo="ABCDE";
		foo = foo.substring(0,3);
	foo = foo.concat("XYZ"); 
	System.out.println(foo);}
//
//	public static void main(String[] args) {
//
//		int n = 5;
//		int a[][] = new int[n][n];
//		int count = 1;
//		for (int i = 0; i < n / 2 + 1; i++) {
//
//			// down
//			for (int j = i; j < n - i; j++) {
//				if (i == 0 && j == 0) {
//					a[j][i] = count;
//				} else {
//					a[j][i] = ++count;
//				}
//			}
//
//			// right
//			for (int j = n-i-1; j >i; j--) {
//				a[n-i-1][n-j] = ++count;
//			}
//
//			// up
//			for (int j = n-i-1; j >i; j--) {
//				a[j-1][n-i-1] = ++count;
//			}
//			
//			// left
//			for (int j =i; j<n-i-2; j++) {
//				a[i][n-j-2] = ++count;
//			}
//		}
//		
//		for(int i=0;i<n;i++){
//			for(int j=0;j<n;j++){
//				System.out.print(a[i][j]+" ");
//				
//			}
//			System.out.println("");
//		}
//	}

}

package com.cal;

public class HowMuchEggs {
	
	
	public static void main(String[] args) {
		for(int i=1;i<200000;i++){
			if(testInteger(i)){
				System.out.println(i);
			}
		}
	}
	
	private static boolean testInteger(int i){
		return  i%2==1 && i%3==0 && 
				i%4==1 && i%5==4 && i%6==3 && i%7==0
				&& i%8==1 && i%9==0 ;
	}
}

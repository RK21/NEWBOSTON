package com.thenewboston.ronak;

 class Apples {

	public static void main(String[] args){
		
		int ronak[] = {1,2,3,4,5};
		change(ronak);
		for(int y:ronak)
			System.out.println(y);
		
		
	}
	public static void change(int x[]){
		
		for(int counter=0;counter<x.length;counter++){
			
			x[counter]+= 4;
			
		}
		
	}
}


package codeforces;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {//ww

		Scanner sc = new Scanner(System.in);
		
	 
		
		int count=0;

		int n = sc.nextInt();
		
		
		List<Number[]> l = new ArrayList<Number[]>();

		for (int i = 0; i < n; i++) {	
			Number arr[] = new Number[2];
				arr[0] = sc.nextInt();
				arr[1] = sc.nextInt();
		l.add(i,arr);
		}
		
		 
		
		 
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if(j!=i){
					if(l.get(i)[0] == l.get(j)[1])
						count++;
				}
				
			
		}
		System.out.println(count);

		//////////////////////////////////////////////////////////////////
	}
}

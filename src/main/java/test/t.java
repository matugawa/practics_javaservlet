package test;

import java.util.Arrays;
import java.util.List;

public class t {
	
	public static Integer len(String s) {
		return s.length();
	}

	public static void main(String[] args) {
		//
		List<Integer> li=Arrays.asList(1,2,3,4,5);
		li.stream().filter(i ->i%2==0)
		.forEach(i ->System.out.println(i));
			
		
		

	}

}

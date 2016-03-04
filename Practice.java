package umlparser.src.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class Practice {

	public static boolean isUnique(String str){
		if(str.length()>256){
			return false;
		}
		boolean[] charSet = new boolean[256];
		for(int i =0; i<str.length();i++){
			int val = str.charAt(i);
			if(charSet[val]){
				return false;
			}
			charSet[val]=true;
		}
		return true;
	}
	public static void main(String[] args) {
		
		String str = "abcd";
		StringBuffer sb = new StringBuffer();
		int counter = 1;
		for(int i=0; i<str.length();i++){
			for(int j=i+1; j<str.length();j++){
			if(str.charAt(j)==str.charAt(i)){
				counter++;
			}else{
				
				break;}
			}
			sb.append(str.charAt(i));
			if(counter!=1){
				sb.append(counter);
			}
			
			i= i+counter-1;
			counter=1;
					
		}
		System.out.println(sb);
		
	}

}

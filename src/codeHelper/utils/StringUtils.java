package codeHelper.utils;

public class StringUtils {
	
	//Repeats a specified string n times
	public static String repeat(String string, int n){
		StringBuilder sb = new StringBuilder(n);
		while(n-->0)
			sb.append(string);
		return sb.toString();
	}
	
}

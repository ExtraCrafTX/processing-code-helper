package codeHelper.utils;

public class StringUtils {
	
	public static String repeat(String string, int n){
		StringBuilder sb = new StringBuilder(n);
		while(n-->0)
			sb.append(string);
		return sb.toString();
	}
	
}

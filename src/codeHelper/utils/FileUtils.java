package codeHelper.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	
	public static File baseDir = new File("CodeCompleter");
	
	public static int assertExists(String dirName, String fileName, String[] lines){
		try{
			File dir = new File(baseDir, dirName);
			dir.mkdirs();
			File file = new File(dir, fileName);
			if(!file.exists()){
				PrintWriter writer = new PrintWriter(file);
				for(String line : lines){
					writer.println(line);
				}
				writer.close();
				return 0;
			}else{
				return 1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}
	
	public static String[] getLines(String dirName, String fileName){
		File dir = new File(baseDir, dirName);
		dir.mkdirs();
		File file = new File(dir, fileName);
		try{
			List<String> lines = new ArrayList<String>();
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null){
				lines.add(line);
			}
			reader.close();
			return lines.toArray(new String[lines.size()]);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}

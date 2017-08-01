package codeHelper.autoCompleter;

import codeHelper.utils.FileUtils;

public class AutoCompletes {
	
	static String fileName = "autocompletes.txt";
	static String[] completes = new String[]{
			"\\s*fori\\s*", "for(int i = 0; i < \\|; i++)'{'{0}  {0}}",
			"\\s*for (\\w+)\\s*", "for(int {1} = 0; {1} < \\|; {1}++)'{'{0}  {0}}",
			"\\s*(\\w+)\\s*", "void {1}()'{'{0}  \\|{0}}"
	};
	
	public static void loadCompletes(){
		int exists = FileUtils.assertExists("", fileName, completes);
		if(exists < 1)
			return;
		else{
			String[] lines = FileUtils.getLines("", fileName);
			if(lines.length % 2 != 0){
				System.out.println("AutoComplete file was corrupted. Using defaults, but file will not be overwritten.");
			}else{
				completes = lines;
			}
		}
	}
	
}
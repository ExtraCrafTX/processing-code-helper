package codeHelper.autoCompleter;

import codeHelper.utils.FileUtils;

public class AutoCompletes {
	
	static String fileName = "autocompletes.txt";
	//Default auto completes
	static String[] completes = new String[]{
			"\\s*fori\\s*", "for(int i = 0; i < \\|; i++)'{'{0}  {0}}",
			"\\s*for (\\w+)\\s*", "for(int {1} = 0; {1} < \\|; {1}++)'{'{0}  {0}}",
			"\\s*(\\w+)\\s*", "void {1}()'{'{0}  \\|{0}}"
	};
	
	//Load auto completes from file
	public static void loadCompletes(){
		//Make sure file exists
		int exists = FileUtils.assertExists("", fileName, completes);
		//If it didn't exist, leave defaults as is
		if(exists < 1)
			return;
		//Else load defaults into the completes array to be used
		else{
			String[] lines = FileUtils.getLines("", fileName);
			//Makes sure that there are an even number of lines, else file is corrupt
			if(lines.length % 2 != 0){
				System.out.println("AutoComplete file was corrupted. Using defaults, but file will not be overwritten.");
			}else{
				completes = lines;
			}
		}
	}
	
}
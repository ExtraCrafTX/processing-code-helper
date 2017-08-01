package codeHelper.autoCompleter;

import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import codeHelper.utils.EditorUtils;
import codeHelper.utils.StringUtils;
import processing.app.Base;
import processing.app.ui.Editor;

public class AutoCompleter {
	
	Base base;
	
	public AutoCompleter(Base base){
		this.base = base;
	}
	
	//When a key is typed
	public void onType(KeyEvent e){
		//Get the editor and the character that was typed
		Editor editor = base.getActiveEditor();
		char key = e.getKeyChar();
		//If the key was 'tab' try auto completing
		if(key=='\t'){
			//Get the text on the current line
			int lineNum = EditorUtils.getCurrentLine(editor);
			String line = editor.getLineText(lineNum);
			//Only auto complete if at the end of the line
			if(EditorUtils.isAtEndOfLine(editor, lineNum)){
				//Check if any auto completes apply
				for(int i = 0; i < AutoCompletes.completes.length; i+=2){
					//Compile the regex to check the line
					Pattern pattern = Pattern.compile(AutoCompletes.completes[i]);
					Matcher matcher = pattern.matcher(line);
					if(matcher.matches()){
						//If the line matched the current auto complete, run it
						int lineStart = editor.getLineStartOffset(lineNum);
						int start = editor.getPdeTextArea().getLineStartNonWhiteSpaceOffset(lineNum);
						int end = editor.getLineStopOffset(lineNum);
						int spaces = start - lineStart;
						//Make a string with spaces to maintain indentation
						String space = "\n" + StringUtils.repeat(" ", spaces);
						//Select the line so it can be replaced
						editor.setSelection(start, end-1);
						ArrayList<String> args = new ArrayList<String>();
						args.add(space);
						//Add any arguments (from regex) to ArrayList so they can be passed into format
						for(int j = 1;;j++){
							try{
								args.add(matcher.group(j));
							}catch(Exception exc){
								break;
							}
						}
						//Formats the string, ready to be replaced. Also looks for \| to find where to place the caret
						String formatted = MessageFormat.format(AutoCompletes.completes[i+1], args.toArray());
						int caretIndex = formatted.indexOf("\\|");
						//If caret pos not specified, put at end
						int caretRelative = (caretIndex == -1 ? formatted.length() : caretIndex) - formatted.length() + 2;
						formatted = formatted.replaceFirst("\\\\\\|", "");
						//Over write the text
						editor.getPdeTextArea().overwriteSetSelectedText(formatted);
						//Put caret in place
						EditorUtils.moveCaretRelative(editor, caretRelative);
						break;
					}
				}
			}
		}
	}
	
}

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
	
	public void onType(KeyEvent e){
		Editor editor = base.getActiveEditor();
		char key = e.getKeyChar();
		if(key=='\t'){
			int lineNum = EditorUtils.getCurrentLine(editor);
			String line = editor.getLineText(lineNum);
			if(EditorUtils.isAtEndOfLine(editor, lineNum)){
				for(int i = 0; i < AutoCompletes.completes.length; i+=2){
					Pattern pattern = Pattern.compile(AutoCompletes.completes[i]);
					Matcher matcher = pattern.matcher(line);
					if(matcher.matches()){
						int lineStart = editor.getLineStartOffset(lineNum);
						int start = editor.getPdeTextArea().getLineStartNonWhiteSpaceOffset(lineNum);
						int end = editor.getLineStopOffset(lineNum);
						int spaces = start - lineStart;
						String space = "\n" + StringUtils.repeat(" ", spaces);
						editor.setSelection(start, end-1);
						ArrayList<String> args = new ArrayList<String>();
						args.add(space);
						for(int j = 1;;j++){
							try{
								args.add(matcher.group(j));
							}catch(Exception exc){
								break;
							}
						}
						String formatted = MessageFormat.format(AutoCompletes.completes[i+1], args.toArray());
						int caretRelative = formatted.indexOf("\\|") - formatted.length() + 2;
						formatted = formatted.replaceFirst("\\\\\\|", "");
						editor.getPdeTextArea().overwriteSetSelectedText(formatted);
						EditorUtils.moveCaretRelative(editor, caretRelative);
						break;
					}
				}
			}
		}
	}
	
}

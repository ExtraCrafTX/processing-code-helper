package codeHelper.utils;

import processing.app.ui.Editor;

public class EditorUtils {
	
	//Gets the line the caret is currently on
	public static int getCurrentLine(Editor editor){
		int caret = editor.getCaretOffset();
		for(int i = 0; i < editor.getLineCount(); i++){
			if(editor.getLineStartOffset(i)<=caret && editor.getLineStopOffset(i)>=caret){
				return i;
			}
		}
		return -1;
	}
	
	//Returns whether the caret is at the end of the line
	public static boolean isAtEndOfLine(Editor editor, int line){
		int caret = editor.getCaretOffset();
		return caret==editor.getLineStopOffset(line)-1;
	}
	
	//Moves the caret to a specific offset
	public static void moveCaretTo(Editor editor, int pos){
		editor.setSelection(pos, pos);
	}
	
	//Moves the caret relative to its current location
	public static void moveCaretRelative(Editor editor, int dist){
		int pos = editor.getCaretOffset()+dist;
		moveCaretTo(editor, pos);
	}
	
}

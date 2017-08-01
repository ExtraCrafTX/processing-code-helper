package codeHelper.utils;

import processing.app.ui.Editor;

public class EditorUtils {
	
	public static int getCurrentLine(Editor editor){
		int caret = editor.getCaretOffset();
		for(int i = 0; i < editor.getLineCount(); i++){
			if(editor.getLineStartOffset(i)<=caret && editor.getLineStopOffset(i)>=caret){
				return i;
			}
		}
		return -1;
	}
	
	public static boolean isAtEndOfLine(Editor editor, int line){
		int caret = editor.getCaretOffset();
		return caret==editor.getLineStopOffset(line)-1;
	}
	
	public static void moveCaretTo(Editor editor, int pos){
		editor.setSelection(pos, pos);
	}
	
	public static void moveCaretRelative(Editor editor, int dist){
		int pos = editor.getCaretOffset()+dist;
		moveCaretTo(editor, pos);
	}
	
}

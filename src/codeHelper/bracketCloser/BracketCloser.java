package codeHelper.bracketCloser;

import java.awt.event.KeyEvent;

import processing.app.Base;
import processing.app.ui.Editor;

public class BracketCloser {
	
	Base base;
	char last;
	
	char[] opening = new char[]{'(', '{', '[', '\'', '"'};
	char[] closing = new char[]{')', '}', ']', '\'', '"'};
	
	public BracketCloser(Base base){
		this.base = base;
	}
	
	public void onType(KeyEvent e){
		char key = e.getKeyChar();
		Editor editor = base.getActiveEditor();
		for(int i = 0; i < opening.length; i++) {
			if(key == opening[i]){
				if(editor.isSelectionActive()){
					wrapSelection(i, editor);
					e.setKeyChar(closing[i]);
					break;
				}else{
					closeBracket(i, editor);
					last = key;
				}
			}else if(key == closing[i] && opening[i] == last){
				last = key;
				if(editor.getText().charAt(editor.getCaretOffset()) == closing[i]){
					e.setKeyChar((char)0);
					editor.setSelection(editor.getCaretOffset()+1, editor.getCaretOffset()+1);
				}
			}
		}
	}
	
	public void closeBracket(int i, Editor editor){
		editor.insertText(Character.toString(closing[i]));
		int caret = editor.getCaretOffset()-1;
		editor.setSelection(caret, caret);
	}
	
	public void wrapSelection(int i, Editor editor){
		int start = editor.getSelectionStart();
		int end = editor.getSelectionStop()+1;
		editor.setSelection(start, start);
		editor.insertText(Character.toString(opening[i]));
		editor.setSelection(end, end);
	}
	
}
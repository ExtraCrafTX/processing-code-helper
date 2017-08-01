/**
 * Adds some neat features to speed up programming.
 *
 * ##copyright##
 *
 * This tool is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This tool is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with this tool; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 *
 * @author   ##author##
 * @modified ##date##
 * @version  ##tool.prettyVersion##
 */

package codeHelper;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import codeHelper.autoCompleter.AutoCompleter;
import codeHelper.autoCompleter.AutoCompletes;
import codeHelper.bracketCloser.BracketCloser;
import processing.app.Base;
import processing.app.tools.Tool;
import processing.app.ui.Editor;

public class CodeHelper implements Tool, KeyListener {
	
	//Stores instance of the processing application
	Base base;
	//Stores instance of the BracketCloser
	BracketCloser bracketCloser;
	//Stores instance of the AutoCompleter
	AutoCompleter completer;

	//Returns the name shown in the menu
	public String getMenuTitle() {
		return "##tool.name##";
	}

	//Run on initialisation of the tool (on startup of processing)
	public void init(Base base) {
		// Store a reference to the Processing application itself
		this.base = base;
		//Create new instances for the modules and store them
		bracketCloser = new BracketCloser(base);
		completer = new AutoCompleter(base);
	}

	//Executed when the tool is selected in the tool menu
	//Adds a key listener to the editor
	public void run() {
		Editor editor = base.getActiveEditor();
		editor.removeKeyListener(this);
		editor.getTextArea().addKeyListener(this);
		AutoCompletes.loadCompletes();
		System.out.println("CodeHelper is now active!");
	}
	
	//When a key is pressed
	@Override
	public void keyPressed(KeyEvent e) {}

	//When a key is released
	@Override
	public void keyReleased(KeyEvent e) {}

	//When a key is typed. Passes on to the modules
	@Override
	public void keyTyped(KeyEvent e) {
		bracketCloser.onType(e);
		completer.onType(e);
	}
}

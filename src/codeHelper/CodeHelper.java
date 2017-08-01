/**
 * you can put a one sentence description of your tool here.
 *
 * ##copyright##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
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

// when creating a tool, the name of the main class which implements Tool must
// be the same as the value defined for project.name in your build.properties

public class CodeHelper implements Tool, KeyListener {
	Base base;
	boolean running;
	BracketCloser bracketCloser;
	AutoCompleter completer;

	public String getMenuTitle() {
		return "##tool.name##";
	}

	public void init(Base base) {
		// Store a reference to the Processing application itself
		this.base = base;
		bracketCloser = new BracketCloser(base);
		completer = new AutoCompleter(base);
	}

	public void run() {
		Editor editor = base.getActiveEditor();
		editor.removeKeyListener(this);
		editor.getTextArea().addKeyListener(this);
		AutoCompletes.loadCompletes();
		System.out.println("CodeHelper is now active!");
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {
		bracketCloser.onType(e);
		completer.onType(e);
	}
}

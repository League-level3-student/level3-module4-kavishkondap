package _00_IntroToStacks;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class _02_TextUndoRedo implements KeyListener{
    /* 
     * Create a JFrame with a JPanel and a JLabel.
     * 
     * Every time a key is pressed, add that character to the JLabel. It should
     * look like a basic text editor.
     * 
     * Make it so that every time the BACKSPACE key is pressed, the last
     * character is erased from the JLabel.
     * 
     * Save that deleted character onto a Stack of Characters.
     * 
     * Choose a key to be the Undo key. Make it so that when that key is
     * pressed, the top Character is popped  off the Stack and added back to
     * the JLabel.
     */
	 JFrame frame = new JFrame ();
	 JPanel panel = new JPanel ();
	 JLabel label = new JLabel ();
	 Stack <String> delete = new Stack ();
	public  void setup () {
		frame.add(panel);
		panel.add(label);
		frame.addKeyListener(this);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(1000, 600);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_ALT) {
			label.setText(label.getText()+delete.pop());
		}else if (arg0.getKeyCode () == KeyEvent.VK_BACK_SPACE) {
			StringBuilder sb = new StringBuilder (label.getText());
			delete.push(sb.charAt(label.getText().length()-1)+"");
			String newLabel = sb.deleteCharAt(label.getText().length()-1).toString();
			label.setText(newLabel);
		}
		else {
			label.setText(label.getText() + arg0.getKeyChar());
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

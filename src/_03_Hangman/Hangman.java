package _03_Hangman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements KeyListener {
	JFrame frame = new JFrame ();
	JPanel panel = new JPanel ();
	JLabel label = new JLabel ();
	JLabel lives = new JLabel ();
	int live = 3;
	int numWords;
	String dashes;
	String curWord;
	Stack <String> words = new Stack ();
	boolean close = false;
	public void setup () {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.add(label);
		panel.add(lives);
		frame.addKeyListener(this);
		lives.setText ("" + live);
		String amtWords = JOptionPane.showInputDialog(null, "How many words?");
		numWords = Integer.parseInt(amtWords);
		for (int i = 0; i < numWords; i++) {
			String word = Utilities.readRandomLineFromFile ("dictionary.txt");
			while (words.contains(word)) {
				word = Utilities.readRandomLineFromFile ("dictionary.txt");
			}
			words.push (word);
		}
		run();
	}
	public void run () {
		if (!close) {
			curWord = words.pop();
			dashes = "";
			for (int i = 0; i < curWord.length(); i++) {
				dashes+= "-";
			}
			label.setText(dashes);
			lives.setText("Lives: " + live);
			frame.pack();
		}
		else {
			frame.dispose();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (curWord.contains(Character.toString(e.getKeyChar()))){
			ArrayList <Integer> index = new ArrayList ();
			ArrayList <String> letters = new ArrayList ();
			for (int i = 0; i < curWord.length(); i++) {
				letters.add(curWord.substring(i, i+1));
			}
			for (int i = 0; i < letters.size(); i++) {
				if (letters.get (i).equals (Character.toString((e.getKeyChar ())))) {
					index.add(i);
				}
			}
			StringBuilder sb = new StringBuilder (dashes);
			for (int i = 0; i < index.size (); i++){
				dashes = sb.replace(index.get(i), index.get(i)+1, Character.toString(e.getKeyChar())).toString();
			}
			if (dashes.equals(curWord)) {
				JOptionPane.showMessageDialog(null, "Congrats you got the word!");
				if (words.size()>0) {
					run ();
				}else {
					String input = JOptionPane.showInputDialog(null, "YOU WIN!!! Would you like to play again");
					if (!input.contains("y")) {
						frame.dispose();
					}
				}
			}
			
		}else {
			live--;
			if (live == 0) {
				String playAgain = JOptionPane.showInputDialog(null, "You lost all your lives! Would you like to play again?");
				if (playAgain.equalsIgnoreCase("no")) {
					//frame.dispose();
					close = true;
				}
			}
		}
		label.setText(dashes);
		lives.setText("Lives: " + live);
		System.out.println(e.getKeyChar() + "\n" + dashes + "\n" + curWord + "\n" + live);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

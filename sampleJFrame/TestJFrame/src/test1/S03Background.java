// Background colorを設定
package test1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

class S03Background extends JFrame {
	
	S03Background(String title) {
		setTitle(title);
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JButton btn = new JButton("Open");
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.ORANGE);
		contentPane.add(btn, BorderLayout.NORTH);
	}
	
	public static void main(String args[]) {
		S03Background frame = new S03Background("AppTitle");
		frame.setVisible(true);
	}
}
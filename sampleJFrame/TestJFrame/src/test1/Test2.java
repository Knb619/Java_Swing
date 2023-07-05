package test1;

import javax.swing.JFrame;

class Test2 extends JFrame {
	Test2(String title) {
		setTitle(title);
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) {
		Test2 frame = new Test2("Test2");
		frame.setVisible(true);
	}
}
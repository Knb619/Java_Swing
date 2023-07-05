package test1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

class S02LayoutMgr extends JFrame {
	
	S02LayoutMgr(String title) {
		Container contentPane = new Container();
		contentPane.setLayout(new FlowLayout());
	}
	
	public static void main(String args[]) {
		
	}
}
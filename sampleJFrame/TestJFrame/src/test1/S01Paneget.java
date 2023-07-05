package test1;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;

class S01Paneget extends JFrame {
	
	S01Paneget(String title) {
		setTitle(title);
		setBounds(100, 100, 1000, 400);
		
		JButton btn = new JButton("MyButton"); // Bottunオブジェクトのインスタンス化
		
		Container contentPane = getContentPane(); // ContentPaneのオブジェクトを取得
		contentPane.add(btn, BorderLayout.NORTH);
//		contentPane.add(btn); // 第二引数省略時は、CENTERとみなされる
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) {
		S01Paneget frame = new S01Paneget("MyTitle");
		frame.setVisible(true);
		
	}
}
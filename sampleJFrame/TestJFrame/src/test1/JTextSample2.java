// TextField 色指定
package test1;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class JTextSample2 extends JFrame {
	JTextSample2(String title) {
		setTitle(title);
		setBounds(10, 10, 1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextField text1 = new JTextField("TextField test");
		// JTextField text1 = new JTextField();
		JTextField text2 = new JTextField("Tokyo", 10);
		JTextField text3 = new JTextField("before");
		text3.setColumns(20);
		text3.setText("after");
		
		JTextField text4 = new JTextField("Back ground color test");
		
		// 前景色 (文字)
		text1.setForeground(Color.GREEN);
		text2.setForeground(Color.BLUE);
		text3.setForeground(Color.MAGENTA);
		text4.setForeground(Color.WHITE);
		
		// 背景色 (デフォルトは透明となっているので、背景色を付けたい場合は可視化する必要あり)
		text4.setOpaque(true); // 背景を可視化
		text4.setBackground(Color.BLACK);
		
		JPanel panel1 = new JPanel();
		panel1.add(text1);
		panel1.add(text2);
		panel1.add(text3);
		panel1.add(text4);

		Container container = getContentPane();
		container.add(panel1);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JTextSample2("JPanelテスト"));
	}
}

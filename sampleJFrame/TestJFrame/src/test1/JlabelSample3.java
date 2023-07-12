// ラベルの文字フォント設定
package test1;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JlabelSample3 extends JFrame {
	JlabelSample3(String title) {
		setTitle(title);
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("ラベルテスト");
		label1.setFont(new Font("MS ゴシック", Font.BOLD, 30)); // フォント設定(物理)
		JLabel label2 = new JLabel("ラベルテスト2");
		label2.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24)); // フォント設定(論理)
		panel1.add(label1);
		panel1.add(label2);
		
		Container container = getContentPane();
		container.add(panel1);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JlabelSample3("JPanelテスト"));
	}
}

// ラベルの文字列の色 前景色と背景色
package test1;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JlabelSample2 extends JFrame {
	JlabelSample2(String title) {
		setTitle(title);
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("ラベルテスト");
		JLabel label2 = new JLabel("ラベルテスト2");
		label1.setOpaque(true);// ラベル1の背景を可視化
		label1.setBackground(Color.ORANGE); // 背景色設定(背景はデフォルトで透明のため、表示有効に切り替える必要あり)
		label2.setForeground(Color.BLUE); // 前景色設定
		panel1.add(label1);
		panel1.add(label2);
		
		Container container = getContentPane();
		container.add(panel1);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JlabelSample2("JPanelテスト"));
	}
}

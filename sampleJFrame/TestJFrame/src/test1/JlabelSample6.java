// 画像を表示 画像のみ表示・画像と文字を合わせて表示・文字位置のカスタマイズ
package test1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JlabelSample6 extends JFrame {
	JlabelSample6(String title) {
		setTitle(title);
		setBounds(10, 10, 1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();
		
		JLabel label1 = new JLabel("ラベルテスト");
		label1.setOpaque(true);
		label1.setBackground(Color.ORANGE);
		label1.setPreferredSize(new Dimension(100, 100));
		label1.setHorizontalAlignment(JLabel.CENTER); // 水平位置を指定
		
		ImageIcon icon1 = new ImageIcon("./Resources/img/european-shorthair.jpg"); // アイコン画像を取得
		JLabel label2 = new JLabel(icon1); // ラベルにアイコンをセット
//		JLabel label2 = new JLabel("ラベル2");
		label2.setPreferredSize(new Dimension(300, 300));
		
		JLabel label3 = new JLabel("画像未登録");
		label3.setPreferredSize(new Dimension(600, 300));
		ImageIcon icon2 = new ImageIcon("./Resources/img/kitten.jpg");
		label3.setIcon(icon2); // 空のラベルに後から画像をセット
		
		ImageIcon icon3 = new ImageIcon("./Resources/img/kitten2.jpg");
		JLabel label4 = new JLabel("子猫", icon3, JLabel.CENTER); // 画像と文字を同時表示
		
		ImageIcon icon4 = new ImageIcon("./Resources/img/cat-gbe.jpg");
		JLabel label5 = new JLabel(icon4);
		label5.setText("文字列をあとから登録"); // 画像のみのラベルに後から文字を追加
		label5.setVerticalTextPosition(JLabel.TOP); // ラベル内の文字の垂直位置を指定
		label5.setHorizontalTextPosition(JLabel.CENTER); // ラベル内の文字の水平位置を指定
		
		panel1.add(label1);
		panel1.add(label2);
		panel1.add(label3);
		panel1.add(label4);
		panel1.add(label5);
		
		Container container = getContentPane();
		container.add(panel1);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JlabelSample6("JPanelテスト"));
	}
}

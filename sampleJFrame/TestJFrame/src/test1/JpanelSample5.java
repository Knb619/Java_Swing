// パネルの枠線とサイズ変更
package test1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class JpanelSample5 extends JFrame {
	JpanelSample5(String title) {
		setTitle(title);
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		// パネル1 パネルサイズ指定なし 枠線なし
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.BLUE);
//		panel1.setPreferredSize(new Dimension(100, 100));
		JButton bottun1 = new JButton("Open");
		panel1.add(bottun1);
		
		// パネル2 パネルサイズ指定あり 枠線あり
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.RED);
		panel2.setPreferredSize(new Dimension(200, 200)); // パネルサイズ指定
		JButton bottun2 = new JButton("Close");
		panel2.add(bottun2);
		LineBorder border = new LineBorder(Color.BLACK, 5, true); // 枠線定義
		panel2.setBorder(border); // 枠線追加

		Container containtPane = getContentPane();
		containtPane.add(panel1);
		containtPane.add(panel2);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JpanelSample5("JPanelテスト"));
	}
}

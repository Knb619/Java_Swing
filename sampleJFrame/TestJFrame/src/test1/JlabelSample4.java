// ラベルのサイズと枠線
package test1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class JlabelSample4 extends JFrame {
	JlabelSample4(String title) {
		setTitle(title);
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();
		
		JLabel label1 = new JLabel("ラベルテスト");
		label1.setOpaque(true);
		label1.setBackground(Color.ORANGE);
		label1.setPreferredSize(new Dimension(100, 100));
		
		JLabel label2 = new JLabel("ラベルテスト2");
		label2.setOpaque(true);
		label2.setBackground(Color.MAGENTA);
		label2.setPreferredSize(new Dimension(200, 100)); // ラベルサイズ指定
		LineBorder border1 = new LineBorder(Color.BLACK, 5, true);
		label2.setBorder(border1);
		panel1.add(label1);
		panel1.add(label2);
		
		Container container = getContentPane();
		container.add(panel1);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JlabelSample4("JPanelテスト"));
	}
}

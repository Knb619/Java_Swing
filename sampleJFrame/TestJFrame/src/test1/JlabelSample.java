package test1;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JlabelSample extends JFrame {
	JlabelSample(String title) {
		setTitle(title);
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();		
		JLabel label1 = new JLabel("ラベルテスト");
		JLabel label2 = new JLabel("before update");
		label2.setText("after update"); // ラベルの文字列は後から上書き可能
		panel1.add(label1);
		panel1.add(label2);
		
		Container container = getContentPane();
		container.add(panel1);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JlabelSample("JPanelテスト"));
	}
}

// パネルのバックグラウンドカラー変更

package test1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JpanelSample4 extends JFrame {
	JpanelSample4(String title) {
		setTitle(title);
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		// パネル1
		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(200, 100));
		panel1.setBackground(Color.RED);
		JButton button1 = new JButton("Open");
		panel1.add(button1);
		
		// パネル2
		JPanel panel2= new JPanel();
		panel2.setPreferredSize(new Dimension(200, 100));
		panel2.setBackground(Color.BLUE);
		JButton button2 = new JButton("Close");
		panel2.add(button2);
		
		Container containtPane = getContentPane();
		containtPane.add(panel1);
		containtPane.add(panel2);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JpanelSample4("JPanelテスト"));
	}
}

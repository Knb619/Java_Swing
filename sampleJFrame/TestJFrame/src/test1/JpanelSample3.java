// レイアウトマネージャを変更(パネルで使用されるデフォルトのレイアウトマネージャーは FlowLayout)

package test1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JpanelSample3 extends JFrame {
	JpanelSample3(String title) {
		setTitle(title);
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout()); // レイアウトマネージャを変更
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 200));
		panel.setLayout(new BorderLayout()); // レイアウトマネージャを変更
		
		JButton button1 = new JButton("NORTH");
		JButton button2 = new JButton("WEST");
		JButton button3 = new JButton("SOUTH");
		JButton button4 = new JButton("EAST");
		
		panel.add(button1, BorderLayout.NORTH);
		panel.add(button2, BorderLayout.WEST);
		panel.add(button3, BorderLayout.SOUTH);
		panel.add(button4, BorderLayout.EAST);
		
		Container containtPane = getContentPane();
		containtPane.add(panel, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JpanelSample3("JPanelテスト"));
	}
}

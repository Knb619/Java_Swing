// パネルにコンポーネント追加(JButton)

package test1;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JpanelSample2 extends JFrame {
	JpanelSample2(String title) {
		setTitle(title);
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JButton bottun1 = new JButton("Open");
		JButton bottun2 = new JButton("Cancel");
		JButton bottun3 = new JButton("Close");
		panel.add(bottun1);
		panel.add(bottun2);
		panel.add(bottun3);
		
		Container containtPane = getContentPane();
		containtPane.add(panel, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JpanelSample2("JPanelテスト"));
	}
}

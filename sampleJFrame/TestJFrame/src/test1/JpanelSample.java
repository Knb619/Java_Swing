package test1;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class JpanelSample extends JFrame {
	JpanelSample(String title) {
		setTitle(title);
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JLabel label = new JLabel("Name");
		JTextField text = new JTextField(20);
		
		panel.add(label);
		panel.add(text);
		
		Container containtPane = getContentPane();
		containtPane.add(panel, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JpanelSample("JPanelテスト"));
	}
}

// TextField
package test1;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class JTextSample extends JFrame {
	JTextSample(String title) {
		setTitle(title);
		setBounds(10, 10, 1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextField text1 = new JTextField("TextField test");
		// JTextField text1 = new JTextField();
		JTextField text2 = new JTextField("Tokyo", 10);
		JTextField text3 = new JTextField("before");
		text3.setColumns(20);
		text3.setText("after");
		
		JPanel panel1 = new JPanel();
		panel1.add(text1);
		panel1.add(text2);
		panel1.add(text3);

		Container container = getContentPane();
		container.add(panel1);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JTextSample("JPanelテスト"));
	}
}

// TextField フォント指定
package test1;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JTextSample3 extends JFrame {
	JTextSample3(String title) {
		setTitle(title);
		setBounds(10, 10, 1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JPanel panel1 = new JPanel();
		panel1.add();

		Container container = getContentPane();
		container.add(panel1);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JTextSample3("JPanelテスト"));
	}
}

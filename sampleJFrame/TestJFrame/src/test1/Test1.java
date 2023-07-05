package test1;

import javax.swing.JFrame;

public class Test1 extends JFrame {
	public static void main(String args[]) {
		final String frameTitle = "Swing tutorial";
		
		// --- 初期設定 -------------------------------------------------
		JFrame frame = new JFrame(); // frameを生成
		
		frame.setTitle(frameTitle); // タイトル設定
		frame.setSize(900, 600); // Windowサイズ指定
		frame.setLocationRelativeTo(null); // frameのウィンドウの位置を画面中央に配置する
		
		frame.setVisible(true); // frameを可視化 (初期状態は非表示のため)
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE); // Windowを閉じるボタンクリック時の挙動を定義
		
		//frame.setBounds(220, 20, 900, 600);	// frameのサイズと位置を同時に指定
		//frame.setLocation(220, 20);// Windowの初期ロケーションを指定 引数⇒(x:横, y:縦)
		
		
	}
}

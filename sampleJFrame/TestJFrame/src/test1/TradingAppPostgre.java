package test1;

import javax.swing.*;

import org.postgresql.core.Tuple;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class ConnectDB {
	private String url;
	private String userName;
	private String password;
	private Connection connection;
	
	ConnectDB(String url, String userName, String password) {
		this.url = url;
		this.userName = userName;
		this.password = password;
		
		try {
			connection = DriverManager.getConnection(url, userName, password);
			
		} catch (SQLException e) {
			System.out.println("[ERROR] SQLExceptions are raised.");
			e.printStackTrace();
			
		} catch (Exception e) {
			System.out.println("[ERROR] Exceptions are raised.");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}
}

public class TradingAppPostgre extends JFrame {
	public TradingAppPostgre() {
        // ウィンドウの設定
        setTitle("Stock Trading App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // メインパネルの作成
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));

        // 銘柄情報パネルの作成
        JPanel stockInfoPanel = new JPanel();
        stockInfoPanel.setBorder(BorderFactory.createTitledBorder("銘柄情報"));
        stockInfoPanel.setLayout(new GridLayout(3, 2));

        JLabel symbolLabel = new JLabel("銘柄コード:");
        JTextField symbolTextField = new JTextField();
        JLabel priceLabel = new JLabel("株価:");
        JTextField priceTextField = new JTextField();
        JLabel volumeLabel = new JLabel("出来高:");
        JTextField volumeTextField = new JTextField();

        stockInfoPanel.add(symbolLabel);
        stockInfoPanel.add(symbolTextField);
        stockInfoPanel.add(priceLabel);
        stockInfoPanel.add(priceTextField);
        stockInfoPanel.add(volumeLabel);
        stockInfoPanel.add(volumeTextField);

        // 注文パネルの作成
        JPanel orderPanel = new JPanel();
        orderPanel.setBorder(BorderFactory.createTitledBorder("注文"));
        orderPanel.setLayout(new GridLayout(2, 2));

        JLabel quantityLabel = new JLabel("数量:");
        JTextField quantityTextField = new JTextField();
        JButton buyButton = new JButton("買う");
        JButton sellButton = new JButton("売る");

        orderPanel.add(quantityLabel);
        orderPanel.add(quantityTextField);
        orderPanel.add(buyButton);
        orderPanel.add(sellButton);

        // メインパネルに銘柄情報パネルと注文パネルを追加
        mainPanel.add(stockInfoPanel);
        mainPanel.add(orderPanel);

        // メインパネルをフレームに追加
        add(mainPanel, BorderLayout.CENTER);
        
        // 注文ボタンのアクションリスナーを設定
        buyButton.addActionListener(e -> {
            String symbol = symbolTextField.getText(); // 銘柄
            int quantity = Integer.parseInt(quantityTextField.getText()); // 数量
            if (symbol != null && quantity != 0) {
            	insertOrder(symbol, quantity, "buy"); // モード(buy:買取 sell 売却)
            }
        });

        sellButton.addActionListener(e -> {
            String symbol = symbolTextField.getText(); // 銘柄
            int quantity = Integer.parseInt(quantityTextField.getText()); // 数量
            if (symbol != null && quantity != 0) {
            	insertOrder(symbol, quantity, "sell"); // モード(buy:買取 sell 売却)
            }
        });
        
        // 履歴パネルの設定
        JPanel historyPanel = new JPanel();
        historyPanel.setBorder(BorderFactory.createTitledBorder("履歴"));
        historyPanel.setLayout(new GridLayout(1, 2));
        
        JButton historyButton = new JButton("履歴");
        
        // 履歴パネルに履歴ボタン追加
        historyPanel.add(historyButton);
        
        // 履歴パネルをフレームに追加
        add(historyPanel, BorderLayout.SOUTH);

        // ウィンドウを表示
        setVisible(true);
    }
	
	private void insertOrder(String symbol, int quantity, String type) {
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String userName = "postgres";
		String password = "2bdi2109";
		
		Connection connectObj;
		ConnectDB connection = new ConnectDB(url, userName, password);
		
		connectObj = connection.getConnection();
		
		String sql = "INSERT INTO orders (Symbol, quantity, type) VALUES (?, ?, ?);";
		
		try {
			PreparedStatement statement = connectObj.prepareStatement(sql);
			statement.setString(1, symbol);
			statement.setInt(2, quantity);
			statement.setString(3, type);
			statement.executeUpdate();
			JOptionPane.showMessageDialog(this, "注文が追加されました。");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "注文の追加に失敗しました。");
			e.printStackTrace();
			
		} catch (Exception e) {
			System.out.println("[ERROR] Exceptions are raised.");
			e.printStackTrace();
		}
	}
	
	private void serchHistory() {
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String userName = "postgres";
		String password = "2bdi2109";
		
		Connection connectObj;
		ConnectDB connection = new ConnectDB(url, userName, password);
		
		connectObj = connection.getConnection();
		
		String sql = "SELECT * FROM order;";
		
		try {
			PreparedStatement statement = connectObj.prepareStatement(sql);
			statement.executeQuery();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "履歴の取得に失敗しました。");
			e.printStackTrace();
			
		} catch (Exception e) {
			System.out.println("[ERROR] Exceptions are raised.");
			e.printStackTrace();
		}
	}

    public static void main(String[] args) {
        // メインスレッドでUIを作成
        SwingUtilities.invokeLater(() -> new TradingAppPostgre());
    }
}

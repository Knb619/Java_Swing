package test1;

import javax.swing.*;

import org.postgresql.core.Tuple;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class ConnectDB {
	private final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private final String USER_NAME = "postgres";
	private final String PASSWORD = "2bdi2109";

	private Connection connection = null;
	
	ConnectDB() {
		try {
			connection = DriverManager.getConnection(this.URL, this.USER_NAME, this.PASSWORD);
			System.out.println("Connected to the PostgreSQL server successfully.");
			
		} catch (SQLException e) {
			System.out.println("[ERROR] SQLExceptions are raised.");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
}

public class TradingAppPostgre extends JFrame {
	public TradingAppPostgre() {
        // --- ウィンドウの設定 --------------------------------------------
        setTitle("Stock Trading App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // --- メインパネルの作成 --------------------------------------------
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));

        // --- 銘柄情報パネルの作成 --------------------------------------------
        JPanel stockInfoPanel = new JPanel();
        stockInfoPanel.setBorder(BorderFactory.createTitledBorder("銘柄情報"));
        stockInfoPanel.setLayout(new GridLayout(3, 2));
        
        // 銘柄情報パネルに設置するコンポーネントを生成
        JLabel symbolLabel = new JLabel("銘柄コード:");
        JTextField symbolTextField = new JTextField();
        JLabel priceLabel = new JLabel("株価:");
        JTextField priceTextField = new JTextField();
        JLabel volumeLabel = new JLabel("出来高:");
        JTextField volumeTextField = new JTextField();

        // 銘柄情報パネルに各コンポーネントを追加
        stockInfoPanel.add(symbolLabel);
        stockInfoPanel.add(symbolTextField);
        stockInfoPanel.add(priceLabel);
        stockInfoPanel.add(priceTextField);
        stockInfoPanel.add(volumeLabel);
        stockInfoPanel.add(volumeTextField);

        // --- 注文パネルの作成 --------------------------------------------
        JPanel orderPanel = new JPanel();
        orderPanel.setBorder(BorderFactory.createTitledBorder("注文"));
        orderPanel.setLayout(new GridLayout(2, 2));

        // 注文パネルに設置するコンポーネントを生成
        JLabel quantityLabel = new JLabel("数量:");
        JTextField quantityTextField = new JTextField();
        JButton buyButton = new JButton("買う");
        JButton sellButton = new JButton("売る");

        // 注文パネルに各コンポーネントを追加
        orderPanel.add(quantityLabel);
        orderPanel.add(quantityTextField);
        orderPanel.add(buyButton);
        orderPanel.add(sellButton);

        // --- メインパネルに銘柄情報パネルと注文パネルを追加 --------------------------------------------
        mainPanel.add(stockInfoPanel);
        mainPanel.add(orderPanel);

        // --- メインパネルをフレームに追加 --------------------------------------------
        add(mainPanel, BorderLayout.CENTER);
        
        // --- 履歴パネルの設定 --------------------------------------------
        JPanel historyPanel = new JPanel();
        historyPanel.setBorder(BorderFactory.createTitledBorder("履歴"));
        historyPanel.setLayout(new GridLayout(1, 2));
        
        JButton historyButton = new JButton("履歴画面表示");
        
        // 履歴パネルに履歴ボタン追加
        historyPanel.add(historyButton);
        
        // 履歴パネルをフレームに追加
        add(historyPanel, BorderLayout.SOUTH);
        
        // --- アクションリスナー --------------------------------------------
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
        
        historyButton.addActionListener(e -> {
        	JFrame historyFrame = new JFrame("履歴");
        	historyFrame.setSize(800, 600);
        	
        	// 履歴表示用パネルを作成
        	// レイアウトマネージャを使用しない自由配置のパネルを作成
        	JPanel contentPanel = new JPanel(null);
        	
        	// 項目名表示用パネル作成
        	JPanel headerPanel = new JPanel(new GridLayout(1, 3));
        	headerPanel.add(new JLabel("銘柄"));
        	headerPanel.add(new JLabel("価格"));
        	headerPanel.add(new JLabel("購買区分"));
        	headerPanel.setBounds(1, 10, 200, 20);
        	contentPanel.add(headerPanel);
        	
        	// スクロールペイン作成 (https://www.javadrive.jp/tutorial/jscrollpane/index1.html)
        	JScrollPane scrollPane = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        	scrollPane.setViewportView(contentPanel);
//        	contentPanel.add(scrollPane);
//        	contentPanel.add(scrollPane, BorderLayout.CENTER);
        	
        	// DBから履歴を検索
        	try {
	        	ResultSet resultset = fetchHistory();
	        	if (resultset != null) {
	        		int row = 1;
//	        		int row = 0;
	        		while(resultset.next()) {
	        			String symbol = resultset.getString("symbol");
	        			int price = resultset.getInt("quantity");
	        			String type = resultset.getString("type");
	        			
	        			// 銘柄表示エリア
	        			JLabel symbolLabal = new JLabel(symbol);
	        			symbolLabal.setBounds(1, row * 30, 200, 20);
//	        			symbolLabal.setBounds(30, row * 30, 200, 20);
	                    contentPanel.add(symbolLabal);
	
	                    // 価格表示エリア
	                    JLabel priceLabel2 = new JLabel(String.valueOf(price));
	                    priceLabel2.setBounds(111, row * 30, 200, 20);
//	                    priceLabel2.setBounds(140, row * 30, 200, 20);
	                    contentPanel.add(priceLabel2);
	
	                    // 購買区分表示エリア
	                    JLabel typeLabel = new JLabel(type);
	                    typeLabel.setBounds(221, row * 30, 200, 20);
//	                    typeLabel.setBounds(250, row * 30, 200, 20);
	                    contentPanel.add(typeLabel);
	
	                    row++;
	        		}
	        		contentPanel.setBounds(0, 0, 600, row * 30 + 20);
	        	}
        	} catch (SQLException e2) {
        		System.out.println("[ERROR] SQLExceptions are raised.");
    			e2.printStackTrace();
			}
        	// 履歴ウィンドウを表示
        	historyFrame.add(contentPanel);
        	historyFrame.setVisible(true);
        });
//        historyButton.addActionListener(e -> {
//        	JFrame historyFrame = new JFrame("履歴");
//        	historyFrame.setSize(800, 600);
//        	
//        	// パネル作成
//        	JPanel historyPanel2 = new JPanel();
//        	historyPanel2.setLayout(new BorderLayout());
//        	
//        	// 履歴表示用パネルを作成
//        	// レイアウトマネージャを使用しない自由配置のパネルを作成
//        	JPanel contentPanel = new JPanel(null);
//        	
//        	// 項目名表示用パネル作成
//        	JPanel headerPanel = new JPanel(new GridLayout(1, 3));
//        	headerPanel.add(new JLabel("銘柄"));
//        	headerPanel.add(new JLabel("価格"));
//        	headerPanel.add(new JLabel("購買区分"));
//        	headerPanel.setBounds(1, 10, 200, 20);
//        	contentPanel.add(headerPanel);
//        	
//        	// スクロールペイン作成 (https://www.javadrive.jp/tutorial/jscrollpane/index1.html)
//        	JScrollPane scrollPane = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        	scrollPane.setViewportView(contentPanel);
////        	contentPanel.add(scrollPane);
////        	contentPanel.add(scrollPane, BorderLayout.CENTER);
//        	
//        	// DBから履歴を検索
//        	try {
//	        	ResultSet resultset = fetchHistory();
//	        	if (resultset != null) {
//	        		int row = 1;
////	        		int row = 0;
//	        		while(resultset.next()) {
//	        			String symbol = resultset.getString("symbol");
//	        			int price = resultset.getInt("quantity");
//	        			String type = resultset.getString("type");
//	        			
//	        			// 銘柄表示エリア
//	        			JLabel symbolLabal = new JLabel(symbol);
//	        			symbolLabal.setBounds(1, row * 30, 200, 20);
////	        			symbolLabal.setBounds(30, row * 30, 200, 20);
//	                    contentPanel.add(symbolLabal);
//	
//	                    // 価格表示エリア
//	                    JLabel priceLabel2 = new JLabel(String.valueOf(price));
//	                    priceLabel2.setBounds(111, row * 30, 200, 20);
////	                    priceLabel2.setBounds(140, row * 30, 200, 20);
//	                    contentPanel.add(priceLabel2);
//	
//	                    // 購買区分表示エリア
//	                    JLabel typeLabel = new JLabel(type);
//	                    typeLabel.setBounds(221, row * 30, 200, 20);
////	                    typeLabel.setBounds(250, row * 30, 200, 20);
//	                    contentPanel.add(typeLabel);
//	
//	                    row++;
//	        		}
//	        		contentPanel.setBounds(0, 0, 600, row * 30 + 20);
//	        	}
//        	} catch (SQLException e2) {
//        		System.out.println("[ERROR] SQLExceptions are raised.");
//    			e2.printStackTrace();
//			}
//        	// 履歴ウィンドウを表示
//        	historyFrame.add(contentPanel);
//        	historyFrame.setVisible(true);
//        });
        
        // ウィンドウを表示
        setVisible(true);
    }
	
	private ConnectDB postgre = new ConnectDB();
	
	private void insertOrder(String symbol, int quantity, String type) {
		String sql = "INSERT INTO orders (Symbol, quantity, type) VALUES (?, ?, ?);";
		
		try {
			PreparedStatement statement = postgre.getConnection().prepareStatement(sql);
			statement.setString(1, symbol);
			statement.setInt(2, quantity);
			statement.setString(3, type);
			statement.executeUpdate();
			JOptionPane.showMessageDialog(this, "注文が追加されました。");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "注文の追加に失敗しました。");
			e.printStackTrace();
		}
	}
	
	private ResultSet fetchHistory() {
		String sql = "SELECT symbol, quantity, type FROM orders;";
		
		try {
			PreparedStatement statement = postgre.getConnection().prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			return resultSet;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "履歴の取得に失敗しました。");
			e.printStackTrace();
			return null;
		}
	}

    public static void main(String[] args) {
        // メインスレッドでUIを作成
        SwingUtilities.invokeLater(() -> new TradingAppPostgre());
    }
}

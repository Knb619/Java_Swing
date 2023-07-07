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
        	historyFrame.setSize(400, 300);
        	
        	// パネル作成
        	JPanel historyPnanel = new JPanel();
        	historyPanel.setLayout(new BorderLayout());
        	
        	// スクロールペイン作成
        	JScrollPane scrollPane = new JScrollPane();
        	historyPanel.add(scrollPane, BorderLayout.CENTER);
        	
        	// レイアウトマネージャを使用しない自由配置のパネルを作成
        	JPanel contentPanel = new JPanel(null);
        	scrollPane.setViewportView(contentPanel);
        	
        	// DBから履歴を検索
        	ResultSet resultset = fetchHistory();
        	if (resultset != null) {
        		
        	}
        });
        
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
			
		} catch (Exception e) {
			System.out.println("[ERROR] Exceptions are raised.");
			e.printStackTrace();
		}
	}
	
	private ResultSet fetchHistory() {
		String sql = "SELECT symbol, quantity, type FROM oreder;";
		
		try {
			PreparedStatement statement = postgre.getConnection().prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			return resultSet;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "履歴の取得に失敗しました。");
			e.printStackTrace();
			return null;
			
		} catch (Exception e) {
			System.out.println("[ERROR] Exceptions are raised.");
			e.printStackTrace();
			return null;
		}
	}

    public static void main(String[] args) {
        // メインスレッドでUIを作成
        SwingUtilities.invokeLater(() -> new TradingAppPostgre());
    }
}

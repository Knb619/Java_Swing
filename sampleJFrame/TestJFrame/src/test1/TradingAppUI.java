package test1;

import javax.swing.*;
import java.awt.*;

public class TradingAppUI extends JFrame {
    public TradingAppUI() {
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

        // ウィンドウを表示
        setVisible(true);
    }

    public static void main(String[] args) {
        // メインスレッドでUIを作成
        SwingUtilities.invokeLater(() -> new TradingAppUI());
    }
}

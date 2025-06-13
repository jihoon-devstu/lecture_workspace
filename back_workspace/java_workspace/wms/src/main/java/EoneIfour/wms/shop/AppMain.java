package EoneIfour.wms.shop;

import javax.swing.*;
import java.awt.*;

public class AppMain extends JFrame {

    public AppMain() {
        setTitle("쇼핑몰 메인 화면");
        setSize(1280, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Color bisque = new Color(255, 228, 196);

        // 🔶 상단 패널 (인사말 + 로그인/로그아웃 버튼)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(bisque);
        topPanel.setPreferredSize(new Dimension(1280, 60));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));

        JTextArea greeting = new JTextArea("OOO님 안녕하세요");
        greeting.setEditable(false);
        greeting.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        greeting.setForeground(Color.DARK_GRAY);
        greeting.setBackground(bisque);
        greeting.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(bisque);
        JButton loginBtn = new JButton("로그인");
        JButton logoutBtn = new JButton("로그 아웃");
        styleOrangeButton(loginBtn);
        styleOrangeButton(logoutBtn);
        loginPanel.add(loginBtn);
        loginPanel.add(logoutBtn);

        topPanel.add(greeting, BorderLayout.WEST);
        topPanel.add(loginPanel, BorderLayout.EAST);

        // 🔷 메뉴 패널 (가운데 버튼 5개)
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        menuPanel.setBackground(bisque);
        menuPanel.setPreferredSize(new Dimension(1280, 70));
        menuPanel.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2)); // SteelBlue 테두리

        for (int i = 0; i < 5; i++) {
            JButton menuBtn = new JButton("메뉴 1");
            menuBtn.setPreferredSize(new Dimension(100, 40));
            menuBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            menuBtn.setForeground(new Color(70, 130, 180)); // SteelBlue 글자색
            menuBtn.setBackground(Color.WHITE);
            menuBtn.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true));
            menuPanel.add(menuBtn);
        }

        // 🟢 콘텐츠 영역
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        JLabel contentLabel = new JLabel("Content 영역");
        contentLabel.setForeground(new Color(0, 153, 0));
        contentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        contentPanel.add(contentLabel);
        contentPanel.setPreferredSize(new Dimension(1280, 600));

        // 🔧 프레임에 컴포넌트 추가
        add(topPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void styleOrangeButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(255, 140, 0)); // Dark Orange
        button.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0)));
        button.setPreferredSize(new Dimension(80, 30));
    }
    
    public static void main(String[] args) {
    	new AppMain();
	}
}

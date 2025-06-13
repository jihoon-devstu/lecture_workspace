package com.eoneifour.shopadmin.view.frame;

import com.eoneifour.common.frame.AbstractMainFrame;
import com.eoneifour.common.util.ButtonUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 쇼핑몰 관리자 메인 프레임
 * - 상속받은 상단바,좌측바 만들어서 넘기면 됨
 * - 각 메뉴별 페이지 등록
 * 
 * @author 혜원
 */
public class ShopAdminMainFrame extends AbstractMainFrame {

    public ShopAdminMainFrame() {
        super("쇼핑몰 메인 (관리자)"); // 타이틀 설정
        initPages();
    }

    // 페이지 등록
    private void initPages() {
        //각 페이지 메뉴 버튼 연결
        //contentPanel.add(new UserListPage(this), "USER"); // 회원관리 페이지
        //contentPanel.add(new ProductPage(this), "PRODUCT"); // 상품관리 페이지
        //contentPanel.add(new OrderPage(this), "ORDER"); // 주문관리 페이지
        //contentPanel.add(new PurchasePage(this), "PURCHASE"); // 발주관리 페이지
        //contentPanel.add(new SettingPage(this), "SETTING"); // 설정 페이지
    }

    // 쇼핑몰은 상단패널 2개 사용
    @Override
    public JPanel createTopPanel() {
        JPanel infoBar = createInfoBar();
        JPanel menuBar = createMenuBar();

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(infoBar, BorderLayout.NORTH);
        topPanel.add(menuBar, BorderLayout.SOUTH);

        return topPanel;
    }


    private JPanel createInfoBar() {
        JPanel infoBar = new JPanel(new BorderLayout());
        infoBar.setBackground(Color.BLACK);
        infoBar.setPreferredSize(new Dimension(1280, 50));

        // Left Panel: 사용자 이름 포함한 인삿말
        JLabel userInfoLabel = new JLabel("운영자님, 안녕하세요");
        userInfoLabel.setForeground(Color.WHITE);
        // 왼쪽 정렬 + 좌우 15pt,위아래 10px 여백을 위한 Panel
        JPanel leftWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        leftWrapper.setOpaque(false);
        leftWrapper.add(userInfoLabel);
        infoBar.add(leftWrapper, BorderLayout.WEST);

        // Right Panel: 버튼 area
        JButton logoutButton = new JButton("로그아웃");
        ButtonUtil.styleHeaderButton(logoutButton);
        // 오른쪽 정렬 + 좌우 15pt,위아래 10px 여백을 위한 Panel
        JPanel rightWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        rightWrapper.setOpaque(false);
        rightWrapper.add(logoutButton);
        infoBar.add(rightWrapper, BorderLayout.EAST);

        return infoBar;
    }

    private JPanel createMenuBar() {
        JPanel menuBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        menuBar.setBackground(Color.WHITE);

        JButton userBtn = new JButton("회원관리");
        ButtonUtil.styleMenuButton(userBtn);
        userBtn.addActionListener(e->showPage("USER"));

        JButton productBtn = new JButton("상품관리");
        ButtonUtil.styleMenuButton(productBtn);
        productBtn.addActionListener(e->showPage("PRODUCT"));

        JButton orderBtn = new JButton("주문관리");
        ButtonUtil.styleMenuButton(orderBtn);
        orderBtn.addActionListener(e->showPage("ORDER"));

        JButton purchaseBtn = new JButton("발주관리");
        ButtonUtil.styleMenuButton(purchaseBtn);
        purchaseBtn.addActionListener(e->showPage("PURCHASE"));

        JButton settingBtn = new JButton("설정");
        ButtonUtil.styleMenuButton(settingBtn);
        settingBtn.addActionListener(e->showPage("SETTING"));

        menuBar.add(userBtn);
        menuBar.add(productBtn);
        menuBar.add(orderBtn);
        menuBar.add(purchaseBtn);
        menuBar.add(settingBtn);

        return menuBar;
    }

    // 쇼핑몰은 좌측패널 없음
    @Override
    public JPanel createLeftPanel() {
        return null;
    }

    public static void main(String[] args) {
        new ShopAdminMainFrame();
    }
}

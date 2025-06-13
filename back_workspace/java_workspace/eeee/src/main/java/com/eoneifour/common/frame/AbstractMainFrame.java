package com.eoneifour.common.frame;

import javax.swing.*;
import java.awt.*;


/**
 * 모든 메인 프레임 화면 공통 기본 구조
 * - 상단, 좌측, 중앙 영역 자동 배치
 * - 자식 클래스에서 상단/좌측 패널 만들어서 넘기면 됨
 *
 * @author 혜원
 */
public abstract class AbstractMainFrame extends JFrame {
    public JPanel topPanel;
    public JPanel leftPanel;
    public JPanel contentPanel;
    public CardLayout cardLayout;

    public AbstractMainFrame(String title) {
        // 상단 패널 (필요 없으면 null return)
        topPanel = createTopPanel();
        if(topPanel != null) add(topPanel, BorderLayout.NORTH);

        // 좌측 패널 (필요 없으면 null return)
        JPanel leftPanel = createLeftPanel();
        if (leftPanel != null) add(leftPanel, BorderLayout.WEST);
        
        // 중앙 패널
        cardLayout = new CardLayout(); // 화면 전환용 레이아웃
        contentPanel = new JPanel(cardLayout);
        add(contentPanel, BorderLayout.CENTER);

        // 기본 프레임 설정
        setSize(1280, 800); // 창 크기
        setLocationRelativeTo(null); // 창 가운데 배치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title); // 윈도우 타이틀 설정
        setVisible(true);
    }

    // 상단 nav panel
    public abstract JPanel createTopPanel();

    // 좌측 side panel
    public abstract JPanel createLeftPanel();

    // 각 메뉴 클릭 시 화면 전환
    public void showPage(String key) {
        cardLayout.show(contentPanel, key);
    }
}

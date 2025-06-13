package com.eoneifour.common.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 버튼 공통 디자인 유틸 클래스
 * - infobar 버튼 : 빨강(기본) → 진한 빨강(호버)
 * - menubar 버튼 : 검정(기본) → 파랑(호버)
 * - 호버 시 마우스 커서 손모양 변경
 *
 * @author 혜원
 */
public class ButtonUtil {
    // 메뉴 버튼 색상 상수
    private static final Color MENU_NORMAL = Color.BLACK;  // 기본 검정색
    private static final Color MENU_HOVER = new Color(13, 71, 161); // hover시 파란색
    // 헤더 버튼 색상 상수
    private static final Color HEADER_BG = new Color(231, 76, 60);  // 기본 빨간색
    private static final Color HEADER_HOVER = new Color(192, 57, 43); // hover시 진한빨강

    /**
     * 상단 메뉴 바 버튼
     * @param btn 적용할 JButton
     */
    public static void styleMenuButton(JButton btn) {
        // 기본 상태 설정
        btn.setFocusPainted(false); // 포커스 테두리 제거
        btn.setBorderPainted(false); // border 제거
        btn.setContentAreaFilled(false); // 배경 투명
        btn.setOpaque(false);
        btn.setForeground(MENU_NORMAL);
        btn.setPreferredSize(new Dimension(160, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 손모양 표시

        // hover 효과
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setForeground(MENU_HOVER);  // 글씨 파란색으로
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setForeground(MENU_NORMAL); // 원래 검정색 복귀
            }
        });
    }

    /**
     * InfoBar 버튼
     * @param btn 적용할 JButton
     */
    public static void styleHeaderButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setBackground(HEADER_BG);
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new Dimension(100, 30));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // hover 효과
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(HEADER_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(HEADER_BG);
            }
        });
    }
}

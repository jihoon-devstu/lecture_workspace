package test;
import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.TextArea;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;

public class GuiTest 
{
	public static void main(String[] args) 
	{
		//HTML에서 요소들을 부모 컨테이너에 추가하듯 , 자바도 마찬가지로 , GUI 적 요소들을 윈도우라는 컨테이너에 부착해야 눈에 보임.
		Frame f = new Frame(); //윈도우 생성
		
		f.setLayout(new FlowLayout());
		//html 등에서 익숙하게 보아왔던 UI컴포넌트 요소들을 자바 버전으로 생성하여
		// 확인해보자.
		//자바의 윈도우를 사용하려면 , 너비,높이를 지정해야함
		//또한 윈도우의 default 보기 옵션은 비활성화되어있어서 눈에 보이지 않음.
		//따라서 활성화 시켜야함
		//버튼이 너무 크게 나오는 이유는
		f.setSize(600,500);
		f.setVisible(true);
		
		Button bt = new Button("click"); 
		TextField t = new TextField(20);
		Choice ch1 = new Choice();
		ch1.add("@naver.com");
		ch1.add("@gmail.com");
		ch1.add("@daum.net");
		TextArea area = new TextArea(12, 20);
		//C,C#등등의 고전적 프로그램은 반드시 생성 시 배열의 크기 지정해야함
		Checkbox[] chArray = new Checkbox[3]; 
		chArray[0] = new Checkbox("Java",true);
		chArray[1] = new Checkbox("JSP",true);
		chArray[2] = new Checkbox("Oracle",true);
		for(int i=0;i<chArray.length;i++){
			f.add(chArray[i]);
		}
		
		Label la = new Label("제목 등 일반 텍스트");
		//메뉴 바에 메뉴 만들기
		Menu m_file, m_edit, m_style, m_view, m_help;
		m_file = new Menu("파일");
		m_edit = new Menu("편집");
		m_style = new Menu("서식");
		m_view = new Menu("보기");
		m_help = new Menu("도움말");
		MenuBar bar = new MenuBar();
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_style);
		bar.add(m_view);
		bar.add(m_help);
		
		f.add(bt); //프레임에 버튼 부착.
		f.add(t);
		f.add(ch1);
		f.add(area);
		f.add(la);
		
		f.setMenuBar(bar);
		
		System.out.println("Hello World!");
	}
}

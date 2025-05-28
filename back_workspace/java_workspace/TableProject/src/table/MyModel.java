package table;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel implements TableModelListener{

	//회원정보 (층,호를 표현하기 위한 이차원 배열 형태의 데이터가 필요)
	
	String[][] rows = new String[0][4];
	String[] columns = {"ID", "Name", "Tel"};
	
	MemberRegist memberRegist; //제어가 필요하기 때문에 , 주소값을 보유하기 위한 has a 관계 선언
	
	public MyModel(MemberRegist memberRegist) {
		this.memberRegist = memberRegist; //생성자 주입을 이용한 멤버변수 대입
		//모델과 리스너 연결
		this.addTableModelListener(this); //나의 레코드가 변경될 때 그것을 감지하겠다!!!
	}

	//테이블에 보여질 총 레코드 수
	@Override
	public int getRowCount() {
		return rows.length;
	}

	//테이블을 구성하는 컬럼 수 
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	//컬럼의 이름 반환해주는 메서드
	//아래의 메서드는 컬럼 수만큼 반복하면서 호출되는데 , 이때 매개변수로 넘겨받는 col의 값은 자동 증가 하면서 
	//전달되어진다.
	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return columns[col];
	}

	//getValueAt() 메서드는 
	@Override
	public Object getValueAt(int row, int col) {
		return rows[row][col];
	}
	
	//유저가 테이블 셀에서 아무리 데이터를 편집한다 하더라도 , 현재 모델이 보유한 2차원 배열을 수정하지 않는 한
	//값은 수정되지 않는다. 따라서 값 변경을 위한 setter가 필요하다.
	//셀에서 원하는 데이터 K를 입력하고 , 엔터를 치는 순간 , 해당 셀의 row , col , k 값이 전달됨...
	
	public void setValueAt(Object value, int row , int col) {
		super.setValueAt(value, row, col);
		System.out.println("당신은"+row+","+col+"의 데이터를"+value+"로 바꾸길 원하죠?");
		
		rows[row][col] = (String)value;
		
		memberRegist.edit(rows[row]); //데이터베이스도 수정 되어야 하니까 !! 
	}
	
	public boolean isCellEditable(int row, int col) {
		System.out.println(row+"행,"+col+"열은 수정 가능합니다.");
		return true;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		
		System.out.println("편집했어?");
		// TODO Auto-generated method stub
		
	}
	

}
package mall.exception;

public class MemberExistException extends RuntimeException{

	public MemberExistException (String msg) {
		super(msg); //생성자는 물려받지 못하므로 , 부모의 생성자를 호출한다
	}
	
	public MemberExistException(String msg , Throwable e) {
		super(msg , e);
	
	}
	
	public MemberExistException(Throwable e) {
		super(e);
	}
	
}

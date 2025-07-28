package mall.model.member;

import com.github.scribejava.core.builder.api.DefaultApi20;

//Scribejava api는 Google이 기본적으로 지원되지만 , 그 이외의 Naver, Kakao는 지원하지 않기 때문에
//직접 구현.
public class NaverApi20 extends DefaultApi20{

	private static final String AUTHORIZE_URL="https://nid.naver.com/oauth2.0/authorize";
	private static final String ACCESS_TOKEN_URL = "https://nid.naver.com/oauth2.0/token";
	
	protected NaverApi20() {}
	private static class InstanceHolder{
		private static final NaverApi20 INSTANCE=new NaverApi20();
	}
	
	public static NaverApi20 instance() {
		return InstanceHolder.INSTANCE;
	}
	
	//네이버의 동의화면 URL 주소
	@Override
	protected String getAuthorizationBaseUrl() {
		return AUTHORIZE_URL;
	}
	
	//네이버에 요청할 토큰 요청 주소
	@Override
	public String getAccessTokenEndpoint() {
		return ACCESS_TOKEN_URL;
	}



}

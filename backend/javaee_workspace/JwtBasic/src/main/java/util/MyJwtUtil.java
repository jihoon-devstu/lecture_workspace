package util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MyJwtUtil {
	private static final String SECRET ="MySecret123"; //우리가 앱에서 사용할 시크릿 키
	
	/*데이터 전송을 위해서 인코딩될 필요가 있는데 , 본 예제에서 Base64 기반 인코딩 사용
	JWT 규격(스펙) 은 Base64Url 인코딩 사용해야 함.
	 [Base64Url 인코딩의 특징]
	 전송할 데이터 = (padding) 과 같은 문자가 포함되어 있을 경우 , 이 패딩을 제거해 줌. 즉 안전한 형태로 변환해줌. 
	 
	 예) + 는 -(대시) 로 전환 , /는 _(언더스코어) 로 변환 =(패딩) 은 생략
	 abcd+/== 는 Base64Url 로 인코딩하면 abcd-_
	 
	 따라서 Url 파라미터로 전송 시 Base64Url로 인코딩하면 안전하게 전송 됨.
	*/
	private static String base64UrlEncode(byte[] bytes) {
		return Base64.getEncoder().withoutPadding().encodeToString(bytes);
	}
	
	
	/*
	 * HMAC - SHA256 알고리즘
	 * 
	 * (Hash-based Message Authentication Code)
	 * HMAC 방식에 기존 SHA-256 해시 함수를 결합한 메시지 인증 코드 알고리즘
	 * data : Header (머리) + Payload(몸)
	 * */
	
	private static String hmacSha256(String data, String secret) {
		Mac mac = null;
		try {
			mac = Mac.getInstance("HmacSHA256");		//알고리즘 선택
			
			//비밀 키 생성
			SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),"HmacSHA256");
			//비밀키 입력
			mac.init(secretKeySpec);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return base64UrlEncode(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
	}
	
	//클라이언트 서버의 자원을 접근할 때 사용할 엑세스용 토큰
	public static String generateAccessToken(String username , long expireMillis) {
		
		//Header 머리 만들기
		String headerJson = "{ \"alg\":\"HS256\", \"typ\":\"JWT\" }"; //알고리즘 : HS256
		String header = base64UrlEncode(headerJson.getBytes(StandardCharsets.UTF_8));
		
		//Payload 몸체 만들기
		//자바로 서식 문자열(format 문자열)이 지원됨
		//%s를 포맷 지정자 (specifier) %s 문자열로 대체됨 , %d 정수로 대체됨.
		long exp = (System.currentTimeMillis() + expireMillis)/1000; //초 단위 임.
		String payloadJson = String.format("{ \"sub\" : \"%s\", \"exp\" : %d }", username, exp);
		String payload = base64UrlEncode(payloadJson.getBytes(StandardCharsets.UTF_8));
		
		//Signature 서명하기
		String data = header +"."+ payload;
		
		//서명 = header + payload + 우리앱비번
		String signature = hmacSha256(data, SECRET);
		
		//최종 결과물 반환
		
		return data+"."+signature; //JWT는 header.payload.signature 으로 구성됨
	}
	
	//클라이언트에게 새로운 토큰을 발급할 리프레시 토큰 생성 메서드
	
	public static void main(String[] args) {
		
	}
}

package mall.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PasswordUtil {
	
	//소금을 생성한다
	public static String generateSalt() {
		//보안에 사용할 난수 생성기
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[16]; //16바이트 배열 (128 비트)
		sr.nextBytes(salt); //배열을 무작위 바이트값으로 채우기
		
		log.debug("채워진 salt 값은" + salt);
		
		return Base64.getEncoder().encodeToString(salt);
	}
	
	//소금과 비번을 조합한 해시를 만든다
	public static String hashPassword(String password, String salt) {
		//해시 함수 사용 객체 , 문자열을 일정 길이의 고정된 해시값으로 바꿔주는 객체
		//banana - 64 , x - 64
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt.getBytes("UTF-8")); //salt를 digest에 추가 , 즉 해시의 대상이 되는 값을 추가할 때 사용하는 메서드. 즉 누적
			byte[] hashedByte = md.digest(password.getBytes("UTF-8")); //지금까지 md에 누적된 데이터를 대상으로 암호화 즉 해시화
			
			result =  Base64.getEncoder().encodeToString(hashedByte);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	
		return result;
	}
	
	public static void main(String[] args) {
		String salt = generateSalt();
		log.debug("문자열로 변경된 salt는 " + salt);
		
		String password = "bird1234";
		String result = hashPassword(password,salt);
		log.debug("result is "+result);
	}
}

package shop;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageUtil {

	//메서드를 정의하여 , 어떤 애플리케이션에서건 사용할 수 있도록 처리하자
	public Icon getIcon(String filename, int width, int height) {
		
		//아이콘 얻기
		Class myClass = getClass();
		
		//패키지 안에 들어있는 자원의 이름을 명시하면 , URL을 반환해줌
		URL url = myClass.getClassLoader().getResource(filename);
		
		
		ImageIcon icon = null;
		
		try {
			BufferedImage buffrImg = ImageIO.read(url);		
			Image image = buffrImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);		
			icon = new ImageIcon(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return icon;
	}
}

package shop;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Util {

	//메서드를 정의하여 , 어떤 애플리케이션에서건 사용할 수 있도록 처리하자
	public Icon getIcon(URL url) {
		
		
		ImageIcon icon = null;
		
		try {
			BufferedImage buffrImg = ImageIO.read(url);		
			Image image = buffrImg.getScaledInstance(35, 30, Image.SCALE_SMOOTH);		
			icon = new ImageIcon(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}

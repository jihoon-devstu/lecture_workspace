package instrument;

public class Guiter {
	
	int volume = 50;
	
	public void setVolume(int volume){
		this.volume=volume;
	}
	
	public void play(){
		System.out.println("쟈가쟈가장");
	}
	
	public void playMp3(){
		System.out.println("노래가 재생돼요");
	}
	 
	public static void main(String[] args){
		Guiter g = new Guiter();
		g.play();
		g.playMp3();
	}
}

import edu.princeton.cs.algs4.StdDraw;


public class Prova {

	/**
	 * @param args(
	 */
	public static void main(String[] args) {
		while(true){
			if(StdDraw.mousePressed()){
				double xiniz = StdDraw.mouseX();
				double yiniz = StdDraw.mouseY();
				StdDraw.square(xiniz, yiniz, 0.1);
				System.out.println("x: "+ xiniz + " Y: " + yiniz);
				while(true){
					if(StdDraw.mouseX()!=xiniz || StdDraw.mouseY()!=yiniz){
						StdDraw.clear();  //oppure potrei rifarlo sopra bianco
						xiniz = StdDraw.mouseX();
						yiniz = StdDraw.mouseY();
						System.out.println("x: "+ xiniz + " Y: " + yiniz);
						StdDraw.square(xiniz, yiniz, 0.1);
					}
					if(StdDraw.mousePressed()) break;
				} 		
			}
			break;
		}

	}

}

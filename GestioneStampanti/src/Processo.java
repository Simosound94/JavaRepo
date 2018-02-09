import java.util.Random;

public class Processo implements Runnable {
	
	private CodaDiStampa coda;
	private int nome;
	
	
	private String randString(){
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		return output;
	}
	

	public Processo(CodaDiStampa coda, int nome) {
		super();
		this.coda = coda;
		this.nome = nome;
	}


	@Override
	public void run() {
		try{
			while(true){
				Thread.sleep((int)(4000+Math.random()*4000));
				PrintRequest pr = new PrintRequest();
				pr.priority = ((int)(Math.random()*10));
				pr.document = randString();
				System.out.println("Processo #"+nome+ " sending pr: "+pr);
				coda.addRequest(pr);
			}
		}catch(Exception e){e.printStackTrace();}
	}

}

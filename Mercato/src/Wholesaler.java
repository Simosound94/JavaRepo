import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Wholesaler {
	
public static void main(String[] args) {
		
		String ipAddr = args[0];
		String port = args[1];
		//int merce = Integer.parseInt(args[2]);
		//int qta = Integer.parseInt(args[3]);
		
		try {
			Domanda d = new Domanda();
			//d.qta = qta;
			d.qta = (int)((Math.random()+1)*400);
			d.merce = Merce.Tipi.values()[(int)(Math.random()*4)];

			//d.merce = Merce.Tipi.values()[merce];
			System.out.println("Wholesaler send request for "+d.qta+" of "+d.merce);
			Socket s = new Socket(ipAddr, Integer.parseInt(port));
			
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			oos.writeObject(d);
			Integer cost, totalCost = 0;
			while(true){
				cost = (Integer) ois.readObject();
				if(cost > 0){
				totalCost += cost;
				System.out.println("Buy for "+cost+" Tot cost: "+totalCost);
				}
				if(cost == 0){
					System.out.println("Done");
					break;
				}
				if(cost == -1){
					System.out.println("Market as not enough "+d.merce);
					break;
				}
			}

			
		}catch(Exception e){e.printStackTrace();}
	}

}

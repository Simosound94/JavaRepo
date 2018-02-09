import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Produttore {

	public static void main(String[] args) {
		String ipAddr = args[0];
		String port = args[1];
		//int merce = Integer.parseInt(args[2]);
		//int qta = Integer.parseInt(args[3]);
		
		try {
			Socket s = new Socket(ipAddr, Integer.parseInt(port));
			
			// recupero outputstream
			OutputStream os = s.getOutputStream();
			// objectoutputstream...
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			Offerta off = new Offerta();
			//off.merce = Merce.Tipi.values()[merce];
			//off.qta = qta;
			off.qta = (int)((Math.random()+1)*400);
			off.merce = Merce.Tipi.values()[(int)(Math.random()*4)];
			
			
			oos.writeObject(off);
			System.out.println("Producer sends offer about: "+off.merce);
			
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			Integer totEarn = 0;
			Integer earn = null;
			while(true){
				try{
				earn = (Integer)ois.readObject();
				}catch(Exception ex){ex.printStackTrace();}
				
			totEarn += earn;
			System.out.println("Sell for: "+earn+" Tot Earn: "+totEarn);
			if(earn == 0){
				System.out.println("All selled");
				break;
				}
			}
			
		}catch(Exception e){e.printStackTrace();}
	}

}

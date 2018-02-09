import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

public class ServerWorker implements Runnable {
	
	HashMap<Integer, FIFOMacchinaStati> fifoMacchineStati;
	Socket s;


	public ServerWorker(HashMap<Integer, FIFOMacchinaStati> fifoMacchineStati, Socket s) {
		super();
		this.fifoMacchineStati = fifoMacchineStati;
		this.s = s;
	}


	@Override
	public void run() {
		try{
			System.out.println("Pacchetto Arrivato");
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			Pacchetto pacc = (Pacchetto) ois.readObject();
			FIFOMacchinaStati fms = null;
			synchronized(fifoMacchineStati){
				if(fifoMacchineStati.containsKey(pacc.id)){
					//Macchina stati già nota
					fms = fifoMacchineStati.get(pacc.id);
				}
				else{
					//Aggiungi nuova macchina stati
					System.out.println("Nuova Macchina stati");
					fms = new FIFOMacchinaStati();
					MacchinaStati ms = new MacchinaStati(fms);
					Thread tms = new Thread(ms);
					tms.start();
					fifoMacchineStati.put(pacc.id, fms);
				}
			}
			//Gia sincronizzata di suo
			fms.push(pacc);
			
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.flush();
			Pacchetto p = new Pacchetto();
			p.id =0;
			p.protocollo = "0";
			p.infoNecessarie = "Pacchetto arrivato";
			
			oos.writeObject(p);
		}catch(Exception e){e.printStackTrace();}
	}

}

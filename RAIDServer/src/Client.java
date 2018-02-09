
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;


public class Client {

	public static void main(String[] args) {

		
		try{
			ErrMess em =null;
			while(true){
				Socket s = new Socket("127.0.0.1", RaidServer.MAINSERVERPORT);
				OutputStream os = s.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.flush();
				em = new ErrMess();
				em.errCode = (int)(Math.random()*200);
				em.errMess = "Errore .....";
				em.timeSteps.add(Calendar.getInstance().getTime());
				System.out.println("ErrMessage sent: "+em);
				oos.writeObject(em);
				Thread.sleep((int)(Math.random()*2000+2000));
				s.close();
			}
			
		}catch(Exception e){e.printStackTrace();}
	}

}

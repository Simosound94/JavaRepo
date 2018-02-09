package cipi.tutorial.infinispan.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.infinispan.Cache;

import cipi.tutorial.infinispan.MessageApp;
import cipi.tutorial.infinispan.MessageListener;

public class ConsoleUI {
	private static String applicationTitle = "MESSAGE APP";
	private static String menuMessage = "Scegli un'opzione:";
	private static String endMessage = "Uscito dall'applicazione";
	private static String inputMenuMessage = "Numero operazione da eseguire?";

	public static void main (String [] args)
	{
		boolean runProgram=true;
		//inizializzazione
		MessageApp messageApp = new MessageApp ();
		messageApp.initializeGlobal();
		Cache <String, String> messaggi= messageApp.getCacheManager().getCache("messaggi");
		MessageListener messageListener = null;
		boolean listenerActive = false;
		while (runProgram)
		{
			String menuOption1= "1---Invia un messaggio";
			String menuOption2= "2---Vedi tutti i messaggi salvati";
			String menuOption3= "3---Attiva/disattiva visualizzazione aggiornamenti su messaggi";
			String menuOption4= "4---Invia un messaggio con scadenza";
			String menuOption5= "5---";
			String menuOptionEXIT= "exit--- Esci dal programma";
			String [] menu = {menuOption1, menuOption2, menuOption3, menuOption4, menuOption5, menuOptionEXIT};
			
			System.out.println(applicationTitle);
			System.out.println(menuMessage);
			for (String menuOption : menu)
			{
				System.out.println(menuOption);
			}
			
			//INPUT REQUEST
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String choice="";
		    System.out.println(inputMenuMessage);
		    try {
		    	choice = br.readLine();
		    } catch (IOException e) {
		    	e.printStackTrace();
			}
		    
		    switch (choice){
		    case "1":
		    	if (messaggi!=null)
		    	{
		    		String key="";
					String value="";
				    System.out.println("Insert title:");
				    try {
				    	key = br.readLine();
				    	 System.out.println("Insert message:");
				    	value = br.readLine();
				    } catch (IOException e) {
				    	e.printStackTrace();
				    	System.out.println("Failed");
				    	break;
					}
		    		try {
		    			messaggi.put(key, value);
					} catch (Exception e) {
						e.printStackTrace();
					}
		    	}
		    	break;
		    case "2":
		    	System.out.printf("Cache contents on node %s\n", messaggi.getAdvancedCache().getRpcManager().getAddress());

		        ArrayList<Map.Entry<String, String>> entries = new ArrayList<>(messaggi.entrySet());
		        
		        for (Map.Entry<String, String> e : entries) {
		           System.out.printf("\t%s = %s\n", e.getKey(), e.getValue());
		        }
		        System.out.println();
		    	break;
		    case "3":
		    	if (!listenerActive)
		    	{
		    		messageListener= new MessageListener();
		    		messaggi.addListener(messageListener);
		    		listenerActive=true;
		    	}else	{
		    		messaggi.removeListener(messageListener);
		    		listenerActive=false;
		    	}
		    	
		    	break;
		    case "4":
		    	if (messaggi!=null)
		    	{
		    		String key="";
					String value="";
					long exptime=0;
				    System.out.println("Insert title:");
				    try {
				    	key = br.readLine();
				    	System.out.println("Insert message:");
				    	value = br.readLine();
				    	System.out.println("Insert expiration time (seconds):");
				    	exptime = Long.parseUnsignedLong(br.readLine());
				    } catch (Exception e) {
				    	e.printStackTrace();
				    	System.out.println("Failed");
				    	break;
					}
		    		try {
		    			messaggi.put(key, value, exptime, TimeUnit.SECONDS);
					} catch (Exception e) {
						e.printStackTrace();
					}
		    	}
		    	break;
		    case "5":
		    	break;
		    case "exit":
		    	messageApp.stopCacheManager();
		    	System.out.println(endMessage);
		    	runProgram=false;
		    	break;
		    default:
		    	break;
		    }
		}
	}
}

package cipi.tutorial.infinispan.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUI {
	private static String applicationTitle = "MESSAGE APP";
	private static String menuMessage = "Scegli un'opzione:";
	private static String endMessage = "Uscito dall'applicazione";
	private static String inputMenuMessage = "Numero operazione da eseguire?";

	public static void main (String [] args)
	{
		boolean runProgram=true;
		//INIZIALIZZAZIONE INFINISPAN
		
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
		    	break;
		    case "2":
		    	break;
		    case "3":		    	
		    	break;
		    case "4":
		    	break;
		    case "5":
		    	break;
		    case "exit":
		    	System.out.println(endMessage);
		    	runProgram=false;
		    	break;
		    default:
		    	break;
		    }
		}
	}
}

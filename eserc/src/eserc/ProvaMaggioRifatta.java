package eserc;
import java.util.*;
public class ProvaMaggioRifatta {
	static Scanner input=new Scanner (System.in);
	public static void main(String[] args) {
		
		int scelta;
		
		String numero[]=new String[100];
		String nome[]=new String [100];
		int numCont=0;
		do{
			
			scelta=scriviMenu();
			
			switch(scelta){
			
			case 1:{
				numCont=aggPers(numero, nome, numCont);
			break;}
			
			case 2:{
				ricerca(numero, nome);
			break;}
			
			case 3:{
				ricercaApp(numero, nome, numCont);
			break;}
			
			case 5:{
				System.out.println("arrivederci");
			break;}
			
			}//switch
			
			
			
		}while(scelta!=5);
		
	}//main
	
	public static int scriviMenu(){
		
		int scelta;
		
		System.out.println("scegli fra le seguenti proposte del menu");
		
		do{
			
			System.out.println("1) aggiungi persona nella rubrica");
			System.out.println("");
			System.out.println("2) ricerca esatta");
			System.out.println("");
			System.out.println("3) ricerca approssimata");
			System.out.println("");
			System.out.println("4) stampa rubrica");
			System.out.println("");
			System.out.println("5) esci");
			scelta=input.nextInt();
			
		}while(scelta<0 || scelta>5);
		
		return scelta;
	}//scriviMenu
	
	public static int aggPers(String numero[], String nome[], int numCont){
		int i=numCont;
		
		System.out.println("inserisci numero");
		numero[i]=input.next();
		
		numCont++;
		
		System.out.println("inserisci nome");
		nome[i]=input.next();
		
		return numCont;
		
	}//aggPers
	
	public static void ricerca(String numero[], String nome[]){
	
		int i;
		
		System.out.println("quale nome vuoi ricercare?");
		String ric=input.next();
		
		for(i=0;i<100;i++){
			
			if(ric.equals(nome[i])){
				
				System.out.println("nome: "+ric+" numero: "+numero[i]);
				return;
				
			}//if
			
		}//for

		System.out.println("non esiste nessuna persona nella rubrica con nome "+ric);
		
	}//ricerca
	
	public static void ricercaApp(String numero[], String nome[], int numCont){
		
		int i;
		boolean trovato=false;
		
		System.out.println("quale nome vuoi ricercare?");
		String ric=input.next();
		
		for(i=0;i<numCont;i++){
			
			if(ric.equals(nome[i].substring(0, ric.length()))){
				
				System.out.println("nome: "+nome[i]+" numero: "+numero[i]);
			
				trovato = true;
		
			}//if
			
		}//for
		
		if(!trovato){
			
			System.out.println("non esiste nessuna persona nella rubrica con nome "+ric);
		
		}//if
		
	}//ricercaApp

}//class
